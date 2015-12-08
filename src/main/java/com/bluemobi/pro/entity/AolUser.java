package com.bluemobi.pro.entity;

/**
 * 子女端
 * @author Administrator
 *
 */
public class AolUser {

	private String aolUserId; // 子女端
	private String userId; // 老人端ID
	private String name;
	private String headPic;
	private String fnote;
	private int isOnline;
	
	public String getAolUserId() {
		return aolUserId;
	}
	public void setAolUserId(String aolUserId) {
		this.aolUserId = aolUserId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public String getFnote() {
		return fnote;
	}
	public void setFnote(String fnote) {
		this.fnote = fnote;
	}
	public int getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}
	
	
}
