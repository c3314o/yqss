package com.bluemobi.pro.service.impl;

import org.springframework.stereotype.Service;

import com.bluemobi.pro.entity.FeedBack;
import com.bluemobi.sys.service.BaseService;
import com.bluemobi.utils.DateUtils;
import com.bluemobi.utils.UUIDUtil;

@Service
public class FeedBackService extends BaseService{

	private static final String PRIFIX = FeedBackService.class.getName();
	
	/**
	 * 新增一键反馈
	 * @param feedBack
	 * @throws Exception
	 */
	public void insert(FeedBack feedBack) throws Exception {
		feedBack.setId(UUIDUtil.generateUUID());
		feedBack.setCreateDate(DateUtils.getCurrentTime());
		this.getBaseDao().save(PRIFIX + ".insert", feedBack);
	}
}
