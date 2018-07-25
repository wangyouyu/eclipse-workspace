package com.test;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 商品条码、详情、数量、库存、谁的、品牌、类别等
* gen by beetlsql 2018-07-26
*/
@Table(name="erp.商品目录")
public class 商品目录   {
	
	private Integer 会员 ;
	private String 商品标识码 ;
	private Object 库存 ;
	private Object 详情 ;
	
	public 商品目录() {
	}
	
	public Integer get会员(){
		return  会员;
	}
	public void set会员(Integer 会员 ){
		this.会员 = 会员;
	}
	
	public String get商品标识码(){
		return  商品标识码;
	}
	public void set商品标识码(String 商品标识码 ){
		this.商品标识码 = 商品标识码;
	}
	
	public Object get库存(){
		return  库存;
	}
	public void set库存(Object 库存 ){
		this.库存 = 库存;
	}
	
	public Object get详情(){
		return  详情;
	}
	public void set详情(Object 详情 ){
		this.详情 = 详情;
	}
	

}
