package com.bluemobi.pro.entity;

/**
 * 
 * @ClassName: ProductBorrowRepayRecord
 * @Description: 商品分期还款记录
 * @author yesong
 * @date 2015年12月10日
 *
 */
public class ProductBorrowRepayRecord extends BaseEntity {

	/**
	 * 还款金额
	 */
	private Double amount;
	
	/**
	 * 所属贷款信息
	 */
	private Integer pdId;
	
	private String sn;
	
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getPdId() {
		return pdId;
	}

	public void setPdId(Integer pdId) {
		this.pdId = pdId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
