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
			// 1.������������
			Class.forName(driver).newInstance();
			// 2.������ݿ�����
			conn = DriverManager.getConnection(url, username, password);
			// 3.ͨ�����ݿ�����Ӳ������ݿ⣬ʵ����ɾ�Ĳ飨ʹ��Statement�ࣩ
			st = conn.createStatement();
			rs = st.executeQuery("select * from emp");
			// 4.�������ݿ�ķ��ؽ��(ʹ��ResultSet��)
			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}
			// �ر���Դ
			rs.close();
			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
