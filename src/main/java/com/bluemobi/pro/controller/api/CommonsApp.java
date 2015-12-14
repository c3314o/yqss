package com.bluemobi.pro.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bluemobi.cache.CacheService;
import com.bluemobi.constant.ErrorCode;
import com.bluemobi.pro.entity.Bank;
import com.bluemobi.pro.entity.BorrowInfo;
import com.bluemobi.pro.entity.FeedBack;
import com.bluemobi.pro.entity.Help;
import com.bluemobi.pro.entity.Home;
import com.bluemobi.pro.entity.RegisterUser;
import com.bluemobi.pro.entity.UserInfo;
import com.bluemobi.pro.entity.UserLogin;
import com.bluemobi.pro.service.impl.BorrowService;
import com.bluemobi.pro.service.impl.CommonService;
import com.bluemobi.pro.service.impl.FeedBackService;
import com.bluemobi.pro.service.impl.UserService;
import com.bluemobi.utils.CommonUtils;
import com.bluemobi.utils.ImageUtils;
import com.bluemobi.utils.ParamUtils;
import com.bluemobi.utils.Result;
import com.bluemobi.utils.SmsUtils;

@Controller
@RequestMapping("/app/common/")
public class CommonsApp {

	@Resource(name = "cacheTempCodeServiceImpl")
	private CacheService<String> cacheService;

	@Autowired
	private UserService service;

	@Autowired
	private FeedBackService fservice;

	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private BorrowService borrowService;
	
	/**
	 * 发送验证码
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "sendCode", method = RequestMethod.POST)
	@ResponseBody
	public Result sendCode(@RequestParam Map<String, Object> params) {

		String code = null;
		try {
			if (ParamUtils.existEmpty(params, "mobile")) {
				return Result.failure();
			}

			// 验证手机号是否存在
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("mobile", params.get("mobile"));
			// 生成验证码
			code = CommonUtils.getCode(6);

			String mobile = params.get("mobile").toString();
//			String result = JavaSmsApi.sendShortMessage(mobile, code);
			// 成功
			if (SmsUtils.cjsmsSend(code, mobile)) {
				cacheService.put(mobile, code);
				return Result.success();
			}
			// 失败
			else {
				return Result.failure(ErrorCode.ERROR_09);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
	}

	/**
	 * 老人端用户注册
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public Result register(RegisterUser user) {

		try {
			String requestCode = user.getCode();
			String code = cacheService.get(user.getCode());
			if (service.isExist(user)) {
				return Result.failure(ErrorCode.ERROR_05);
			}
//			 else if(StringUtils.isBlank(requestCode) || !requestCode.equals(code)) {
//				 return Result.failure(ErrorCode.ERROR_10);
//			 }
			else {
				service.addUser(user);
				return Result.success();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
	}

	/**
	 * 老人端登录
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public Result checkLogin(RegisterUser userLogin) {

		UserInfo userInfo = null;
		UserLogin _userLogin = null;
		try {
			_userLogin = service.findUserByMobile(userLogin);
			if (_userLogin == null || !_userLogin.getPassword().equals(userLogin.getPassword())) {
				return Result.failure(ErrorCode.ERROR_03);
			}
			
			// 根据用户ID查询用户信息
			userInfo = new UserInfo();
			userInfo.setUserId(_userLogin.getId());
			userInfo = service.findUserInfoById(userInfo);
			
			return Result.success(userInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
	}

	/**
	 * 
     * @Title: home
     * @Description: 首页信息
     * @param @param userInfo
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	public Result home(UserInfo userInfo) {
		Home home = new Home();
		
		BorrowInfo info = new BorrowInfo();
		info.setUserId(userInfo.getUserId());
		List<BorrowInfo> list = null;
		
		try {
			list = borrowService.findBorrowByUserId(info);
			home.setAvailable(5000);
			home.setBorrowday(30);
			
			list.get(0).getBorrow();
			home.setSurplusday(list.get(0).getResidueDays());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Result.success(home);
	}
	
	@RequestMapping(value = "feedback", method = RequestMethod.POST)
	@ResponseBody
	public Result feedback(FeedBack feedBack) {

		try {
			fservice.insert(feedBack);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success();
	}

	@RequestMapping(value = "uploadHead", method = RequestMethod.POST)
	@ResponseBody
	public Result uploadHead(UserInfo userInfo, @RequestParam("head") MultipartFile file) {

		String headPic = null;
		try {
			headPic = ImageUtils.saveImage(file, false)[0];
			userInfo.setHeadPic(headPic);
			service.modifyUser(userInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Result.success();
	}
	
	/**
	 * 
     * @Title: findAllBank
     * @Description: 查询所有银行
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	@RequestMapping(value = "banklist", method = RequestMethod.POST)
	@ResponseBody
	public Result findAllBank() {
		List<Bank> list = null;
		try {
			list = commonService.findAllBank();
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(list);
	}
	
	@RequestMapping(value = "help", method = RequestMethod.POST)
	@ResponseBody
	public Result findAllHelper() {
		
		Help help = null;
		try {
			help = commonService.findAllHelper();
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(help);
	}
}
