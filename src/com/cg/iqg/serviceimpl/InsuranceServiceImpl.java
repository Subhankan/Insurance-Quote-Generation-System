package com.cg.iqg.serviceimpl;

import java.util.List;
import java.util.regex.Pattern;

import com.cg.iqg.dao.InsuranceServiceDao;
import com.cg.iqg.daoimpl.InsuranceServiceDaoImpl;
import com.cg.iqg.model.Accounts;
import com.cg.iqg.model.Policy;
import com.cg.iqg.model.PolicyDetails;
import com.cg.iqg.model.ReportGeneration;
import com.cg.iqg.model.UserRole;
import com.cg.iqg.service.InsuranceService;
import com.cg.iqgexception.IQGException;

public class InsuranceServiceImpl implements InsuranceService {

	InsuranceServiceDao dao = new InsuranceServiceDaoImpl();

	@Override
	public String validUser(UserRole role) throws IQGException {

		return dao.validLogIn(role);
	}



	public boolean validName(String name) throws IQGException {
		String nameRegEx = "[A-Z]{1}[A-Za-z ]{2,29}";
		if(Pattern.matches(nameRegEx, name)) {
			return true;
		}else {
			throw new IQGException("First letter of the name should be capital...");
		}
	}

	public boolean validStreet(String street) throws IQGException 
	{
		String streetRegEx = "[A-Z]{1}[A-Za-z0-9/,.- ]{2,39}";
		if( Pattern.matches(streetRegEx, street)) {
			return true;
		}else {
			throw new IQGException("First letter of the street should be capital and min size is 3");
		}
	}

	public boolean validCity(String city)  throws IQGException
	{
		String cityRegEx = "[A-Za-z ]{3,15}";
		if(Pattern.matches( cityRegEx,city)) {
			return true;
		}else {
			throw new IQGException("min size is 3 and max size is 15...");
		}
	}

	public boolean validState(String  state) throws IQGException{
		String stateRegEx = "[A-Za-z ]{3,15}";
		if(Pattern.matches(stateRegEx,state)) {
			return true;
		}else {
			throw new IQGException("min size is 3 and max size is 15...");
		}

	}
	public boolean validZip(int zip) throws IQGException{
		String zipRegEx = "[0-9]{5}";
		if(Pattern.matches(zipRegEx, Integer.toString(zip))) {
			return true;
		}else {
			throw new IQGException("Zip code should contain 5 digits...");
		}
	}

	@Override
	public int createAccount(Accounts accounts) throws IQGException {
		return dao.createAccount(accounts);
	}

	@Override
	public boolean getUserName(String userName) throws IQGException {
		return dao.getUserName(userName);
	}

	@Override
	public boolean checkUserName(String userName) throws IQGException {
		String userNameRegEx="[a-z0-9]{3,20}";
		if(Pattern.matches(userNameRegEx, userName)) {
			return true;
		}else {
			throw new IQGException("username should not contain capital letter and also should contain digits also.");
		}
	}

	@Override
	public boolean checkPassword(String password) throws IQGException {
		String passwordRegEx="[a-zA-Z0-9]{3,12}";
		if(Pattern.matches(passwordRegEx, password)) {
			return true;
		}else {
			throw new IQGException("Password must be alphanumeric and the minimum length should be 3");
		}
	}

	@Override
	public boolean checkUserRole(String userRole) throws IQGException {
		boolean userFlag=false;
		if(userRole.equalsIgnoreCase("Insured")||userRole.equalsIgnoreCase("Agent")||userRole.equalsIgnoreCase("Admin")) {
			userFlag=true;
			return userFlag;
		}else {
			throw new IQGException("User role must be Agent or Insured or Admin");
		}
		
	}

	@Override
	public int addProfile(UserRole role) throws IQGException {
		
		return dao.addProfile(role);
	}

	@Override
	public int createPolicy(int account) throws IQGException {
		// TODO Auto-generated method stub
		//return dao.createPolicy();
		return 0;
	}

	@Override
	public boolean validAccountNumber(int accountNumber) throws IQGException {
		String accountRegEx="[0-9]{7}";
		return Pattern.matches(accountRegEx, Integer.toString(accountNumber));
	}

	@Override
	public boolean existAccount(int accountNumber) throws IQGException {
		return dao.existAccount(accountNumber);
	}

	@Override
	public String getBuisnessSegment(int accountNumber) throws IQGException {
		
		return dao.getBuisnessSegment(accountNumber);
	}

	@Override
	public String getBuisnessSegmentId(String businessSegment) throws IQGException {
		
		return dao.getBuisnessSegmentId(businessSegment);
	}

	@Override
	public List<String> getQuestions(String buisnessSegId) throws IQGException {
		
		return dao.getQuestions(buisnessSegId);
	}

	@Override
	public List<String> getAnswer(String string) throws IQGException {
		return dao.getAnswer(string);
	}

	

	@Override
	public int getWeightage(String string,String option) throws IQGException {
		return dao.getWeightage(string,option);
	}

	@Override
	public int insertPolicy(Policy policy) throws IQGException {
		return dao.insertPolicy(policy);
	}

	@Override
	public String getQuesId(String question) throws IQGException {
		return dao.getQuesId(question);
	}

	@Override
	public List<String> getQuestionId(String buisnessSegId) throws IQGException {
		return dao.getQuestionId(buisnessSegId);
	}

	@Override
	public void insertPolicyDetails(PolicyDetails policyDetails) throws IQGException {
		dao.insertPolicyDetails(policyDetails);
		
	}

	@Override
	public List<Policy> viewPolicyDetails(String userName) throws IQGException {
		return dao.viewPolicyDetails(userName);
	}

	@Override
	public List<ReportGeneration> generateReport(int accountNumber1) throws IQGException {
		return dao.generateReport(accountNumber1);
	}

	@Override
	public List<Policy> getPolicy(String userName) throws IQGException {
		return dao.getPolicy(userName);
	}



	@Override
	public List<Policy> viewPolicyDetails() throws IQGException {
		return dao.viewPolicyDetails();
	}



	@Override
	public String getBusinessSegment(int businessSeg) throws IQGException {
		return dao.getBusinessSegment(businessSeg);
	}



	@Override
	public boolean checkExist(String userName) throws IQGException {
		return dao.checkExist(userName);
	}

	

}
