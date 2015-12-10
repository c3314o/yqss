package com.bluemobi.pro.entity;

/**
 * 
 * @ClassName: ProductBorrowRepayRecord
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2015年12月10日
 *
 */
public class ProductBorrowRepayRecord extends BaseEntity {

	/**
	 * 还款金额
	 */
	private Double amount;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
