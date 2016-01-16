package com.bluemobi.sys.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bluemobi.utils.MD5;

/**
 * 解密验证
 * @author yesong
 *
 */
public class TokenInterceptor extends HandlerInterceptorAdapter{

	public static final int TIMES = 60 * 1000; // 有效时间60秒
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String url = request.getParameter("url");
		String appid = request.getParameter("appid");
		String nonce_str = request.getParameter("nonce_str");
		String sign = request.getParameter("sign");
		
		if(StringUtils.isBlank(url) || StringUtils.isBlank(appid) || StringUtils.isBlank(nonce_str) || StringUtils.isBlank(sign) ) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "");
			return false;
		}
		if(System.currentTimeMillis() - Long.parseLong(appid) > TIMES) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "");
			return false;
		}
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(url).append(nonce_str).append(appid).append(nonce_str);
		String _sign = MD5.md5(buffer.toString());
		
		System.out.println("sgin==" + sign + "==this sign:" + _sign);
		if(sign.toUpperCase().equals(_sign.toUpperCase())) {
			return true;
		}else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "");
			return false;
		}
	}
}
