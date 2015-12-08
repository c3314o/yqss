package com.bluemobi.pro.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.bluemobi.location.LocationUtils;
import com.bluemobi.pro.entity.AolUser;
import com.bluemobi.sys.service.BaseService;

@Service
public class AolUserService extends BaseService{

	private static final String PRIFIX = AolUserService.class.getName();
	
	/**
	 * 查询关注人列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<AolUser> findFocus(String userId) throws Exception {
		List<AolUser> userList  = this.getBaseDao().getList(PRIFIX + ".findFocus", userId);
		for (AolUser aolUser : userList) {
			aolUser.setIsOnline(LocationUtils.create().isOnline(aolUser.getUserId()) ? 1 : 0);
		}
		return userList;
	}
}
