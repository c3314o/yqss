package com.bluemobi.pro.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.pro.entity.Product;
import com.bluemobi.pro.entity.ProductComment;
import com.bluemobi.pro.entity.SecondHandProduct;
import com.bluemobi.pro.service.impl.ProductService;
import com.bluemobi.sys.page.Page;
import com.bluemobi.utils.Result;

/**
 * 商品controller
 * @author yesong
 *
 */
@Controller
@RequestMapping("/app/product/")
public class ProductApp {

	@Autowired
	private ProductService service;
	
	/**
	 * 查询商品列表
	 * 翻页
	 * @param product
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Result findProduct(Product product,@RequestParam(value = "pageNum",required = false) Integer pageNum,
										@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		pageNum =( pageNum == null ? 0 : pageNum - 1);
		pageSize = ( pageSize == null ? 10 : pageSize );
		
		Page<Product> page = null;
		try {
			page = service.findProduct(product, pageNum, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(page);
	}
	
	/**
	 * 查询商品详情
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "detail", method = RequestMethod.POST)
	@ResponseBody
	public Result findProductDetail(final Product product) {
		
		Product _product = null;
		try {
			_product = service.findProductDetail(product);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(_product);
	}
	
	/**
	 * 
     * @Title: publishSHProduct
     * @Description: 发布二手商品
     * @param @param shp
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	@RequestMapping(value = "second/publish", method = RequestMethod.POST)
	@ResponseBody
	public Result publishSHProduct(SecondHandProduct shp) {
		
		try {
			service.insertSHProduct(shp);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success();
	}
	
	/**
	 * 查询二手商品
	 * 翻页
	 * @param shp
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "second/list", method = RequestMethod.POST)
	@ResponseBody
	public Result findSHProduct(SecondHandProduct shp,@RequestParam(value = "pageNum",required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		
		pageNum =( pageNum == null ? 0 : pageNum - 1);
		pageSize = ( pageSize == null ? 10 : pageSize );
		
		Page<SecondHandProduct>  page = null;
		try {
			page = service.findSecondHandProduct(shp, pageNum, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(page);
	}
	
	/**
	 * 查询二手商品详情
	 * @param shp
	 * @return
	 */
	@RequestMapping(value = "second/detail", method = RequestMethod.POST)
	@ResponseBody
	public Result findSHProductDetail(final SecondHandProduct shp) {
		
		SecondHandProduct _shp = null;
		try {
			_shp = service.findDetail(shp);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(_shp);
	}
	
	/**
	 * 
     * @Title: deleteSHProduct
     * @Description: 删除我的发布
     * @param @param shp
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	@RequestMapping(value = "second/delete", method = RequestMethod.POST)
	@ResponseBody
	public Result deleteSHProduct(SecondHandProduct shp) {
		
		try {
			service.deleteSHProduct(shp);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success();
	}
	
	/**
	 * 评论二手商品
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "second/comment", method = RequestMethod.POST)
	@ResponseBody
	public Result commentProduct(ProductComment comment) {
		
		try {
			service.commentProduct(comment);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success();
	}
	
	/**
	 * 查询二手商品评论翻页
	 * @param comment
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "second/comment/list", method = RequestMethod.POST)
	@ResponseBody
	public Result findSHProductComment(ProductComment comment,@RequestParam(value = "pageNum",required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
	
		pageNum =( pageNum == null ? 0 : pageNum - 1);
		pageSize = ( pageSize == null ? 10 : pageSize );
		
		Page<ProductComment> page = null;
		try {
			page = service.findProductComment(comment, pageNum, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(page);
	}
}
