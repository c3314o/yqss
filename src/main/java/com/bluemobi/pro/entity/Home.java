package com.bluemobi.pro.entity;

/**
 * 
 * @ClassName: Home
 * @Description: 首页信息
 * @author yesong
 * @date 2015年12月14日
 *
 */
public class Home {

	private int id;

	private int available;

	private int borrowday;

	private double amount;

	private int surplusday;

	public int getAvailable() {
		return available;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getBorrowday() {
		return borrowday;
	}

	public void setBorrowday(int borrowday) {
		this.borrowday = borrowday;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public int getSurplusday() {
		return surplusday;
	}

	public void setSurplusday(int surplusday) {
		this.surplusday = surplusday;
	}
}
