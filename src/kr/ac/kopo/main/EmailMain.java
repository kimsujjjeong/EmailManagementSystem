package kr.ac.kopo.main;

import java.util.Scanner;

import kr.ac.kopo.controller.EmailController;
import kr.ac.kopo.controller.UserController;
import kr.ac.kopo.migrations.EmailMigration;
import kr.ac.kopo.migrations.UserMigration;
import kr.ac.kopo.model.Email;
import kr.ac.kopo.model.User;



public class EmailMain {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("--------Admin Menu--------");
	System.out.println("관리자 비밀번호 입력:");
	String pwd = sc.nextLine();
	if(pwd.equals("hamsu123")) {
		System.out.println("--------Admin Menu--------");
		System.out.println("1. user 테이블 생성");
		System.out.println("2. user 테이블 삭제");
		System.out.println("3. email 테이블 생성");
		System.out.println("4. email 테이블 삭제");
		
		System.out.println("**번호를 입력하세요**");
		int imm = sc.nextInt();
		if(imm == 1) {
			UserMigration um = new UserMigration();
			um.createTable();
		}
		if(imm == 2) {
			UserMigration um = new UserMigration();
			um.dropTable();
		}
		
		if(imm == 3) {
			EmailMigration em = new EmailMigration();
			em.createTable();
		}
		if(imm == 4) {
			EmailMigration em = new EmailMigration();
			em.dropTable();
		}
	} else {
		System.err.println("관리자 아닙니다.");
	}
	
	System.out.println("--------Main Menu--------");
	System.out.println("1. 회원가입");
	System.out.println("2. 로그인");
	System.out.println("3. 로그아웃");
	System.out.println("4. 받은 메일함");
	System.out.println("5. 보낸 메일함");
	System.out.println("6. 메일함 삭제");
	System.out.println("7. 이메일 발송");
	System.out.println("8. 스팸 차단");
	System.out.println("9. 전체 메일함 샂제");
	System.out.println("10. 전체 스팸메일함");
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
		System.out.println("3. 이름");
		String name = sc.next();
		System.out.println("4. 생년월일");
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
	if (choice == 3) {
		//logout
	}
	if (choice == 4) {		
		//email 받은 메일함
		EmailController emC = new EmailController();
		emC.allEmails();
		
	}
	if (choice == 5) {
		//email 보낸 메일함
	}
	if (choice == 6) {
		System.out.println("ID 입력");
		int id = sc.nextInt();
		
		EmailController emC = new EmailController();
		emC.deleteEmail(id);
		
		
	}
	if (choice == 9) {
		
		
		EmailController emC = new EmailController();
		emC.deleteAllEmail();
		
		
	}
	if (choice == 7) {
		//이메일 발송
		System.out.println("1. 받는 사람");
		String toemailID = sc.next();
		System.out.println("2. 메일 제목");
		String title = sc.next();
		System.out.println("3. 메일 내용");
		String content = sc.next();
		
		Email em = new Email(toemailID, "kim@dd.c",title, content);
		EmailController emC = new EmailController();
		emC.sendEmail(em);
		
	}
	if (choice == 8) {
		//Blacklist
		System.out.println("ID 입력");
		int id = sc.nextInt();
		
		EmailController emC = new EmailController();
		emC.moveToSpam(id);
	}
	if (choice == 10) {
		//Blacklist
		
		
		EmailController emC = new EmailController();
		emC.spamEmails();
	}
}
}
