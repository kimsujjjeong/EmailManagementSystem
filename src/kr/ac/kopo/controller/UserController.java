package kr.ac.kopo.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.kopo.config.DatabaseConnection;
import kr.ac.kopo.model.User;

public class UserController {
	Connection conn;
	public UserController() {
		DatabaseConnection connObj = new DatabaseConnection();
		this.conn = connObj.getConnection();
		
	}
	
	public void findIDPassword(String birthday, String phone) {
		String sql =" SELECT EMAIL_ID, PASSWORD FROM USERS WHERE DATE_OF_BIRTH = TO_DATE(?, 'YYYY-MM-DD') AND PHONE = ? ";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,birthday);
			pstmt.setString(2,phone);
			
			//pstmt.executeUpdate();
			ResultSet res = pstmt.executeQuery();

			while(res.next()) {s
				String email = res.getString("Email_ID");
				//String name = res.getString("NAME");
				String pwd = res.getString("PASSWORD");
				//String dob = res.getDate("DATE_OF_BIRTH").toString();
				//String ph = res.getString("PHONE");
				
				
				System.out.println("아이디는:'" + email + "' 비밀번호는: '"+pwd+"'입니다");
				return;
				
				
			}
			System.err.println("정보를 잘못 입력하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void signUp(User u) {
		
		String emailID = u.getem();
		String name = u.getna();
		String password = u.getpa();
		String birth = u.getbi();
		String phone = u.getph();
		
		String sql = "INSERT INTO USERS (EMAIL_ID, NAME, PASSWORD, DATE_OF_BIRTH, PHONE) "
				+ " VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?) ";
		
		//INSERT INTO USERS (EMAIL_ID, NAME, PASSWORD, DATE_OF_BIRTH, PHONE) "
		// + " VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?)
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,emailID);
			pstmt.setString(2,name);
			pstmt.setString(3,password);
			pstmt.setString(4,birth);
			pstmt.setString(5,phone);
			
			//INSERT INTO USERS (EMAIL_ID, NAME, PASSWORD, DATE_OF_BIRTH, PHONE) "
			//+ " VALUES (emailID, name, password, TO_DATE(?, 'YYYY-MM-DD'), ?)
			pstmt.executeUpdate();
			//pstmt.executeUpdate();
			
//					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int signIn(User u) {
		String emailID = u.getem();
		String password = u.getpa();
		String sql =" SELECT * FROM USERS WHERE EMAIL_ID = ? AND PASSWORD = ? ";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,emailID);
			pstmt.setString(2,password);
			
			//pstmt.executeUpdate();
			ResultSet res = pstmt.executeQuery();

			while(res.next()) {
				String email = res.getString("Email_ID");
				String name = res.getString("NAME");
				String pwd = res.getString("PASSWORD");
				String dob = res.getDate("DATE_OF_BIRTH").toString();
				String ph = res.getString("PHONE");
				
				
				SessionController sess = new SessionController();
				sess.setSession(email);
				System.out.println("성공적으로 로그인 되었습니다.");
				return 1;
				
				
			}
			System.err.println("유효하지 않은 아이디나 비밀번호입니다.");
			return 0;
//					
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public int removeAccount(String sessEmail) {
		String sql = "DELETE FROM USERS WHERE EMAIL_ID = ?";
		String sess_sql = "DELETE FROM USER_SESSIONS WHERE EMAIL_ID = ?";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessEmail);
			
			PreparedStatement pstmt2 = conn.prepareStatement(sess_sql);
			pstmt2.setString(1, sessEmail);
			
			
			
			pstmt.executeUpdate();
			pstmt2.executeUpdate();
			
			//pstmt.executeUpdate();
			System.out.println("탈퇴 완료");
			return 1;
			
//					
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
}
