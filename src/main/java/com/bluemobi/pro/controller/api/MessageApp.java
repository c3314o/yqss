package com.bluemobi.pro.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.pro.entity.Message;
import com.bluemobi.pro.service.impl.MessageService;
import com.bluemobi.utils.Result;

/**
 * 
 * @ClassName: MessageApp
 * @Description: 消息controller
 * @author Administrator
 * @date 2015年12月11日
 *
 */
@RequestMapping
@Controller
public class MessageApp {
	
	@Autowired
	private MessageService service;

	/**
	 * 
     * @Title: findMessageByUserId
     * @Description: 查询用户消息
     * @param @param message
     * @param @return    参数
     * @return Result    返回类型
     * @throws
	 */
	@RequestMapping
	@ResponseBody
	public Result findMessageByUserId(Message message) {
		
		List<Message> messageList = null;
		try {
			messageList = service.findMessageByUserId(message);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(messageList);
	}
	
	/**
	 * 
     * @Title: deleteMessage
     * @Description: 删除消息
     * @param @param message
     * @param @return    参数
     * @return Result    返回类型
	 * @throws
	 */
	@RequestMapping
	@ResponseBody
	public Result deleteMessage(Message message) {
		
		try {
			service.deleteMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success();
	}
}
