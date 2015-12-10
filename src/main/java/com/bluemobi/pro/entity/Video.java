package com.bluemobi.pro.entity;

/**
 * 
 * @ClassName: Video
 * @Description: 视频
 * @author yesong
 * @date 2015年12月10日
 *
 */
public class Video extends BaseEntity {

	/**
	 * 视频标题
	 */
	private String title;

	/**
	 * 视频介绍
	 */
	private String content;
	
	/**
	 * 视频背景图片
	 */
	private String image;

	/**
	 * 视频地址
	 */
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
