package com.bluemobi.pro.entity;

/**
 * 借贷/购物 相关信息
 * @author yesong
 *
 */
public class BorrowRelevanceInfo extends BaseEntity{

	private Integer type;
	
	private String parentName;
	
	private String parentMobile;
	
	private String parentAddress;
	
	private String friendName;
	
	private String friendMobile;
	
	private String friendQq;
	
	private String friendAddress;
	
	private String classmateName;
	
	private String classmateMobile;
	
	private String classmateQq;
	
	private String classmateAddress;

	private Integer userId;
	
	private Integer borrowId;
	
	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentMobile() {
		return parentMobile;
	}

	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}

	public String getParentAddress() {
		return parentAddress;
	}

	public void setParentAddress(String parentAddress) {
		this.parentAddress = parentAddress;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getFriendMobile() {
		return friendMobile;
	}

	public void setFriendMobile(String friendMobile) {
		this.friendMobile = friendMobile;
	}

	public String getFriendQq() {
		return friendQq;
	}

	public void setFriendQq(String friendQq) {
		this.friendQq = friendQq;
	}

	public String getFriendAddress() {
		return friendAddress;
	}

	public void setFriendAddress(String friendAddress) {
		this.friendAddress = friendAddress;
	}

	public String getClassmateName() {
		return classmateName;
	}

	public void setClassmateName(String classmateName) {
		this.classmateName = classmateName;
	}

	public String getClassmateMobile() {
		return classmateMobile;
	}

	public void setClassmateMobile(String classmateMobile) {
		this.classmateMobile = classmateMobile;
	}

	public String getClassmateQq() {
		return classmateQq;
	}

	public void setClassmateQq(String classmateQq) {
		this.classmateQq = classmateQq;
	}

	public String getClassmateAddress() {
		return classmateAddress;
	}

	public void setClassmateAddress(String classmateAddress) {
		this.classmateAddress = classmateAddress;
	}
}
