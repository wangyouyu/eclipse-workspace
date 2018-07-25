package 服务.数据;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.DefaultNameConversion;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.PostgresStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.GenFilter;

public class beetlsql {

	private ConnectionSource cons = ConnectionSourceHelper.getSingle(访问数据.getDS());
	public beetlsql() {
		DBStyle dbStyle = new PostgresStyle();
		SQLLoader loader = new ClasspathLoader("/sql");
		
		System.out.println("路径："+loader.getCharset());
		DefaultNameConversion nc = new  DefaultNameConversion();
		SQLManager sqlManager = new SQLManager(dbStyle,loader,cons,nc,new Interceptor[]{new DebugInterceptor()});
//		try {
//			sqlManager.genALL("com.test", new GenConfig(), new GenFilter() {
//				
//				@Override
//				public boolean accept(String arg0) {
//					
//					return true;
//				}
//			});
//		} catch (Exception e1) {
//			// TODO 自动生成的 catch 块
//			e1.printStackTrace();
//		}
		try {
			sqlManager.genPojoCodeToConsole("企业");
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		try {
			sqlManager.genSQLTemplateToConsole("企业");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		beetlsql beetlsql = new beetlsql();
	}
}
