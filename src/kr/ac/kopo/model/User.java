package kr.ac.kopo.model;

public class User {
	String email;
	String name;
	String password;
	String birthday;
	String phone;

	public User(String e, String n, String p, String b, String ph){
		this.email=e;
		this.name=n;
		this.password=p; 
		this.birthday=b;
		this.phone=ph;
	}
	
	public User(String e, String p){
		this.email=e;
		this.password=p; 
	}
	public String getem() {
		return this.email;
	}
	public String getna() {
		return this.name;
	}
	public String getpa() {
		return this.password;
	}
	public String getbi() {
		return this.birthday;
	}
	
	public String getph() {
		return this.phone;
	}
	public void setem(String emm) {
		this.email =emm;
	}
	public void setna(String naa) {
		this.name = naa;
	}
	public void setpas(String pass) {
		this.password =pass;
	}
	public void setbir(String birr) {
		this.birthday=birr;
	}
	
	public void setph(String ph) {
		this.phone=ph;
	}
	
}