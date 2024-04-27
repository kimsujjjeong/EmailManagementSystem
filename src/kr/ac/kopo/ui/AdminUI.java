package kr.ac.kopo.ui;

import java.util.Scanner;

import kr.ac.kopo.migrations.EmailMigration;
import kr.ac.kopo.migrations.Migration;
import kr.ac.kopo.migrations.SessionMigration;
import kr.ac.kopo.migrations.UserMigration;

public class AdminUI {
	public void execute() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-------- 오렌지 관리자 메뉴 --------");
		System.out.println("오렌지 관리자 비밀번호 입력:");
		
		String pwd = sc.nextLine();
		if(pwd.equals("su123")) {
			System.out.println("-------- 오렌지 관리자 --------");
			System.out.println("1. 데이터베이스 설치");
			System.out.println("2. 데이터베이스 삭제");
			System.out.println("3. user 테이블 생성");
			System.out.println("4. user 테이블 삭제");
			System.out.println("5. email 테이블 생성");
			System.out.println("6. email 테이블 삭제");
			System.out.println("7. session 테이블 생성");
			System.out.println("8. session 테이블 삭제");
			System.out.println("9. 관리자 나가기");
			
			System.out.println(" ** 번호를 입력하세요. ** ");
			int imm = sc.nextInt();
			
			if(imm == 1) {
				Migration um = new Migration();
				um.installDatabase();
				return;
			}
			if(imm == 2) {
				Migration um = new Migration();
				um.deleteDatabase();
				return;
			}
			
			
			if(imm == 3) {
				UserMigration um = new UserMigration();
				um.createTable();
				return;
			}
			if(imm == 4) {
				UserMigration um = new UserMigration();
				um.dropTable();
				return;
			}
			
			if(imm == 5) {
				EmailMigration em = new EmailMigration();
				em.createTable();
				return;
			}
			if(imm == 6) {
				EmailMigration em = new EmailMigration();
				em.dropTable();
				return;
			}
			if(imm == 7) {
				SessionMigration em = new SessionMigration();
				em.createTable();
				return;
			}
			if(imm == 8) {
				SessionMigration em = new SessionMigration();
				em.dropTable();
				return;
			}
			if(imm == 9) {
				return;
			}
		} else {
			System.err.println("오렌지 관리자가 아닙니다.");
			return;
			
		}
	}
}
