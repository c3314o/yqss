package com.bluemobi.pro.controller.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bluemobi.pay.util.XMLUtil;
import com.bluemobi.pro.entity.BorrowRepayRecord;
import com.bluemobi.pro.entity.ProductBorrowRepayRecord;
import com.bluemobi.pro.service.impl.BorrowService;
import com.bluemobi.pro.service.impl.ProductBorrowService;
import com.bluemobi.pro.service.impl.XxShopServiceImpl;

@RequestMapping("/pay/")
@Controller
public class PayNotifyApp {

	@Autowired
	private BorrowService borrowService;
	
	@Autowired
	private ProductBorrowService pbService;
	
	@Autowired
	private XxShopServiceImpl iShopServiceImpl;
	
	/**************************************************************************
	 *******************************notify*************************************
	 **************************************************************************
	 */
	
	@RequestMapping(value="notify/weixinborrow", method = RequestMethod.POST)
	public void notifyWeixinBorrow(HttpServletRequest request) {
		Map<String,Object> result = parse(request);
		String status = result.get("result_code").toString();
		if ("SUCCESS".equals(status)) {
			String sn = result.get("out_trade_no").toString();
			
			try {
				BorrowRepayRecord record = new BorrowRepayRecord();
				record.setSn(sn);
				record.setAmount(Integer.parseInt(result.get("total_fee").toString()) / 100.0);
				
				borrowService.repayFinish(record);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value="notify/weixinpb", method = RequestMethod.POST)
	public void notifyWeixinProductBorrow(HttpServletRequest request) {
		Map<String,Object> result = parse(request);
		String status = result.get("result_code").toString();
		if("SUCCESS".equals(status)) {
			String sn = result.get("out_trade_no").toString();
			
			try {
				ProductBorrowRepayRecord pbrr = new ProductBorrowRepayRecord();
				pbrr.setSn(sn);
				pbrr.setAmount(Integer.parseInt(result.get("total_fee").toString()) / 100.0);
				
				pbService.repayFinish(pbrr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value="notify/alipayborrow", method = RequestMethod.POST)
	public void notifyAlipayBorrow(HttpServletRequest request) {
		String status = request.getParameter("trade_status").toString();
		String sn = request.getParameter("out_trade_no").toString();
		
		System.out.println("status:" + status + "==sn:" + sn);
		double amount = Double.parseDouble(request.getParameter("total_fee").toString());
		System.out.println("total_fee:" + amount);
		
		if("TRADE_SUCCESS".equals(status) || "FINISH".equals(status)) {
			System.out.println("SUCCESS");
			try {
				BorrowRepayRecord record = new BorrowRepayRecord();
				record.setSn(sn);
				record.setAmount(amount);
				
				borrowService.repayFinish(record);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value="notify/alipaypb", method = RequestMethod.POST)
	public void notifyAlipaypb(HttpServletRequest request) {
		String status = request.getParameter("trade_status").toString();
		String sn = request.getParameter("out_trade_no").toString();
		double amount = Double.parseDouble(request.getParameter("total_fee").toString());
		
		if("TRADE_SUCCESS".equals(status) || "FINISH".equals(status)) {
			
			try {
				ProductBorrowRepayRecord pbrr = new ProductBorrowRepayRecord();
				pbrr.setSn(sn);
				pbrr.setAmount(amount);
				
				pbService.repayFinish(pbrr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> parse(HttpServletRequest request) {
		Map<String,Object> resultMap = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String line = null;
			String result = "";
			while ((line = reader.readLine()) != null) {
				result += line;
			}
			System.out.println("result:" + result);
			resultMap = XMLUtil.doXMLParse(result);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	// ----------------------------------------------------------------------------------
		// ------------------------------------通知方法-----------------------------------------
		// ----------------------------------------------------------------------------------
		@RequestMapping(value = "notifyAlipay", method = RequestMethod.POST)
		public void notifyAlipay(HttpServletRequest request) {
			try {
				iShopServiceImpl.toEntityByAlipay(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 微信支付回调地址
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "notifyWeixin", method = RequestMethod.POST)
		public void notifyWeixin(HttpServletRequest request, HttpServletResponse response) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String line = null;
				String result = "";
				while ((line = reader.readLine()) != null) {
					result += line;
				}
				System.out.println("result:" + result);
				Map<String,Object> resultMap = XMLUtil.doXMLParse(result);
				iShopServiceImpl.toEntityByWeixin(resultMap);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
