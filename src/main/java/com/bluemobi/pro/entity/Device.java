package com.bluemobi.pro.entity;

import java.io.Serializable;

/**
 * 设备类
 * @author yesong
 *
 */
public class Device {
	
	private String deviceId = null;
	//用户id
	private String userId = null;
	//设备生产年份
	private String deviceProYear = null;
	//设备生产月份
	private String deviceProMonth = null;
	//绑定设备类型 1.血压 2、血糖、3.体温 4.运动
	private String deviceType = null;
	//设备序列号
	private String deviceSerial = null;
	//绑定的程序版本
	private String version = null;
	//设备类型0：普通设备，1：GSM设备
	private String bak1 = null;
	//备用字段2
	private String bak2 = null;
	//备用字段3
	private String bak3 = null;
	//备用字段4
	private String bak4 = null;
	//备用字段5
	private java.sql.Timestamp bak5 = null;
	//时间戳
	private java.sql.Timestamp bak6 = null;
	//备用字段7
	private Double bak7 = null;
	//备用字段8
	private Double bak8 = null;
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getUserId() {
		return userId;
	}

	//
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeviceProYear() {
		return deviceProYear;
	}
	public void setDeviceProYear(String deviceProYear) {
		this.deviceProYear = deviceProYear;
	}
	public String getDeviceProMonth() {
		return deviceProMonth;
	}
	public void setDeviceProMonth(String deviceProMonth) {
		this.deviceProMonth = deviceProMonth;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceSerial() {
		return deviceSerial;
	}
	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getBak1() {
		return bak1;
	}
	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}
	public String getBak2() {
		return bak2;
	}
	public void setBak2(String bak2) {
		this.bak2 = bak2;
	}
	public String getBak3() {
		return bak3;
	}
	public void setBak3(String bak3) {
		this.bak3 = bak3;
	}
	public String getBak4() {
		return bak4;
	}
	public void setBak4(String bak4) {
		this.bak4 = bak4;
	}
	public java.sql.Timestamp getBak5() {
		return bak5;
	}
	public void setBak5(java.sql.Timestamp bak5) {
		this.bak5 = bak5;
	}
	public java.sql.Timestamp getBak6() {
		return bak6;
	}
	public void setBak6(java.sql.Timestamp bak6) {
		this.bak6 = bak6;
	}
	public Double getBak7() {
		return bak7;
	}
	public void setBak7(Double bak7) {
		this.bak7 = bak7;
	}
	public Double getBak8() {
		return bak8;
	}
	public void setBak8(Double bak8) {
		this.bak8 = bak8;
	}
}
