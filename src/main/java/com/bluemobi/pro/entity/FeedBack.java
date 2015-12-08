package com.bluemobi.pro.entity;

import java.io.Serializable;

/**
 * 反馈
 * @author yesong
 *
 */
public class FeedBack implements Serializable {

	private static final long serialVersionUID = -7947018252844267189L;

	private String id;
	private String title;
	private String content;
	private String createUser;
	private String createDate;

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

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
