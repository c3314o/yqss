package com.bluemobi.pro.entity;

/**
 * 
 * @ClassName: UserInfo
 * @Description: 用户基本信息
 * @author yesong
 * @date 2015年12月10日
 *
 */
public class UserInfo extends BaseEntity {

	private Integer userId;
	private String name;
	private String mobile;
	
	private int gender;
	private String identity;
	
	private String school;
	private String address;
	private String headPic;
	
	private String nickname;
	
	private Integer bind; // 第三方是否绑定手机 0:未绑定 1:已绑定
	private Integer platform; // 平台 0:正常 1:第三方
	
	public Integer getPlatform() {
		return platform;
	}
	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	public Integer getBind() {
		return bind;
	}
	public void setBind(Integer bind) {
		this.bind = bind;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
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
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
}
