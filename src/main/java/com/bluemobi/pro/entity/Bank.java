package com.bluemobi.pro.entity;

import com.bluemobi.constant.ExcludeFile;

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
	private String bankName;
	
	/**
	 * 创建用户
	 */
	@ExcludeFile
	private Integer createUser;
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
}
