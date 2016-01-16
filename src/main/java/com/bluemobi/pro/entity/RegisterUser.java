package com.bluemobi.pro.entity;

/**
 * 注册用户对象
 * @author yesong
 *
 */
public class RegisterUser extends UserLogin{

	private String code;
	
	private String mobile;
	
	private String nickname;
	
	private String openId; // 第三方登录
	
	private Integer gender; // 性别
	
	private String headPic; // 头像

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
