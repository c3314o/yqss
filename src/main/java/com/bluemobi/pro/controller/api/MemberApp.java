package com.bluemobi.pro.controller.api;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bluemobi.cache.CacheService;
import com.bluemobi.constant.ErrorCode;
import com.bluemobi.pro.entity.MsgCount;
import com.bluemobi.pro.entity.RegisterUser;
import com.bluemobi.pro.entity.UserBank;
import com.bluemobi.pro.entity.UserInfo;
import com.bluemobi.pro.entity.UserLogin;
import com.bluemobi.pro.service.impl.UserService;
import com.bluemobi.utils.Result;

/**
 * 
 * @ClassName: MemberApp
 * @Description: 用户controller
 * @author yesong
 * @date 2015年12月10日
 *
 */
@Controller
@RequestMapping("/app/member/")
public class MemberApp {

	@Autowired
	private UserService service;

	@Resource(name = "cacheTempCodeServiceImpl")
	private CacheService<String> cacheService;

	/**
	 * 
     * @Title: modifyMemberInfo
     * @Description: 修改用户信息
     * @param @param userInfo
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	public Result modifyMemberInfo(UserInfo userInfo,@RequestParam(value = "headPic",required = false) MultipartFile file) {

		try {
			service.modifyUser(userInfo,file);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success();
	}

	/**
	 * 
     * @Title: modifyMobile
     * @Description: 修改账号
     * @param @param userInfo
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	@RequestMapping(value = "modifyMobile", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public Result modifyMobile(RegisterUser rUser,@RequestParam("userId") Integer userId) {
		if(StringUtils.isNotBlank(rUser.getMobile())) {
			
			UserLogin userLogin = new UserLogin();
			userLogin.setId(userId);
			userLogin.setUsername(rUser.getMobile());
			
			// 查询手机号是否存在
			try {
				if(service.findUserByMobile(rUser) != null) {
					return Result.failure(ErrorCode.ERROR_05);
				}
				String requestCode = rUser.getCode();
				String code = cacheService.get(rUser.getMobile());
				
//				if (StringUtils.isBlank(requestCode) || !requestCode.equals(code)) {
//					return Result.failure(ErrorCode.ERROR_10);
//				}
				service.modifyUserLoginPassword(userLogin);
				
				UserInfo userInfo = new UserInfo();
				userInfo.setMobile(rUser.getMobile());
				service.modifyUser(userInfo,null);
			} catch (Exception e) {
				e.printStackTrace();
				return Result.failure();
			}
		}
		return Result.success();
	}
	
	/**
	 * 
     * @Title: forget
     * @Description: 忘记密码
     * @param @param user
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	@RequestMapping(value = "forget", method = RequestMethod.POST)
	@ResponseBody
	public Result forget(RegisterUser user,@RequestParam(value = "oldpassword", required = false) String oldpassword) {

		try {
			UserLogin userLogin = service.findUserByMobile(user);
			if(StringUtils.isNotBlank(oldpassword)) {
				if(!oldpassword.equals(userLogin.getPassword())) {
					return Result.failure(ErrorCode.ERROR_15);
				}
			}
			else {
				String requestCode = user.getCode();
				String code = cacheService.get(user.getUsername());

				if (userLogin == null)
					return Result.failure(ErrorCode.ERROR_06);
//				if (StringUtils.isBlank(requestCode) || !requestCode.equals(code)) {
//					return Result.failure(ErrorCode.ERROR_10);
//				}
			}
			userLogin.setPassword(user.getPassword());
			service.modifyUserLoginPassword(userLogin);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success();
	}
	
	/**
	 * 
     * @Title: findUserBank
     * @Description: 查询用户绑定的银行卡
     * @param @param userBank
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	@RequestMapping(value = "bank/list", method = RequestMethod.POST)
	@ResponseBody
	public Result findUserBank(UserBank userBank) {
		
		List<UserBank> list = null;
		try {
			list = service.findUserBank(userBank);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(list);
	}
	
	/**
	 * 
     * @Title: bingBank
     * @Description: 绑定银行卡
     * @param @param userBank
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	@RequestMapping(value = "bank/bind", method = RequestMethod.POST)
	@ResponseBody
	public Result bindBank(UserBank userBank) {
		
		try {
			int flag = service.bingBank(userBank);
			// 已绑定该银行卡
			if(flag == -1) {
				return Result.failure(ErrorCode.ERROR_16);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success();
	}
	
	/**
	 * 
     * @Title: deleteBank
     * @Description: 解除银行卡绑定
     * @param @param userBank
     * @param @return    参数
     * @return Result    返回类型
	     * @throws
	 */
	@RequestMapping(value = "bank/delete", method = RequestMethod.POST)
	@ResponseBody
	public Result deleteBank(UserBank userBank) {
		
		try {
			service.deleteUserBank(userBank);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success();
	}

	/**
	 * 
     * @Title: counMsg
     * @Description: 计算消息数
     * @param @param userInfo
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	@RequestMapping(value = "msgcount", method = RequestMethod.POST)
	@ResponseBody
	public Result counMsg(UserInfo userInfo) {
		
		MsgCount m = null;
		try {
			int count = service.countMgsNum(userInfo);
			m = new MsgCount();
			m.setNum(count);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(m);
	}
}
