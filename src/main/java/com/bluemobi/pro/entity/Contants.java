package com.bluemobi.pro.entity;

/**
 * 手机联系人
 * @author yesong
 *
 */
public class Contants extends BaseEntity{

	private Integer userId;
	
	private String name;
	
	private String mobile;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
