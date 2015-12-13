package com.bluemobi.pro.entity;

import com.bluemobi.pro.entity.BaseEntity;
import com.bluemobi.pro.entity.UserInfo;

public class ProductComment extends BaseEntity{

	private String content;
	
	private UserInfo user;

	private Integer productId;
	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}
}
