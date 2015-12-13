package com.bluemobi.pro.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bluemobi.pro.entity.Product;
import com.bluemobi.pro.entity.ProductComment;
import com.bluemobi.pro.entity.SecondHandProduct;
import com.bluemobi.sys.page.Page;
import com.bluemobi.sys.service.BaseService;

@Service
public class ProductService extends BaseService{

	public static final String PRIFIX_SECOND_HAND = SecondHandProduct.class.getName();
	public static final String PRIFIX_PRODUCT = Product.class.getName();
	
	/**
	 * 查询二手商品
	 * @param shp
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Page<SecondHandProduct> findSecondHandProduct(SecondHandProduct shp,Integer currentPage,Integer pageSize) throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userId", shp.getUserId());
		return this.getBaseDao().page(PRIFIX_SECOND_HAND + ".findSecondProduct", paramMap, currentPage, pageSize);
	}
	
	/**
	 * 查询二手商品详细信息
	 * @param shp
	 * @return
	 * @throws Exception
	 */
	public SecondHandProduct findDetail(SecondHandProduct shp) throws Exception {
		return this.getBaseDao().get(PRIFIX_SECOND_HAND + ".findSecondProduct", shp);
	}
	
	/**
	 * 查询商品
	 * @param shp
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Page<Product> findProduct(Product product,Integer currentPage,Integer pageSize) throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("title", product.getTitle());
		return this.getBaseDao().page(PRIFIX_PRODUCT + ".PRIFIX_PRODUCT", paramMap, currentPage, pageSize);
	}
	
	/**
	 * 查询商品详细信息
	 * @param shp
	 * @return
	 * @throws Exception
	 */
	public Product findProductDetail(Product product) throws Exception {
		return this.getBaseDao().get(PRIFIX_PRODUCT + ".PRIFIX_PRODUCT", product);
	}
	
	/**
	 * 查询二手商品评论
	 * 翻页
	 * @return
	 * @throws Exception
	 */
	public Page<ProductComment> findProductComment(ProductComment comment,Integer currentPage,Integer pageSize) throws Exception {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("productId", comment.getProductId());
		return this.getBaseDao().page(PRIFIX_SECOND_HAND + ".findProductComment", paramMap, currentPage, pageSize);
	}
	
	/**
	 * 评新增二手商品评论
	 * @throws Exception
	 */
	public void commentProduct(ProductComment comment) throws Exception {
		this.getBaseDao().save(PRIFIX_SECOND_HAND + ".insertComment", comment);
	}
}
