package com.bluemobi.pro.entity;

/**
 *
 * @ClassName: UserLogin
 * @Description: 用户登录信息
 * @author 叶松
 * @date 2015年12月10日
 *
 */
public class UserLogin extends BaseEntity {

	/**
	 * 用户登录名
	 */
	private String username;
	
	/**
	 * 用户登录密码
	 */
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
