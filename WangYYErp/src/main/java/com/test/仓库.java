package com.test;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 仓库信息并包括库位信息
* gen by beetlsql 2018-07-26
*/
@Table(name="erp.仓库")
public class 仓库   {
	
	private Integer 仓库标识 ;
	private Integer 会员编码 ;
	private Object 库位 ;
	private Object 详情 ;
	private Date 日期 ;
	
	public 仓库() {
	}
	
	public Integer get仓库标识(){
		return  仓库标识;
	}
	public void set仓库标识(Integer 仓库标识 ){
		this.仓库标识 = 仓库标识;
	}
	
	public Integer get会员编码(){
		return  会员编码;
	}
	public void set会员编码(Integer 会员编码 ){
		this.会员编码 = 会员编码;
	}
	
	public Object get库位(){
		return  库位;
	}
	public void set库位(Object 库位 ){
		this.库位 = 库位;
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
