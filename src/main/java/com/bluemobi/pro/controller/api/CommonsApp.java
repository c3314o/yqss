package com.bluemobi.pro.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.bluemobi.pro.entity.BorrowRepayRecord;
import com.bluemobi.pro.entity.Contants;
import com.bluemobi.pro.entity.ContantsList;
import com.bluemobi.pro.entity.FeedBack;
import com.bluemobi.pro.entity.Help;
import com.bluemobi.pro.entity.Home;
import com.bluemobi.pro.entity.Message;
import com.bluemobi.pro.entity.MsgCount;
import com.bluemobi.pro.entity.Oauth;
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
import com.bluemobi.utils.ResultUtils;
import com.bluemobi.utils.SmsUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.emay.channel.SmsSend;

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
			// 成功
			if (SmsSend.send(mobile, code)) {
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
			String code = cacheService.get(user.getMobile());
			if (service.isExist(user)) {
				return Result.failure(ErrorCode.ERROR_05);
			}
			 else if(StringUtils.isBlank(requestCode) || !requestCode.equals(code)) {
				 return Result.failure(ErrorCode.ERROR_10);
			 }
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
		
		String openId = userLogin.getOpenId();
		if(openId != null) {
			// 第三方登录
			Integer userId = loginOpenId(userLogin);
			if(userId != null && userId != -1 && userId != -2) {
				userInfo = new UserInfo();
				userInfo.setUserId(userId);
				try {
					userInfo = service.findUserInfoById(userInfo);
					userInfo.setBind(1);
					userInfo.setPlatform(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return Result.success(userInfo);
			}
			else if(userId != null && userId == -1){
				return Result.failure(ErrorCode.ERROR_10);
			}
			else {
				if(userInfo == null) {
					userInfo = new UserInfo();
					userInfo.setBind(0);
					userInfo.setPlatform(1);
				}
				return Result.success(userInfo);
			}
		}
		else {
			// 正常登录
			try {
				_userLogin = service.findUserByMobile(userLogin);
				if (_userLogin == null || !_userLogin.getPassword().equals(userLogin.getPassword())) {
					return Result.failure(ErrorCode.ERROR_03);
				}
				// 根据用户ID查询用户信息
				userInfo = new UserInfo();
				userInfo.setUserId(_userLogin.getId());
				userInfo = service.findUserInfoById(userInfo);
				userInfo.setPlatform(0);
				
				return Result.success(userInfo);
			} catch (Exception e) {
				e.printStackTrace();
				return Result.failure();
			}
		}
	}

	/**
	 * 第三方登录
	 * @param userLogin
	 */
	private Integer loginOpenId(RegisterUser userLogin) {
		try {
			Oauth oauth = service.findOauthByOpenId(userLogin.getOpenId());
			if(oauth != null && oauth.getUserId() != null) {
				return oauth.getUserId();
			}
			else {
				if(oauth == null) {
					Oauth _o = new Oauth();
					_o.setOpenId(userLogin.getOpenId());
					service.insertAuth(_o);
				}
				else {
					if(oauth.getUserId() == null && userLogin.getMobile() == null) {
						return -2;
					}
					// 绑定
					String requestCode = userLogin.getCode();
					String code = cacheService.get(userLogin.getMobile());
				    if(StringUtils.isBlank(requestCode) || !requestCode.equals(code)) {
				    	return -1;
				    }
				    
				    UserLogin _ul = service.findUserByMobile(userLogin);
				    if(_ul != null) {
				    	// 绑定用户ID
				    	Oauth auth = new Oauth();
				    	auth.setUserId(_ul.getId());
				    	auth.setOpenId(oauth.getOpenId());
				    	service.updateOauth(auth);
				    	return _ul.getId();
				    }
				    else {
				    	// 新建用户信息
				    	userLogin.setPassword("");
				    	Integer userId = service.addUser(userLogin);
				    	Oauth auth = new Oauth();
				    	auth.setUserId(userId);
				    	auth.setOpenId(oauth.getOpenId());
				    	service.updateOauth(auth);
				    	return userId;
				    }
				    
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Result sysMsgCount() {
		return null;
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
	@RequestMapping(value = "home", method = RequestMethod.POST)
	@ResponseBody
	public Result home(UserInfo userInfo) {
		Home home = new Home();
		
		BorrowInfo info = new BorrowInfo();
		info.setUserId(userInfo.getUserId());
		List<BorrowInfo> list = null;
		
		try {
			list = borrowService.findBorrowByUserId(info);
			if(list == null || list.size() == 0 || list.get(0).getMoney() == 0) {
				home.setAvailable(5000);
				home.setBorrowday(30);
				
				home.setAmount(0.0);
				home.setSurplusday(0);
			}
			else {
				BorrowInfo info2 = new BorrowInfo();
				info2.setId(list.get(list.size() - 1).getId());
				List<BorrowRepayRecord> recordList = borrowService.findBRR(info2);
				list.get(list.size() - 1).setList(recordList);
				
				home.setAvailable(5000);
				home.setBorrowday(list.get(list.size() - 1).getTotalDays());
				
				home.setId(list.get(list.size() - 1).getId());
				home.setAmount(list.get(list.size() - 1).getResidueMoney());
				home.setSurplusday(list.get(list.size() - 1).getResidueDays());
			}
		
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

	/**
	 * 
     * @Title: uploadHead
     * @Description: 上传头像
     * @param @param userInfo
     * @param @param file
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	@RequestMapping(value = "uploadHead", method = RequestMethod.POST)
	@ResponseBody
	public Result uploadHead(UserInfo userInfo, @RequestParam("head") MultipartFile file) {

		UserInfo info = null;
		try {
			info = service.modifyUser(userInfo,file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(info);
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
	
	/**
	 * 查询用户是否有联系人记录
	 * @param c
	 * @return
	 */
	@RequestMapping(value="contants/counts", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> countContants(Contants c) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			int counts = commonService.countUserContants(c);
			map.put("counts", counts);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultUtils.map2(map);
	}
	
	/**
	 * 新增用户联系人
	 * @param c
	 * @return
	 */
	@RequestMapping(value="contants/insert", method = RequestMethod.POST)
	@ResponseBody
	public Result insert(@RequestParam("contant") String contant) {
		
//		contant = "{list:[{mobile: 010-8525-5588, name: 苹果中国 }],userId:10}";
		Gson gson = new GsonBuilder().create();
		ContantsList cl = gson.fromJson(contant, ContantsList.class);
		Integer userId = cl.getUserId();
		List<Contants> list = cl.getList();
		try {
			for (Contants contants : list) {
				contants.setUserId(userId);
				commonService.insertContants(contants);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success();
	}
}
