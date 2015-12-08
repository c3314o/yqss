package com.bluemobi.pro.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.pro.entity.Pathology;
import com.bluemobi.pro.service.impl.PathologyService;
import com.bluemobi.sys.page.Page;
import com.bluemobi.utils.Result;

/**
 * 讲座controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/app/lecture/")
public class LectureApp {

	@Autowired
	private PathologyService service;
	
	/**
	 * 讲座列表
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "page", method = RequestMethod.POST)
	@ResponseBody
	public Result page(@RequestParam Map<String,Object> params) {
		
		Integer currentPage = params.get("pageNum") == null ? 1 : Integer.parseInt(params.get("pageNum").toString());
		Integer pageSize = params.get("pageSize") == null ? 10 : Integer.parseInt(params.get("pageSize").toString());
		
		Page<Pathology> page = null;
		try {
			params.put("type", "1"); // 设置类型为讲座
			page = service.page(params, currentPage, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(page);
	}
	
	/**
	 * 查询讲座详细信息
	 * @param pathology
	 * @return
	 */
	@RequestMapping(value = "detail", method = RequestMethod.POST)
	@ResponseBody
	public Result detail(final Pathology pathology) {
		
		Pathology pathology2 = null;
		try {
			pathology2 = service.findById(pathology2);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(pathology2);
	}
}
