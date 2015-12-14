package com.bluemobi.pro.entity;

import java.util.ArrayList;
import java.util.List;

import com.bluemobi.utils.DateUtils;
import com.bluemobi.utils.YqssUtils;

/**
 * 
 * @ClassName: BorrowInfo
 * @Description: 借款
 * @author yesong
 * @date 2015年12月10日
 *
 */
public class BorrowInfo extends BaseEntity {

	private Integer userId;
	
	/**
	 * 借款金额
	 */
	private Double borrow;
	
	/**
	 * 借款时间
	 */
	private Long jkTime;
	
	/**
	 * 最后还款时间
	 */
	private String lastRepayDate;
	
	/**
	 * 剩余还款天数
	 */
	private Integer residueDays;
	
	/**
	 * 借款总天数
	 */
	private Integer totalDays;
	
	/**
	 * 还款时间
	 */
	private String nextResidueDate;
	
	/**
	 * 还款状态  0 已结清  1  还款中  2  催款中
	 */
	private Integer state;
	
	private List<BorrowRepayRecord> list = new ArrayList<BorrowRepayRecord>();
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNextResidueDate() {
		return nextResidueDate;
	}

	public void setNextResidueDate(String nextResidueDate) {
		this.nextResidueDate = nextResidueDate;
	}

	/**
	 * 
     * @Title: getTotalDays
     * @Description:  计算借款总天数
     * @param @return    参数
     * @return Integer    返回类型
    * @throws
	 */
	public Integer getTotalDays() {
		totalDays = YqssUtils.totalDays(DateUtils.parse(jkTime, YqssUtils.DEFAULT_FORMAT));
		return totalDays;
	}

	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
	}

	/**
     * @Title: getResidueDays
     * @Description: 计算剩余天数
     * @param @return    参数
     * @return Integer    返回类型
     * @throws
	 */
	public Integer getResidueDays() {
		residueDays = YqssUtils.residueDay(lastRepayDate);
		return residueDays;
	}

	public void setResidueDays(Integer residueDays) {
		this.residueDays = residueDays;
	}

	public String getLastRepayDate() {
		return lastRepayDate;
	}

	public void setLastRepayDate(String lastRepayDate) {
		this.lastRepayDate = lastRepayDate;
	}

	public List<BorrowRepayRecord> getList() {
		return list;
	}

	public void setList(List<BorrowRepayRecord> list) {
		this.list = list;
	}

	public Double getBorrow() {
		return borrow;
	}

	public void setBorrow(Double borrow) {
		this.borrow = borrow;
	}

	public Long getJkTime() {
		return jkTime;
	}

	public void setJkTime(Long jkTime) {
		this.jkTime = jkTime;
	}

	/**
	 * 
     * @Title: getState
     * @Description: 计算还款状态
     * @param  参数
     * @return   
     * @return Integer    返回类型
     * @throws
	 */
	public Integer getState() {
		int allReypayMoney = 0;
		for (BorrowRepayRecord borrowRepayRecord : list) {
			allReypayMoney += borrowRepayRecord.getAmount();
		}
		int residueDays = YqssUtils.residueDay(lastRepayDate);
		if( residueDays <= 0) {
			state = 2;
		}
		else if( borrow <=  allReypayMoney){
			state = 0;
		}
		else if(borrow > allReypayMoney) {
			state = 1;
		}
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	} 
}
