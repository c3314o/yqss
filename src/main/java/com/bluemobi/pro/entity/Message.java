package com.bluemobi.pro.entity;

/**
 * 
 * @ClassName: Messsage
 * @Description: 消息
 * @author yesong
 * @date 2015年12月10日
 *
 */
public class Message extends BaseEntity {

	/**
	 * 消息标题
	 */
	private String msgTitle;
	
	/**
	 * 消息内容
	 */
	private String msgContent;
	
	private String userId;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
}
