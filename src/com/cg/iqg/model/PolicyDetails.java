package com.cg.iqg.model;

public class PolicyDetails {
	
	private Integer policyNumber;
	private String questionID;
	private String answer;
	
	public PolicyDetails() {
		// TODO Auto-generated constructor stub
	}

	public PolicyDetails(Integer policyNumber, String questionID, String answer) {
		super();
		this.policyNumber = policyNumber;
		this.questionID = questionID;
		this.answer = answer;
	}

	public Integer getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(Integer policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getQuestionID() {
		return questionID;
	}

	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "PolicyDetails [policyNumber=" + policyNumber + ", questionID=" + questionID + ", answer=" + answer
				+ "]";
	}
	
	

}
