package kr.ac.kopo.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import kr.ac.kopo.config.DatabaseConnection;
import kr.ac.kopo.model.Email;

public class EmailController {
	Connection conn;
	String sessEmail;
	public EmailController(String sess) {
		DatabaseConnection connObj = new DatabaseConnection(); // 데이터베이스 연결 코드 JDBC
		this.conn = connObj.getConnection();
		this.sessEmail = sess; // 로그인 아이디
		
	}
	
	public void sendEmail(Email e) {
		
		String emailTo = e.getemTo();
		String emailFrom = e.getefr();
		String title= e.getti();
		String content = e.getco();
		
		String sql = "INSERT INTO EMAILS (EMAIL_ID_FROM, EMAIL_ID_TO, TITLE, CONTENT, EMAIL_DATE, CATEGORY) "
				+ "VALUES (?, ?, ?, ?, SYSTIMESTAMP, 2) ";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,emailFrom);
			pstmt.setString(2,emailTo);
			pstmt.setString(3,title);
			pstmt.setString(4,content);
			
			pstmt.executeUpdate();
			//pstmt.executeUpdate();
			System.out.println(emailTo+"에게 이메일을 발송하였습니다.");
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		
		
	}
	
	public void allEmails() { //셀렉트는 전체이메일을 받는다
		String sql = "SELECT * FROM EMAILS where (EMAIL_ID_FROM = ? or EMAIL_ID_TO = ?) AND CATEGORY != 5 ORDER BY EMAIL_DATE DESC";
		try { 
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessEmail); //로그인 아이디
			pstmt.setString(2, sessEmail);
			
			
			ResultSet res = pstmt.executeQuery();
			System.out.printf("%-6s%-20s%-20s%-20s%-20s\n", "No", "From", "Title", "Content", "Date");
			int emailCount = 0;
			while(res.next()) {
				int id = res.getInt("ID");
				String email_from = res.getString("EMAIL_ID_FROM");
				String title = res.getString("TITLE");
				String content = res.getString("CONTENT");
				String time = formatTimestamp(res.getTimestamp("EMAIL_DATE"));
				emailCount++;
				System.out.printf("%-6d%-20s%-20s%-15s%-20s\n", id, email_from, title, content, time);
			}
			if(emailCount == 0) {
				System.out.printf("메일이 없습니다.\n");
			}
			//pstmt.executeUpdate();
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void receivedEmails() { //받은메일함 TO는 다른 사람이 to나에게 보내는거라서
		String sql = "SELECT * FROM EMAILS where EMAIL_ID_TO = ? and CATEGORY != 3 AND CATEGORY != 5  ORDER BY EMAIL_DATE DESC";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessEmail);
			
			
			ResultSet res = pstmt.executeQuery();
			System.out.printf("%-6s %-20s %-20s %-20s %-20s\n", "No", "From", "Title", "Content", "Date");
			int emailCount = 0;
			while(res.next()) {
				int id = res.getInt("ID");
				String email_from = res.getString("EMAIL_ID_FROM");
				String title = res.getString("TITLE");
				String content = res.getString("CONTENT");
				String time = formatTimestamp(res.getTimestamp("EMAIL_DATE"));
				emailCount++;
				
		        System.out.printf("%-6d %-20s %-20s %-20s %-20s\n", id, email_from, title, content,time);
			}
			//pstmt.executeUpdate();
			if(emailCount == 0) {
				System.out.printf("메일이 없습니다\n");
			}
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void sentEmails() { // 보낸 메일함 FROM이 다른 사람이라서
		String sql = "SELECT * FROM EMAILS where EMAIL_ID_FROM = ? AND CATEGORY != 5 ORDER BY EMAIL_DATE DESC";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessEmail);
			
			
			ResultSet res = pstmt.executeQuery();  //보낸 메일함
			System.out.printf("%-6s %-20s %-20s %-20s %-20s\n", "No", "To", "Title", "Content", "Date");
			int emailCount = 0;
			while(res.next()) {
				int id = res.getInt("ID");
				String email_from = res.getString("EMAIL_ID_TO");
				String title = res.getString("TITLE");
				String content = res.getString("CONTENT");
				String time = formatTimestamp(res.getTimestamp("EMAIL_DATE"));
				
				// Header
				emailCount++;
		        System.out.printf("%-6d %-20s %-20s %-20s %-20s\n", id, email_from, title, content,time);
		        //System.out.println("NO: "+id+" FROM:"+email_from+"Title:"+title+"내용:"+content+"Time"+time);
			}
			if(emailCount == 0) {
				System.out.printf("메일이 없습니다\n");
			}
			//pstmt.executeUpdate();
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void spamEmails() {
//		String sql = "SELECT * FROM EMAILS where category = 3 AND (EMAIL_ID_TO = '"+sessEmail+"' OR EMAIL_ID_FROM = '"+sessEmail+"')";
		String sql = "SELECT * FROM EMAILS where category = ? AND (EMAIL_ID_TO = ? OR EMAIL_ID_FROM = ?)  ORDER BY EMAIL_DATE DESC";
		//System.err.println(sql);
		try {
			//System.err.println(sessEmail);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 3);
			pstmt.setString(2, sessEmail);
			pstmt.setString(3, sessEmail);
			
			
			ResultSet res = pstmt.executeQuery();
			int emailCount = 0;
			System.out.printf("%-6s %-20s %-20s %-20s %-20s\n", "No", "From", "Title", "Content", "Date");
			while(res.next()) {
				int id = res.getInt("ID");
				String email_from = res.getString("EMAIL_ID_FROM");
				String title = res.getString("TITLE");
				String content = res.getString("CONTENT");
				String time = formatTimestamp(res.getTimestamp("EMAIL_DATE"));
				emailCount++;
				System.out.printf("%-6d %-20s %-20s %-20s %-20s\n", id, email_from, title, content,time);
			}
			if(emailCount == 0) {
				System.out.printf("메일이 없습니다\n");
			}
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void favEmails() { //중요메일 // 그 사람 입장에서 TO 크리스탈 하면 내가 받으니까 TO는 받는 이메일
		String sql = "SELECT * FROM EMAILS where category = 4 AND (EMAIL_ID_TO = ? OR EMAIL_ID_FROM = ?)  ORDER BY EMAIL_DATE DESC";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessEmail); //내 로그인 아이디
			pstmt.setString(2, sessEmail); 
			
			
			ResultSet res = pstmt.executeQuery();
			int emailCount = 0;
			System.out.printf("%-6s %-20s %-20s %-20s %-20s\n", "No", "From", "Title", "Content", "Date");
			while(res.next()) {
				int id = res.getInt("ID");
				String email_from = res.getString("EMAIL_ID_FROM");
				String title = res.getString("TITLE");
				String content = res.getString("CONTENT");
				String time = formatTimestamp(res.getTimestamp("EMAIL_DATE"));
				emailCount++;
				System.out.printf("%-6d %-20s %-20s %-20s %-20s\n", id, email_from, title, content,time);
			}
			if(emailCount == 0) {
				System.out.printf("메일이 없습니다\n");
			}
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void binEmails() {
		String sql = "SELECT * FROM EMAILS where category = 5 AND (EMAIL_ID_TO = ? OR EMAIL_ID_FROM = ?)  ORDER BY EMAIL_DATE DESC";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessEmail);
			pstmt.setString(2, sessEmail);
			
			
			ResultSet res = pstmt.executeQuery();
			int emailCount = 0;
			System.out.printf("%-6s %-20s %-20s %-20s %-20s\n", "No", "From", "Title", "Content", "Date");
			while(res.next()) {
				int id = res.getInt("ID");
				String email_from = res.getString("EMAIL_ID_FROM");
				String title = res.getString("TITLE");
				String content = res.getString("CONTENT");
				String time = formatTimestamp(res.getTimestamp("EMAIL_DATE"));
				emailCount++;
				System.out.printf("%-6d %-20s %-20s %-20s %-20s\n", id, email_from, title, content,time);
			}
			if(emailCount == 0) {
				System.out.printf("메일이 없습니다\n");
			}
			//pstmt.executeUpdate();
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public int isFav(int id) {
		String favSQL = "SELECT category from emails where ID = ? ";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(favSQL);
			pstmt.setInt(1, id);
			
			//결과값 받는다 PS는 물음표를 대체하고 첫번째로 아이디를 대체하고 쿼리를 실행시킨다
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int category = res.getInt("Category");
				return category;
			}
			return 0;
			//pstmt.executeUpdate();
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	//휴지통 이동
	public void deleteEmail(int id) {
		String sql = "UPDATE EMAILS SET CATEGORY = 5  WHERE ID = ?";
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
	
	public void deletePermanentEmail(int id) {//휴지통 비우기
		String sql = "DELETE FROM EMAILS  WHERE ID = ?";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
	
			
			
			pstmt.executeUpdate();
			//pstmt.executeUpdate();
			System.out.println("선택한 휴지통 비우기 완료");
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void restoreEmail(int id) {
		String sql = "UPDATE EMAILS SET CATEGORY = 2  WHERE ID = ?";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
	
			
			
			pstmt.executeUpdate();
			//pstmt.executeUpdate();
			System.out.println("이메일 복원 완료!");
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void deleteAllEmail() {
		String sql = "UPDATE EMAILS SET CATEGORY = 5 WHERE EMAIL_ID_FROM = ? OR EMAIL_ID_TO = ?";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessEmail);
			pstmt.setString(2, sessEmail);
			
			
			
			pstmt.executeUpdate();
			//pstmt.executeUpdate();
			System.out.println("전체 메일함 삭제 완료");
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void cleanTrashEmail() {
		String sql = "DELETE FROM EMAILS WHERE CATEGORY = 5 AND  (EMAIL_ID_FROM = ? OR EMAIL_ID_TO = ?)";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessEmail);
			pstmt.setString(2, sessEmail);
			
			
			
			pstmt.executeUpdate();
			//pstmt.executeUpdate();
			System.out.println("전체 휴지통 비우기 완료");
			
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
	
	
	public void moveToFavorite(int id) {
		String sql = "UPDATE EMAILS SET CATEGORY = 4 WHERE ID = ?";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			
			
			pstmt.executeUpdate();
			//pstmt.executeUpdate();
			System.out.println("*중요 메일함으로 이동");
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static String formatTimestamp(java.sql.Timestamp timestamp) {
        SimpleDateFormat sdf;
        long now = System.currentTimeMillis();
        long diff = now - timestamp.getTime();
        if (diff < 24 * 60 * 60 * 1000) { // Less than 24 hours ago
            sdf = new SimpleDateFormat("hh:mm a"); // Show time only
        } else {
            sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a"); // Show date and time
        }
        return sdf.format(timestamp);
    }
}
