package com.test;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 存储平台的企业会员信息表，并做为企业会员注册信息申请与审核表
* gen by beetlsql 2018-07-26
*/
@Table(name="erp.会员")
public class 会员   {
	
	private Integer 会员标识 ;
	private Object 付费 ;
	private Object 详情 ;
	private Date 日期 ;
	
	public 会员() {
	}
	
	public Integer get会员标识(){
		return  会员标识;
	}
	public void set会员标识(Integer 会员标识 ){
		this.会员标识 = 会员标识;
	}
	
	public Object get付费(){
		return  付费;
	}
	public void set付费(Object 付费 ){
		this.付费 = 付费;
	}
	
	public Object get详情(){
		return  详情;
	}
	public void set详情(Object 详情 ){
		this.详情 = 详情;
	}
	
	public Date get日期(){
		return  日期;
	}
	public void set日期(Date 日期 ){
		this.日期 = 日期;
	}
	

}
