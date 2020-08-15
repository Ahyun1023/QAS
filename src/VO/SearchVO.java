package VO;

import java.sql.Date;

public class SearchVO {
	private String category;
	private int id;
	private String userId;
	private String title;
	private String content;
	private int view;
	private Date created;
	
	private String searchWord;
	private String searchCategory;
	
	public SearchVO(int id, String userId, String category, String title, String content, int view, Date created) {
		super();
		this.id = id;
		this.userId = userId;
		this.category = category;
		this.title = title;
		this.content = content;
		this.view = view;
		this.created = created;
	}
	
	public SearchVO(String category) {
		super();
		this.category = category;
	}
	
	public SearchVO(String searchCategory, String searchWord) {
		super();
		this.searchCategory = searchCategory;
		this.searchWord = searchWord;
	}

	public SearchVO() {
	}

	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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

	public String getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}

}
