package com.cg.iqg.model;

import java.io.Serializable;

public class ReportGeneration implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1328268670012254954L;
	private String insuredName;
	private String insuredStreet;
	private String insuredCity;
	private String insuredState;
	private Integer insuredZip;
	private String businessSegment;
	private String policyQuestion;
	private String policyAnswer;
	private Double policyPremium;
	public ReportGeneration(String insuredName, String insuredStreet, String insuredCity, String insuredState,
			Integer insuredZip, String businessSegment, String policyQuestion, String policyAnswer,
			Double policyPremium) {
		super();
		this.insuredName = insuredName;
		this.insuredStreet = insuredStreet;
		this.insuredCity = insuredCity;
		this.insuredState = insuredState;
		this.insuredZip = insuredZip;
		this.businessSegment = businessSegment;
		this.policyQuestion = policyQuestion;
		this.policyAnswer = policyAnswer;
		this.policyPremium = policyPremium;
	}
	
	public ReportGeneration() {
		// TODO Auto-generated constructor stub
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getInsuredStreet() {
		return insuredStreet;
	}

	public void setInsuredStreet(String insuredStreet) {
		this.insuredStreet = insuredStreet;
	}

	public String getInsuredCity() {
		return insuredCity;
	}

	public void setInsuredCity(String insuredCity) {
		this.insuredCity = insuredCity;
	}

	public String getInsuredState() {
		return insuredState;
	}

	public void setInsuredState(String insuredState) {
		this.insuredState = insuredState;
	}

	public Integer getInsuredZip() {
		return insuredZip;
	}

	public void setInsuredZip(Integer insuredZip) {
		this.insuredZip = insuredZip;
	}

	public String getBusinessSegment() {
		return businessSegment;
	}

	public void setBusinessSegment(String businessSegment) {
		this.businessSegment = businessSegment;
	}

	public String getPolicyQuestion() {
		return policyQuestion;
	}

	public void setPolicyQuestion(String policyQuestion) {
		this.policyQuestion = policyQuestion;
	}

	public String getPolicyAnswer() {
		return policyAnswer;
	}

	public void setPolicyAnswer(String policyAnswer) {
		this.policyAnswer = policyAnswer;
	}

	public Double getPolicyPremium() {
		return policyPremium;
	}

	public void setPolicyPremium(Double policyPremium) {
		this.policyPremium = policyPremium;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
