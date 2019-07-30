package com.cg.iqg.service;

import java.util.List;

import com.cg.iqg.model.Accounts;
import com.cg.iqg.model.Policy;
import com.cg.iqg.model.PolicyDetails;
import com.cg.iqg.model.ReportGeneration;
import com.cg.iqg.model.UserRole;
import com.cg.iqgexception.IQGException;

public interface InsuranceService {

	String validUser(UserRole role) throws IQGException;

	int createAccount(Accounts accounts) throws IQGException;

	public boolean validName(String name) throws IQGException;
	
	public boolean validStreet(String street) throws IQGException ;
	
	public boolean validCity(String city)  throws IQGException;
	
	public boolean validState(String  state) throws IQGException;
	
	public boolean validZip(int zip) throws IQGException;

	boolean getUserName(String userName) throws IQGException;

	boolean checkUserName(String userName) throws IQGException;

	boolean checkPassword(String password) throws IQGException;

	boolean checkUserRole(String userRole) throws IQGException;

	int addProfile(UserRole role) throws IQGException;

	int createPolicy(int accountNumber) throws IQGException;

	boolean validAccountNumber(int accountNumber) throws IQGException;

	boolean existAccount(int accountNumber) throws IQGException;

	String getBuisnessSegment(int accountNumber) throws IQGException;

	String getBuisnessSegmentId(String businessSegment) throws IQGException;

	List<String> getQuestions(String buisnessSegId) throws IQGException;

	List<String> getAnswer(String string) throws IQGException;

	int getWeightage(String string, String string2) throws IQGException;

	int insertPolicy(Policy policy)throws IQGException;

	String getQuesId(String string) throws IQGException;

	List<String> getQuestionId(String buisnessSegId) throws IQGException;

	void insertPolicyDetails(PolicyDetails policyDetails) throws IQGException;

	List<Policy> viewPolicyDetails(String userName) throws IQGException;

	List<ReportGeneration> generateReport(int accountNumber1) throws IQGException;

	List<Policy> getPolicy(String userName) throws IQGException;

	List<Policy> viewPolicyDetails() throws IQGException;

	String getBusinessSegment(int businessSeg) throws IQGException;

	boolean checkExist(String userName) throws IQGException;



}
