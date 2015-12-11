package com.bluemobi.pro.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bluemobi.pro.entity.Bank;
import com.bluemobi.sys.service.BaseService;

/**
 * 
 * @ClassName: CommonService
 * @Description: 一些基本信息service
 * @author Administrator
 * @date 2015年12月11日
 *
 */
@Service
public class CommonService extends BaseService {

	public static final String PRIFIX_BANK = Bank.class.getName();
	
	/**
	 * 查询所有银行
     * @Title: findAllBank
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @return
     * @param @throws Exception    参数
     * @return List<Bank>    返回类型
     * @throws
	 */
	public List<Bank> findAllBank() throws Exception {
		return this.getBaseDao().getList(PRIFIX_BANK + ".findAll");
	}
}
