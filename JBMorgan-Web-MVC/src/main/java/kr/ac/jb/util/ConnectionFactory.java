package kr.ac.jb.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
public Connection getConnection(){  //이렇게 연결하고 있는 객체를 한 쪽으로 몰아놓으면, 아이피 등이 변경되도 이 부분만 수정하면 됨
		
		// 1단계 드라이버 로딩
		Connection conn =null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		//	System.out.println("드라이버 로딩 성공");
			
		// 2단계 url user password
			String url ="jdbc:oracle:thin:@192.168.217.202:1521/KOPODA";
			String user="da2122";
			String password="da22";
			
			conn = DriverManager.getConnection(url,user,password);
		//	System.out.println("DB연결 성공 : " + conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return conn;
	}
	
	
	
	
	
	
	
	
}
