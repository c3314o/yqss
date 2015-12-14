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
	 * 
     * @Title: insertSHProduct
     * @Description: 发布二手商品
     * @param @param shp
     * @param @throws Exception    参数
     * @return void    返回类型
     * @throws
	 */
	public void insertSHProduct(SecondHandProduct shp) throws Exception {
		this.getBaseDao().save(PRIFIX_SECOND_HAND + ".insertSHProduct", shp);
	}
	
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
		paramMap.put("productType", product.getProductType());
		return this.getBaseDao().page(PRIFIX_PRODUCT + ".findProduct", paramMap, currentPage, pageSize);
	}
	
	/**
	 * 查询商品详细信息
	 * @param shp
	 * @return
	 * @throws Exception
	 */
	public Product findProductDetail(Product product) throws Exception {
		return this.getBaseDao().get(PRIFIX_PRODUCT + ".findProduct", product);
	}
	
	
	/**
	 * 删除二手商品 
	 * @param shp
	 * @throws Exception
	 */
	public void deleteSHProduct(SecondHandProduct shp) throws Exception {
		this.getBaseDao().delete(PRIFIX_SECOND_HAND + ".deleteSHProduct", shp.getId());
	}
	
	/**
	 * 更新二手商品信息
	 * @param shp
	 * @throws Exception
	 */
	public void updateSHProduct(SecondHandProduct shp) throws Exception {
		this.getBaseDao().update(PRIFIX_SECOND_HAND + ".updateSHProduct", shp);
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
		if(comment.getToUserId() == null) comment.setToUserId(0);
		this.getBaseDao().save(PRIFIX_SECOND_HAND + ".insertComment", comment);
	}
}
