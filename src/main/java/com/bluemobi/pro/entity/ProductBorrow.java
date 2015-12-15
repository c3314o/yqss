package com.bluemobi.pro.entity;

import java.util.ArrayList;
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
	private String productName;
	
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
	 * 总期数
	 */
	private Integer period;
	
	/**
	 * 剩余期数
	 */
	private Integer surplusStages;
	
	// =======================================================
	private String name;
	private String identity;
	private String mobile;
	private String school;
	private String address;
	private Integer flag ;
	private Integer stage;
	private Integer type;
	
	/**
	 * 还款记录
	 */
	private List<ProductBorrowRepayRecord> list = new ArrayList<ProductBorrowRepayRecord>();
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

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
