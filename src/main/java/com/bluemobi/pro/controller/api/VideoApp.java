package com.bluemobi.pro.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.pro.entity.Video;
import com.bluemobi.pro.service.impl.VideoService;
import com.bluemobi.sys.page.Page;
import com.bluemobi.utils.Result;

/**
 * 
 * @ClassName: VideoApp
 * @Description: 宣传视频controller
 * @author yesong
 * @date 2015年12月14日
 *
 */
@RequestMapping("/app/video/")
@Controller
public class VideoApp {

	@Autowired
	private VideoService service;
	
	/**
	 * 
     * @Title: findDefaultVideo
     * @Description: 默认视频信息
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	@RequestMapping(value = "default", method = RequestMethod.POST)
	@ResponseBody
	public Result findDefaultVideo() {
		try {
			Video video = service.findDefault();
			return Result.success(video);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
	}
	
	/**
	 * 
     * @Title: findVideo
     * @Description: 获取视频列表
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Result findVideo(@RequestParam(value = "pageNum",required = false) Integer pageNum,
									  @RequestParam(value = "pageSize",required = false) Integer pageSize) {
		
		pageNum = (pageNum  == null ? 0 : pageNum - 1);
		pageSize = (pageSize == null ? 10 : pageSize);
		
		Page<Video> page = null;
		try {
			page = service.findVideo(pageNum, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(page);
	}
}
