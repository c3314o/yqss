package com.bluemobi.pro.entity;

public class Qanda extends BaseEntity{

	private Integer helpTitle;
	
	private String question;
	
	private String answer;

	public Integer getHelpTitle() {
		return helpTitle;
	}

	public void setHelpTitle(Integer helpTitle) {
		this.helpTitle = helpTitle;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
