package com.bluemobi.pro.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluemobi.pro.entity.UserInfo;
import com.bluemobi.pro.entity.UserLogin;
import com.bluemobi.sys.service.BaseService;

@Service
public class UserService extends BaseService{

	public static final String PRIFIX_USER_LOGIN = UserLogin.class.getName();
	public static final String PRIFIX_USER_INFO = UserInfo.class.getName();
	
	/**
	 * 新增用户记录
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public int addUser(UserLogin user) throws Exception {
		
		// 新增用户登录信息
		this.getBaseDao().save(PRIFIX_USER_LOGIN + ".insert", user);
		
		// 新增用户基本信息
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(user.getId());
		return this.getBaseDao().save(PRIFIX_USER_INFO + ".insert", user);
	}
	
	/**
	 * 
     * @Title: findUserInfoById
     * @Description: 根据用户ID查询用户信息
     * @param @param userInfo
     * @param @return
     * @param @throws Exception    参数
     * @return UserInfo    返回类型
     * @throws
	 */
	public UserInfo findUserInfoById(UserInfo userInfo) throws Exception {
		return this.getBaseDao().get(PRIFIX_USER_INFO + "findOne", userInfo);
	}
	
	/**
	 * 判断用户是否存在
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public Boolean isExist(UserLogin user) throws Exception  {
		
		UserLogin userLogin = findUserByMobile(user);
		if(userLogin != null && userLogin.getUsername() != null && userLogin.getId() != null) {
			return Boolean.valueOf(true);
		}
		return Boolean.valueOf(false);
	}
	
	/**
	 * 根据手机号查询用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public UserLogin findUserByMobile(UserLogin user) throws Exception {
		UserLogin userLogin = this.getBaseDao().getObject(PRIFIX_USER_LOGIN + ".findOne", user);
		return userLogin;
	} 
	
	/**
	 * 修改用户信息
	 * @param user
	 * @throws Exception
	 */
	public void modifyUser(UserInfo userInfo) throws Exception {
		this.getBaseDao().update(PRIFIX_USER_INFO + ".update", userInfo);
	}
	
	/**
	 * 
	 * @Title: modifyUserLoginPassword 
	 * @Description: 修改用户登录信息密码 
	 * @param 
	 * @param user
	 * @param 
	 * @throws Exception 参数 
	 * @return void 返回类型 
	 * @throws
	 */
	public void modifyUserLoginPassword(UserLogin user) throws Exception {
		this.getBaseDao().update(PRIFIX_USER_LOGIN + "update", user);
	}
}
