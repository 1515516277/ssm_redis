package com.ming.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.ming.until.PropertiesUntil;

public class jdbc {
	
	public static void main(String[] args) {
		Properties properties=PropertiesUntil.read();
		String url = properties.getProperty("jdbc.url");
		String username = properties.getProperty("jdbc.user");
		String password = properties.getProperty("jdbc.password");
		String driver=properties.getProperty("jdbc.driver");
		
		
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
