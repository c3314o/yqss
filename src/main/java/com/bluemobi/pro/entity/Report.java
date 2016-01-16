package com.bluemobi.pro.entity;

public class Report {

	private Integer id;
	
	private Integer userId;
	
	private Integer shId;
	
	private String content;

	private Long createDate = System.currentTimeMillis();
	
	public Integer getShId() {
		return shId;
	}

	public void setShId(Integer shId) {
		this.shId = shId;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
