package com.bluemobi.pro.entity;

import java.util.ArrayList;
import java.util.List;

public class SecondHandProduct extends BaseEntity {

	private Double price;

	private Integer productId;
	
	private String title;

	private String content;

	private Integer userId;
	
	private UserInfo user;
	
	private List<ProductImage> picList = new ArrayList<ProductImage>();
	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public List<ProductImage> getPicList() {
		return picList;
	}

	public void setPicList(List<ProductImage> picList) {
		this.picList = picList;
	}
}
