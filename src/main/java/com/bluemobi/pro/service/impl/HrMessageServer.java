package com.bluemobi.pro.service.impl;

import org.springframework.stereotype.Service;

import com.bluemobi.pro.entity.HrMessage;
import com.bluemobi.sys.page.Page;
import com.bluemobi.sys.service.BaseService;

/**
 * 
 * @ClassName: HrMessageServer
 * @Description: 人力资源消息
 * @author Administrator
 * @date 2015年12月11日
 *
 */
@Service
public class HrMessageServer extends BaseService {

	public static final String PRIFIX = HrMessage.class.getName();
	
	/**
	 * 
     * @Title: findHrMessage
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param currentPage
     * @param @param pageSize
     * @param @return
     * @param @throws Exception    参数
     * @return Page<HrMessage>    返回类型
     * @throws
	 */
	public Page<HrMessage> findHrMessage(Integer currentPage,Integer pageSize) throws Exception {
		return this.getBaseDao().page(PRIFIX + ".findHrMessage", null, currentPage, pageSize);
	}
}
