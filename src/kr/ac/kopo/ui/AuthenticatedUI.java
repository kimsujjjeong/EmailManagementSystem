package kr.ac.kopo.ui;

import java.util.Scanner;

import kr.ac.kopo.controller.EmailController;
import kr.ac.kopo.controller.SessionController;
import kr.ac.kopo.controller.UserController;
import kr.ac.kopo.model.Email;

public class AuthenticatedUI {
	private String sessEmail;
	
	public AuthenticatedUI(String sE) { //클래스에서 생성자가 있으면 
		this.sessEmail = sE;
	}
	
	public void execute() {

		System.out.println("--------오렌지 회원 메뉴--------");
		
		System.out.println("1. 전체 메일함");
		System.out.println("2. 받은 메일함");
		System.out.println("3. 보낸 메일함");
		System.out.println("4. 메일함 삭제");
		System.out.println("5. 이메일 발송");
		System.out.println("6. 스팸 메일함");
		System.out.println("7. 전체 메일함 삭제");
		System.out.println("8. 전체 스팸메일함");
		System.out.println("9. 휴지통 ");
		System.out.println("10. *중요 메일 선택");
		System.out.println("11. *중요 메일함");
		System.out.println("12. 로그아웃");
		System.out.println("13. 오렌지메일 탈퇴");
		System.out.println("14. 이메일 복원");
		System.out.println("15. 휴지통 비우기");
		System.out.println("16. 전체 휴지통 비우기");
		System.out.println("** 번호를 입력하세요 **");
		
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		EmailController emC = new EmailController(sessEmail);
		if (choice == 1) {		
			//email 전체 메일함
			emC.allEmails();
			
		}
		if (choice == 2) {
			//email  받은 메일함
			emC.receivedEmails();
		}
		if (choice == 3) {
			//email 보낸 메일함
			emC.sentEmails();
		}
		if (choice == 4) {
			System.out.println("NO 입력"); // 글 번호 입력으로 바꾸기
			int no = sc.nextInt();
			
			if(emC.isFav(no) == 4) {
				System.out.println("*중요*메일입니다, 정말 삭제하시겠습니끼? (y/n))");
				String res = sc.next();
				
				if(res.equals("n")) {
					return;
				}
				
			}
			emC.deleteEmail(no);
			 //휴지통
			
			
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
			//스팸메일함으로 보내기
			System.out.println("번호 입력");
			int no = sc.nextInt();
			
			emC.moveToSpam(no);
		}
		if (choice == 7) {
			// 삭제할 메일함 --> 휴지통으로 넣기
			emC.deleteAllEmail();
						
		}
		
		
		if (choice == 8) {
			//전체 스팸메일함 키워드
			
			emC.spamEmails();
		}
		
		
		if(choice == 9) {
			
			// 휴지통
			emC.binEmails();
		}
		
		if(choice == 10) {
			System.out.println("번호 입력");
			int no = sc.nextInt();
			// 중요
			emC.moveToFavorite(no);
		}
		
		if(choice == 11) {
			
			// 휴지통
			emC.favEmails();
		}
		
		if(choice == 12) {
			// 로그아웃
			SessionController sess = new SessionController();
			sess.logout(sessEmail);
		}
		if(choice == 13) {
			// 로그아웃
			System.out.println("탈퇴하시곘습니까?: 네 (y) /아니요 (n) ");
			String no = sc.next();
			if(no.equals("y") ) {
				UserController u = new UserController();
				if(u.removeAccount(sessEmail) == 1) {
					return;
				}
			}
			
			
		}
		if(choice == 14) {
			System.out.println("번호 입력");
			int no = sc.nextInt();
			// 중요
			emC.restoreEmail(no);
		}
		
		if(choice == 15) {
			System.out.println("번호 입력");
			int no = sc.nextInt();
			// 중요
			emC.deletePermanentEmail(no);
		}
		
		if(choice == 16) {
			emC.cleanTrashEmail();
		}
		
		
		
	
	}
}
