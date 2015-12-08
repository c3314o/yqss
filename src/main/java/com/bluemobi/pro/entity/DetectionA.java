package com.bluemobi.pro.entity;

/**
 * 题目答案
 * 
 * @author yesong
 *
 */
public class DetectionA {

	private String id; //
	private String detectionTId; // 题目类型ID
	private String detectionQId; // 题目ID
	private String answer; // 选择答案
	private String userId; // 用户ID
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDetectionTId() {
		return detectionTId;
	}

	public void setDetectionTId(String detectionTId) {
		this.detectionTId = detectionTId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDetectionQId() {
		return detectionQId;
	}

	public void setDetectionQId(String detectionQId) {
		this.detectionQId = detectionQId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
