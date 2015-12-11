package com.bluemobi.pro.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluemobi.pro.entity.HrMessage;
import com.bluemobi.pro.service.impl.HrMessageServer;
import com.bluemobi.sys.page.Page;
import com.bluemobi.utils.Result;

/**
 * 
 * @ClassName: HrMessageApp
 * @Description: 人力资料controller
 * @author yesong
 * @date 2015年12月11日
 *
 */
@RequestMapping
@Controller
public class HrMessageApp {
		
	@Autowired
	private HrMessageServer service;
	
	/**
	 * 
     * @Title: findHrMessage
     * @Description: 查询人力资源消息
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	public Result findHrMessage(@RequestParam(value = "pageNum",required = false) Integer pageNum,
											  @RequestParam(value = "pageSize", required = false) Integer pageSize) {
		pageNum = (pageNum == null ? 0 : pageNum -1);
		pageSize = (pageSize == null ? 10 : pageSize);
		
		Page<HrMessage> page = null;
		try {
			page = service.findHrMessage(pageNum, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(page);
	}
}
