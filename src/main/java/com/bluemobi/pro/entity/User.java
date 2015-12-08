package com.bluemobi.pro.entity;

/**
 * 老人端用户表
 * 
 * @author yesong
 *
 */
public class User {

	private String userId;

	/** **第一阶段验证*** */
	// 注册用户手机号
	private String mobile = null;
	// 邮箱
	private String email = null;

	/** *注册第二阶段填写** */
	// 注册序列码
	private String regcode = null;
	// 账户
	private String account = null;
	// 密码
	
	private String password = null;

	/** *注册第三阶段填写** */
	// 头像地址
	private String headPic = null;
	// 姓名
	private String name = null;
	// 性别
	private String sex = null;
	// 生日
	private String birthday = null;
	// 身高
	private String height = null;
	// 体重
	private String weight = null;

	// 机器码
	private String deviceCode = null;
	// 用户唯一令牌
	private String userToken = null;
	// 苹果token
	private String iosToken = null;

	// 备用字段1 年龄
	private String bak1 = null;
	// 备用字段2
	private String bak2 = null;
	// 备用字段3
	private String bak3 = null;
	// 用户类型(0:普通用户, 1:代理商管理员, 2:特殊用户, 99:总公司)
	private String bak4 = null;
	// 注册时间
	private java.sql.Timestamp bak5 = null;
	// 备用字段6
	private java.sql.Timestamp bak6 = null;
	// 备用字段7
	private Double bak7 = null;
	// 备用字段8
	private Double bak8 = null;

	// 新增字段：备注姓名
	private String remark = null;

	private Integer dr;
	
	private String ts;
	
	private String city;

	private String maritalStatus;
	
	private String isHighOrSugar;

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getIsHighOrSugar() {
		return isHighOrSugar;
	}

	public void setIsHighOrSugar(String isHighOrSugar) {
		this.isHighOrSugar = isHighOrSugar;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegcode() {
		return regcode;
	}

	public void setRegcode(String regcode) {
		this.regcode = regcode;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getIosToken() {
		return iosToken;
	}

	public void setIosToken(String iosToken) {
		this.iosToken = iosToken;
	}

	public String getBak1() {
		return bak1;
	}

	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}

	public String getBak2() {
		return bak2;
	}

	public void setBak2(String bak2) {
		this.bak2 = bak2;
	}

	public String getBak3() {
		return bak3;
	}

	public void setBak3(String bak3) {
		this.bak3 = bak3;
	}

	public String getBak4() {
		return bak4;
	}

	public void setBak4(String bak4) {
		this.bak4 = bak4;
	}

	public java.sql.Timestamp getBak5() {
		return bak5;
	}

	public void setBak5(java.sql.Timestamp bak5) {
		this.bak5 = bak5;
	}

	public java.sql.Timestamp getBak6() {
		return bak6;
	}

	public void setBak6(java.sql.Timestamp bak6) {
		this.bak6 = bak6;
	}

	public Double getBak7() {
		return bak7;
	}

	public void setBak7(Double bak7) {
		this.bak7 = bak7;
	}

	public Double getBak8() {
		return bak8;
	}

	public void setBak8(Double bak8) {
		this.bak8 = bak8;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
