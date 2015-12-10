package com.bluemobi.pro.entity;

/**
 * 
 * @ClassName: UserBankCard
 * @Description: 用户银行卡号
 * @author yesong
 * @date 2015年12月10日
 *
 */
public class UserBankCard extends BaseEntity {
	
	/**
	 * 银行名称
	 */
	private String bank;
	
	/**
	 * 银行卡号
	 */
	private String member;

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}
	
	
}
