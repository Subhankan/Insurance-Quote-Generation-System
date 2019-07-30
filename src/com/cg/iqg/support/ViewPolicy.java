package com.cg.iqg.support;

import java.util.List;

import com.cg.iqg.model.Policy;
import com.cg.iqg.service.InsuranceService;
import com.cg.iqg.serviceimpl.InsuranceServiceImpl;
import com.cg.iqgexception.IQGException;

public class ViewPolicy {

	InsuranceService service=new InsuranceServiceImpl();
	public List<Policy> viewPolicyDetails(String userName) throws IQGException{
		
		List<Policy> list=null;
		try {
			list=service.viewPolicyDetails(userName);
		} catch (IQGException e) {
			System.err.println(e.getMessage());
		}
		
		return list;
		
	}
	public List<Policy> getPolicy(String userName)throws IQGException {
		
		return service.getPolicy(userName);
	}
	public List<Policy> viewPolicyDetails()throws IQGException  {
		List<Policy> list=null;
		try {
			list=service.viewPolicyDetails();
		} catch (IQGException e) {
			System.err.println(e.getMessage());
		}
		
		return list;
	}

}
