package com.bluemobi.pro.entity;

import java.util.ArrayList;
import java.util.List;

import com.bluemobi.utils.DBUtils;
import com.bluemobi.utils.YqssUtils;

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
	private String nextDate;
	
	/**
	 * 商品名字
	 */
	private String productName;
	
	/**
	 * 商品图片
	 */
	private String pic;
	
	private double price;
	
	/**
	 * 利率
	 */
	private double rate = DBUtils.getRate();
	
	/**
	 * 利息
	 */
	private double all;
	
	/**
	 * 每期还的金额
	 */
	private double once;
	
	/**
	 * 月供
	 */
	private double installment;
	
	/**
	 * 剩余还款金额
	 */
	private double surplus;
	
	/**
	 * 剩余期数
	 */
	private Integer period;
	
	/**
	 * 剩余期数
	 */
	private Integer surplusStages;
	
	private Integer state;// 还款状态  0 已结清  1  还款中  2  催款中”
	
	private Integer surplusDays;
	
	// =======================================================
	private String name;
	private String identity;
	private String mobile;
	private String school;
	private String address;
	private Integer flag ;
	private Integer stage;
	private Integer type;
	
	public Integer getSurplusDays() {
		surplusDays = YqssUtils.residueDay(nextDate);
		return surplusDays;
	}

	public void setSurplusDays(Integer surplusDays) {
		this.surplusDays = surplusDays;
	}

	/**
	 * 还款记录
	 */
	private List<ProductBorrowRepayRecord> list = new ArrayList<ProductBorrowRepayRecord>();

	public double getInstallment() {
		installment = YqssUtils.numberFormat( YqssUtils.countRate0(nextDate,this.getStage(), this.getPrice()));
		return installment;
	}

	public void setInstallment(double installment) {
		this.installment = installment;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getState() {
		int allReypayMoney = 0;
		for (ProductBorrowRepayRecord pbrr : list) {
			allReypayMoney += pbrr.getAmount();
		}
		int residueDays = YqssUtils.residueDay(nextDate);
		if( residueDays <= 0) {
			state = 2;
		}
		else if( price <=  allReypayMoney){
			state = 0;
		}
		else if(price > allReypayMoney) {
			state = 1;
		}
		return state;
	}
	
	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public void setState(Integer state) {
		this.state = state;
	}

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

	public String getNextDate() {
		return nextDate;
	}

	public void setNextDate(String nextDate) {
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

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getAll() {
		all = YqssUtils.getInterest(nextDate, this.getPrice(), rate, getStage());
//		all = getSurplus() % (rate / 100);
		return all;
	}

	public void setAll(double all) {
		this.all = all;
	}

	public double getOnce() {
		once = YqssUtils.countRate(rate, stage, price);
		return once;
	}

	public void setOnce(double once) {
		this.once = once;
	}

	public double getSurplus() {
		double rrMoney = 0.0;
		for (ProductBorrowRepayRecord pbrr : list) {
			rrMoney += pbrr.getAmount();
		}
		surplus = price - rrMoney;
		return surplus;
	}

	public void setSurplus(double surplus) {
		this.surplus = surplus;
	}

	public Integer getSurplusStages() {
		surplusStages = period;
		return surplusStages;
	}

	public void setSurplusStages(Integer surplusStages) {
		this.surplusStages = surplusStages;
	}
}
