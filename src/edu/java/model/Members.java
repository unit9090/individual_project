package edu.java.model;

public class Members {
	
	// DB
	public interface Entity {
		String TBL_NAME = "MEMBERS";
		String COL_ID = "ID";
		String COL_TRAINER = "TRAINER";
		String COL_NAME = "NAME";
		String COL_GENDER = "GENDER";
		String COL_BIRTH = "BIRTH";
		String COL_PHONE = "PHONE";
		String COL_HEIGHT = "HEIGHT";
		String COL_WEIGHT = "WEIGHT";
	}
	
	// fields
	private String id;
	private String trainer;
	private String name;
	private String gender;
	private String birth;
	private String phone;
	private int height;
	private int weight;
	
	// constructor
	public Members() {}

	public Members(String id, String trainer, String name, String gender, String birth, String phone, int height, int weight) {
		super();
		this.id = id;
		this.trainer = trainer;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.phone = phone;
		this.height = height;
		this.weight = weight;
	}
	
	public Members(String id, String trainer, String name, String gender, String birth, int height, int weight) {
		super();
		this.id = id;
		this.trainer = trainer;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.height = height;
		this.weight = weight;
	}

	// getter & setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	// toString method
	@Override
	public String toString() {
		return "Member(id =" + this.id
				+ ", trainer = " + this.trainer
				+ ", name = " + this.name
				+ ", gender = " + this.gender
				+ ", birth = " + this.birth
				+ ", phone = " + this.phone
				+ ", height = " + this.height
				+ ", weight = " + this.weight
				+ ")";
	}
	
	
	
}
