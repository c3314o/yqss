package com.bluemobi.pro.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.pro.entity.Doctor;
import com.bluemobi.pro.service.impl.DoctorService;
import com.bluemobi.sys.page.Page;
import com.bluemobi.utils.Result;

/**
 * 医生controller
 * @author yesong
 *
 */
@RequestMapping("/app/doctor/")
@Controller
public class DoctorApp {

	@Autowired
	private DoctorService service;
	
	/**
	 * 查询医生列表
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "page", method = RequestMethod.POST)
	@ResponseBody
	public Result page(@RequestParam Map<String,Object> params) {
		
		Integer currentPage = params.get("pageNum") == null ? 1 : Integer.parseInt(params.get("pageNum").toString());
		Integer pageSize = params.get("pageSize") == null ? 10 : Integer.parseInt(params.get("pageSize").toString());
		Page<Doctor> page = null;
		try {
			page = service.findDoctorList(params, currentPage, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(page);
	}
	
	/**
	 * 查询医生详细信息
	 * @param doctor
	 * @return
	 */
	@RequestMapping(value = "detail", method = RequestMethod.POST)
	@ResponseBody
	public Result detail(final Doctor doctor) {
		Doctor doctor2 = null;
		try {
			doctor2 = service.findDoctorDetail(doctor);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(doctor2);
	}
}
