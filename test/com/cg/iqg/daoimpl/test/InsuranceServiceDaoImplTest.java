package com.cg.iqg.daoimpl.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.iqg.daoimpl.InsuranceServiceDaoImpl;
import com.cg.iqg.model.Accounts;
import com.cg.iqg.model.UserRole;
import com.cg.iqgexception.IQGException;

@SuppressWarnings("unused")
public class InsuranceServiceDaoImplTest {

	InsuranceServiceDaoImpl dao=null;
	@Before
	public void setUp() throws Exception {
		dao=new InsuranceServiceDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
		dao=null;
	}

	@Test
	public void testValidLogIn() {
		UserRole role=new UserRole("sushanta97", "abc123");
		try {
			String userRole=dao.validLogIn(role);
			assertNotNull(userRole);
		} catch (IQGException e) {
			System.out.println(e.getMessage());
		}
	}

	/*@Test
	public void testCreateAccount() {
		Accounts accounts=new Accounts("Sushanta Ray", "Gachibowli", "Hyderabad", "Telengana", 12345, "Business Auto", "sushanta97");
		
		try {
			int accountValidation = dao.createAccount(accounts);
			assertNotNull(accountValidation);
		} catch (IQGException e) {
			System.out.println(e.getMessage());
		}
	}*/

	@Test
	public void testAddProfile() {
		UserRole role1=new UserRole("rohit97", "abc123", "Insured");
		try {
			int addProfileTest = dao.addProfile(role1);
			assertNotNull(addProfileTest);
		} catch (IQGException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testCheckExist() {
		
		try {
			boolean userExistTest = dao.checkExist("rohit97");
			assertFalse(userExistTest);
		} catch (IQGException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
