package com.bluemobi.pro.entity;

import com.bluemobi.constant.ExcludeFile;

/**
 * 
 * @ClassName: Ads
 * @Description: 广告
 * @author yesong
 * @date 2015年12月14日
 *
 */
public class Ads extends BaseEntity {

	@ExcludeFile
	private String title;
	
	private String content;
	
	private Integer productId;
	
	@ExcludeFile
	private String linkUrl;
	
	private String image;
	
	@ExcludeFile
	private Integer type;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	
	
}
