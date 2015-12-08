package com.bluemobi.pro.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.pro.entity.DetectionA;
import com.bluemobi.pro.entity.DetectionQ;
import com.bluemobi.pro.entity.DetectionT;
import com.bluemobi.pro.service.impl.DetectionService;
import com.bluemobi.sys.page.Page;
import com.bluemobi.utils.Result;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 检测（题目）controller
 * @author yesong
 *
 */
@Controller
@RequestMapping("/app/detection/")
public class DetectionApp {

	@Autowired
	private DetectionService service;
	
	/**
	 * 查询题目列表集合
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "tPage", method = RequestMethod.POST)
	@ResponseBody
	public Result detectionTpage(@RequestParam Map<String,Object> params) {
		
		Integer currentPage = params.get("pageNum") == null ? 1 : Integer.parseInt(params.get("pageNum").toString());
		Integer pageSize = params.get("pageSize") == null ? 10 : Integer.parseInt(params.get("pageSize").toString());
		
		Page<DetectionT> page = null;
		try {
			page = service.pageDetection(params, currentPage, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(page);
	}
	
	/**
	 * 根据类型ID查询题目列表
	 * @param detectionQ
	 * @return
	 */
	@RequestMapping(value = "/qList", method = RequestMethod.POST)
	@ResponseBody
	public Result detectionQList(DetectionQ detectionQ) {
		
		List<DetectionQ> qList = null;
		try {
			qList = service.findAllQuestion(detectionQ);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(qList);
	}
	
	/**
	 * 提交答案
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "comfirmAnswer", method = RequestMethod.POST)
	@ResponseBody
	public Result comfirmAnswer(@RequestParam Map<String,Object> params) {
		
		try {
			service.insertAnswer(params);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success();
	}
	
	/**
	 * 查询提交的答案集合
	 * @param answer
	 * @return
	 */
	@RequestMapping(value = "findAnswerByUserId", method = RequestMethod.POST)
	@ResponseBody
	public Result findAnswerByUserId(DetectionA answer) {
		
		List<DetectionA> answerList = null;
		try {
			answerList = service.findAnswer(answer);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(answerList);
	}
}
