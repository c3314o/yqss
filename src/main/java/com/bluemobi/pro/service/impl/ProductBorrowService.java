package com.bluemobi.pro.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		String image = (_product.getImageList() != null && _product.getImageList().size() > 0 ? _product.getImageList().get(0).getImage() :"");
		
		pb.setPrice(_product.getPrice());
		pb.setProductName(_product.getTitle());
		pb.setPic(image);
		pb.setNextDate(YqssUtils.nextResidueDay(new Date()));
		
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
		return this.getBaseDao().getList(PRIFIX + ".findByUserId", pb);
	}
	
	/**
	 * 
     * @Title: findBorrowById
     * @Description: 购物借款详情
     * 本月还款数
     * 上月是否有未还的金额
     * 1.有：上月的应还的金额加上利息
     * 
     * @param @param pb
     * @param @return
     * @param @throws Exception    参数
     * @return ProductBorrow    返回类型
     * @throws
	 */
	public ProductBorrow findBorrowById(ProductBorrow pb) throws Exception {
		ProductBorrow productBorrow = this.getBaseDao().get(PRIFIX + ".findByUserId", pb);
		
		if(productBorrow != null) {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("pdId", productBorrow.getId());
			List<ProductBorrowRepayRecord> pbrrList = this.getBaseDao().getList(PRIFIX + ".findRecordByPbId", paramMap);
			productBorrow.setList(pbrrList);
		}
		return productBorrow;
	}
	
	/**
	 * 
     * @Title: findIsBorrow
     * @Description: 查询用户本月是否还过款
     * @param @return
     * @param @throws Exception    参数
     * @return int    返回类型
     * @throws
	 */
	public int findIsBorrow(ProductBorrowRepayRecord pbrr) throws Exception {
		return this.getBaseDao().getObject(PRIFIX + ".findIsBorrow", pbrr);
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
	public int repay(ProductBorrowRepayRecord pbrr) throws Exception {
		return this.getBaseDao().save(PRIFIX + ".insertRecord", pbrr);
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
	
	/**
	 * 
     * @Title: getIdBySn
     * @Description: 根据订单号获取ID
     * @param @param sn
     * @param @return
     * @param @throws Exception    参数
     * @return int    返回类型
     * @throws
	 */
	public int getIdBySn(String sn) throws Exception {
		return this.getBaseDao().getObject(PRIFIX + ".getIdBySn", sn);
	}
	
	/**
	 * 
     * @Title: repayFinish
     * @Description: 支付完成修改金额
     * @param @param brr
     * @param @throws Exception    参数
     * @return void    返回类型
     * @throws
	 */
	public void repayFinish(ProductBorrowRepayRecord brr) throws Exception {
		ProductBorrowRepayRecord _brr = this.getBaseDao().getObject(PRIFIX + ".getIdBySn", brr.getSn());
		
		ProductBorrow pb = new ProductBorrow();
		pb.setId(_brr.getPdId());
		pb = findBorrowById(pb);
		System.out.println(_brr.getAmount());
		if(_brr.getAmount() != null && _brr.getAmount().doubleValue() == 0) {
			System.out.println("=============repayFinishPb============");
			pb.setNextDate(YqssUtils.nextResidueDay(pb.getNextDate()));
			if(findIsBorrow(_brr) == 1 && _brr.getAmount() == 0) {
				pb.setPeriod(1);
			}
			this.getBaseDao().update(PRIFIX + ".update", pb);
			this.getBaseDao().update(PRIFIX + ".repayFinish", brr);
		}
	}
	
	/**
	 * 判断还款日期是否在本月
	 * @param pbrr
	 * @return
	 */
	public boolean isThisMonth(ProductBorrowRepayRecord pbrr) {
		try {
			ProductBorrow pb = new ProductBorrow();
			pb.setId(pbrr.getPdId());
			pb = findBorrowById(pb);
			
			if(pb != null) {
				String nextDate = pb.getNextDate();
				List<ProductBorrowRepayRecord> recordList = pb.getList();
				if(recordList != null && recordList.size() > 0) {
					for (ProductBorrowRepayRecord _record : recordList) {
						double amount = _record.getAmount();
						long repayDate = _record.getCreateDate();
						if(amount != 0 && YqssUtils.isThisMonth(repayDate, nextDate, 1)) {
							return true;
						} 
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
