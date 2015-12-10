package com.bluemobi.pro.entity;

/**
 * 
 * @ClassName: ProductImage
 * @Description: 商品图片
 * @author yesong
 * @date 2015年12月10日
 *
 */
public class ProductImage extends BaseEntity {

	/**
	 * 所属商品ID
	 */
	private Integer productId;
	
	/**
	 * 图片路径
	 */
	private String image;
	
	/**
	 * 图片高度
	 */
	private Double height;
	
	/**
	 * 图片宽度
	 */
	private Double width;
	
	/**
	 * 缩略图路径
	 */
	private String small_url;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public String getSmall_url() {
		return small_url;
	}
	public void setSmall_url(String small_url) {
		this.small_url = small_url;
	}
}
