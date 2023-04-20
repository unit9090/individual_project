package edu.java.model;

public class PtDiary {
	
	// DB
	public interface Entity {
		String TBL_NAME = "PT_DIARY";
		String COL_PIDX = "PD_IDX";
		String COL_TRID = "TR_ID";
		String COL_MBID = "MB_ID";
		String COL_TITLE = "TITLE";
		String COL_CONTENT = "CONTENT";
		String COL_CREATED = "CREATED_TIME";
		String COL_MODIFIED = "MODIFIED_TIME";
	}
	
	// fields
	private int pidx;
	private String trId;
	private String mbId;
	private String title;
	private String content;
	
	
	// constructor
	public PtDiary() {}

	public PtDiary(int pidx, String trId, String mbId, String title, String content) {
		super();
		this.pidx = pidx;
		this.trId = trId;
		this.mbId = mbId;
		this.title = title;
		this.content = content;
	}

	// getter & setter
	public int getPidx() {
		return pidx;
	}
	
	public String getTrId() {
		return trId;
	}

	public void setTrId(String trId) {
		this.trId = trId;
	}

	public String getMbId() {
		return mbId;
	}

	public void setMbId(String mbId) {
		this.mbId = mbId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	// toString method
	@Override
	public String toString() {
		return "PtDiary(pidx = " + this.pidx
				+ ", trid =" + this.trId
				+ ", mbId = " + this.mbId
				+ ", title = " + this.title
				+ ", content = " + this.content
				+ ")";
	}
	
	
	
	
}
