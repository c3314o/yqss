package com.bluemobi.pro.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bluemobi.pro.entity.Video;
import com.bluemobi.sys.page.Page;
import com.bluemobi.sys.service.BaseService;

/**
 * 
 * @ClassName: VideoService
 * @Description: 宣传视频
 * @author Administrator
 * @date 2015年12月11日
 *
 */
@Service
public class VideoService extends BaseService {

	public static final String PRIFIX = Video.class.getName();
	
	public Video findDefault() throws Exception {
		Video v = new Video();
		v.setStatus(1);
		return this.getBaseDao().getObject(PRIFIX + ".findVideo", v);
	}
	
	/**
	 * 
     * @Title: findVideo
     * @Description: 
     * @param @param currentPage
     * @param @param pageSize
     * @param @return    参数
     * @return Page<Video>    返回类型
     * @throws
	 */
	public Page<Video> findVideo(Integer currentPage,Integer pageSize) throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("status", 2);
		return this.getBaseDao().page(PRIFIX + ".findVideo", paramMap, currentPage, pageSize);
	}
}
