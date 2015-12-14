package com.bluemobi.pro.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bluemobi.pro.entity.Ads;
import com.bluemobi.sys.service.BaseService;

/**
 * 
 * @ClassName: AdsService
 * @Description: 广告service
 * @author Administrator
 * @date 2015年12月14日
 *
 */
@Service
public class AdsService extends BaseService{

	public static final String PRIFIX = Ads.class.getName();
	
	/**
	 * 
     * @Title: findAds
     * @Description : 查询广告列表
     * @param @return
     * @param @throws Exception    参数
     * @return List<Ads>    返回类型
     * @throws
	 */
	public List<Ads> findAds(Ads ads) throws Exception {
		return this.getBaseDao().getList(PRIFIX + ".findAll",ads);
	}
}
