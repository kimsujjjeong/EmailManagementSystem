package kr.ac.kopo.ui;

import java.util.Scanner;

import kr.ac.kopo.migrations.EmailMigration;
import kr.ac.kopo.migrations.SessionMigration;
import kr.ac.kopo.migrations.UserMigration;

public class AdminUI {
	public void execute() {
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
			System.out.println("5. session 테이블 생성");
			System.out.println("6. session 테이블 삭제");
			System.out.println("7. 관리자 나가기");
			
			System.out.println("**번호를 입력하세요**");
			int imm = sc.nextInt();
			if(imm == 1) {
				UserMigration um = new UserMigration();
				um.createTable();
				return;
			}
			if(imm == 2) {
				UserMigration um = new UserMigration();
				um.dropTable();
				return;
			}
			
			if(imm == 3) {
				EmailMigration em = new EmailMigration();
				em.createTable();
				return;
			}
			if(imm == 4) {
				EmailMigration em = new EmailMigration();
				em.dropTable();
				return;
			}
			if(imm == 5) {
				SessionMigration em = new SessionMigration();
				em.createTable();
				return;
			}
			if(imm == 6) {
				SessionMigration em = new SessionMigration();
				em.dropTable();
				return;
			}
			if(imm == 7) {
				return;
			}
		} else {
			System.err.println("관리자 아닙니다.");
			return;
			
		}
	}
}
