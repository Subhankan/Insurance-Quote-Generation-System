package com.cg.iqg.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.iqg.dao.InsuranceServiceDao;
import com.cg.iqg.dao.QueryMapper;
import com.cg.iqg.model.Accounts;
import com.cg.iqg.model.Policy;
import com.cg.iqg.model.PolicyDetails;
import com.cg.iqg.model.ReportGeneration;
import com.cg.iqg.model.UserRole;
import com.cg.iqg.utility.JdbcUtility;
import com.cg.iqgexception.IQGException;

public class InsuranceServiceDaoImpl implements InsuranceServiceDao {

	static Logger logger = Logger.getLogger(InsuranceServiceDaoImpl.class);
	Connection connection = null;
	PreparedStatement statement = null;

	
	/**
	 * method name : insertDetails argument : SongComposer class object return type
	 * : int author : Capgemini date : 14-02-2019
	 * 
	 * description : This method will take the model object as an argument and
	 * returns the generated id to the user
	 */
	@Override
	public String validLogIn(UserRole role) throws IQGException {
		ResultSet resultSet = null;
		String role1 = null;
		logger.info("in DAO impl class");
		logger.info("User data is : " + role.getUserName());
		connection = JdbcUtility.getConnection();
		logger.info("connection object created");
		try {
			statement = connection.prepareStatement(QueryMapper.logIn);
			logger.info("connection established..");
			statement.setString(1, role.getUserName());
			statement.setString(2, role.getPassword());

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String userName = resultSet.getString(1);
				String password = resultSet.getString(2);
				if (role.getUserName().equals(userName) && password.equals(role.getPassword())) {
					role1 = resultSet.getString(3);
					break;
				}
			}

		} catch (SQLException e) {
			throw new IQGException("unable to create statement.");
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close resultset object");
			}

			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}
		return role1;
	}

	@Override
	public int createAccount(Accounts accounts) throws IQGException {
		int accountNumber = 0;
		ResultSet resultSet = null;
		logger.info("in DAO impl class");
		logger.info("Account data is : " + accounts);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");

		try {
			statement = connection.prepareStatement(QueryMapper.InsertData);
			logger.info("connection established..");
			statement.setString(1, accounts.getInsuredName());
			statement.setString(2, accounts.getInsuredStreet());
			statement.setString(3, accounts.getInsuredCity());
			statement.setString(4, accounts.getInsuredState());
			statement.setInt(5, accounts.getInsuredZip());
			statement.setString(6, accounts.getBusinessSegment());
			statement.setString(7, accounts.getUserName());
			// System.out.println("Hello");
			statement.executeUpdate();
			logger.info("statement executed, record inserted");
			// System.out.println("Hello");
			statement = connection.prepareStatement(QueryMapper.getAccountNumber);
			resultSet = statement.executeQuery();
			logger.info("resultset cretaed");

			resultSet.next();

			accountNumber = resultSet.getInt(1);
			logger.info("Account Number : " + accountNumber);
			connection.commit();

		} catch (SQLException e) {
			logger.error(e.getMessage());
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new IQGException("Unable to rollback");
			}
			throw new IQGException("unable to create the statement");
		} finally {

			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close resultset object");
			}

			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}
		return accountNumber;
	}

	@Override
	public boolean getUserName(String userName) throws IQGException {
		boolean userFlag = false;
		ResultSet resultSet = null;
		logger.info("in DAO impl class");
		logger.info("username : " + userName);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");

		try {
			statement = connection.prepareStatement(QueryMapper.getUser);
			statement.setString(1, userName);

			resultSet = statement.executeQuery();

			resultSet.next();

			String userName1 = resultSet.getString(8);
			if (userName.equals(userName1)) {
				userFlag = true;
			}

		} catch (SQLException e) {
			throw new IQGException("unable to create statement.");
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close resultset object");
			}

			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}

		return userFlag;
	}

	@Override
	public int addProfile(UserRole role) throws IQGException {
		int record = 0;
		logger.info("AddProfile");
		logger.info("user name: " + role.getUserName());

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");

		try {
			statement = connection.prepareStatement(QueryMapper.createProfile);
			statement.setString(1, role.getUserName());
			statement.setString(2, role.getPassword());
			statement.setString(3, role.getRoleCode());

			record = statement.executeUpdate();
			logger.info("statement executed, profile record inserted");
		} catch (SQLException e) {
			throw new IQGException("Unable to create statement"+e);
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}
		return record;
	}

	@Override
	public boolean existAccount(int accountNumber) throws IQGException {

		boolean validAccountFlag = false;
		ResultSet resultSet = null;
		logger.info("in DAO impl class");
		logger.info("account number is: " + accountNumber);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");

		try {
			statement = connection.prepareStatement(QueryMapper.validAccountNumber);

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if (accountNumber == resultSet.getInt(1)) {
					validAccountFlag = true;
					break;
				}
			}
		} catch (SQLException e) {
			throw new IQGException("Unable to create statement");
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}
		return validAccountFlag;
	}

	@Override
	public String getBuisnessSegment(int accountNumber) throws IQGException {
		String buisnessSegment = "";
		ResultSet resultSet = null;
		logger.info("in DAO impl class");
		logger.info("account number is: " + accountNumber);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");

		try {
			statement = connection.prepareStatement(QueryMapper.getBuisnessSegment);
			statement.setInt(1, accountNumber);

			resultSet = statement.executeQuery();

			resultSet.next();

			buisnessSegment = resultSet.getString(7);

			logger.info("Buisness Segment: " + buisnessSegment);
		} catch (SQLException e) {
			throw new IQGException("Unable to create statement");
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}
		return buisnessSegment;
	}

	@Override
	public String getBuisnessSegmentId(String businessSegment) throws IQGException {
		String buisnessSegmentId = "";
		ResultSet resultSet = null;
		logger.info("in DAO impl class");
		logger.info("BusinessSegment number is: " + businessSegment);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");

		try {
			statement = connection.prepareStatement(QueryMapper.getBuisnessSegmentId);

			statement.setString(1, businessSegment);

			resultSet = statement.executeQuery();

			resultSet.next();
			buisnessSegmentId = resultSet.getString(1);
		} catch (SQLException e) {
			throw new IQGException("Unable to create statement");
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}
		return buisnessSegmentId;
	}

	@Override
	public List<String> getQuestions(String buisnessSegId) throws IQGException {
		List<String> questionList = new ArrayList<>();
		ResultSet resultSet = null;
		logger.info("in DAO impl class");
		logger.info("BusinessSegment Id is: " + buisnessSegId);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");
		try {
			statement = connection.prepareStatement(QueryMapper.getQuestions);

			statement.setString(1, buisnessSegId);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				questionList.add(resultSet.getString(4));
			}
		} catch (SQLException e) {
			throw new IQGException("Unable to create statement" + e.getMessage());
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}
		return questionList;
	}

	@Override
	public List<String> getAnswer(String string) throws IQGException {
		List<String> answerList = new ArrayList<>();
		ResultSet resultSet = null;
		String answer1 = "";
		String answer2 = "";
		String answer3 = "";
		logger.info("in DAO impl class");
		logger.info("Question is: " + string);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");

		try {
			statement = connection.prepareStatement(QueryMapper.getAnswer);
			statement.setString(1, string);

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				answer1 = resultSet.getString(5);
				answerList.add(answer1);
				answer2 = resultSet.getString(7);
				answerList.add(answer2);
				answer3 = resultSet.getString(9);
				answerList.add(answer3);
			}

		} catch (SQLException e) {
			throw new IQGException("Unable to create statement" + e.getMessage());
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}
		return answerList;
	}

	@Override
	public int getWeightage(String questionDesc, String option) throws IQGException {
		ResultSet resultSet = null;
		int premium = 0;
		logger.info("in DAO impl class");
		logger.info("Ans is: " + questionDesc);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");

		try {
			statement = connection.prepareStatement(QueryMapper.getAnswer);
			statement.setString(1, questionDesc);
			resultSet = statement.executeQuery();
			resultSet.next();
			int column = 5;
			while (column <= 9) {
				if (resultSet.getString(column).equals(option)) {
					premium = resultSet.getInt(column + 1);
					break;
				}
				column = column + 2;
			}
		} catch (SQLException e) {
			throw new IQGException("Unable to create statement");
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}
		return premium;
	}

	@Override
	public int insertPolicy(Policy policy) throws IQGException {
		int policyNumber = 0;
		ResultSet resultSet = null;
		logger.info("in DAO impl class");
		logger.info("Policy data is : " + policy);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");

		try {
			statement = connection.prepareStatement(QueryMapper.insertInPolicy);
			logger.info("connection established..");
			statement.setDouble(1, policy.getPolicyPremium());
			statement.setInt(2, policy.getAccountNumber());
			statement.setString(3, policy.getUserName());

			statement.executeUpdate();
			logger.info("statement executed, record inserted");
			// System.out.println("Hello");
			statement = connection.prepareStatement(QueryMapper.getPolicyNumber);
			resultSet = statement.executeQuery();
			logger.info("resultset created");

			resultSet.next();

			policyNumber = resultSet.getInt(1);
			logger.info("Policy Number : " + policyNumber);
			connection.commit();

		} catch (SQLException e) {
			logger.error(e.getMessage());
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new IQGException("Unable to rollback");
			}
			throw new IQGException("unable to create the statement");
		} finally {

			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close resultset object");
			}

			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}
		return policyNumber;
	}

	@Override
	public String getQuesId(String question) throws IQGException {
		return null;
	}

	@Override
	public List<String> getQuestionId(String buisnessSegId) throws IQGException {
		List<String> questionIdList = new ArrayList<>();
		ResultSet resultSet = null;
		logger.info("in DAO impl class");
		logger.info("BusinessSegment Id is: " + buisnessSegId);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");
		try {
			statement = connection.prepareStatement(QueryMapper.getQuestions);

			statement.setString(1, buisnessSegId);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				questionIdList.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			throw new IQGException("Unable to create statement" + e.getMessage());
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}
		return questionIdList;
	}

	@Override
	public void insertPolicyDetails(PolicyDetails policyDetails) throws IQGException {
		logger.info("in DAO impl class");
		logger.info("Policy details: " + policyDetails);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");

		try {
			statement = connection.prepareStatement(QueryMapper.insertPolicyDetails);

			statement.setInt(1, policyDetails.getPolicyNumber());
			statement.setString(2, policyDetails.getQuestionID());
			statement.setString(3, policyDetails.getAnswer());

			statement.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new IQGException("Unable to rollback");
			}
			throw new IQGException("unable to create the statement");
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}

	}

	@Override
	public List<Policy> viewPolicyDetails(String userName) throws IQGException {
		List<Policy> viewPolicy=new ArrayList<>();
		ResultSet resultSet=null;
		logger.info("in DAO impl class");

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");
		
		try {
			statement=connection.prepareStatement(QueryMapper.getPolicyDetails);
			statement.setString(1, userName);
			
			resultSet=statement.executeQuery();
			
			while (resultSet.next()) {
				int policyNumber=resultSet.getInt(1);
				double policyPremium=resultSet.getDouble(2);
				int accountNumber=resultSet.getInt(3);
				
				Policy policy=new Policy(policyNumber, policyPremium, accountNumber);
				viewPolicy.add(policy);
			}
			
		}  catch (SQLException e) {
			throw new IQGException("Unable to create statement" + e.getMessage());
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}
		return viewPolicy;
	}

	@Override
	public List<ReportGeneration> generateReport(int accountNumber1) throws IQGException {
		List<ReportGeneration> generatedList=new ArrayList<>();
		ResultSet resultSet=null;
		logger.info("in DAO impl class");

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");
		
		try {
			statement=connection.prepareStatement(QueryMapper.generatedReport);
			statement.setInt(1, accountNumber1);
			
			resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				String insuredName=resultSet.getString(1);
				String insuredStreet=resultSet.getString(2);
				String insuredCity=resultSet.getString(3);
				String insuredState=resultSet.getString(4);
				int insuredZip=resultSet.getInt(5);
				String businessSegment=resultSet.getString(6);
				String policyQuestion=resultSet.getString(7);
				String answer=resultSet.getString(8);
				double premium=resultSet.getDouble(9);
				
				ReportGeneration generation=new ReportGeneration(insuredName, insuredStreet, insuredCity, insuredState, insuredZip, businessSegment, policyQuestion, answer, premium);
				
				generatedList.add(generation);
			}
		} catch (SQLException e) {
			throw new IQGException("Unable to create statement" + e.getMessage());
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}
		return generatedList;
	}

	@Override
	public List<Policy> getPolicy(String userName) throws IQGException {
		Policy policy=null;
		ResultSet resultSet=null;
		List<Policy> policyList=new ArrayList<>();
		logger.info("in DAO impl class");

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");
		
		try {
			statement=connection.prepareStatement(QueryMapper.getPolicy);
			statement.setString(1, userName);
			
			resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
			int policyNumber=resultSet.getInt(1);
			double policyPremium=resultSet.getDouble(2);
			int accountNumber=resultSet.getInt(3);
			policy=new Policy(policyNumber, policyPremium, accountNumber);
			policyList.add(policy);
			}
			
		}  catch (SQLException e) {
			throw new IQGException("You have not created any policy...");
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}
		return policyList;
	}

	@Override
	public List<Policy> viewPolicyDetails() throws IQGException {
		List<Policy> viewPolicy=new ArrayList<>();
		ResultSet resultSet=null;
		logger.info("in DAO impl class");

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");
		
		try {
			statement=connection.prepareStatement(QueryMapper.getAllPolicyDetails);
			
			resultSet=statement.executeQuery();
			
			while (resultSet.next()) {
				int policyNumber=resultSet.getInt(1);
				double policyPremium=resultSet.getDouble(2);
				int accountNumber=resultSet.getInt(3);
				
				Policy policy=new Policy(policyNumber, policyPremium, accountNumber);
				viewPolicy.add(policy);
			}
			
		}  catch (SQLException e) {
			throw new IQGException("Unable to create statement" + e.getMessage());
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}
		return viewPolicy;
	}

	@Override
	public String getBusinessSegment(int businessSeg) throws IQGException {
		ResultSet resultSet = null;
		String businessSegment="";
		logger.info("in DAO impl class");
		logger.info("Policy data is : " + businessSeg);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");
		//System.out.println(businessSeg);
		
		try {
			statement=connection.prepareStatement(QueryMapper.getBusinessSegment);
			
			resultSet=statement.executeQuery();
			resultSet.next();
			if(businessSeg==1) {
				businessSegment=resultSet.getString(3);
			}
			if(businessSeg==2) {
				resultSet.next();
				businessSegment=resultSet.getString(3);
			}
			if(businessSeg==3) {
				resultSet.next();
				resultSet.next();
				businessSegment=resultSet.getString(3);
			}
			if(businessSeg==4) {
				resultSet.next();
				resultSet.next();
				resultSet.next();
				businessSegment=resultSet.getString(3);
			}
			
			
			
		} catch (SQLException e) {
			throw new IQGException("Unable to create statement" + e.getMessage());
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new IQGException("unable to close connection object");
			}

		}
		
		return businessSegment;
	}

	@Override
	public boolean checkExist(String userName) throws IQGException {
		ResultSet resultSet = null;
		boolean existFlag=true;
		logger.info("in DAO impl class");
		logger.info("Policy data is : " + userName);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");
		
		try {
			statement=connection.prepareStatement(QueryMapper.existUserName);
			
			resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				if(userName.equals(resultSet.getString(1))) {
					existFlag=false;
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existFlag;
	}

}
