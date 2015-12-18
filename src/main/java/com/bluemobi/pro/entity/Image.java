package com.bluemobi.pro.entity;

/**
 * 
 * @ClassName: Image
 * @Description: 图片
 * @author Administrator
 * @date 2015年12月18日
 *
 */
public class Image extends BaseEntity {

	/**
	 * 图片宽度
	 */
	private double width;

	/**
	 * 图片高度
	 */
	private double height;

	/**
	 * 图片链接
	 */
	private String image;

	/**
	 * 缩略图链接
	 */
	private String smallUrl = "";

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSmallUrl() {
		return smallUrl;
	}

	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}

}
