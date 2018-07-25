package com.test;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2018-07-26
*/
@Table(name="erp.供应商")
public class 供应商   {
	
	private Integer 商号码 ;
	private Integer 会员编号 ;
	private Object 商户详情 ;
	
	public 供应商() {
	}
	
	public Integer get商号码(){
		return  商号码;
	}
	public void set商号码(Integer 商号码 ){
		this.商号码 = 商号码;
	}
	
	public Integer get会员编号(){
		return  会员编号;
	}
	public void set会员编号(Integer 会员编号 ){
		this.会员编号 = 会员编号;
	}
	
	public Object get商户详情(){
		return  商户详情;
	}
	public void set商户详情(Object 商户详情 ){
		this.商户详情 = 商户详情;
	}
	

}
