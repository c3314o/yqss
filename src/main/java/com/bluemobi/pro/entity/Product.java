package com.bluemobi.pro.entity;

import java.util.List;

import com.bluemobi.constant.ExcludeFile;

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
	 * 商品类型
	 * 
	 */
	private Integer productType;
	
	/**
	 * 商品链接
	 */
	private String url;
	
	private Integer isCollect;
	
	@ExcludeFile
	private Integer userId;
	
	/**
	 * 图片列表
	 */
	private List<ProductImage> imageList;
	
	private List<Stage> stageList;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}
	public List<Stage> getStageList() {
		return stageList;
	}
	public void setStageList(List<Stage> stageList) {
		this.stageList = stageList;
	}
	public Integer getProductType() {
		return productType;
	}
	public void setProductType(Integer productType) {
		this.productType = productType;
	}
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
