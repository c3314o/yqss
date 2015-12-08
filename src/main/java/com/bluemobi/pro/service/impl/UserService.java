package com.bluemobi.pro.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bluemobi.pro.entity.RegisterUser;
import com.bluemobi.pro.entity.User;
import com.bluemobi.sys.service.BaseService;
import com.bluemobi.utils.UUIDUtil;

@Service
public class UserService extends BaseService{

	private static final String PRIFIX = UserService.class.getName();
	
	/**
	 * 新增用户记录
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int addUser(RegisterUser user) throws Exception {
		user.setUserId(UUIDUtil.generateUUID());
		user.setBak4("0");
		return this.getBaseDao().save(PRIFIX + ".insert", user);
	}
	
	/**
	 * 判断用户是否存在
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public Boolean isExist(User user) throws Exception  {
		
		User reUser = findUserByMobile(user);
		if(reUser != null && reUser.getMobile() != null && reUser.getUserId() != null) {
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
	public User findUserByMobile(User user) throws Exception {
		User reUser = this.getBaseDao().getObject(PRIFIX + ".findUserByMobile", user);
		return reUser;
	} 
	
	/**
	 * 修改用户信息
	 * @param user
	 * @throws Exception
	 */
	public void modifyUser(User user) throws Exception {
		this.getBaseDao().update(PRIFIX + ".update", user);
	}
	
}
