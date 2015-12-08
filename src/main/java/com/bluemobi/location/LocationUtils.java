package com.bluemobi.location;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

// 地址位置工具
public class LocationUtils {

	private static LocationUtils instance = new LocationUtils();
	
	public static final int DEFAULT_TIME = 10; // 默认5秒在线
	
	public static LocationUtils create() {
		return instance;
	}
	
	public LocationUtils() {
		initData();
	}
	
	private ConcurrentHashMap<String, Location> _map = new ConcurrentHashMap<String, Location>();
	
	// --------------------------------
	// 测试数据
	// --------------------------------
	private ConcurrentHashMap<Integer, String> test = new ConcurrentHashMap<Integer, String>();
	
	public void receivceLocation(Location location) {
		_map.put(location.getAolUserId(), location);
	}
	
	public void removeLocation(Location location) {
		_map.remove(location.getAolUserId());
	}
	
	public Location getLocation(String aolUserID) {
		if(StringUtils.isBlank(aolUserID)) throw new IllegalArgumentException("aolUserId 不能为空 ");
		return _map.get(aolUserID);
	}
	
	public Long getCurrentTime() {
		return System.currentTimeMillis();
	}
	
	// 判断是否在线
	public Boolean isOnline(String aolUserId) {
		
		//---------------
		//测试
		//---------------
		if("f9aa885b483e3a4001483e3a40520000".equals(aolUserId)) {
			return Boolean.valueOf(true);
		}
		
		Location location = _map.get(aolUserId);
		if(location == null) return Boolean.valueOf(false);
		
		Long lastTime = location.getLastLocationTime();
		Long currentTime = getCurrentTime();
		
		return ( (currentTime - lastTime) <= (DEFAULT_TIME * 1000) ? Boolean.valueOf(true) : Boolean.valueOf(false));
	}
	
	// ----------------------------------------------------------
	// 测试数据
	// ----------------------------------------------------------
	public void initData() {
		test.put(1, "30.783651253735187,114.41197761445015");
		test.put(2, "30.787651253735187,114.41197761445015");
		test.put(3, "30.790651253735187,114.41197761445015");
		test.put(4, "30.783651253735187,114.42197761445015");
		test.put(5, "30.783451253735187,114.41191161445015");
		test.put(6, "30.783651252222187,114.41197761445015");
		test.put(7, "30.787651253333187,114.41771261445015");
		test.put(7, "30.790651254444187,114.41197761445015");
		test.put(8, "30.783651555555187,114.42121261145015");
		test.put(9, "30.783455666735187,114.41191226445015");
		test.put(10, "30.783455666735187,114.41191226445015");
	}
	
//	public Location getTest(String aolUserId) {
//		
//		int i = new Random().nextInt(10);
//		String data = test.get(i+1);
//		String[] datas = data.split(",");
//		Location l = new Location(aolUserId, Double.parseDouble(datas[1]), Double.parseDouble(datas[0]));
//		return l;
//	}
	
	
}
