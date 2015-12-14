package com.bluemobi.pro.entity;

/**
 * 
 * @ClassName: UserBank
 * @Description: 用户绑定银行卡
 * @author yesong
 * @date 2015年12月14日
 *
 */
public class UserBank extends BaseEntity{

	private Integer userId;
	private Integer bankType;
	private String cardNo;
	private String mobile;
	private String bankName;
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getBankType() {
		return bankType;
	}
	public void setBankType(Integer bankType) {
		this.bankType = bankType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
