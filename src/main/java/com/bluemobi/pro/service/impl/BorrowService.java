package com.bluemobi.pro.service.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bluemobi.pro.entity.BorrowInfo;
import com.bluemobi.pro.entity.BorrowRepayRecord;
import com.bluemobi.sys.service.BaseService;
import com.bluemobi.utils.DBUtils;
import com.bluemobi.utils.DateUtils;
import com.bluemobi.utils.DateUtils2;
import com.bluemobi.utils.YqssUtils;

/**
 * 
 * @ClassName: BorrowService
 * @Description: 借款service
 * @author yesong
 * @date 2015年12月10日
 *
 */
@Service
public class BorrowService extends BaseService {

	public static final String PRIFIX = BorrowInfo.class.getName();

	/**
	 * 
	 * @Title: borrow @Description: 借款-填写 @param @return @param @throws
	 * Exception 参数 @return int 返回类型 @throws
	 */
	public int borrowInsertUserInfo(BorrowInfo borrowInfo) throws Exception {
		// 判断是否有未还完的借款
		List<BorrowInfo> list = findBorrowByUserId(borrowInfo);
		if (list != null && list.size() > 0 && !isEnd(list)) {
			BorrowInfo _info = list.get(list.size() - 1);
			if (_info != null && _info.getMoney() != 0) {
				borrowInfo.setId(_info.getId());
				return -1;
			}
			if (_info != null && _info.getMoney() == 0) {
				borrowInfo.setId(_info.getId());
				return -2;
			}
		}
		return this.getBaseDao().save(PRIFIX + ".insertBrrow", borrowInfo);
	}

