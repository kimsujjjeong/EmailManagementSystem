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
		SessionController sess = new SessionController();
		int isloggedIn = sess.isLoggedIn();
		System.out.println(" ");
		System.out.println(" * * * W e l c o m e ! ! " );
		System.out.println(" This is Orange Email! * * * ");
		System.out.println(" ");
		
		int role;
		
		if(isloggedIn == 1) {
			System.out.printf( "오렌지 메일함 \t (Email Menu)   \t\t\n");
			role = 2;
		} else {
			System.out.println( "번호를 선택하세요 ! ");
			System.out.printf( "관리자 \t (admin) \t\t[1]\n");
			System.out.printf( "로그인 \t (login)   \t\t[2]\n");
			System.out.printf( "회원가입 \t (Sign up) \t\t[3]\n");
			role = sc.nextInt();
		}
		
		
		if(role == 1) {
			AdminUI ui = new AdminUI();
			ui.execute();
		}
		else if(role == 2) {
			//System.out.println("아이디를 입력하세요.");
			//String email = sc.next();
			//회원 로그인 상태 확인 로그인->메일함으로 이동
			
			if(sess.isLoggedIn() == 0) {
				System.out.println("아이디를 입력하세요.");
				String email = sc.next();
				System.out.println("비밀번호를 입력하세요.");
				String password = sc.next();
			
				User u = new User(email, password);
				UserController uc = new UserController();
				if(uc.signIn(u) == 0) {
					System.out.println("아이디 또는 비밀번호를 찾으시겠습니까? (y/n))");
					String res = sc.next();
					
					if(res.equals("y")) {
						System.out.println("생년월일 입력하세요");
						String bir = sc.next();
						
						System.out.println("핸드폰번호를 입력하세요");
						String phone = sc.next();
						
						uc.findIDPassword(bir, phone);
					}
				} 
				continue;
				//AuthenticatedUI aui = new AuthenticatedUI(sess.sessionEmail());
				//aui.execute();
				
				
				
				

			}
			if(sess.isLoggedIn() == 1) {
				
				AuthenticatedUI aui = new AuthenticatedUI(sess.sessionEmail());
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
