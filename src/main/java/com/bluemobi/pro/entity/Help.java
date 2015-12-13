package com.bluemobi.pro.entity;

import java.util.ArrayList;
import java.util.List;

public class Help {

	private Integer id;
	private String qq;
	private String phone;
	
	private List<Qanda> list = new ArrayList<Qanda>();
			
	public List<Qanda> getList() {
		return list;
	}
	public void setList(List<Qanda> list) {
		this.list = list;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
