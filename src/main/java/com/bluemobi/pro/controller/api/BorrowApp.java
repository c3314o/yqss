package com.bluemobi.pro.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluemobi.pro.entity.BorrowInfo;
import com.bluemobi.pro.entity.BorrowRepayRecord;
import com.bluemobi.pro.entity.ProductBorrow;
import com.bluemobi.pro.service.impl.BorrowService;
import com.bluemobi.pro.service.impl.ProductBorrowService;
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
	
	@Autowired
	private ProductBorrowService pbService;
	
	/**
	 * 
     * @Title: insertBorrow
     * @Description: TODO(这里用一句话描述这个方法的作用)
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
				service.borrowInsertUserInfo(borrowInfo);
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
				pbService.borrowProduct(pb);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success();
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
	public Result updateBorrow(BorrowInfo borrowInfo) {
		
		try {
			service.borrowInsertBorrowInfo(borrowInfo);
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
}
