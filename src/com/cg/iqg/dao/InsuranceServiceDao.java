package com.cg.iqg.dao;

import java.util.List;

import com.cg.iqg.model.Accounts;
import com.cg.iqg.model.Policy;
import com.cg.iqg.model.PolicyDetails;
import com.cg.iqg.model.ReportGeneration;
import com.cg.iqg.model.UserRole;
import com.cg.iqgexception.IQGException;

public interface InsuranceServiceDao {

	String validLogIn(UserRole role) throws IQGException;
	

	int createAccount(Accounts accounts) throws IQGException;

	boolean getUserName(String userName) throws IQGException;


	int addProfile(UserRole role) throws IQGException;


	boolean existAccount(int accountNumber) throws IQGException;


	String getBuisnessSegment(int accountNumber) throws IQGException;


	String getBuisnessSegmentId(String businessSegment) throws IQGException;


	List<String> getQuestions(String buisnessSegId) throws IQGException;


	List<String> getAnswer(String string) throws IQGException;


	int getWeightage(String string, String option) throws IQGException;


	int insertPolicy(Policy policy)throws IQGException;


	String getQuesId(String question) throws IQGException;


	List<String> getQuestionId(String buisnessSegId) throws IQGException;


	void insertPolicyDetails(PolicyDetails policyDetails) throws IQGException;


	List<Policy> viewPolicyDetails(String userName) throws IQGException;


	List<ReportGeneration> generateReport(int accountNumber1) throws IQGException;


	List<Policy> getPolicy(String userName) throws IQGException;


	List<Policy> viewPolicyDetails() throws IQGException;


	String getBusinessSegment(int businessSeg) throws IQGException;


	boolean checkExist(String userName) throws IQGException;

}
