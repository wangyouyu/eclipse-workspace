package 服务.字典;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;


import com.speedment.runtime.core.exception.SpeedmentException;
import com.wangyy.erp.ErpApplication;
import com.wangyy.erp.ErpApplicationBuilder;
import com.wangyy.erp.erp.public_.字典.字典;
import com.wangyy.erp.erp.public_.字典.字典Impl;
import com.wangyy.erp.erp.public_.字典.字典Manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class 字典读写 {
	private final Logger logger = (Logger) LogManager.getLogger(this.getClass().getName());
	private final ErpApplication erpApplication = new ErpApplicationBuilder().withUsername("postgres")
			.withPassword("wangyouyu").build();
	private final 字典Manager manager = erpApplication.getOrThrow(字典Manager.class);
	private JSONObject jsonObject;
	/**
	 * 
	 * @param 会员码
	 * @param 字典值
	 * @param 类别
	 * @return
	 */
	public int 添加(String strjson) {
		jsonObject = JSONObject.fromObject(strjson);
		Integer intcode =(Integer) jsonObject.get("会员号");
		String zd = jsonObject.getString("字典");
		String lb = jsonObject.getString("类别");
				
		try {
			字典 ov = new 字典Impl()
					.set字典值(zd)
					.set类别(lb)
					.set会员(intcode);
			manager.persist(ov);
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
	 * @param 字典值
	 * @param 类别
	 * @return
	 */
	public int 删除(String strjson) {
		jsonObject = JSONObject.fromObject(strjson);
		Integer intcode =(Integer) jsonObject.get("会员号");
		Integer code = (Integer) jsonObject.getInt("标识号");
		try {
			Optional<字典> optional= manager.stream()
					.filter(字典.会员.equal(intcode))
					.filter(字典.标识号.equal(code))
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
	 * @param 字典值
	 * @param 类别
	 * @return
	 */
	public int 修改(String strjson) {
		jsonObject = JSONObject.fromObject(strjson);
		Integer intcode =(Integer) jsonObject.get("会员号");
		Integer code = (Integer) jsonObject.getInt("标识号");
		String zd = jsonObject.getString("字典");
		String lb = jsonObject.getString("类别");
		try {
			Optional<字典> optional= manager.stream()
					.filter(字典.会员.equal(intcode))
					.filter(字典.标识号.equal(code))
					.findFirst();
			optional.ifPresent(l-> {
				l.set字典值(zd);
				l.set类别(lb);
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
	 * @param 字典值
	 * @param 类别
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
	 * @param 类别
	 * @return
	 */
	public String 读取(String strjson) {
		JSONArray jsonArray = null;
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
			List<字典> list = manager.stream()
					.filter(字典.会员.equal(intcode))
					.skip(ib)
					.limit(ie)
					.collect(Collectors.toList());
			jsonArray = JSONArray.fromObject(list);
		} catch (SpeedmentException se) {
			logger.error(se.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info(jsonArray.toString());
		return jsonArray.toString();
	}
	
}
