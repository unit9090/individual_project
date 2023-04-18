package edu.java.model;

import java.time.LocalDateTime;

public class PtDiary {
	
	// DB
	public interface Entity {
		String TBL_NAME = "PT_DIARY";
		String COL_TRID = "TR_ID";
		String COL_MBID = "MB_ID";
		String COL_TITLE = "TITLE";
		String COL_CONTENT = "CONTENT";
		String COL_CREATED = "CREATED_TIME";
		String COL_MODIFIED = "MODIFIED_TIME";
	}
	
	// fields
	private String trId;
	private String mbId;
	private String title;
	private String content;
	private LocalDateTime created;
	private LocalDateTime modified;
	
	
	// constructor
	public PtDiary() {}

	public PtDiary(String trId, String mbId, String title, String content, LocalDateTime created,
			LocalDateTime modified) {
		super();
		this.trId = trId;
		this.mbId = mbId;
		this.title = title;
		this.content = content;
		this.created = created;
		this.modified = modified;
	}

	// getter & setter
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

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}
	
	// toString method
	@Override
	public String toString() {
		return "PtDiary(trid =" + this.trId
				+ ", mbId = " + this.mbId
				+ ", title = " + this.title
				+ ", content = " + this.content
				+ ", created = " + this.created
				+ ", modified = " + this.modified
				+ ")";
	}
	
	
	
	
}
