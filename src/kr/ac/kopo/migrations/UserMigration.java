package kr.ac.kopo.migrations;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.ac.kopo.config.DatabaseConnection;

public class UserMigration {
	Connection conn;
	public UserMigration() {
		DatabaseConnection dc = new DatabaseConnection();
		conn = dc.getConnection();
	}
	
	public void createTable() {
		String sql = "CREATE TABLE users (\r\n"
				+ "    email_id VARCHAR2(100) PRIMARY KEY,\r\n"
				+ "    name VARCHAR2(100),\r\n"
				+ "    password VARCHAR2(100),\r\n"
				+ "    date_of_birth Date ,\r\n"	
				+ "    phone VARCHAR2(100),\r\n"
				+ "    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP\r\n"
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
		String sql = "DROP TABLE users";
		
		try (
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				
			){
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
