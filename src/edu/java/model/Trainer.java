package edu.java.model;

public class Trainer {
	
	// 오라클 DB 테이블 이름과 컬럼 이름들을 상수로 정의
	public interface Entity {
		String TBL_NAME = "TRAINER";
		String COL_ID = "ID";
		String COL_NAME = "NAME";
		String COL_GENDER = "GENDER";
		String COL_BIRTH = "BIRTH";
		String COL_PHONE = "PHONE";
		String COL_EMAIL = "EMAIL";
	}
	
	// fields
	private String id;
	private String name;
	private String gender;
	private String birth;
	private String phone;
	private String email;
	
	// constructor
	public Trainer() {}

	public Trainer(String id, String name, String gender, String birth, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.phone = phone;
		this.email = email;
	}

	// getter & setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	};
	
	// toString method
	@Override
	public String toString() {
		return "Trainer(id =" + this.id
				+ ", name = " + this.name
				+ ", gender = " + this.gender
				+ ", birth = " + this.birth
				+ ", phone = " + this.phone				
				+ ", email = " + this.email
				+ ")";
		// return String.format("Contact(cid = %d, name = %s, phone = %s, email = %s)", cid, name, phone, email);
	}
	
	
}
