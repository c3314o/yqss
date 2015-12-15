package com.bluemobi.pro.entity;

/**
 * 
 * @ClassName: Collection
 * @Description: 收藏
 * @author yesong
 * @date 2015年12月15日
 *
 */
public class Collection {

	private Integer id;
	private Integer userId;
	private Integer productId;

	private Long createDate;
	
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
}
