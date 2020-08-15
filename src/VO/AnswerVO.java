package VO;

import java.util.Date;

public class AnswerVO {
	private int id;
	private String userId;
	private int qId;
	private String title;
	private String content;
	private Date created;
	
	public AnswerVO(int id, String userId, String title, String content, Date created) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.created = created;
	}
	
	public AnswerVO(int id) {
		super();
		this.id = id;
	}

	public AnswerVO(int qId, String userId, String title, String content) {
		super();
		this.qId = qId;
		this.userId = userId;
		this.title = title;
		this.content = content;
	}
	
	public AnswerVO(int id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getqId() {
		return qId;
	}
	public void setqId(int qId) {
		this.qId = qId;
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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}
