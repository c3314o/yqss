package com.bluemobi.pro.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.pro.entity.Device;
import com.bluemobi.pro.service.impl.DeviceService;
import com.bluemobi.utils.Result;

/**
 * 设备controller
 * @author yesong
 *
 */
@Controller
@RequestMapping("/app/device/")
public class DeviceApp {

	@Autowired
	private DeviceService service;
	
	/**
	 * 用户新增设备
	 * @param device
	 * @return
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Result addNewDeviceToUser(Device device) {
		Device _d = new Device();
		try {
			_d.setDeviceId(service.addDevice(device));
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(_d);
	}
	
	/**
	 * 更新设备信息
	 * @param device
	 * @return
	 */
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	public Result modifyDevice(Device device) {
		
		try {
			service.modifyDevice(device);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success();
	}
	
	/**
	 * 移除设备
	 * @param device
	 * @return
	 */
	@RequestMapping(value = "remove", method = RequestMethod.POST)
	@ResponseBody
	public Result removeDevice(Device device) {
		try {
			service.deleteDevice(device.getDeviceId());
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success();
	}
	
	/**
	 * 根据设备ID查询设备信息
	 * @param device
	 * @return
	 */
	@RequestMapping(value = "findById", method = RequestMethod.POST)
	@ResponseBody
	public Result findDeviceById(Device device) {
		
		Device respDevice = null;
		try {
			respDevice = service.findDeviceById(device.getDeviceId());
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(respDevice);
	}
	
	/**
	 * 根据用户ID查询 设备集合
	 * @param device
	 * @return
	 */
	@RequestMapping(value = "findByUserId", method = RequestMethod.POST)
	@ResponseBody
	public Result findDeviceByUserId(Device device){
		
		List<Device> list = null;
		try {
			list = service.findDeviceByUserId(device.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failure();
		}
		return Result.success(list);
	}
}
