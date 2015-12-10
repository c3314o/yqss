package com.bluemobi.pro.entity;

/**
 * 
 * @ClassName: Collect
 * @Description: 用户收藏
 * @author yesong
 * @date 2015年12月10日
 *
 */
public class Collect extends BaseEntity {

	/**
	 * 收藏商品名称
	 */
	private String collectTitle;
	
	/**
	 * 商品价格
	 */
	private Double price;
	
	/**
	 * 商品图片
	 */
	private String pic;
	
	
	public String getCollectTitle() {
		return collectTitle;
	}
	public void setCollectTitle(String collectTitle) {
		this.collectTitle = collectTitle;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
}
