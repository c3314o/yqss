package com.bluemobi.pro.entity;

import java.util.List;

/**
 * 
 * @ClassName: Product
 * @Description: 商品
 * @author yesong
 * @date 2015年12月10日
 *
 */
public class Product extends BaseEntity {

	/**
	 * 商品名称
	 */
	private String title;
	
	/**
	 * 商品内容
	 */
	private String content;
	
	/**
	 * 商品价格
	 */
	private Double price;
	
	/**
	 * 图片列表
	 */
	private List<ProductImage> imageList;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<ProductImage> getImageList() {
		return imageList;
	}
	public void setImageList(List<ProductImage> imageList) {
		this.imageList = imageList;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
