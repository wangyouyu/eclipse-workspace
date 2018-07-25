package 服务.会员;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.speedment.runtime.core.exception.SpeedmentException;
import com.wangyy.erp.ErpApplication;
import com.wangyy.erp.ErpApplicationBuilder;
import com.wangyy.erp.erp.public_.会员.会员Impl;
import com.wangyy.erp.erp.public_.会员.会员Manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import 服务.数据.访问数据;

public class 会员 {

	private final Connection create = 访问数据.gConnection();
	private final Logger logger = (Logger) LogManager.getLogger(this.getClass().getName());
	private final ErpApplication erpApplication = new ErpApplicationBuilder().withUsername("postgres")
			.withPassword("wangyouyu").build();
	private final 会员Manager manager = erpApplication.getOrThrow(会员Manager.class);

	private JSONObject jsonObject;

	public int 添加(String strjson) {
		int eceInt = 0;
		try {

			jsonObject = JSONObject.fromObject(strjson);
			Integer intcode = (Integer) jsonObject.get("会员号");
			JSONArray jsonXq = jsonObject.getJSONArray("详情");
			JSONArray jsonFf = jsonObject.getJSONArray("付费");
			com.wangyy.erp.erp.public_.会员.会员 dd = new 会员Impl().set会员标识(intcode).set详情(jsonXq.toString())
					.set付费(jsonFf.toString()).set日期(LocalDateTime.now());
			manager.persist(dd);
			eceInt = 1;
		} catch (SpeedmentException se) {
			logger.error(se.getMessage());
		} catch (Exception e) {
			logger.error(e);
		}
		return eceInt;
	}

	public String 读取(String strjson) {

		jsonObject = JSONObject.fromObject(strjson);
		Integer intcode = (Integer) jsonObject.get("会员号");
		int ib = jsonObject.getInt("开始");
		if (ib <0) {
			ib = 0;
		}
		int ie = jsonObject.getInt("结束");
		if (ie<1) {
			ib = 10;
		}
		JSONArray jsonArray = null;
		try {
			List<com.wangyy.erp.erp.public_.会员.会员> list = manager.stream()
					// .filter(com.wangyy.erp.erp.public_.会员.会员.会员标识.equal(intcode))
					.skip(ib)
					.limit(ie)
					.collect(Collectors.toList());
			jsonArray = JSONArray.fromObject(list);
		} catch (SpeedmentException se) {
			logger.error(se.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info(jsonArray.toString());
		return jsonArray.toString();

	}

	public int 删除(String strjson) {
		jsonObject = JSONObject.fromObject(strjson);
		Integer intcode = (Integer) jsonObject.get("会员号");
		try {

			Optional<com.wangyy.erp.erp.public_.会员.会员> dOptional = manager.stream()
					.filter(com.wangyy.erp.erp.public_.会员.会员.会员标识.equal(intcode)).findFirst();
			dOptional.ifPresent(l -> manager.remove(l));
			return 1;
		} catch (SpeedmentException se) {
			logger.error(se.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

	public int 修改(String strjson) {
		jsonObject = JSONObject.fromObject(strjson);

		Integer intcode = (Integer) jsonObject.getInt("会员号");
		JSONArray jsonXq = jsonObject.getJSONArray("详情");
		JSONArray jsonFf = jsonObject.getJSONArray("付款");
		try {

			Optional<com.wangyy.erp.erp.public_.会员.会员> dOptional = manager.stream()
					.filter(com.wangyy.erp.erp.public_.会员.会员.会员标识.equal(intcode)).findFirst();
			dOptional.ifPresent(l -> {
				if (jsonXq.size() > 0) {
					l.set详情(jsonXq.toString());
				}
				if (jsonFf.size() > 0) {
					l.set付费(jsonFf.toString());
				}

				l.set日期(LocalDateTime.now());
				manager.update(l);
			});
			return 1;
		} catch (SpeedmentException se) {
			logger.error(se.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
}
