import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.NoSuchProviderException;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.codehaus.xfire.util.Base64;
import org.json.JSONObject;

@ServerEndpoint("/login/{model}")
public class 登陆 {

	private final Logger logger = (Logger) LogManager.getLogger(this.getClass().getName());
	private Session session;

	@OnOpen
	public void onOpen(Session session) {
		if (session.isSecure()) {
			this.session = session;
		} else {
			try {
				logger.info("不安全的请求连接");
				session.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}

	@OnMessage
	public void onMessage(String mString, Session session, @PathParam("model") String mode) {
		if (session.isSecure()) {
			switch (mode) {
			case "key":
				String strCode = loginJson(mString);

				send(strCode);
				logger.info(strCode);
				break;
			case "userinfo":
				JSONObject jsonObject = new JSONObject(mString);
				userInfo(jsonObject.getString("encrypted"), jsonObject.getString("key"), jsonObject.getString("iv"));
				break;
			default:
				break;
			}

		}

	}

	@OnClose
	public void close(CloseReason reason) {
		logger.info("关闭访问啦!" + reason.getReasonPhrase());
	}

	@OnError
	public void error(Throwable error) {
	}

	private void send(String mes) {
		try {
			this.session.getBasicRemote().sendText(mes);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	private String loginJson(String code) {

		String strCode = null;
		try {
			HttpClient httpClient = HttpClients.createDefault();
			String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx3ae694d7063922c5&secret=54ebdd4f129b3e19a6c04197f19a1a16&js_code="
					+ code + "&grant_type=authorization_code";
			logger.info(url);
			HttpGet httpGet = new HttpGet(url);
			strCode = EntityUtils.toString(httpClient.execute(httpGet).getEntity());

		} catch (ParseException e) {
			logger.error(e.getMessage());
		} catch (ClientProtocolException ee) {
			logger.error(ee.getMessage());
		} catch (IOException eee) {
			logger.error(eee.getMessage());
		}

		return strCode;
	}

	public void userInfo(String encryptedData, String sessionKey, String iv) {
		// 被加密的数据
		byte[] dataByte = Base64.decode(encryptedData);
		// 加密秘钥
		byte[] keyByte = Base64.decode(sessionKey);
		// 偏移量
		byte[] ivByte = Base64.decode(iv);
		try {
			// 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
			int base = 16;
			if (keyByte.length % base != 0) {
				int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
				keyByte = temp;
			}
			// 初始化
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
			AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
			parameters.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
			byte[] resultByte = cipher.doFinal(dataByte);
			if (null != resultByte && resultByte.length > 0) {
				String result = new String(resultByte, "UTF-8");
				send(result);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (java.security.NoSuchProviderException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvalidParameterSpecException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
