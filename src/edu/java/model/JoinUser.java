package edu.java.model;

public class JoinUser {
	// 오라클 DB 테이블 이름과 컬럼 이름들을 상수로 정의
	public interface Entity {
		String TBL_NAME = "JOIN_USER";
		String COL_ID = "ID";
		String COL_PWD = "PASSWORD";
		String COL_PHONE = "PHONE";
		String COL_DIVISION = "DIVISION";
	}
	
	// fields
	private String id;
	private String pwd;
	private int phone;
	private String division;
	
	// constructor
	public JoinUser() {}

	public JoinUser(String id, String pwd, int phone, String division) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.phone = phone;
		this.division = division;
	}

	// getter & setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	};
	
	// toString method
	@Override
	public String toString() {
		return "User(id =" + this.id
				+ ", pwd = " + this.pwd
				+ ", phone = " + this.phone
				+ ", division = " + this.division
				+ ")";
	}
	
}
