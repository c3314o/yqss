package com.bluemobi.pro.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bluemobi.utils.DBUtils;
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
	private double money;

	/**
	 * 借款时间
	 */
	private Long jkTime;

	/**
	 * 借款组大天数
	 */
	private Integer timeLimite;

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
	 * 剩余应还金额
	 */
	private Double residueMoney;

	/**
	 * 还款状态 0 已结清 1 还款中 2 催款中
	 */
	private Integer state;

	private double repay;
	
	private double rate = DBUtils.getRate();
	
	private double all;

	// =====================================

	private String name;
	private String identity;
	private String mobile;
	private String school;
	private String address;
	private Integer flag;
	private Integer productId;
	private Integer stage;
	private Integer type;

	private Integer isList;
	
	private List<BorrowRepayRecord> list = new ArrayList<BorrowRepayRecord>();
	
	public Integer getIsList() {
		return isList;
	}

	public void setIsList(Integer isList) {
		this.isList = isList;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getAll() {
		all = YqssUtils.getInterest(lastRepayDate, money, rate, 1);
		return all;
	}

	public void setAll(double all) {
		this.all = all;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getRepay() {
		Double rrMoney = 0.0;
		for (BorrowRepayRecord borrowRepayRecord : list) {
			rrMoney += borrowRepayRecord.getAmount();
		}
		repay = rrMoney;
		return repay;
	}

	public void setRepay(double repay) {
		this.repay = repay;
	}

	public Double getResidueMoney() {
		Double rrMoney = 0.0;
		for (BorrowRepayRecord borrowRepayRecord : list) {
			rrMoney += borrowRepayRecord.getAmount();
		}
		residueMoney = money - rrMoney;
		return residueMoney;
	}

	public void setResidueMoney(Double residueMoney) {
		this.residueMoney = residueMoney;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTimeLimite() {
		return timeLimite;
	}

	public void setTimeLimite(Integer timeLimite) {
		this.timeLimite = timeLimite;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

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
	 * @Title: getTotalDays @Description: 计算借款总天数 @param @return 参数 @return
	 * Integer 返回类型 @throws
	 */
	public Integer getTotalDays() {
		if (jkTime == null)
			return -1;
		totalDays = YqssUtils.totalDays(DateUtils.parse(jkTime, YqssUtils.DEFAULT_FORMAT));
		return totalDays;
	}

	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
	}

	/**
	 * @Title: getResidueDays @Description: 计算剩余天数 @param @return 参数 @return
	 * Integer 返回类型 @throws
	 */
	public Integer getResidueDays() {
		if (StringUtils.isBlank(lastRepayDate))
			return -1;
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Long getJkTime() {
		return jkTime;
	}

	public void setJkTime(Long jkTime) {
		this.jkTime = jkTime;
	}

	/**
	 * 
	 * @Title: getState @Description: 计算还款状态 @param 参数 @return @return Integer
	 * 返回类型 @throws
	 */
	public Integer getState() {
		int allReypayMoney = 0;
		for (BorrowRepayRecord borrowRepayRecord : list) {
			allReypayMoney += borrowRepayRecord.getAmount();
		}
		int residueDays = YqssUtils.residueDay(lastRepayDate);
		if (residueDays <= 0) {
			state = 2;
		} else if (money <= allReypayMoney) {
			state = 0;
		} else if (money > allReypayMoney) {
			state = 1;
		}
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
