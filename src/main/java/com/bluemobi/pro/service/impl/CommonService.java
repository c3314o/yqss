package com.bluemobi.pro.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bluemobi.pro.entity.Address;
import com.bluemobi.pro.entity.Bank;
import com.bluemobi.pro.entity.Contants;
import com.bluemobi.pro.entity.Help;
import com.bluemobi.pro.entity.Message;
import com.bluemobi.pro.entity.Qanda;
import com.bluemobi.pro.entity.Report;
import com.bluemobi.pro.entity.UserInfo;
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
	public static final String PRIFIX_HELPER = Help.class.getName();
	public static final String PRIFIX_MESSAGE = Message.class.getName();
	public static final String PRIFIX_CONTANTS = Contants.class.getName();
	public static final String PRIFIX_REPORT = Report.class.getName();
	public static final String PRIFIX_ADDRESS = UserInfo.class.getName();
	
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
		return this.getBaseDao().getList(PRIFIX_BANK + ".findAll",null);
	}
	
	
	public Help findAllHelper() throws Exception {
		Help help = new Help();
		Map<String, Object> commons = this.getBaseDao().getObject(PRIFIX_HELPER + ".findHelper",null);
		List<Qanda> list = this.getBaseDao().getList(PRIFIX_HELPER + ".findInnerHelper");
		if(help != null) {
			help.setId(Integer.parseInt(commons.get("id").toString()));
			help.setQq(commons.get("qq").toString());
			help.setPhone(commons.get("phone").toString());
			help.setList(list);
		}
		return help;
	}
	
	/**
	 * 查询用户是否有通讯录的记录
	 * @return
	 * @throws Exception 
	 */
	public int countUserContants(Contants c) throws Exception {
		return this.getBaseDao().get(PRIFIX_CONTANTS + ".findByUserId", c);
	}
	
	/**
	 * 保存联系人
	 * @param c
	 * @throws Exception
	 */
	public void insertContants(Contants c) throws Exception {
		this.getBaseDao().save(PRIFIX_CONTANTS + ".insert", c);
	}
	
	/**
	 * 创建举报信息
	 * @param report
	 * @throws Exception
	 */
	public void insertReport(Report report) throws Exception {
		this.getBaseDao().save(PRIFIX_REPORT + ".insertReport", report);
	}
	
	// 根据区ID查询街道
	public List<Map<String,Object>> findStreet(Map<String,Object> params) throws Exception {
		return this.getBaseDao().getList(PRIFIX_ADDRESS + ".findStreet", params);
	}
}
