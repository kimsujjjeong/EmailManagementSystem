package kr.ac.kopo.ui;

import java.util.Scanner;

import kr.ac.kopo.controller.UserController;
import kr.ac.kopo.model.User;

public class AnonymousUI {
	public void execute() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("--------Anonymous User Menu--------");
		
		System.out.println("1. 회원 가입");
		System.out.println("2. 로그인");
		System.out.println("**번호를 입력하세요**");
		
		
		int choice = sc.nextInt();
		
		
		
		
		
		if (choice == 1) {
			//sign up()
			
			System.out.println("--------회원 가입--------");
			System.out.println("\t");
			
			System.out.println("1. 아이디 설정");
			String emailID = sc.next();
			System.out.println("2. 비밀번호 설정");
			String password = sc.next();
			System.out.println("2. 비밀번호 확인");
			String cpassword = sc.next();
			
			if(!password.equals(cpassword)) {
				System.err.println("비밀번호 일치하지 않는다");
				System.exit(1);
			}
			System.out.println("3. 이름");
			String name = sc.next();
			System.out.println("4. 생년월일 (YYYYMMDD)");
			String birth = sc.next();
			System.out.println("5. 핸드폰 번호");
			String phone = sc.next();
			
			
			System.out.println("Email:"+emailID+",password:"+password+",Name:"+name+", Birthday:"+birth+", "
					+ "Phone:"+phone);
			
			User u = new User(emailID, name, password, birth, phone);
			
			
			UserController uc = new UserController();
			uc.signUp(u);
			
		}
		if (choice == 2) {
			//login()
			System.out.println("아이디를 입력하세요 ");
			String emailID = sc.next();
			System.out.println("비밀번호를 입력하세요 ");
			String password = sc.next();
		
			User u = new User(emailID, password);
			UserController uc = new UserController();
			uc.signIn(u);
		}
		
		
	}
}
