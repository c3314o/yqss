package com.bluemobi.pro.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.pro.entity.Ads;
import com.bluemobi.pro.service.impl.AdsService;
import com.bluemobi.utils.Result;

/**
 * 
 * @ClassName: AdsApp
 * @Description: 广告controller
 * @author yesong
 * @date 2015年12月14日
 *
 */
@Controller
@RequestMapping("/app/ads/")
public class AdsApp {

	@Autowired
	private AdsService service;
	
	/**
	 * 
     * @Title: findAds
     * @Description: 广告轮播图
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Result findAds(Ads ads) {
		
		List<Ads> list = null;
		try {
			list = service.findAds(ads);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(list);
	}
}
