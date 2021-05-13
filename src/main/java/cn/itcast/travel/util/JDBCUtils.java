package cn.itcast.travel.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
	1. 声明静态数据源成员变量
	2. 创建连接池对象
	3. 定义公有的得到数据源的方法
	4. 定义得到连接对象的方法
	5. 定义关闭资源的方法
 */
public class JDBCUtils {
	private static DataSource ds;
	static {
		Properties pro = new Properties();
		try {
			//DataSource ds = DruidDataSourceFactory.createDataSource(pro);
			pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
			ds = DruidDataSourceFactory.createDataSource(pro);
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	public static void close(Statement statement, Connection con) {
		close1(null,statement,con);
	}
	public static void close1(ResultSet resultSet, Statement statement, Connection con) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (Exception throwables) {
				throwables.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
	}
	public static DataSource getDataSource(){
		return ds;
	}
}
