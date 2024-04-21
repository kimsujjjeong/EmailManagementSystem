package kr.ac.kopo.migrations;



import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.ac.kopo.config.DatabaseConnection;

public class EmailMigration {
	Connection conn;
	public EmailMigration() {
		DatabaseConnection dc = new DatabaseConnection();
		conn = dc.getConnection();
	}
	
	public void createTable() {
		String sql = "CREATE TABLE emails (\r\n"
				+ "    ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,\r\n"
				+ "    email_id_from VARCHAR2(255),\r\n"
				+ "    email_id_to VARCHAR2(255),\r\n"
				+ "    title VARCHAR2(255),\r\n"
				+ "    content CLOB,\r\n"
				+ "    email_date TIMESTAMP,\r\n"
				+ "    category INT\r\n"
				+ ")\r\n"
				+ "";
		
		try (
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				
			){
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void dropTable() {
		String sql = "DROP TABLE emails";
		
		try (
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				
			){
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


