package kr.ac.kopo.practice;

import kr.ac.kopo.model.User;

public class UserMain {
	public static void main(String[] args) {
		User u = new User("kim@sss.com","김","123","1996","2024-04-20");
		System.out.println(u.getem());
		u.setem("kys@ssss.co");
		System.out.println(u.getem());
		System.out.println(u.getbi());
		u.setbir("1999");
		System.out.println(u.getbi());
		System.out.println(u);
		
	}

}
