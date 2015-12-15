package com.bluemobi.pro.entity;

/**
 * 
 * @ClassName: HrMessage
 * @Description: 人力资源信息
 * @author yesong
 * @date 2015年12月10日
 *
 */
public class HrMessage extends BaseEntity {

	/**
	 * 工作地址
	 */
	private String address;
	
	/**
	 * 工资（下限）
	 */
	private Double salaryMin;
	
	/**
	 * 工资 (上限)
	 */
	private Double salaryMax;

	/**
	 * 招聘标题
	 */
	private String title;
	
	/**
	 * 招聘单位
	 */
	private String company;

	/**
	 * 招聘岗位
	 */
	private String station;

	/**
	 * 招聘人数
	 */
	private Integer num;

	/**
	 * 联系电话
	 */
	private String phone;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Double getSalaryMin() {
		return salaryMin;
	}

	public void setSalaryMin(Double salaryMin) {
		this.salaryMin = salaryMin;
	}

	public Double getSalaryMax() {
		return salaryMax;
	}

	public void setSalaryMax(Double salaryMax) {
		this.salaryMax = salaryMax;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
