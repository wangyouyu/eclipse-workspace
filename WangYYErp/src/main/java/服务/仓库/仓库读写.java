package 服务.仓库;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.jooq.Record;
import org.jooq.Result;

import com.speedment.runtime.core.exception.SpeedmentException;
import com.speedment.runtime.core.manager.Manager;
import com.wangyy.erp.ErpApplication;
import com.wangyy.erp.ErpApplicationBuilder;
import com.wangyy.erp.erp.public_.仓库.仓库;
import com.wangyy.erp.erp.public_.仓库.仓库Impl;
import com.wangyy.erp.erp.public_.仓库.仓库Manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import 服务.数据.访问数据;

public class 仓库读写 {

	private final Logger logger = (Logger) LogManager.getLogger(this.getClass().getName());
	private ErpApplication erpApplication = new ErpApplicationBuilder().withUsername("postgres")
			.withPassword("wangyouyu").build();
	private 仓库Manager manager = erpApplication.getOrThrow(仓库Manager.class);
	private JSONObject jsonObject;
	public int 添加(String strjson) {
		jsonObject = JSONObject.fromObject(strjson);
		Integer intcode =(Integer) jsonObject.get("会员号");
		String kwStr = jsonObject.get("库位").toString();
		String xqStr = jsonObject.get("详情").toString();
		try {
			仓库 add = new 仓库Impl()
					.set会员编码(intcode)
					.set库位(kwStr)
					.set详情(xqStr)
					.set日期(LocalDateTime.now());
			manager.persist(add);
			return 1;
		} catch (SpeedmentException se) {
			logger.error(se.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return 0;
	}

	public int 删除(String strjson) {
		jsonObject = JSONObject.fromObject(strjson);
		Integer intcode =(Integer) jsonObject.get("会员号");
		try {
			Optional<仓库> optional = manager.stream()
					.filter(仓库.仓库标识.equal(intcode))
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

	public int 修改(String strjson) {
		jsonObject = JSONObject.fromObject(strjson);
		Integer intcode =(Integer) jsonObject.get("会员号");
		Integer code = (Integer) jsonObject.get("库号");
		String strKw = jsonObject.getString("库位");
		String strXq = jsonObject.getString("详情");
		try {
			Optional<仓库> optional = manager.stream()
					.filter(仓库.会员编码.equal(intcode))
					.filter(仓库.仓库标识.equal(code))
					.findFirst();
			optional.ifPresent(l->{
				l.set库位(strKw);
				l.set详情(strXq);
				l.set日期(LocalDateTime.now());
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

	public int 物理删除(String strjson) {
		try {
			
			
		} catch (SpeedmentException se) {
			logger.error(se.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

	public String 读取(String strjson) {
		JSONArray jsonArray = new JSONArray();
		
		jsonObject = JSONObject.fromObject(strjson);
		Integer intcode =(Integer) jsonObject.get("会员号");
		int ib = jsonObject.getInt("开始");
		int ie = jsonObject.getInt("结束");
		if (ib <0) {
			ib = 0;
		}
		if (ie<1) {
			ib = 10;
		}
		try {
			List<仓库> list = manager.stream()
					.filter(仓库.仓库标识.equal(intcode))
					.skip(ib)
					.limit(ie)
					.collect(Collectors.toList());
			Iterator<仓库> iterator = list.iterator();
			while (iterator.hasNext()) {
				仓库 sv = (仓库) iterator.next();
				JSONObject jsonObject = JSONObject.fromObject(sv);
				jsonObject.put("库位", sv.get库位().get());
				jsonArray.add(jsonObject);
			}
			
		} catch (SpeedmentException se) {
			logger.error(se.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info(jsonArray.toString());
		return jsonArray.toString();
	}
	
	public int 执行SQL(String sql) {
		int i = 访问数据.executeSql(sql);
		return i;
	}
	
	public void 执行SQL查值() {
		Result<Record> result = 访问数据.getResultD("");
		for (Record record : result) {
			record.get(0);
			record.size();
		}
	}
}
