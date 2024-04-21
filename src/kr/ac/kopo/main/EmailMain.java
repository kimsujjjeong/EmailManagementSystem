package kr.ac.kopo.main;

import java.util.Scanner;

import kr.ac.kopo.controller.EmailController;
import kr.ac.kopo.controller.SessionController;
import kr.ac.kopo.controller.UserController;
import kr.ac.kopo.model.Email;
import kr.ac.kopo.model.User;
import kr.ac.kopo.ui.AdminUI;
import kr.ac.kopo.ui.AnonymousUI;
import kr.ac.kopo.ui.AuthenticatedUI;



public class EmailMain {
public static void main(String[] args) {
	
	String ans = "n";
	Scanner sc = new Scanner(System.in);
	do {
		System.out.println("Enter Role: [admin, authenticated, anonymous]");
		
		String role = sc.next();
		if(role.equals("admin")) {
			AdminUI ui = new AdminUI();
			ui.execute();
		}
		else if(role.equals("authenticated")) {
			System.out.println("이메일 입력");
			String email = sc.next();
			SessionController sess = new SessionController();
			if(sess.isLoggedIn(email) == 0) {
				System.err.println("로그인 하지 못함");
				
				ans = "n";
				continue;

			}
			else {
				AuthenticatedUI aui = new AuthenticatedUI(email);
				aui.execute();
			}
			
		}
		else {
			AnonymousUI ui = new AnonymousUI();
			ui.execute();
		} 
		
		System.out.println("프로그롬 종료: y, 계속함: n");
		ans = sc.next();
	
	} while(ans.equals("n"));
	System.out.println("감사합니다");
	sc.close();
	return;
	
	
	
}
}
