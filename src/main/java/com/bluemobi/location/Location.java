package com.bluemobi.location;

// 位置信息
public class Location {

	private String aolUserId; // 长辈用户ID
	
	private Double lon; // 经度
	
	private Double lat; // 维度
	
	private Long lastLocationTime; // 最后发送地理位置时间
	
	private String address; // 所在地址
	
	public Location() {}
	
	public Location(String aolUserId,Long lastLocationTime) {
		this.aolUserId = aolUserId;
		this.lastLocationTime = lastLocationTime;
	}
	
	public Location(String aolUserId,Double lon,Double lat,String address) {
		this.aolUserId = aolUserId;
		this.lon = lon;
		this.lat = lat;
		this.address = address;
		this.lastLocationTime = System.currentTimeMillis();
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAolUserId() {
		return aolUserId;
	}

	public void setAolUserId(String aolUserId) {
		this.aolUserId = aolUserId;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Long getLastLocationTime() {
		return lastLocationTime;
	}

	public void setLastLocationTime(Long lastLocationTime) {
		this.lastLocationTime = lastLocationTime;
	}
	
	
}
