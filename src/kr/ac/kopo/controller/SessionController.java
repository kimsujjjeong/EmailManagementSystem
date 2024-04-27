package kr.ac.kopo.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.ac.kopo.config.DatabaseConnection;

public class SessionController {
    Connection conn;
	public SessionController() {
		DatabaseConnection connObj = new DatabaseConnection();
		this.conn = connObj.getConnection();
		
	}

	public int isLoggedIn() {
	    String sql = "SELECT * FROM USER_SESSIONS WHERE IS_LOGGED_IN = 1";
	    try {
	    	PreparedStatement pstmt = conn.prepareStatement(sql);
	        ResultSet res = pstmt.executeQuery();
	        while (res.next()) {
	        	return res.getInt("is_logged_in");
	        } // 로그인 상태 확인 1
	        return 0;// 로그아웃 0
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0;  // 오류 0
	    }
	}
	
	public String sessionEmail() {
	    String sql = "SELECT EMAIL_ID FROM USER_SESSIONS where is_logged_in=1";
	    try {
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        ResultSet res = pstmt.executeQuery();
	        while (res.next()) {
	         
	            return res.getString("EMAIL_ID");
	        }
	     
	        return null;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null; 
	    }
	}
	

    public void setSession(String username) { //기록 존재 상태 확인
        String sql = "SELECT EMAIL_ID FROM USER_SESSIONS WHERE EMAIL_ID = ?";
        try {
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, username);
	        ResultSet res = pstmt.executeQuery();
	        
	        String _sql = null;
	        boolean flag = true;
	        while(res.next()) {
	        	_sql = "UPDATE USER_SESSIONS SET IS_LOGGED_IN = 1 WHERE EMAIL_ID=?"; 
	        	flag = false;
	        }
	        if(flag) { // 한번은 반드시 인서트를 거치고 ㅡ 그다음 로그인을 한번쯤했으면 업데이트로 넘어감
	        	_sql = "INSERT INTO USER_SESSIONS (EMAIL_ID, IS_LOGGED_IN) "
	    				+ "VALUES (?,1) "; //로그인 숫자 1 로그아웃은 0
	        }
	        
	        try {
        		PreparedStatement _pstmt = conn.prepareStatement(_sql);
        		_pstmt.setString(1, username);
    	        _pstmt.executeUpdate();
    	        
    	        String _update_sql = "UPDATE USER_SESSIONS SET IS_LOGGED_IN = 0 WHERE EMAIL_ID != ?";
    	        PreparedStatement _pstmt1 = conn.prepareStatement(_update_sql); //로그아웃상태  !=? not equal 수정

    	        
    	        _pstmt1.setString(1, username);

    	        
    	        _pstmt1.executeUpdate();

    	        
        	} catch (SQLException e) {
    	        e.printStackTrace();
    	    }
	       
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
    }
    
    public int logout(String username) {
    	 String sql = "UPDATE USER_SESSIONS SET IS_LOGGED_IN = 0 WHERE EMAIL_ID = ?";
         try {
 	        PreparedStatement pstmt = conn.prepareStatement(sql);
 	        pstmt.setString(1, username);
 	        pstmt.executeUpdate();
 	        
 	    } catch (SQLException e) {
 	        e.printStackTrace();
 	    }
        System.out.println("User logged out.");
        return 1;
    }
}


