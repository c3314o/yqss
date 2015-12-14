package com.bluemobi.pro.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.pro.entity.BorrowInfo;
import com.bluemobi.pro.entity.BorrowRepayRecord;
import com.bluemobi.pro.service.impl.BorrowService;
import com.bluemobi.utils.Result;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	
	public Result borrow() {
		return null;
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
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(iblist);
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
}
