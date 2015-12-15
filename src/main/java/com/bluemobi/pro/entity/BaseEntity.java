package com.bluemobi.pro.entity;

public class BaseEntity {

	private Integer id;
	
	private Long createDate = System.currentTimeMillis();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

}
