package com.bluemobi.pro.entity;

import com.bluemobi.pro.entity.BaseEntity;
import com.bluemobi.pro.entity.UserInfo;

public class ProductComment extends BaseEntity{

	private String content;
	
	// 评论人ID
	private Integer fromUserId;
	private UserInfo fromUser;
	
	// 被评论人ID
	private Integer toUserId;
	private UserInfo toUser;

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

	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}

	public UserInfo getFromUser() {
		return fromUser;
	}

	public void setFromUser(UserInfo fromUser) {
		this.fromUser = fromUser;
	}

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public UserInfo getToUser() {
		return toUser;
	}

	public void setToUser(UserInfo toUser) {
		this.toUser = toUser;
	}
}
