package VO;

import java.sql.Date;

public class QuestionVO {
	private int id;
	private String userId;
	private String title;
	private String category;
	private String content;
	private int view;
	private Date created;
	
	public QuestionVO(int id, String userId, String category, String title, int view, Date created, String content) {
		super();
		this.id = id;
		this.userId = userId;
		this.category = category;
		this.title = title;
		this.view = view;
		this.created = created;
		this.content = content;
	}
	
	public QuestionVO(int id) {
		super();
		this.id = id;
	}
	
	public QuestionVO(int id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}
	
	public QuestionVO(String userId, String title, String category, String content) {
		super();
		this.userId = userId;
		this.title = title;
		this.category = category;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}
