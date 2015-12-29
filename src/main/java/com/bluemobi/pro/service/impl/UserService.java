package com.bluemobi.pro.service.impl;

import java.util.List;

import org.apache.cassandra.cli.CliParser.exitStatement_return;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bluemobi.pro.entity.Image;
import com.bluemobi.pro.entity.RegisterUser;
import com.bluemobi.pro.entity.UserBank;
import com.bluemobi.pro.entity.UserInfo;
import com.bluemobi.pro.entity.UserLogin;
import com.bluemobi.sys.service.BaseService;
import com.bluemobi.utils.ImageUtils;

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
	public int addUser(RegisterUser user) throws Exception {
		
		// 新增用户登录信息
		this.getBaseDao().save(PRIFIX_USER_LOGIN + ".insert", user);
		
		// 新增用户基本信息
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(user.getId());
		userInfo.setMobile(user.getMobile());
		return this.getBaseDao().save(PRIFIX_USER_INFO + ".insert", userInfo);
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
		return this.getBaseDao().get(PRIFIX_USER_INFO + ".findOne", userInfo);
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
	public UserInfo modifyUser(UserInfo userInfo,MultipartFile file) throws Exception {
		Image image = ImageUtils.saveImage(file, false);
		userInfo.setHeadPic(image.getImage());
		this.getBaseDao().update(PRIFIX_USER_INFO + ".update", userInfo);
		return userInfo;
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
		this.getBaseDao().update(PRIFIX_USER_LOGIN + ".update", user);
	}
	
	/**
	 * 
     * @Title: bingBank
     * @Description: 绑定银行卡
     * @param @param userBank
     * @param @throws Exception    参数
     * @return void    返回类型
     * @throws
	 */
	public int bingBank(final UserBank userBank) throws Exception {
		UserBank _userBank = this.getBaseDao().getObject(PRIFIX_USER_INFO + ".findUserBankByUserId", userBank);
		if( _userBank != null) {
			return -1; // 返回-1表示已绑定过改银行卡
		}
		return this.getBaseDao().save(PRIFIX_USER_INFO + ".bingBankCard", userBank);
	}
	
	/**
	 * 
     * @Title: findUserBank
     * @Description: 查询用户银行卡
     * @param @param userBank
     * @param @return
     * @param @throws Exception    参数
     * @return List<UserBank>    返回类型
     * @throws
	 */
	public List<UserBank> findUserBank(UserBank userBank) throws Exception {
		return this.getBaseDao().getList(PRIFIX_USER_INFO + ".findUserBankByUserId", userBank);
	}
	
	/**
	 * 
     * @Title: deleteUserBank
     * @Description: 解除绑定银行卡
     * @param @param userBank
     * @param @return
     * @param @throws Exception    参数
     * @return int    返回类型
     * @throws
	 */
	public int deleteUserBank(UserBank userBank) throws Exception {
		return this.getBaseDao().delete(PRIFIX_USER_INFO + ".deleteUserBank", userBank);
	}
	
	/**
	 * 
     * @Title: countMgsNum
     * @Description: 计算评论消息数
     * @param @param userInfo
     * @param @return
     * @param @throws Exception    参数
     * @return int    返回类型
     * @throws
	 */
	public int countMgsNum(UserInfo userInfo)  throws Exception {
		return this.getBaseDao().get(PRIFIX_USER_INFO + ".countMgsNum", userInfo);
	}
	
	public void readMsg(Integer userId) throws Exception {
		this.getBaseDao().update(PRIFIX_USER_INFO + ".readMsg", userId);
	}
}
