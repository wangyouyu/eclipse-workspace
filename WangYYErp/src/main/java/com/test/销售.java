package com.test;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2018-07-26
*/
@Table(name="erp.销售")
public class 销售   {
	
	private Integer 编号 ;
	private Integer 会员编号 ;
	private String 单号 ;
	private Object 商品 ;
	private Object 客户 ;
	private Object 日期 ;
	private Object 票据 ;
	private Date 创建时间 ;
	
	public 销售() {
	}
	
	public Integer get编号(){
		return  编号;
	}
	public void set编号(Integer 编号 ){
		this.编号 = 编号;
	}
	
	public Integer get会员编号(){
		return  会员编号;
	}
	public void set会员编号(Integer 会员编号 ){
		this.会员编号 = 会员编号;
	}
	
	public String get单号(){
		return  单号;
	}
	public void set单号(String 单号 ){
		this.单号 = 单号;
	}
	
	public Object get商品(){
		return  商品;
	}
	public void set商品(Object 商品 ){
		this.商品 = 商品;
	}
	
	public Object get客户(){
		return  客户;
	}
	public void set客户(Object 客户 ){
		this.客户 = 客户;
	}
	
	public Object get日期(){
		return  日期;
	}
	public void set日期(Object 日期 ){
		this.日期 = 日期;
	}
	
	public Object get票据(){
		return  票据;
	}
	public void set票据(Object 票据 ){
		this.票据 = 票据;
	}
	
	public Date get创建时间(){
		return  创建时间;
	}
	public void set创建时间(Date 创建时间 ){
		this.创建时间 = 创建时间;
	}
	

}
