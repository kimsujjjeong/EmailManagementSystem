package kr.ac.kopo.model;

public class Email {
	String email_to;
	String email_from;
	String title;
	String content;

	public Email(String e, String n, String p, String b){
		this.email_to=e;
		this.email_from=n;
		this.title=p; 
		this.content=b;
	}
	
	
	public String getemTo() {
		return this.email_to;
	}
	public String getefr() {
		return this.email_from;
	}
	public String getti() {
		return this.title;
	}
	public String getco() {
		return this.content;
	}
	
	
	public void setem(String emm) {
		this.email_to =emm;
	}
	public void setna(String emf) {
		this.email_from = emf;
	}
	public void setpas(String ti) {
		this.title =ti;
	}
	public void setbir(String co) {
		this.content=co;
	}
	
}