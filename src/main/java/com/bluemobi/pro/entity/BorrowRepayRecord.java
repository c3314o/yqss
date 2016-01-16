package com.bluemobi.pro.entity;

import com.bluemobi.constant.ExcludeFile;

/**
 * 
 * @ClassName: BorrowRepayRecord
 * @Description: 还款记录
 * @author yesong
 * @date 2015年12月10日
 *
 */
public class BorrowRepayRecord extends BaseEntity {

	@ExcludeFile
	private Integer borrowId;
	
	/**
	 * 还款金额
	 */
	private Double amount;
	
	/**
	 * 还款时间
	 */
	private Long repayTime;
	
	private String sn;
	
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getRepayTime() {
		return repayTime;
	}
	public void setRepayTime(Long repayTime) {
		this.repayTime = repayTime;
	}
	public Integer getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}
}
