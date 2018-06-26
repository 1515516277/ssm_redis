package com.ming.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/ssm";
		String username = "root";
		String password = "root";
		String driver="com.mysql.jdbc.Driver";
		Statement st = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			// 1.加载驱动程序
			Class.forName(driver).newInstance();
			// 2.获得数据库链接
			conn = DriverManager.getConnection(url, username, password);
			// 3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
			st = conn.createStatement();
			rs = st.executeQuery("select * from emp");
			// 4.处理数据库的返回结果(使用ResultSet类)
			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}
			// 关闭资源
			rs.close();
			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
