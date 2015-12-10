package com.bluemobi.pro.entity;

/**
 * 
 * @ClassName: Bank
 * @Description: 银行
 * @author yesong
 * @date 2015年12月10日
 *
 */
public class Bank extends BaseEntity {

	/**
	 * 银行名称
	 */
	private String bank;
	
	/**
	 * 创建用户
	 */
	private Integer createUser;
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	
	
}
