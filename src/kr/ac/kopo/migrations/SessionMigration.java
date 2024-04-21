package kr.ac.kopo.migrations;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.ac.kopo.config.DatabaseConnection;

public class SessionMigration {
	Connection conn;
	public SessionMigration() {
		DatabaseConnection dc = new DatabaseConnection();
		conn = dc.getConnection();
	}
	public void createTable() {
//		String sql = "CREATE TABLE user_sessions (" +
//	               "ID INT AUTO_INCREMENT PRIMARY KEY, " +
//	               "email_id VARCHAR(255) NOT NULL, " +
//	               "is_logged_in BOOLEAN NOT NULL)";
		String sql = "CREATE TABLE user_sessions (\r\n"
				+ "    email_id VARCHAR2(255) PRIMARY KEY,\r\n"
				+ "    is_logged_in NUMBER(1)\r\n"
				+ ")";

		
		try (
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				
			){
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void dropTable() {
		String sql = "DROP TABLE user_sessions";
		
		try (
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				
			){
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


