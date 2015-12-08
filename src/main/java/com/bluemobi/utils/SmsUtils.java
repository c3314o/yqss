package com.bluemobi.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 短信发送工具类
 * @author yesong 
 *
 */
public class SmsUtils {

	public static boolean cjsmsSend(String code,String... mobiles) {
		
		String content = "【健康管家】您的验证码为:" + code + ",请不要将验证码透露给其他人";
		
		final String url = "http://116.255.153.152:9999/sms.aspx";
		final String account = "whgjkj";
		final String password = "baicai626";
		final String action = "send";
		final String sendTime = DateUtils.getCurrentTime();
		
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(url)
			.append("?")
			.append("action=")
			.append(URLEncoder.encode(action, "utf-8"))
			.append("&")
			.append("userid=529")
			.append("&")
			.append("account=")
			.append(URLEncoder.encode(account, "utf-8"))
			.append("&")
			.append("password=")
			.append(URLEncoder.encode(password,"utf-8"))
			.append("&")
			.append("mobile=")
			.append(URLEncoder.encode(linkMobile(mobiles), "utf-8"))
			.append("&")
			.append("content=")
			.append(URLEncoder.encode(content, "utf-8"))
			.append("&")
			.append("sendTime=")
			.append(URLEncoder.encode(sendTime, "utf-8"))
			.append("&")
			.append("extno=");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String result = WebserviceUtil.post(buffer.toString());
		System.out.println(result);
		return result.contains("Success");
	}
	
	private static String linkMobile(String... mobiles) {
		if(mobiles == null || mobiles.length == 0) return "";
		StringBuffer buffer = new StringBuffer();
		for (String mobile : mobiles) {
			buffer.append(mobile).append(",");
		}
		return buffer.toString().substring(0, buffer.toString().length() - 1);
	}
	
	public static void main(String[] args) {
		String mobile = "13476107753";
		cjsmsSend(CommonUtils.getCode(6), mobile);
	}
}
