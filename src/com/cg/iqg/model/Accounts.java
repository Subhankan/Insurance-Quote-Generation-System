package com.cg.iqg.model;

public class Accounts {
	
	private Integer accountNumber;
	private String insuredName;
	private String insuredStreet;
	private String insuredCity;
	private String insuredState;
	private Integer insuredZip;
	private String BusinessSegment;
	private String userName;
	
	public Accounts() {
		// TODO Auto-generated constructor stub
	}

	public Accounts(String insuredName, String insuredStreet, String insuredCity,
			String insuredState, Integer insuredZip, String businessSegment, String userName) {
		super();
		this.insuredName = insuredName;
		this.insuredStreet = insuredStreet;
		this.insuredCity = insuredCity;
		this.insuredState = insuredState;
		this.insuredZip = insuredZip;
		this.BusinessSegment = businessSegment;
		this.userName = userName;
	}
	
	

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
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
		return BusinessSegment;
	}

	public void setBusinessSegment(String businessSegment) {
		BusinessSegment = businessSegment;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Accounts [accountNumber=" + accountNumber + ", insuredName=" + insuredName + ", insuredStreet="
				+ insuredStreet + ", insuredCity=" + insuredCity + ", insuredState=" + insuredState + ", insuredZip="
				+ insuredZip + ", BusinessSegment=" + BusinessSegment + ", userName=" + userName + "]";
	}
	
	
	

}
