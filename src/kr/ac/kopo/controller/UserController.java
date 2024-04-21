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
	
	public void signUp(User u) {
		
		String emailID = u.getem();
		String name = u.getna();
		String password = u.getpa();
		String birth = u.getbi();
		String phone = u.getph();
		
		String sql = "INSERT INTO USERS (EMAIL_ID, NAME, PASSWORD, DATE_OF_BIRTH, PHONE) "
				+ "VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?) ";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,emailID);
			pstmt.setString(2,name);
			pstmt.setString(3,password);
			pstmt.setString(4,birth);
			pstmt.setString(5,phone);
			pstmt.executeUpdate();
			//pstmt.executeUpdate();
			
//					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public void signIn(User u) {
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
				
				System.out.println("Successfully Logged In");
				return;
				
				
			}
			System.err.println("invalid user name or password");
//					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
