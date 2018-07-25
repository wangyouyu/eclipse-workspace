package 服务.用户;


import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;


import com.speedment.runtime.core.exception.SpeedmentException;
import com.wangyy.erp.ErpApplication;
import com.wangyy.erp.ErpApplicationBuilder;
import com.wangyy.erp.erp.public_.用户.用户;
import com.wangyy.erp.erp.public_.用户.用户Impl;
import com.wangyy.erp.erp.public_.用户.用户Manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class 用户读写 {
	private final Logger logger = (Logger) LogManager.getLogger(this.getClass().getName());
	private final ErpApplication erpApplication = new ErpApplicationBuilder().withUsername("postgres")
			.withPassword("wangyouyu").build();
	private final 用户Manager manager = erpApplication.getOrThrow(用户Manager.class);
	private JSONObject jsonObject;
	
	/**
	 * 
	 * @param 会员码
	 * @param jsonStr
	 * @return
	 */
	public int 添加(String strjson) {
		jsonObject = JSONObject.fromObject(strjson);
		Integer intcode =(Integer) jsonObject.get("会员号");
		String strcode = jsonObject.getString("codekey");
		String strpass = jsonObject.getString("password");
		JSONArray jsonXq = jsonObject.getJSONArray("xq");
		try {
			Optional<用户> optional = manager.stream()
					.filter(用户.账号.equal(strcode))
					.filter(用户.会员编号.equal(intcode))
					.findFirst();
			if(optional.isPresent()) {
				return 1;
			}else {
			用户 账号 = new 用户Impl()
					.set账号(strcode)
					.set密码(strpass)
					.set详情(jsonXq.toString())
					.set会员编号(intcode);
			manager.persist(账号);
			return 1;
			}
		} catch (SpeedmentException se) {
			logger.error(se.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
	/**
	 * 
	 * @param 会员码
	 * @param jString
	 * @return
	 */
	public int 删除(String strjson) {
		jsonObject = JSONObject.fromObject(strjson);
		Integer intcode =(Integer) jsonObject.get("会员号");
		String strcode = jsonObject.getString("codekey");
		try {
			Optional<用户> optional = manager.stream()
					.filter(用户.账号.equal(strcode))
					.filter(用户.会员编号.equal(intcode))
					.findFirst();
			optional.ifPresent(l-> manager.remove(l));
			return 1;
		} catch (SpeedmentException se) {
			logger.error(se.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
	/**
	 * 
	 * @param 会员码
	 * @param jString
	 * @return
	 */
	public int 修改(String strjson) {
		jsonObject = JSONObject.fromObject(strjson);
		Integer intcode =(Integer) jsonObject.get("会员号");
		String strcode = jsonObject.getString("codekey");
		try {
			Optional<用户> optional = manager.stream()
					.filter(用户.账号.equal(strcode))
					.findFirst();
			optional.ifPresent(l->{
				l.set详情("");
				l.set密码("");
				l.set会员编号(intcode);
				manager.update(l);
			});
			return 1;
		} catch (SpeedmentException se) {
			logger.error(se.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
	/**
	 * 
	 * @param 会员码
	 * @param jString
	 * @return
	 */
	public int 物理删除(String strjson) {
		try {
			
			
		} catch (SpeedmentException se) {
			logger.error(se.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
	/**
	 * 
	 * @param 会员码
	 * @param jString
	 * @return
	 */
	public String 读取(String strjson) {
		JSONArray jArray = new JSONArray();
		jsonObject = JSONObject.fromObject(strjson);
		Integer intcode =(Integer) jsonObject.get("会员号");
		int ib = jsonObject.getInt("开始");
		if (ib <0) {
			ib = 0;
		}
		int ie = jsonObject.getInt("结束");
		if (ie<1) {
			ib = 10;
		}
		try {
			List<用户> list = manager.stream()
					.filter(用户.会员编号.equal(intcode))
					.skip(ib)
					.limit(ie)
					.collect(Collectors.toList());
			logger.info("用户list:"+list);
			Iterator<用户> iterator = list.iterator();
			while (iterator.hasNext()) {
				用户 st = (用户) iterator.next();
				JSONObject jsonObject = JSONObject.fromObject(st);
				jsonObject.put("详情", st.get详情().get());
				jArray.add(jsonObject);
			}

		} catch (SpeedmentException se) {
			logger.error(se.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("用户JSON："+jArray);
		return jArray.toString();
	}
}
