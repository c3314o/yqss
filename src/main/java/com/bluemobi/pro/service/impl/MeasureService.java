package com.bluemobi.pro.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bluemobi.pro.entity.Measure;
import com.bluemobi.sys.service.BaseService;
import com.bluemobi.utils.UUIDUtil;

/**
 * 测量service
 * @author yesong
 *
 */
@Service
public class MeasureService extends BaseService{

	private static final String PRIFIX = MeasureService.class.getName();
	
	/**
	 * 新增测量结果
	 * @param measure
	 * @throws Exception
	 */
	public void insertMeasureValue(Measure measure) throws Exception {
		measure.setMeasureId(UUIDUtil.generateUUID());
		measure.setBak1("1");
		measure.setBak6(new Timestamp(new Date().getTime()));
		this.getBaseDao().save(PRIFIX + ".insert", measure);
	}
	
	@SuppressWarnings("rawtypes")
	public List findMeasureValue(Measure measure) throws Exception {
		return this.getBaseDao().getList(PRIFIX + ".findMeasureValue", measure);
	}
	
	public void deleteMeasureValue(String measureId) throws Exception {
		this.getBaseDao().delete(PRIFIX + ".deleteById", measureId);
	}
}
