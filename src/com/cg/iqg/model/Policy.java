package com.cg.iqg.model;

public class Policy {

	private Integer policyNumber;
	private Double policyPremium;
	private Integer accountNumber;
	private String userName;

	public Policy() {
		// TODO Auto-generated constructor stub
	}

	public Policy(Integer policyNumber, Double policyPremium, Integer accountNumber, String userName) {
		super();
		this.policyNumber = policyNumber;
		this.policyPremium = policyPremium;
		this.accountNumber = accountNumber;
		this.userName = userName;
	}

	public Policy(Double policyPremium, Integer accountNumber, String userName) {
		super();
		this.policyPremium = policyPremium;
		this.accountNumber = accountNumber;
		this.userName = userName;
	}
	

	public Policy(Integer policyNumber, Double policyPremium, Integer accountNumber) {
		super();
		this.policyNumber = policyNumber;
		this.policyPremium = policyPremium;
		this.accountNumber = accountNumber;
	}

	public Integer getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(Integer policyNumber) {
		this.policyNumber = policyNumber;
	}

	public Double getPolicyPremium() {
		return policyPremium;
	}

	public void setPolicyPremium(Double policyPremium) {
		this.policyPremium = policyPremium;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Policy [policyNumber=" + policyNumber + ", policyPremium=" + policyPremium + ", accountNumber="
				+ accountNumber + ", userName=" + userName + "]";
	}

}