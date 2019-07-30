package com.cg.iqg.support;

import java.util.Scanner;

import com.cg.iqg.model.UserRole;
import com.cg.iqg.service.InsuranceService;
import com.cg.iqg.serviceimpl.InsuranceServiceImpl;
import com.cg.iqgexception.IQGException;

public class ProfileCreation {

	@SuppressWarnings("resource")
	public void profileCreation() throws IQGException {

		InsuranceService service = new InsuranceServiceImpl();
		Scanner scanner = null;
		String userName = "";
		String password = "";
		String userRole = "";
		boolean userFlag = false;
		boolean passwordFlag = false;
		boolean userRoleFlag = false;

		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter user name: ");
			userName = scanner.nextLine();
			try {
				boolean validUserName = service.checkUserName(userName);
				if (validUserName == true) {
					try {
						boolean alreadyExistUserName = service.checkExist(userName);
						if (alreadyExistUserName == false) {
							System.err.println("This user Name already exist");
							userFlag = false;
						}else {
							userFlag=true;
						}
					} catch (IQGException e) {
						System.err.println(e.getMessage());
					}

				}
			} catch (IQGException e) {
				System.err.println(e.getMessage());
				userFlag = false;
			}

		} while (!userFlag);

		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter password: ");
			password = scanner.nextLine();
			try {
				boolean validPassword = service.checkPassword(password);
				if (validPassword == true) {
					passwordFlag = true;
				}
			} catch (IQGException e) {
				System.err.println(e.getMessage());
				passwordFlag = false;
			}
		} while (!passwordFlag);

		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter user role: ");
			userRole = scanner.nextLine();
			try {
				boolean validUserRole = service.checkUserRole(userRole);
				if (validUserRole == true) {
					userRoleFlag = true;
				}
			} catch (IQGException e) {
				System.err.println(e.getMessage());
				userRoleFlag = false;
			}
		} while (!userRoleFlag);

		UserRole role = new UserRole(userName, password, userRole);
		try {
			int record = service.addProfile(role);
			System.out.println(record + " profile inserted.");
		} catch (IQGException e) {
			System.err.println(e.getMessage());
		}

		// scanner.close();

	}

}
