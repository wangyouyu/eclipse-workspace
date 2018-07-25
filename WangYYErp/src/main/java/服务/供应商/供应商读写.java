package 服务.供应商;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import com.speedment.runtime.core.exception.SpeedmentException;
import com.wangyy.erp.ErpApplication;
import com.wangyy.erp.ErpApplicationBuilder;
import com.wangyy.erp.erp.public_.供应商.供应商;
import com.wangyy.erp.erp.public_.供应商.供应商Impl;
import com.wangyy.erp.erp.public_.供应商.供应商Manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class 供应商读写 {

	private final Logger logger = (Logger) LogManager.getLogger(this.getClass().getName());
	private final ErpApplication erpApplication = new ErpApplicationBuilder().withUsername("postgres")
			.withPassword("wangyouyu").build();
	private final 供应商Manager manager = erpApplication.getOrThrow(供应商Manager.class);
	private JSONObject jsonObject;

	/**
	 * 
	 * @param 会员码
	 * @param jsonStr
	 * @return
	 */
	public int 添加(String jsonStr) {
		jsonObject = JSONObject.fromObject(jsonStr);
		Integer intcode = (Integer) jsonObject.get("会员号");
		String xqString = jsonObject.getJSONArray("详情").toString();
		try {
			供应商 商户 = new 供应商Impl().set商户详情(xqString).set会员编号(intcode);
			manager.persist(商户);
			return 1;
		} catch (SpeedmentException se) {
			logger.error(se.getMessage());
		} catch (Exception e) {
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
	public int 删除(String jString) {
		jsonObject = JSONObject.fromObject(jString);
		Integer intcode = (Integer) jsonObject.get("会员号");
		Integer icode = (Integer) jsonObject.getInt("商号码");
		try {
			Optional<供应商> optional = manager.stream().filter(供应商.会员编号.equal(intcode)).filter(供应商.商号码.equal(icode))
					.findFirst();
			optional.ifPresent(l -> {
				manager.remove(l);
			});
			return 1;
		} catch (SpeedmentException se) {
			logger.error(se.getMessage());
		} catch (Exception e) {
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
	public int 修改(String jString) {
		jsonObject = JSONObject.fromObject(jString);
		Integer intcode = (Integer) jsonObject.get("会员号");
		Integer icode = (Integer) jsonObject.getInt("商号码");
		String xq = jsonObject.getString("详情");

		try {
			Optional<供应商> optional = manager.stream()
					.filter(供应商.会员编号.equal(intcode))
					.filter(供应商.商号码.equal(icode))
					.findFirst();
			optional.ifPresent(l -> {
				l.set商户详情(xq);
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

	/**
	 * 
	 * @param 会员码
	 * @param jString
	 * @return
	 */
	public int 物理删除(String jString) {
		try {

		} catch (SpeedmentException se) {
			logger.error(se.getMessage());
		} catch (Exception e) {
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
	public String 读取(String jString) {
		JSONArray jsonArray = new JSONArray();
		jsonObject = JSONObject.fromObject(jString);
		Integer intcode = (Integer) jsonObject.get("会员号");
		int ib = jsonObject.getInt("开始");
		int ie = jsonObject.getInt("结束");
		if (ib < 0) {
			ib = 0;
		}
		if (ie < 1) {
			ib = 10;
		}
		try {
			List<供应商> list = manager.stream()
					.filter(供应商.会员编号.equal(intcode))
					.skip(ib)
					.limit(ie)
					.collect(Collectors.toList());
			Iterator<供应商> iterator = list.iterator();
			while (iterator.hasNext()) {
				供应商 sv = (供应商) iterator.next();
				JSONObject jsonObject = JSONObject.fromObject(sv);
				jsonObject.put("商户详情", sv.get商户详情());
				jsonArray.add(jsonObject);
			}
		} catch (SpeedmentException se) {
			logger.error(se.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		logger.info(jsonArray.toString());
		return jsonArray.toString();
	}
}
