package 服务.销售;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.speedment.runtime.core.exception.SpeedmentException;
import com.wangyy.erp.ErpApplication;
import com.wangyy.erp.ErpApplicationBuilder;
import com.wangyy.erp.erp.public_.销售.销售;
import com.wangyy.erp.erp.public_.销售.销售Impl;
import com.wangyy.erp.erp.public_.销售.销售Manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class 销售读写 {
	private final Logger logger = (Logger) LogManager.getLogger(this.getClass().getName());
	private final ErpApplication erpApplication = new ErpApplicationBuilder().withUsername("postgres")
			.withPassword("wangyouyu").build();
	private final 销售Manager manager = erpApplication.getOrThrow(销售Manager.class);
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
		String th = jsonObject.getString("单号");
		String sp = jsonObject.getString("商品");
		String kh = jsonObject.getString("客户");
		String rq = jsonObject.getString("日期");
		String pj = jsonObject.getString("票据");
		try {
			销售 订单 = new 销售Impl()
					.set单号(th)
					.set商品(sp)
					.set客户(kh)
					.set日期(rq)
					.set票据(pj)
					.set会员编号(intcode)
					.set创建时间(LocalDateTime.now());
			manager.persist(订单);
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
	public int 删除(String strjson) {
		jsonObject = JSONObject.fromObject(strjson);
		Integer intcode =(Integer) jsonObject.get("会员号");
		try {
			Optional<销售> optional = manager.stream()
					.filter(销售.单号.equal(""))
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
		try {
			Optional<销售> optional = manager.stream()
					.findFirst();
			optional.ifPresent(l->{
				l.set商品("");
				l.set客户("");
				l.set日期("");
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
		jsonObject = JSONObject.fromObject(strjson);
		Integer intcode =(Integer) jsonObject.get("会员号");
		JSONArray jsonArray = null;
		int ib = jsonObject.getInt("开始");
		if (ib <0) {
			ib = 0;
		}
		int ie = jsonObject.getInt("结束");
		if (ie<1) {
			ib = 10;
		}
		try {
			List<销售> list = manager.stream()
					.filter(销售.会员编号.equal(intcode))
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
