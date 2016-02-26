package com.bluemobi.pro.service.impl;

import org.springframework.stereotype.Service;

import com.bluemobi.pro.entity.BorrowRelevanceInfo;
import com.bluemobi.sys.service.BaseService;

@Service
public class BorrowRIService extends BaseService{

	private static final String PRIFIX = BorrowRelevanceInfo.class.getName();
	
	public void insert(BorrowRelevanceInfo bri) throws Exception {
		this.getBaseDao().save(PRIFIX + ".insert", bri);
	}
}
