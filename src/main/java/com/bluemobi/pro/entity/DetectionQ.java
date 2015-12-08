package com.bluemobi.pro.entity;

/**
 * 检测题目
 * 
 * @author yesong
 *
 */
public class DetectionQ {

	private String id;
	private int qno; // 题目ID

	private String title; // 题目标题
	
	private String tid; // 所属模块ID 对应DetectionT 类ID

	// ******************
	// ******题目答案*******
	// ******************
	private String a;
	private String b;
	private String c;
	private String d;
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getQno() {
		return qno;
	}

	public void setQno(int qno) {
		this.qno = qno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

}
