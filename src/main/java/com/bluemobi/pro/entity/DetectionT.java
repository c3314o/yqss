package com.bluemobi.pro.entity;

import java.sql.Timestamp;

/**
 * 检测题目所属类别
 * 
 * @author yesong
 *
 */
public class DetectionT {

	private String id;
	private String title; // 类别标题
	private String image; // 首页图片

	private Timestamp createDate; // 创建时间
	private String createUser; // 创建用户

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
}
