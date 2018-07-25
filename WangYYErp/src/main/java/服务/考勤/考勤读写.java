package 服务.考勤;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.speedment.runtime.core.exception.SpeedmentException;
import com.wangyy.erp.ErpApplication;
import com.wangyy.erp.ErpApplicationBuilder;
import com.wangyy.erp.erp.public_.考勤记录.考勤记录;
import com.wangyy.erp.erp.public_.考勤记录.考勤记录Impl;
import com.wangyy.erp.erp.public_.考勤记录.考勤记录Manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class 考勤读写 {
	private final Logger logger = (Logger) LogManager.getLogger(this.getClass().getName());
	private final ErpApplication erpApplication = new ErpApplicationBuilder().withUsername("postgres")
			.withPassword("wangyouyu").build();
	private final 考勤记录Manager manager = erpApplication.getOrThrow(考勤记录Manager.class);
	private JSONObject jsonObject;

	/**
	 * 
	 * @param 会员码
	 * @param 用户
	 * @param jsonStr
	 * @return
	 */
	public int 添加(String strjson) {
		jsonObject = JSONObject.fromObject(strjson);
		Integer intcode = (Integer) jsonObject.get("会员号");
		String strname = jsonObject.getString("用户").toString();
		String strMx = jsonObject.getString("明细").toString();
		try {
			考勤记录 考勤 = new 考勤记录Impl()
					.set用户(strname)
					.set考勤明细(strMx)
					.set日期(LocalDateTime.now())
					.set会员编号(intcode);
			manager.persist(考勤);
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
	 * @param 用户
	 * @param jString 用户与日期
	 * @return
	 */
	public int 删除(String strjson) {
		jsonObject = JSONObject.fromObject(strjson);
		String intcode = jsonObject.get("用户").toString();
		LocalDateTime localDateTime = (LocalDateTime) jsonObject.get("日期");
		try {
			Optional<考勤记录> optional = manager.stream()
					.filter(考勤记录.用户.equal(intcode))
					.filter(考勤记录.日期.equal(localDateTime))
					.findFirst();
			optional.ifPresent(l -> manager.remove(l));
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
	 * @param 用户
	 * @param jString
	 * @return
	 */
	public int 修改(String strjson) {
		jsonObject = JSONObject.fromObject(strjson);
		String intcode = jsonObject.getString("用户");
		LocalDateTime vString = (LocalDateTime) jsonObject.get("开始日期");
		LocalDateTime tString = (LocalDateTime) jsonObject.get("结束日期");
		String mxString = jsonObject.getString("明细");
		try {
			Optional<考勤记录> optional = manager.stream()
					.filter(考勤记录.用户.equal(intcode))
					.filter(考勤记录.日期.between(vString, tString))
					.findFirst();
			optional.ifPresent(l -> {
				l.set考勤明细(mxString);
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

	/**
	 * 
	 * @param 会员码
	 * @param 用户
	 * @param jString
	 * @return
	 */
	public int 物理删除(String strjson) {
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
	 * @param 用户
	 * @param jString
	 * @return
	 */
	public String 读取(String strjson) {
		jsonObject = JSONObject.fromObject(strjson);
		Integer intcode = (Integer) jsonObject.get("会员号");
		LocalDateTime vString = (LocalDateTime) jsonObject.get("开始日期");
		LocalDateTime tString = (LocalDateTime) jsonObject.get("结束日期");
		String string = jsonObject.getString("用户").toString();
		// 这里有用户个人的记录录和商家查询全员的记录
		int ib = jsonObject.getInt("开始");
		if (ib <0) {
			ib = 0;
		}
		int ie = jsonObject.getInt("结束");
		if (ie<1) {
			ib = 10;
		}
		List<考勤记录> list = manager.stream()
				.filter(考勤记录.会员编号.equal(intcode))
				.filter(考勤记录.用户.equal(string))
				.filter(考勤记录.日期.between(vString, tString))
				.skip(ib)
				.limit(ie)
				.collect(Collectors.toList());
		JSONArray jsonArray = JSONArray.fromObject(list);
		logger.info(jsonArray.toString());
		return jsonArray.toString();
	}
}
