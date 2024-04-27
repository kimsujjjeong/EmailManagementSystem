package kr.ac.kopo.migrations;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.ac.kopo.config.DatabaseConnection;

public class Migration {
	Connection conn;
	public Migration() {
		DatabaseConnection dc = new DatabaseConnection();
		conn = dc.getConnection();
		
	}
	
	public void installDatabase() { //디비테이블 생성
		String email_sql = "CREATE TABLE emails (\r\n"
				+ "    ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,\r\n"
				+ "    email_id_from VARCHAR2(255),\r\n"
				+ "    email_id_to VARCHAR2(255),\r\n"
				+ "    title VARCHAR2(255),\r\n"
				+ "    content CLOB,\r\n"
				+ "    email_date TIMESTAMP,\r\n"
				+ "    category INT\r\n"
				+ ")\r\n"
				+ "";
		String session_sql = "CREATE TABLE user_sessions (\r\n"
				+ "    email_id VARCHAR2(255) PRIMARY KEY,\r\n"
				+ "    is_logged_in NUMBER(1)\r\n"
				+ ")";
		String user_sql = "CREATE TABLE users (\r\n"
				+ "    email_id VARCHAR2(100) PRIMARY KEY,\r\n"
				+ "    name VARCHAR2(100),\r\n"
				+ "    password VARCHAR2(100),\r\n"
				+ "    date_of_birth  DATE ,\r\n"	
				+ "    phone VARCHAR2(100),\r\n"
				+ "    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP\r\n"
				+ ")";
		
		
		
		
		try (
				PreparedStatement pstmt1 = conn.prepareStatement(user_sql);
				PreparedStatement pstmt2 = conn.prepareStatement(email_sql);
				PreparedStatement pstmt3 = conn.prepareStatement(session_sql);
				
			){
			pstmt1.execute();
			pstmt2.execute();
			pstmt3.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteDatabase() {
		
		String email_sql = "DROP TABLE EMAILS";
		String user_sql = "DROP TABLE USERS";
		String session_sql = "DROP TABLE USER_SESSIONS";
		
		
		
		try (
				//PreparedStatement pstmt1 = conn.prepareStatement(user_sql);
				PreparedStatement pstmt2 = conn.prepareStatement(email_sql);
				PreparedStatement pstmt3 = conn.prepareStatement(session_sql);
				
			){
			//pstmt1.execute();
			pstmt2.execute();
			pstmt3.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
