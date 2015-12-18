package com.bluemobi.pro.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bluemobi.pro.entity.BorrowInfo;
import com.bluemobi.pro.entity.BorrowRepayRecord;
import com.bluemobi.sys.service.BaseService;
import com.bluemobi.utils.YqssUtils;

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
     * @Title: borrow
     * @Description:  借款-填写
     * @param @return
     * @param @throws Exception    参数
     * @return int    返回类型
     * @throws
	 */
	public int borrowInsertUserInfo(BorrowInfo borrowInfo) throws Exception {
		// 判断是否有未还完的借款
		List<BorrowInfo> list = findBorrowByUserId(borrowInfo);
		if(list != null && list.size() >0 ) {
			BorrowInfo _info = list.get(list.size() - 1);
			if(_info != null &&_info.getState() != 0) {
				return -1;
			}
		}
		return this.getBaseDao().save(PRIFIX + ".insertBrrow", borrowInfo);
	}
	
	/**
	 * 
	     * @Title: borrowInsertBorrowInfo
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param borrowInfo
	     * @param @return
	     * @param @throws Exception    参数
	     * @return int    返回类型
	     * @throws
	 */
	public int borrowInsertBorrowInfo(BorrowInfo borrowInfo) throws Exception {
		if(borrowInfo.getTimeLimite() != null ) {
			borrowInfo.setLastRepayDate(YqssUtils.borrowResidueDate(borrowInfo.getTimeLimite()));
		}
		return this.getBaseDao().update(PRIFIX + ".updateBorrow", borrowInfo);
	}
	
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
		return this.getBaseDao().getList(PRIFIX + ".findBorrowListByUserId",bi);
	}
	
	/**
	 * 查询借款详情
	 * @param bi
	 * @return
	 * @throws Exception
	 */
	public BorrowInfo findBorrowById(BorrowInfo bi) throws Exception {
		return this.getBaseDao().getObject(PRIFIX + ".findBorrowListByUserId",bi);
	}
	
	/**
	 * 查询还款记录
	 * @param bi
	 * @return
	 * @throws Exception
	 */
	public List<BorrowRepayRecord> findBRR(BorrowInfo bi) throws Exception {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id", bi.getId());
		return this.getBaseDao().getList(PRIFIX + ".findBorrowRRListByBid", paramMap);
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
		this.getBaseDao().save(PRIFIX + ".insertBorrowRR", brr);
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
