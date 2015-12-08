package com.bluemobi.pro.entity;

import java.sql.Timestamp;

/**
 * 病理  or 讲座
 * 
 * @author yesong
 *
 */
public class Pathology {

	private String id; // ID
	private String title; // 病理分析标题
	private String content; // 病理分析内容
	private String image; // 封面
	private Timestamp createDate; // 创建时间
	private String url; // 链接地址
	private int type; // 0:病理 1:讲座
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

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

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
}
