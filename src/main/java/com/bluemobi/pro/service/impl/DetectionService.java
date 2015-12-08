package com.bluemobi.pro.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bluemobi.pro.entity.DetectionA;
import com.bluemobi.pro.entity.DetectionQ;
import com.bluemobi.pro.entity.DetectionT;
import com.bluemobi.sys.page.Page;
import com.bluemobi.sys.service.BaseService;
import com.bluemobi.utils.UUIDUtil;

@Service
public class DetectionService extends BaseService{

	private static final String PRIFIX = DetectionService.class.getName();
	
	/**
	 * 查询题目类别集合
	 * @return
	 * @throws Exception
	 */
	public Page<DetectionT> pageDetection(Map<String,Object> params,Integer currentPage,Integer pageSize) throws Exception {
		return this.getBaseDao().page(PRIFIX + ".pageDetection", params, currentPage, pageSize);
	}
	
	/**
	 * 根据类型ID 查询题目集合
	 * @param question
	 * @return
	 * @throws Exception
	 */
	public List<DetectionQ> findAllQuestion(DetectionQ question) throws Exception {
		return this.getBaseDao().getList(PRIFIX + ".findAllQuestion", question);
	}  
	
	/**
	 * 提交答案
	 * @param params
	 * @throws Exception
	 */
	public void insertAnswer(Map<String,Object> params) throws Exception {
		
		// 答案格式 1,A|2,B|3,C|4,D
		String detectionTId = params.get("detectionTId").toString();
		String userId = params.get("userId").toString();
		String answer = params.get("answer").toString();
		if(StringUtils.isNotBlank(answer)) {
			String[] questions = answer.split("\\|");
			for (String question : questions) {
				if(StringUtils.isNotBlank(question)) {
					String[] questionInfo = question.split(",");
					String qid = questionInfo[0];
					String _answer = questionInfo[1];
					
					DetectionA a = new DetectionA();
					a.setDetectionQId(qid);
					a.setAnswer(_answer);
					a.setDetectionTId(detectionTId);
					a.setId(UUIDUtil.generateUUID());
					a.setUserId(userId);
					this.getBaseDao().save(PRIFIX + ".insertAnswer", a);
				}
			}
		}
	}
	
	/**
	 * 查询答题的答案
	 * @param answer
	 * @return
	 * @throws Exception
	 */
	public List<DetectionA> findAnswer(DetectionA answer) throws Exception {
		return this.getBaseDao().getList(PRIFIX + ".findAnswer", answer);
	}
}
