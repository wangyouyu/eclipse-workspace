package com.test;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 每日、考勤数据、用户账号、会员标识
* gen by beetlsql 2018-07-26
*/
@Table(name="erp.考勤记录")
public class 考勤记录   {
	
	private Integer 会员编号 ;
	private String 用户 ;
	private Object 考勤明细 ;
	private Date 日期 ;
	
	public 考勤记录() {
	}
	
	public Integer get会员编号(){
		return  会员编号;
	}
	public void set会员编号(Integer 会员编号 ){
		this.会员编号 = 会员编号;
	}
	
	public String get用户(){
		return  用户;
	}
	public void set用户(String 用户 ){
		this.用户 = 用户;
	}
	
	public Object get考勤明细(){
		return  考勤明细;
	}
	public void set考勤明细(Object 考勤明细 ){
		this.考勤明细 = 考勤明细;
	}
	
	public Date get日期(){
		return  日期;
	}
	public void set日期(Date 日期 ){
		this.日期 = 日期;
	}
	

}
