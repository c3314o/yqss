package com.bluemobi.pro.service.impl;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.bluemobi.pro.entity.Pathology;
import com.bluemobi.sys.page.Page;
import com.bluemobi.sys.service.BaseService;

/**
 * 病理service
 * @author yesong
 *
 */
@Service
public class PathologyService extends BaseService{
	
	private static final String PRIFIX = PathologyService.class.getName();
	
	/**
	 * 病理分析列表
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Page<Pathology> page(Map<String,Object> params,Integer currentPage,Integer pageSize) throws Exception {
		return this.getBaseDao().page(PRIFIX + ".page", params, currentPage, pageSize);
	}
	
	/**
	 * 根据ID查询病理详情
	 * @param pathology
	 * @return
	 * @throws Exception
	 */
	public Pathology findById(Pathology pathology) throws Exception {
		return this.getBaseDao().getObject(PRIFIX + ".findById", pathology);
	}
}
