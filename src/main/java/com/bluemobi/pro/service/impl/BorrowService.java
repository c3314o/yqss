package com.bluemobi.pro.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bluemobi.pro.entity.BorrowInfo;
import com.bluemobi.pro.entity.BorrowRepayRecord;
import com.bluemobi.sys.service.BaseService;

/**
 * 
 * @ClassName: BorrowService
 * @Description: 借款service
 * @author yesong
 * @date 2015年12月10日
 *
 */
@Service
public class BorrowService extends BaseService {

	public static final String PRIFIX = BorrowInfo.class.getName();
	
	/**
	 * 
     * @Title: findBorrowByUserId
     * @Description: 查询用户借款信息
     * @param @param userInfo
     * @param @return
     * @param @throws Exception    参数
     * @return List<BorrowInfo>    返回类型
     * @throws
	 */
	public List<BorrowInfo> findBorrowByUserId(BorrowInfo bi) throws Exception{
		return this.getBaseDao().getList(PRIFIX + ".findBorrowListByUserId",bi.getUserId());
	}
	
	/**
	 * 
     * @Title: repay
     * @Description: 还款
     * @param @throws Exception    参数
     * @return void    返回类型
     * @throws
	 */
	public void repay(BorrowRepayRecord brr) throws Exception {
		this.getBaseDao().save(PRIFIX + "insertBorrowRR", brr);
	}
	
	/**
	 * 
     * @Title: residueMoney
     * @Description: 计算本月应还款金额
     * @param @param list
     * @param @return    参数
     * @return int    返回类型
     * @throws
	 */
	public int residueMoney(List<BorrowInfo> list) {
		return 0;
	}
}
