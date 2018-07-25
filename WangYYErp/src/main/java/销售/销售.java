package 销售;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import net.sf.json.JSONObject;
import 服务.销售.销售读写;

@ServerEndpoint("/xs/{model}/{code}")
public class 销售 {

	private Session session;
	private final Logger logger = (Logger) LogManager.getLogger(this.getClass().getName());
	private 销售读写 data = new 销售读写();

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
	public void onMessage(String meString, Session session, @PathParam("model") String mString,
			@PathParam("code") String cString) {
		if (session.isSecure()) {

			logger.info("接收到的消息" + meString);
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
}
