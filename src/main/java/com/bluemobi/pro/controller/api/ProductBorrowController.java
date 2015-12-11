package com.bluemobi.pro.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluemobi.pro.entity.ProductBorrow;
import com.bluemobi.pro.entity.ProductBorrowRepayRecord;
import com.bluemobi.pro.service.impl.ProductBorrowService;
import com.bluemobi.utils.Result;

/**
 * 
 * @ClassName: ProductBorrowController
 * @Description: 商品贷款controller
 * @author yesong
 * @date 2015年12月11日
 *
 */
@RequestMapping
@Controller
public class ProductBorrowController {

	@Autowired
	private ProductBorrowService service;
	
	/**
	 * 
     * @Title: findByUserId
     * @Description: 查询自己商品贷款记录
     * @param @param pb
     * @param @return    参数
     * @return Result    返回类型
    * @throws
	 */
	public Result findByUserId(ProductBorrow pb) {
		
		List<ProductBorrow> pbList = null;
		try {
			pbList = service.findBorrowByUserId(pb);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(pbList);
	}
	
	/**
	 * 
     * @Title: repay
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param pbrr
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	public Result repay(ProductBorrowRepayRecord pbrr) {
		
		try {
			service.repay(pbrr);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success();
	}
}
