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
	
	String ans = "y";
	Scanner sc = new Scanner(System.in);
	do {
		System.out.println("role: [관리자 (1), 회원 (2), 비회원 (3)]");
		
		int role = sc.nextInt();
		if(role == 1) {
			AdminUI ui = new AdminUI();
			ui.execute();
		}
		else if(role == 2) {
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
		
		System.out.println("프로그램 계속: y, 종료: n");
		ans = sc.next();
	
	} while(ans.equals("y"));
	System.out.println("프로그램이 종료되었습니다.");
	sc.close();
	return;
	
	
	
}
}
