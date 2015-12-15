package com.bluemobi.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluemobi.pro.entity.Product;
import com.bluemobi.pro.entity.ProductBorrow;
import com.bluemobi.pro.entity.ProductBorrowRepayRecord;
import com.bluemobi.sys.service.BaseService;
import com.bluemobi.utils.DateUtils;
import com.bluemobi.utils.YqssUtils;

/**
 * 
 * @ClassName: BorrowService
 * @Description: 借款service
 * @author yesong
 * @date 2015年12月10日
 *
 */
@Service
public class ProductBorrowService extends BaseService {

	@Autowired
	private ProductService service;
	
	public static final String PRIFIX = ProductBorrow.class.getName();
	
	/**
     * @Title: borrow
     * @Description:  借款-填写
     * @param @return
     * @param @throws Exception    参数
     * @return int    返回类型
     * @throws
	 */
	public int borrowProduct(ProductBorrow pb) throws Exception {
		
		Product product = new Product();
		product.setUserId(pb.getUserId());
		product.setId(pb.getProductId());
		Product _product = service.findProductDetail(product);
		String image = (_product.getImageList() != null && _product.getImageList().size() > 0 ? product.getImageList().get(0).getImage() :"");
		
		pb.setPrice(product.getPrice());
		pb.setProductName(_product.getTitle());
		pb.setPic(image);
		pb.setNextDate(YqssUtils.firstResidueDay());
		
		return this.getBaseDao().save(PRIFIX + ".insertBrrow", pb);
	}
	
	/**
	 * 
     * @Title: findBorrowByUserId
     * @Description: 查询用户借款信息
     * @param @param userInfo
     * @param @return
     * @param @throws Exception    参数
     * @return List<BorrowInfo>    返回类型
     * @throws
	 */
	public List<ProductBorrow> findBorrowByUserId(ProductBorrow pb) throws Exception{
		
		
		return this.getBaseDao().getList(PRIFIX + ".findByUserId", pb.getUserId());
	}
	
	/**
	 * 
     * @Title: repay
     * @Description: 还款
     * @param @throws Exception    参数
     * @return void    返回类型
     * @throws
	 */
	@Transactional
	public void repay(ProductBorrowRepayRecord pbrr) throws Exception {
		ProductBorrow pb = new ProductBorrow();
		pb.setId(pbrr.getPdId());
		pb.setNextDate(YqssUtils.nextResidueDay(DateUtils.getCurrentTime()));
		
		this.getBaseDao().update(PRIFIX + ".update", pb);
		this.getBaseDao().save(PRIFIX + ".insertRecord", pbrr);
	}
	
	/**
	 * 
     * @Title: delete
     * @Description: 删除贷款记录
     * @param @param pb
     * @param @throws Exception    参数
     * @return void    返回类型
     * @throws
	 */
	@Transactional
	public void delete(ProductBorrow pb) throws Exception {
		this.getBaseDao().delete(PRIFIX + ".delete", pb.getId());
		this.getBaseDao().delete(PRIFIX + "deleteRecord", pb.getId());
	}
}
