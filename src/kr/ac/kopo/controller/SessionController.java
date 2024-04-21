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

	public int isLoggedIn(String em) {
	    String sql = "SELECT * FROM USER_SESSIONS WHERE EMAIL_ID = ?";
	    try {
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, em);
	        ResultSet res = pstmt.executeQuery();
	        while (res.next()) {
	            // If there's at least one record with the given email,
	            // return the login status of the first record found
	            return res.getInt("is_logged_in");
	        }
	        // If no record with the given email is found, return false
	        return 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0; // or handle the exception according to your application's requirements
	    }
	}

	

    public void setSession(String username) {
        String sql = "INSERT INTO USER_SESSIONS (EMAIL_ID, IS_LOGGED_IN) VALUES (?, 1)";
        try {
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, username);
	        pstmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
    }

    public void logout(String username) {
    	 String sql = "UPDATE SESSIONS SET IS_LOGGED_IN = 0 WHERE EMAIL_ID = ?";
         try {
 	        PreparedStatement pstmt = conn.prepareStatement(sql);
 	        pstmt.setString(1, username);
 	        pstmt.executeUpdate();
 	        
 	    } catch (SQLException e) {
 	        e.printStackTrace();
 	    }
        System.out.println("User logged out.");
    }
}


