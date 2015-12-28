package com.bluemobi.pro.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bluemobi.pro.entity.Collection;
import com.bluemobi.pro.entity.Image;
import com.bluemobi.pro.entity.Product;
import com.bluemobi.pro.entity.ProductComment;
import com.bluemobi.pro.entity.ProductImage;
import com.bluemobi.pro.entity.SecondHandProduct;
import com.bluemobi.sys.page.Page;
import com.bluemobi.sys.service.BaseService;
import com.bluemobi.utils.ImageUtils;

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
	@Transactional
	public void insertSHProduct(SecondHandProduct shp,MultipartHttpServletRequest fileRequest) throws Exception {
		
		this.getBaseDao().save(PRIFIX_SECOND_HAND + ".insertSHProduct", shp);
		
		Iterator<String> fileNames =  fileRequest.getFileNames();
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile imageFile = (MultipartFile) fileRequest.getFile(fileName);
			Image image = ImageUtils.saveImage(imageFile, false);
			ProductImage pImage = new ProductImage();
			pImage.setImage(image.getImage());
			pImage.setWidth(image.getWidth());
			pImage.setHeight(image.getHeight());
			pImage.setProductId(shp.getId());
			
			insertProductImage(pImage);
		}
	}
	
	public int insertProductImage(ProductImage image) throws Exception {
		return this.getBaseDao().save(PRIFIX_SECOND_HAND + ".insertImage", image);
	}
	
	/**
	 * 
     * @Title: batchDeleteProductImage
     * @Description: 批量删除图片
     * @param @param imagesIds
     * @param @return
     * @param @throws Exception    参数
     * @return int    返回类型
     * @throws
	 */
	public int batchDeleteProductImage(String imagesIds) throws Exception {
		int offest = 0;
		String[] ids = imagesIds.split(",");
		for (String id : ids) {
			deleteProductImage(id);
			++offest;
		}
		return offest;
	}
	
	public int deleteProductImage(String imageId ) throws Exception {
		return this.getBaseDao().delete(PRIFIX_SECOND_HAND + ".deleteImage", imageId);
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
	 * 删除二手商品 
	 * @param shp
	 * @throws Exception
	 */
	public void deleteSHProduct(SecondHandProduct shp) throws Exception {
		this.getBaseDao().delete(PRIFIX_SECOND_HAND + ".deleteSHProduct", shp);
	}
	
	/**
	 * 更新二手商品信息
	 * @param shp
	 * @throws Exception
	 */
	public void updateSHProduct(SecondHandProduct shp,MultipartHttpServletRequest fileRequest) throws Exception {
		shp.setCreateDate(System.currentTimeMillis());
		this.getBaseDao().update(PRIFIX_SECOND_HAND + ".updateSHProduct", shp);
		
		Iterator<String> fileNames =  fileRequest.getFileNames();
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile imageFile = (MultipartFile) fileRequest.getFile(fileName);
			Image image = ImageUtils.saveImage(imageFile, false);
			ProductImage pImage = new ProductImage();
			pImage.setImage(image.getImage());
			pImage.setWidth(image.getWidth());
			pImage.setHeight(image.getHeight());
			pImage.setProductId(shp.getProductId());
			
			insertProductImage(pImage);
		}
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
		paramMap.put("userId", comment.getFromUserId());
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
		paramMap.put("userId", product.getUserId());
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
	 * 
     * @Title: collectProduct
     * @Description: 收藏商品
     * @param @param collection
     * @param @return
     * @param @throws Exception    参数
     * @return int    返回类型
     * @throws
	 */
	public int collectProduct(Collection collection) throws Exception {
		Collection _collect = this.getBaseDao().getObject(PRIFIX_PRODUCT + ".findProductCollect", collection);
		if(_collect != null && _collect.getUserId() != null) {
			return -1;
		}
		collection.setCreateDate(System.currentTimeMillis());
		return this.getBaseDao().save(PRIFIX_PRODUCT + ".insertCollect", collection);
	}
	
	/**
	 * 
     * @Title: unCollectProduct
     * @Description: 取消商品收藏
     * @param @param collection
     * @param @return
     * @param @throws Exception    参数
     * @return int    返回类型
     * @throws
	 */
	public int unCollectProduct(Collection collection) throws Exception {
		return this.getBaseDao().save(PRIFIX_PRODUCT + ".deleteCollect", collection);
	}
	
	/**
	 * 
     * @Title: findUserCollect
     * @Description: 查询用户收藏记录
     * @param @param collection
     * @param @return
     * @param @throws Exception    参数
     * @return List<Collection>    返回类型
     * @throws
	 */
	public List<Collection> findUserCollect(Collection collection) throws Exception {
		return this.getBaseDao().getList(PRIFIX_PRODUCT + ".findProductCollect", collection);
	}
}
