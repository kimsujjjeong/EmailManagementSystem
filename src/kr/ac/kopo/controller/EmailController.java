package kr.ac.kopo.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.kopo.config.DatabaseConnection;
import kr.ac.kopo.model.Email;
import kr.ac.kopo.model.User;

public class EmailController {
	Connection conn;
	String sessEmail;
	public EmailController(String sess) {
		DatabaseConnection connObj = new DatabaseConnection();
		this.conn = connObj.getConnection();
		this.sessEmail = sess; 
		
	}
	
	public void sendEmail(Email e) {
		
		String emailTo = e.getemTo();
		String emailFrom = e.getefr();
		String title= e.getti();
		String content = e.getco();
		
		String sql = "INSERT INTO EMAILS (EMAIL_ID_FROM, EMAIL_ID_TO, TITLE, CONTENT, EMAIL_DATE, CATEGORY) "
				+ "VALUES (?, ?, ?, ?, SYSTIMESTAMP, 1) ";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,emailFrom);
			pstmt.setString(2,emailTo);
			pstmt.setString(3,title);
			pstmt.setString(4,content);
			
			pstmt.executeUpdate();
			//pstmt.executeUpdate();
			System.out.println(emailTo+"에게 이메일 성공적으로 발송돠었습니다");
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		
		
	}
	
	public void allEmails() {
		String sql = "SELECT * FROM EMAILS where EMAIL_ID_FROM = ? or EMAIL_ID_TO = ?";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessEmail);
			pstmt.setString(2, sessEmail);
			
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int id = res.getInt("ID");
				String email_from = res.getString("EMAIL_ID_FROM");
				String title = res.getString("TITLE");
				String content = res.getString("CONTENT");
				String time = res.getTimestamp("EMAIL_DATE").toString();
				
				System.out.println("ID: "+id+" FROM:"+email_from+"Title:"+title+"내용:"+content+"Time"+time);
			}
			//pstmt.executeUpdate();
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void receivedEmails() {
		String sql = "SELECT * FROM EMAILS where EMAIL_ID_TO = ?";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessEmail);
			
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int id = res.getInt("ID");
				String email_from = res.getString("EMAIL_ID_FROM");
				String title = res.getString("TITLE");
				String content = res.getString("CONTENT");
				String time = res.getTimestamp("EMAIL_DATE").toString();
				
				System.out.println("ID: "+id+" FROM:"+email_from+"Title:"+title+"내용:"+content+"Time"+time);
			}
			//pstmt.executeUpdate();
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void sentEmails() {
		String sql = "SELECT * FROM EMAILS where EMAIL_ID_FROM = ?";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessEmail);
			
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int id = res.getInt("ID");
				String email_from = res.getString("EMAIL_ID_FROM");
				String title = res.getString("TITLE");
				String content = res.getString("CONTENT");
				String time = res.getTimestamp("EMAIL_DATE").toString();
				
				System.out.println("ID: "+id+" FROM:"+email_from+"Title:"+title+"내용:"+content+"Time"+time);
			}
			//pstmt.executeUpdate();
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void spamEmails() {
		String sql = "SELECT * FROM EMAILS where category = 3 AND (EMAIL_ID_FROM = ? OR EMAIL_ID_FROM = ?)";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessEmail);
			pstmt.setString(2, sessEmail);
			
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int id = res.getInt("ID");
				String email_from = res.getString("EMAIL_ID_FROM");
				String title = res.getString("TITLE");
				String content = res.getString("CONTENT");
				String time = res.getTimestamp("EMAIL_DATE").toString();
				
				System.out.println("ID: "+id+" FROM:"+email_from+"Title:"+title+"내용:"+content+"Time"+time);
			}
			//pstmt.executeUpdate();
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void deleteEmail(int id) {
		String sql = "DELETE FROM EMAILS WHERE ID = ?";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
	
			
			
			pstmt.executeUpdate();
			//pstmt.executeUpdate();
			System.out.println("이메일 삭제 완료");
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void deleteAllEmail() {
		String sql = "DELETE FROM EMAILS WHERE EMAIL_ID_FROM = ? OR EMAIL_ID_TO = ?";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessEmail);
			pstmt.setString(2, sessEmail);
			
			
			
			pstmt.executeUpdate();
			//pstmt.executeUpdate();
			System.out.println("전체 이메일 삭제 완료");
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void moveToSpam(int id) {
		String sql = "UPDATE EMAILS SET CATEGORY = 3 WHERE ID = ?";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			
			
			pstmt.executeUpdate();
			//pstmt.executeUpdate();
			System.out.println("스팸 메일함으로 이동");
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
