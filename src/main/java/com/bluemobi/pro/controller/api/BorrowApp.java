package com.bluemobi.pro.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.constant.ErrorCode;
import com.bluemobi.pro.entity.BorrowInfo;
import com.bluemobi.pro.entity.BorrowRelevanceInfo;
import com.bluemobi.pro.entity.BorrowRepayRecord;
import com.bluemobi.pro.entity.ProductBorrow;
import com.bluemobi.pro.service.impl.BorrowRIService;
import com.bluemobi.pro.service.impl.BorrowService;
import com.bluemobi.pro.service.impl.ProductBorrowService;
import com.bluemobi.utils.CommonUtils;
import com.bluemobi.utils.Result;
import com.bluemobi.utils.WebserviceUtil;
import com.bluemobi.utils.YqssUtils;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @ClassName: BorrowController
 * @Description: 借款
 * @author Administrator
 * @date 2015年12月14日
 *
 */
@Controller
@RequestMapping("/app/borrow/")
public class BorrowApp {
	
	@Autowired
	private BorrowService service;
	
	@Autowired
	private ProductBorrowService pbService;
	
	@Autowired
	private BorrowRIService briService;
	
	/**
	 * 
     * @Title: insertBorrow
     * @Description: 借款基本信息
     * @param @param borrowInfo
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	@RequestMapping(value = "userinfo", method = RequestMethod.POST)
	@ResponseBody
	public Result insertBorrow(BorrowInfo borrowInfo) {
		
		try {
			int flag = borrowInfo.getFlag();
			if(flag == 0) {
				// 借款
				int _flag = service.borrowInsertUserInfo(borrowInfo);
				
				Map<String,String> resultMap = new HashMap<String,String>();
				resultMap.put("id", String.valueOf(borrowInfo.getId()));
				if(_flag == -1) {
					resultMap.put("status", "1"); // 已借款，无法再次借款
				}
				else if(_flag == -2) {
					resultMap.put("status", "2"); // 已填写用户信息，尚未借款
				}
				return Result.success(resultMap,"borrowinfo");
			}
			else if(flag == 1) {
				ProductBorrow pb = new ProductBorrow();
				pb.setUserId(borrowInfo.getUserId());
				pb.setName(borrowInfo.getName());
				pb.setIdentity(borrowInfo.getIdentity());
				pb.setMobile(borrowInfo.getMobile());
				pb.setSchool(borrowInfo.getSchool());
				pb.setAddress(borrowInfo.getAddress());
				pb.setProductId(borrowInfo.getProductId());
				pb.setStage(borrowInfo.getStage());
				pb.setPeriod(borrowInfo.getStage());
				
				pbService.borrowProduct(pb);
				
				Map<String,String> resutlMap = new HashMap<String,String>();
				resutlMap.put("id", String.valueOf(pb.getId()));
				return Result.success(resutlMap,"productborrow");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return null;
	}
	
	/**
	 * 
     * @Title: updateBorrow
     * @Description: 完善借款信息
     * @param @param borrowInfo
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	@RequestMapping(value = "borrowinfo", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public Result updateBorrow(BorrowInfo borrowInfo) {
		
		Map<String,String> resutlMap = new HashMap<String,String>();
		try {
			service.borrowInsertUserInfo(borrowInfo);
			service.borrowInsertBorrowInfo(borrowInfo);
			resutlMap.put("id", String.valueOf(borrowInfo.getId()));
			
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(resutlMap,"borrowinfo");
	}
	
	/**
	 * 新增借贷 / 购物 相关信息
	 * @param bri
	 * @return
	 */
	@RequestMapping(value = "insertBorrowRI", method = RequestMethod.POST)
	@ResponseBody
	public Result insertBorrowRI(BorrowRelevanceInfo bri) {
		
		try {
			briService.insert(bri);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success();
	}
	
	/**
	 * 
     * @Title: findByUserId
     * @Description: 查询自己商品贷款记录
     * @param @param pb
     * @param @return    参数
     * @return Result    返回类型
    * @throws
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Result findByUserId(BorrowInfo bi) {
		
		List<BorrowInfo> iblist = null;
		try {
			iblist = service.findBorrowByUserId(bi);
			for (BorrowInfo borrowInfo : iblist) {
				List<BorrowRepayRecord> list = service.findBRR(borrowInfo);
				borrowInfo.setList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(iblist);
	}
	
	/**
	 * 借款记录详情
	 * 还款记录列表
	 * @param bi
	 * @return
	 */
	@RequestMapping(value = "detail", method = RequestMethod.POST)
	@ResponseBody
	public Result findBorrowDetail(BorrowInfo bi) {
		
		BorrowInfo _borrow = null;
		try {
			_borrow = service.findBorrowById(bi);
			if(_borrow == null) {
				_borrow = new BorrowInfo();
			}
			List<BorrowRepayRecord> list = service.findBRR(bi);
			_borrow.setList(list);
			
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(_borrow);
	}
	
	/**
	 * 
     * @Title: repay
     * @Description: 还款
     * @param @param pbrr
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	@RequestMapping(value = "repay", method = RequestMethod.POST)
	@ResponseBody
	public Result repay(BorrowRepayRecord brr) {
		
		try {
			service.repay(brr);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success();
	}
	
	/**
	 * 理财主页
	 * 获取用户总资产以及累计收益
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "h5Index", method = RequestMethod.POST)
	@ResponseBody
	public Result h5Index(Integer userId) {
		
		String basepath = "http://localhost:8080/yqss_server/";
		String resultParmas = WebserviceUtil.post(basepath + "moneymag/h5Index?userId=" + userId);
		
		Map<String,Object> mapParams = new GsonBuilder().create().fromJson(resultParmas, Map.class);
		LinkedTreeMap data = (LinkedTreeMap)mapParams.get("data");
		Object obj = data.get("h5Index");
		System.out.println("resultParmas:" + resultParmas);
		return Result.success(obj, "h5Index");
	}
}
