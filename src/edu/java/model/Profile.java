package edu.java.model;

public class Profile {
	
	// DB
	public interface Entity {
		String TBL_NAME = "PROFILE";
		String COL_ID = "ID";
		String COL_PIDX = "PIDX";
		String COL_PBLOB = "PBLOB";
		String COL_PNAME = "PNAME";
	}
	
	// fields
	private String id;
	private int pidx;
	private byte[] pblob;
	private String pname;
	
	// constructor
	public Profile() {}
	
	public Profile(String id, int pidx, byte[] pblob, String pname) {
		super();
		this.id = id;
		this.pidx = pidx;
		this.pblob = pblob;
		this.pname = pname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPidx() {
		return pidx;
	}

	public void setPidx(int pidx) {
		this.pidx = pidx;
	}

	public byte[] getPblob() {
		return pblob;
	}

	public void setPblob(byte[] pblob) {
		this.pblob = pblob;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}
	
	// toString method
	@Override
	public String toString() {
		return "Profile(id =" + this.id
				+ ", pidx = " + this.pidx
				+ ", pblob = " + this.pblob
				+ ", pname = " + this.pname
				+ ")";
	}
	
}
