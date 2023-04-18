package edu.java.model;

import java.time.LocalDateTime;

public class MemberDiary {

	// DB
	public interface Entity {
		String TBL_NAME = "MEMBER_DIARY";
		String COL_ID = "ID";
		String COL_TITLE = "TITLE";
		String COL_CONTENT = "CONTENT";
		String COL_CREATED = "CREATED_TIME";
		String COL_MODIFIED = "MODIFIED_TIME";
	}
	
	// fields
	private String id;
	private String title;
	private String content;
	private LocalDateTime created;
	private LocalDateTime modified;
	
	// constructor
	public MemberDiary() {}

	public MemberDiary(String id, String title, String content, LocalDateTime created, LocalDateTime modified) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.created = created;
		this.modified = modified;
	}

	// getter & setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		return "MemberDiary(id =" + this.id
				+ ", title = " + this.title
				+ ", content = " + this.content
				+ ", created = " + this.created
				+ ", modified = " + this.modified
				+ ")";
	}
	
}
