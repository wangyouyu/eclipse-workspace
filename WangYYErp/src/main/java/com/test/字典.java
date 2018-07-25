package com.test;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 字典表，存储品牌、分类、地区、类型、谁的、岗位、企业类型、会员类型、考勤类别、等数据。例：{ 品牌：}/谁的/类别（品牌/岗位/地区等）
* gen by beetlsql 2018-07-26
*/
@Table(name="erp.字典")
public class 字典   {
	
	private Integer 标识号 ;
	private Integer 会员 ;
	private Object 字典值 ;
	private Object 类别 ;
	
	public 字典() {
	}
	
	public Integer get标识号(){
		return  标识号;
	}
	public void set标识号(Integer 标识号 ){
		this.标识号 = 标识号;
	}
	
	public Integer get会员(){
		return  会员;
	}
	public void set会员(Integer 会员 ){
		this.会员 = 会员;
	}
	
	public Object get字典值(){
		return  字典值;
	}
	public void set字典值(Object 字典值 ){
		this.字典值 = 字典值;
	}
	
	public Object get类别(){
		return  类别;
	}
	public void set类别(Object 类别 ){
		this.类别 = 类别;
	}
	

}
