package com.cg.iqg.dao;

public interface QueryMapper {

	public static final String logIn = "select * from User_Role where user_name=? and password=?";
	
	public static final String InsertData="insert into Accounts values(acc_seq.nextval,?,?,?,?,?,?,?)";
	
	public static final String getAccountNumber="select acc_seq.currval from dual";
	
	public static final String getUser="select * from Accounts where user_name=?";
	
	public static final String createProfile="INSERT INTO user_role values(?,?,?)";
	
	public static final String getBuisnessSegment="SELECT * FROM accounts WHERE account_number=?";
	
	public static final String getBuisnessSegmentId="SELECT * FROM business_segment WHERE BUS_SEG_NAME=?";
	
	public static final String getQuestions="SELECT * from policy_questions WHERE BUS_SEG_ID=?";
	
	public static final String getAnswer="SELECT * from policy_questions WHERE POL_QUES_DESC=?";
	
	public static final String insertInPolicy = "INSERT INTO policy values(policy_number_seq.nextval, ?, ?,?)";
	
	public static final String getPolicyNumber ="select policy_number_seq.currval from dual";
	
	public static final String insertPolicyDetails="INSERT INTO policy_details VALUES(?,?,?)";
	
	public static final String validAccountNumber="SELECT * FROM accounts";
	
	public static final String getPolicyDetails="SELECT * FROM policy WHERE agent_user=?";
	
	public static final String getAllPolicyDetails="SELECT * FROM policy";
	
	public static final String generatedReport="SELECT a.insured_name,a.insured_street,a.insured_city,a.insured_state,a.insured_zip,a.business_segment,p3.Pol_Ques_Desc,p2.answer,p1.policy_premium FROM accounts a,policy p1,policy_details p2 ,policy_questions p3 WHERE a.account_number=p1.account_number and p1.policy_number=p2.policy_number and  p2.question_id=p3.pol_ques_id and a.account_number=?";
	
	public static final String getPolicy="select p.policy_number,p.policy_premium,a.account_number from accounts a, policy p where a.account_number=p.account_number and a.user_name=?";
	
	public static final String getBusinessSegment="SELECT * FROM business_segment ";
	
	public static final String existUserName="SELECT * FROM user_role";
}
