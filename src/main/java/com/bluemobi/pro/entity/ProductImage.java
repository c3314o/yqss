package com.bluemobi.pro.entity;

/**
 * 
 * @ClassName: ProductImage
 * @Description: 商品图片
 * @author yesong
 * @date 2015年12月10日
 *
 */
public class ProductImage extends Image {

	/**
	 * 所属商品ID
	 */
	private Integer productId;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
}
