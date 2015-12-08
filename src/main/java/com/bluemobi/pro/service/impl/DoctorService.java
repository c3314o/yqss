package com.bluemobi.pro.service.impl;


import java.util.Map;

import org.springframework.stereotype.Service;

import com.bluemobi.pro.entity.Doctor;
import com.bluemobi.sys.page.Page;
import com.bluemobi.sys.service.BaseService;

@Service
public class DoctorService extends BaseService{

	private static final String PRIFIX = DoctorService.class.getName();
	
	/**
	 * 查询医生列表
	 * @param doctor
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<Doctor> findDoctorList(Map<String,Object> params,Integer currentPage,Integer pageSize) throws Exception {
		return this.getBaseDao().page(PRIFIX + ".page", params, currentPage, pageSize);
	}
	
	/**
	 * 查询医生详情
	 * @param doctor
	 * @return
	 * @throws Exception
	 */
	public Doctor findDoctorDetail(Doctor doctor) throws Exception {
		return this.getBaseDao().getObject(PRIFIX + ".findDoctorById", doctor);
	}
}