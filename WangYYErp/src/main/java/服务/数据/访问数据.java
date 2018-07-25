package 服务.数据;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.Optional;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.apache.logging.log4j.LogManager;

public final class 访问数据 {
	private static volatile DruidDataSource druidDataSource;
	private static final Logger loginfo = LogManager.getLogger(访问数据.class.getName());
	private static Connection connection;
	private static PreparedStatement preparedStatement;

	/**
	 * 数据库连接池.
	 * 
	 * @return DruidDataSource
	 */
	private static final DruidDataSource getDataSource() {
		if (druidDataSource == null) {
			synchronized (DruidDataSource.class) {
				if (druidDataSource == null) {
					druidDataSource = new DruidDataSource();
					druidDataSource.setDriverClassName("org.postgresql.Driver");
					druidDataSource.setUrl("jdbc:postgresql://47.104.187.104:5432/erp");
					druidDataSource.setUsername("postgres");
					druidDataSource.setPassword("wangyouyu");
					druidDataSource.setInitialSize(2);
					druidDataSource.setMaxActive(3);
					druidDataSource.setMaxWait(10000);
					druidDataSource.setUseUnfairLock(true);
					druidDataSource.setPoolPreparedStatements(false);
					druidDataSource.setMaxOpenPreparedStatements(20);
					druidDataSource.setTimeBetweenEvictionRunsMillis(20000);
					druidDataSource.setMinEvictableIdleTimeMillis(30000);
					druidDataSource.setTestWhileIdle(true);
				}
			}
		}
		return druidDataSource;
	}

	/**
	 * 关闭数据库连接池
	 */
	public static void closeDS() {
		druidDataSource.close();
	}

	/**
	 * 关闭数据库连接池
	 * 
	 * @param 连接池
	 */
	public static void closeDS(DruidDataSource dSource) {
		dSource.close();
	}

	/*
	 * 关闭 Connection
	 */
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {

			loginfo.error(e);
		}
	}

	public static Connection gConnection() {
		Connection connection = null;
		try {
			connection = getDataSource().getConnection();
		} catch (SQLException e) {

			loginfo.error(e.getMessage());
		}
		return connection;
	}
	
	public static DataSource getDS() {
		return getDataSource();
	}

	/**
	 * 
	 * @return
	 */
	private static final DSLContext getDSLcon(Connection conn) {

		DSLContext dslContext = DSL.using(conn, SQLDialect.POSTGRES_9_5);

		return dslContext;
	}

	/**
	 * 
	 * @param conn
	 * @param sql
	 * @return
	 */
	public static int executeSql(String sql) {
		int intEx = 0;
		connection = gConnection();
		intEx = getDSLcon(connection).execute(sql);
		closeConnection(connection);
		return intEx;
	}

	/**
	 * 
	 * @param conn
	 * @param sql
	 * @return
	 */
	public static Result<Record> getResultD(String sql) {
		connection = gConnection();
		Result<Record> result = getDSLcon(connection).fetch(sql);
		closeConnection(connection);
		return result;
	}

	/**
	 * 使用sql语句执行删除操作.
	 * @param sql delete form where a =?.
	 * @param code
	 * @return
	 */
	public static int 删除(String sql, int code) {
		
		int cont = 0;
		try {
			connection = gConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, code);
			cont = preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			loginfo.error(e.getMessage());
		}
		return cont;
	}

	/**
	 * 使用SQL语句进行查询操作.
	 * @see select from where a = ?
	 * @param sql
	 * @param code
	 * @return
	 */
	public static Optional<ResultSet> 查询(String sql, int code) {

		ResultSet resultSet = null;
		try {
			connection = gConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, code);
			resultSet = preparedStatement.executeQuery();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			loginfo.error(e.getMessage());
		}
		Optional<ResultSet> rOptional = Optional.of(resultSet);
		
		return rOptional;
	}
	
}
