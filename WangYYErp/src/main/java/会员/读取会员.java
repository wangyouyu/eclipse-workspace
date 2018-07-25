package 会员;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
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

import com.alibaba.druid.support.calcite.DDLColumn;

import net.sf.json.JSONObject;
import 服务.会员.会员;

@ServerEndpoint("/hy/{model}/{code}")
public class 读取会员 {
	private final Logger logger = (Logger) LogManager.getLogger(this.getClass().getName());
	private Session session;
	private 会员 data = new 会员();

	@OnOpen
	public void open(Session session) {

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
	public void message(@PathParam("model") String mString, @PathParam("code") String code, Session session,
			String meString) {
		if (session.isSecure()) {

			logger.info("接收到的消息" + mString);
			switch (mString) {
			case "del":
				JSONObject jsonObject = new JSONObject();
				int del = data.删除(meString);
				if (del == 1) {
					jsonObject.put("data", "删除成功");
					jsonObject.put("state", 1);
					send(jsonObject.toString());
				} else {
					jsonObject.put("data", "删除失败");
					jsonObject.put("state", 0);
					send(jsonObject.toString());
				}
				break;
			case "add":
				JSONObject jsonObj = new JSONObject();
				int add = data.添加(meString);
				if (add == 1) {
					jsonObj.put("data", "添加成功");
					jsonObj.put("state", 1);
					send(jsonObj.toString());
				} else {
					jsonObj.put("data", "添加失败");
					jsonObj.put("state", 0);
					send(jsonObj.toString());
				}
				break;
			case "se":
				String sjson = data.读取(meString);
				send(sjson);
				break;
			case "up":
				JSONObject jsonObjup = new JSONObject();
				int up = data.修改(meString);
				if (up == 1) {
					jsonObjup.put("data", "更新成功");
					jsonObjup.put("state", 1);
					send(jsonObjup.toString());
				} else {
					jsonObjup.put("data", "更新失败");
					jsonObjup.put("state", 0);
					send(jsonObjup.toString());
				}
				break;
			case "login":
				String strCode = loginJson(meString);
				send(strCode);
				break;
			default:
				break;
			}
		}
	}

	@OnClose
	public void close(CloseReason reason) {
		logger.info("关闭访问啦!"+reason.getReasonPhrase());
	}

	@OnError
	public void error(Throwable error) {
		logger.error(error);
	}

	private void send(String mes) {
		try {
			session.getBasicRemote().sendText(mes);
		} catch (IOException e) {
			logger.error(e);
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
}
