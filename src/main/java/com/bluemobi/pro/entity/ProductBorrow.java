package com.bluemobi.pro.entity;

import java.util.List;

/**
 * 
 * @ClassName: ProductBorrow
 * @Description: 商品借款
 * @author yesong
 * @date 2015年12月10日
 *
 */
public class ProductBorrow extends BaseEntity {
	
	/**
	 * 所属用户ID
	 */
	private Integer userId;
	
	/**
	 * 商品ID
	 */
	private Integer productId;
	
	/**
	 * 下次还款时间
	 */
	private Long nextDate;
	
	/**
	 * 商品名字
	 */
	private String name;
	
	/**
	 * 商品图片
	 */
	private String pic;
	
	/**
	 * 利率
	 */
	private Double rate;
	
	/**
	 * 利息
	 */
	private Double all;
	
	/**
	 * 每期还的金额
	 */
	private Double once;
	
	/**
	 * 剩余还款金额
	 */
	private Double surplus;
	
	/**
	 * 金额总数
	 */
	private Double amount;
	
	/**
	 * 剩余期数
	 */
	private Integer surplusStages;
	
	/**
	 * 还款记录
	 */
	private List<ProductBorrowRepayRecord> list;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Long getNextDate() {
		return nextDate;
	}

	public void setNextDate(Long nextDate) {
		this.nextDate = nextDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductBorrowRepayRecord> getList() {
		return list;
	}

	public void setList(List<ProductBorrowRepayRecord> list) {
		this.list = list;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getAll() {
		return all;
	}

	public void setAll(Double all) {
		this.all = all;
	}

	public Double getOnce() {
		return once;
	}

	public void setOnce(Double once) {
		this.once = once;
	}

	public Double getSurplus() {
		return surplus;
	}

	public void setSurplus(Double surplus) {
		this.surplus = surplus;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getSurplusStages() {
		return surplusStages;
	}

	public void setSurplusStages(Integer surplusStages) {
		this.surplusStages = surplusStages;
	}
}
