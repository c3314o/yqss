package com.bluemobi.pro.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bluemobi.pro.entity.Message;
import com.bluemobi.sys.service.BaseService;

/**
 * 
 * @ClassName: MessageService
 * @Description: 消息service
 * @author Administrator
 * @date 2015年12月11日
 *
 */
@Service
public class MessageService extends BaseService {

	public static final String PRIFIX = Message.class.getName();
	
	/**
	 * 
     * @Title: findMessageByUserId
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param message
     * @param @return    参数
     * @return List<Message>    返回类型
     * @throws
	 */
	public List<Message> findMessageByUserId(Message message ) throws Exception {
		return this.getBaseDao().getList(PRIFIX + ".findMessageByUserId", message);
	}
	
	/**
	 * 
     * @Title: deleteMessage
     * @Description: 删除消息
     * @param @param message
     * @param @return
     * @param @throws Exception    参数
     * @return Integer    返回类型
     * @throws
	 */
	public Integer deleteMessage(Message message) throws Exception {
		return this.getBaseDao().delete(PRIFIX + ".delete", message);
	}
}