	private boolean isEnd(List<BorrowInfo> list) {
		if (list != null && !list.isEmpty()) {
			BorrowInfo info = list.get(list.size() - 1);
			double money = info.getMoney();
			BorrowInfo info2 = new BorrowInfo();
			info2.setId(list.get(list.size() - 1).getId());
			try {
				List<BorrowRepayRecord> recordList = findBRR(info2);
				double repaymoney = 0.0;
				for (BorrowRepayRecord borrowRepayRecord : recordList) {
					repaymoney += borrowRepayRecord.getAmount();
				}
				if (money <= repaymoney) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 
	 * @Title: borrowInsertBorrowInfo @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param @param
	 * borrowInfo @param @return @param @throws Exception 参数 @return int
	 * 返回类型 @throws
	 */
	public int borrowInsertBorrowInfo(BorrowInfo borrowInfo) throws Exception {
		if (borrowInfo.getTimeLimite() != null) {
			borrowInfo.setLastRepayDate(YqssUtils.borrowResidueDate(borrowInfo.getTimeLimite()));
		}
		return this.getBaseDao().update(PRIFIX + ".updateBorrow", borrowInfo);
	}

	/**
	 * 
	 * @Title: findBorrowByUserId @Description: 查询用户借款信息 @param @param
	 * userInfo @param @return @param @throws Exception 参数 @return
	 * List<BorrowInfo> 返回类型 @throws
	 */
	public List<BorrowInfo> findBorrowByUserId(BorrowInfo bi) throws Exception {
		return this.getBaseDao().getList(PRIFIX + ".findBorrowListByUserId", bi);
	}

	/**
	 * 
	 * @Title: findBorrowByUserId @Description: 查询用户借款信息 @param @param
	 * userInfo @param @return @param @throws Exception 参数 @return
	 * List<BorrowInfo> 返回类型 @throws
	 */
	public List<BorrowInfo> findBorrowByUserId2(BorrowInfo bi) throws Exception {
		return this.getBaseDao().getList(PRIFIX + ".findBorrowListByUserId2", bi);
	}

	/**
	 * 查询借款详情
	 * 
	 * @param bi
	 * @return
	 * @throws Exception
	 */
	public BorrowInfo findBorrowById(BorrowInfo bi) throws Exception {
		return this.getBaseDao().getObject(PRIFIX + ".findBorrowListByUserId", bi);
	}

	/**
	 * 查询还款记录
	 * 
	 * @param bi
	 * @return
	 * @throws Exception
	 */
	public List<BorrowRepayRecord> findBRR(BorrowInfo bi) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", bi.getId());
		return this.getBaseDao().getList(PRIFIX + ".findBorrowRRListByBid", paramMap);
	}

	/**
	 * 
	 * @Title: repay @Description: 还款 @param @throws Exception 参数 @return void
	 * 返回类型 @throws
	 */
	public void repay(BorrowRepayRecord brr) throws Exception {

		this.getBaseDao().save(PRIFIX + ".insertBorrowRR", brr);
	}

	/**
	 * 
	 * @Title: residueMoney @Description: 计算本月应还款金额 @param @param
	 * list @param @return 参数 @return int 返回类型 @throws
	 */
	public int residueMoney(List<BorrowInfo> list) {
		return 0;
	}

	public int getIdBySn(String sn) throws Exception {
		return this.getBaseDao().getObject(PRIFIX + ".getIdBySn", sn);
	}

	/**
	 * 
	 * @Title: repayFinish @Description: 支付完成修改金额 @param @param
	 * brr @param @throws Exception 参数 @return void 返回类型 @throws
	 */
	public void repayFinish(BorrowRepayRecord brr) throws Exception {
		Double amount = this.getBaseDao().getObject(PRIFIX + ".getIdBySn", brr.getSn());
		if (amount != null && amount.doubleValue() == 0.0) {
			this.getBaseDao().update(PRIFIX + ".repayFinish", brr);
		}
	}

	/**
	 * 本月是否还款
	 * 
	 * @param repay
	 * @return
	 * @throws Exception
	 */
	public boolean isThisMonth(BorrowRepayRecord repay) throws Exception {

		Integer borrowId = repay.getBorrowId();
		BorrowInfo info = new BorrowInfo();
		info.setId(borrowId);
		info = this.findBorrowById(info);
		// List<BorrowRepayRecord> list = findBRR(info);
		//
		// if(list == null || list.isEmpty()) return false;
		//
		// int allPeriod = YqssUtils.allPeriod(info.getTotalDays());
		// String last = DateUtils2.longToString(list.get(list.size() -
		// 1).getCreateDate(), "yyyy-MM-dd HH:mm:ss");
		// int residuePeriod = YqssUtils.residuePeriod(last);
		//
		// int apartPeriod = allPeriod - residuePeriod;
		//
		// Long createDate = info.getCreateDate();
		// Calendar c = Calendar.getInstance();
		// c.setTimeInMillis(createDate);
		// c.add(Calendar.DAY_OF_MONTH, apartPeriod);
		//
		// Date date = c.getTime();
		// String dateStr = DateUtils.toString(date, "yyyy-MM-dd HH:mm:ss");
		// return YqssUtils.isThisMonth(list.get(list.size() -
		// 1).getCreateDate(), dateStr, 1);
		Calendar c = Calendar.getInstance();
		long jdTime = info.getJkTime();
		c.setTimeInMillis(jdTime);
		int jdDay = c.get(Calendar.DAY_OF_MONTH);

		c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, jdDay);
		c.add(Calendar.MONTH, 1);
		long date1 = c.getTimeInMillis();
		String date1Str = DateUtils2.longToString(date1, "yyyy-MM-dd HH:mm:ss");

		List<BorrowRepayRecord> list = findBRR(info);
		long date2 = 0;
		if (list == null || list.isEmpty()) {
			date2 = jdTime;
		} else {
			date2 = list.get(list.size() - 1).getRepayTime();
		}
		boolean flag = YqssUtils.isThisMonth2(date2, date1Str, 1);
		return flag;
	}

	/**
	 * 本期应还金额
	 * 
	 * @param borrowInfo
	 * @return
	 */
	public double[] currentMoney(BorrowInfo borrowInfo) {
		long createDate = borrowInfo.getJkTime();
		long currentDate = System.currentTimeMillis();

		int createYear = 0;
		int createMonth = 0;
		int createDay = 0;
		int currentMonth = 0;
		int currentDay = 0;

		int lastYear = 0;
		int lastMonth = 0;
		int lastDay = 0;

		long finalDate = 0;

		double all = 0;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(createDate);
		createYear = c.get(Calendar.YEAR);
		createMonth = c.get(Calendar.MONTH) + 1;
		createDay = c.get(Calendar.DAY_OF_MONTH);

		int totalPeriod = YqssUtils.allPeriod(borrowInfo.getTotalDays());
		c.add(Calendar.MONTH, totalPeriod);
		lastYear = c.get(Calendar.YEAR);
		lastMonth = c.get(Calendar.MONTH) + 1;
		lastDay = c.get(Calendar.DAY_OF_MONTH);

		finalDate = c.getTimeInMillis();

		c.setTimeInMillis(currentDate);
		currentMonth = c.get(Calendar.MONTH) + 1;
		currentDay = c.get(Calendar.DAY_OF_MONTH);
		int intervalMonth = currentMonth - createMonth; // 间隔月份

		int MONTHS = 0;
		if (lastYear > createYear) {
			MONTHS += 12;
		}

		int intervalMonth2 = lastMonth - currentMonth + MONTHS;
		if (currentDay > createDay) {
			// 本月还款已过期，需要计算利息
			++intervalMonth;
		}
		
		
		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(createDate);
		int jdDay = c2.get(Calendar.DAY_OF_MONTH);

		c2 = Calendar.getInstance();
		c2.set(Calendar.DAY_OF_MONTH, jdDay);
		c2.add(Calendar.MONTH, 1);
		long date1 = c2.getTimeInMillis();
		String date1Str = "";
		try {
			date1Str = DateUtils2.longToString(date1, "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<BorrowRepayRecord> list = borrowInfo.getList();
		long date2 = 0;
		if (list == null || list.isEmpty()) {
			date2 = createDate;
		} else {
			date2 = list.get(list.size() - 1).getRepayTime();
		}
		boolean flag = YqssUtils.isThisMonth2(date2, date1Str, 1);
		
		if ((currentDay > lastDay) && flag) {
			--intervalMonth2;
		}

		double repayMoney = repayMoney(borrowInfo.getList()); // 已还金额
		// double all2 = YqssUtils.getAll(borrowInfo.getMoney(), totalPeriod);
		double pm = (intervalMonth2 <= 0 ? (YqssUtils.getAll(borrowInfo.getMoney(), totalPeriod) - repayMoney)
				: ((YqssUtils.getAll(borrowInfo.getMoney(), totalPeriod) - repayMoney) / intervalMonth2)); // 月供
		double rate0 = DBUtils.getRate0();
		// while(intervalMonth > 0) {
		// int days = intervalMonth * 30;
		// all += days * rate0 + pm;
		// intervalMonth--;
		// }

		
		long lastRepay = 0;
		if (list == null || list.isEmpty()) {
			lastRepay = createDate;
		} else {
			lastRepay = list.get(list.size() - 1).getRepayTime();
		}
		double overdueMoney = YqssUtils.nextDateByLastRepayDateAndCreateDate(lastRepay, createDate) * rate0;
		all = pm + overdueMoney;
		return new double[] { YqssUtils.numberFormat(all), intervalMonth2, totalPeriod };
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	public double repayMoney(List<BorrowRepayRecord> list) {
		Double rrMoney = 0.0;
		for (BorrowRepayRecord borrowRepayRecord : list) {
			rrMoney += borrowRepayRecord.getAmount();
		}
		return rrMoney;
	}

	/**
	 * 计算利息
	 * 
	 * @return
	 */
	public double rate(BorrowInfo borrowInfo) {
		int allDays = borrowInfo.getTotalDays();
		double money = borrowInfo.getMoney();
		return ((allDays - 30) <= 0 ? 0 : (allDays - 30) * borrowInfo.getRate() * money / 100.0) + money;
	}
}
