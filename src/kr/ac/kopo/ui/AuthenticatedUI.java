package kr.ac.kopo.ui;

import java.util.Scanner;

import kr.ac.kopo.controller.EmailController;
import kr.ac.kopo.controller.SessionController;
import kr.ac.kopo.model.Email;

public class AuthenticatedUI {
	private String sessEmail;
	
	public AuthenticatedUI(String sE) {
		this.sessEmail = sE;
	}
	
	public void execute() {

		System.out.println("--------Authenticated User Menu--------");
		
		System.out.println("1. 전체 메일함");
		System.out.println("2. 받은 메일함");
		System.out.println("3. 보낸 메일함");
		System.out.println("4. 메일함 삭제");
		System.out.println("5. 이메일 발송");
		System.out.println("6. 스팸 차단");
		System.out.println("7. 전체 메일함 삭제");
		System.out.println("8. 전체 스팸메일함");
		System.out.println("9. 로그아웃");
		System.out.println("**번호를 입력하세요**");
		
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		EmailController emC = new EmailController();
		if (choice == 1) {		
			//email 받은 메일함
			
			emC.allEmails();
			
		}
		if (choice == 2) {
			//email 보낸 메일함
			emC.receivedEmails(sessEmail);
		}
		if (choice == 3) {
			//email 보낸 메일함
			emC.sentEmails(sessEmail);
		}
		if (choice == 4) {
			System.out.println("ID 입력");
			int id = sc.nextInt();
			
			emC.deleteEmail(id);
			
			
		}
		if (choice == 5) {
			//이메일 발송
			System.out.println("1. 받는 사람");
			String toemailID = sc.next();
			System.out.println("2. 메일 제목");
			String title = sc.next();
			System.out.println("3. 메일 내용");
			String content = sc.next();
			
			Email em = new Email(toemailID, sessEmail,title, content);
			
			emC.sendEmail(em);
			
		}
		if (choice == 6) {
			//Blacklist
			System.out.println("ID 입력");
			int id = sc.nextInt();
			
			emC.moveToSpam(id);
		}
		if (choice == 7) {
			
			emC.deleteAllEmail();
						
		}
		
		
		if (choice == 8) {
			//Blacklist
			
			
			emC.spamEmails();
		}
		if(choice == 9) {
			// logout
			SessionController sess = new SessionController();
			sess.logout(sessEmail);
		}
		
	
	}
}
