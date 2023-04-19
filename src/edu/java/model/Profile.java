package edu.java.model;

public class Profile {
	
	// DB
	public interface Entity {
		String TBL_NAME = "PROFILE";
		String COL_ID = "ID";
		String COL_PBLOB = "PBLOB";
	}
	
	// fields
	private String id;
	private byte[] pblob;
	
	// constructor
	public Profile() {}
	
	public Profile(String id, byte[] pblob) {
		super();
		this.id = id;
		this.pblob = pblob;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getPblob() {
		return pblob;
	}

	public void setPblob(byte[] pblob) {
		this.pblob = pblob;
	}

	// toString method
	@Override
	public String toString() {
		return "Profile(id =" + this.id
				+ ", pblob = " + this.pblob
				+ ")";
	}
	
}
