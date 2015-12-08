package com.bluemobi.utils;

import java.util.UUID;

public class UUIDUtil {

	/**
	 * 生成主键ID
	 * @return
	 */
	public static String generateUUID() {
		
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-","");
	}
	
	public static void main(String[] args) {
		System.out.println(generateUUID());
	}
}
