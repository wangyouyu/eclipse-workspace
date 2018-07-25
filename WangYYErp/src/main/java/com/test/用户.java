package com.test;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 系统使用用户信息表，用户名、密码、基础信息等
* gen by beetlsql 2018-07-26
*/
@Table(name="erp.用户")
public class 用户   {
	
	private String 账号 ;
	private Integer 会员编号 ;
	private String 密码 ;
	private Object 详情 ;
	
	public 用户() {
	}
	
	public String get账号(){
		return  账号;
	}
	public void set账号(String 账号 ){
		this.账号 = 账号;
	}
	
	public Integer get会员编号(){
		return  会员编号;
	}
	public void set会员编号(Integer 会员编号 ){
		this.会员编号 = 会员编号;
	}
	
	public String get密码(){
		return  密码;
	}
	public void set密码(String 密码 ){
		this.密码 = 密码;
	}
	
	public Object get详情(){
		return  详情;
	}
	public void set详情(Object 详情 ){
		this.详情 = 详情;
	}
	

}
