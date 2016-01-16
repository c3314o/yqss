package com.bluemobi.pro.entity;

public class Oauth extends BaseEntity{

	private String openId;
	private Integer userId;
	
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
