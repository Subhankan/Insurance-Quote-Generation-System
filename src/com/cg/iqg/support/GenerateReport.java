package com.cg.iqg.support;

import java.util.List;

import com.cg.iqg.model.ReportGeneration;
import com.cg.iqg.service.InsuranceService;
import com.cg.iqg.serviceimpl.InsuranceServiceImpl;
import com.cg.iqgexception.IQGException;

public class GenerateReport {

	InsuranceService insuranceService= new InsuranceServiceImpl();

	public List<ReportGeneration> generateReport(int accountNumber1) throws IQGException{
		return insuranceService.generateReport(accountNumber1);
		
	}
}
