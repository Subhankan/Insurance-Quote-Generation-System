package com.cg.iqg.support;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.iqg.model.Accounts;
import com.cg.iqg.service.InsuranceService;
import com.cg.iqg.serviceimpl.InsuranceServiceImpl;
import com.cg.iqgexception.IQGException;

public class AccountCreation {

	Scanner scanner = null;
	int accountNumber = 0;
	InsuranceService service = new InsuranceServiceImpl();

	public int createAccount(String userName) {
		String insuredName = "";
		String insuredStreet = "";
		String insuredCity = "";
		String insuredState = "";
		int insuredZip = 0;
		int businessSeg =0;
		String businessSegment="";
		boolean nameFlag = false;
		boolean streetFlag = false;
		boolean cityFlag = false;
		boolean stateFlag = false;
		boolean segFlag = false;
		boolean zipFlag = false;
		boolean validZipFlag = false;
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter insured Name: ");
			insuredName = scanner.nextLine();
			try {
				boolean validFlag = service.validName(insuredName);
				if (validFlag == true) {
					nameFlag = true;
				}

			} catch (IQGException e) {
				System.err.println(e.getMessage());
				nameFlag = false;
			}
		} while (!nameFlag);

		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter insured Street: ");
			insuredStreet = scanner.nextLine();
			try {
				boolean validFlag = service.validStreet(insuredStreet);
				if (validFlag == true) {
					streetFlag = true;
				}
			} catch (IQGException e) {
				System.err.println(e.getMessage());
				stateFlag = false;
			}
		} while (!streetFlag);

		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter insured City: ");
			insuredCity = scanner.nextLine();
			try {
				boolean validFlag = service.validCity(insuredCity);
				if (validFlag == true) {
					cityFlag = true;
				}
			} catch (IQGException e) {
				System.err.println(e.getMessage());
				cityFlag = false;
			}
		} while (!cityFlag);

		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter insured State: ");
			insuredState = scanner.nextLine();
			try {
				boolean validFlag = service.validState(insuredState);
				if (validFlag == true) {
					stateFlag = true;
				}
			} catch (IQGException e) {
				System.err.println(e.getMessage());
				stateFlag = false;
			}
		} while (!stateFlag);

		do {
			System.out.println("Enter insured Zip: ");
			scanner = new Scanner(System.in);
			try {

				insuredZip = scanner.nextInt();
				zipFlag = true;
				do {
					try {
						boolean validFlag = service.validZip(insuredZip);
						//System.out.println(validFlag);
						if (validFlag == true) {
							validZipFlag = true;
							//zipFlag = false;
						}else {
							zipFlag = false;
						}
					} catch (IQGException e) {
						System.err.println(e.getMessage());
						validZipFlag =true;
						zipFlag=false;
					}

				} while (!validZipFlag);

			} catch (InputMismatchException e) {

				System.err.println("Enter digits only");
				zipFlag = false;
			}

		} while (!zipFlag);

		scanner.nextLine();
		do {
			scanner=new Scanner(System.in);
			System.out.println("1.Business Auto");
			System.out.println("2.Restaurant");
			System.out.println("3.Apartment");
			System.out.println("4.General Merchant");
			System.out.println();
			System.out.println("Enter choice: ");
			try {
				businessSeg=scanner.nextInt();
				segFlag=true;
				switch (businessSeg) {
				case 1:
					try {
					businessSegment=service.getBusinessSegment(businessSeg);
					}catch (IQGException e) {
						System.err.println(e.getMessage());
					}
					break;
				case 2:
					try {
						businessSegment=service.getBusinessSegment(businessSeg);
						}catch (IQGException e) {
							System.err.println(e.getMessage());
						}
					break;
				case 3:
					try {
						businessSegment=service.getBusinessSegment(businessSeg);
						}catch (IQGException e) {
							System.err.println(e.getMessage());
						}
					break;
				case 4:
					try {
						businessSegment=service.getBusinessSegment(businessSeg);
						}catch (IQGException e) {
							System.err.println(e.getMessage());
						}
					break;
					
				default:
					System.err.println("Enter choice(1-4)");
					segFlag=false;
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Enter digits only.....");
				segFlag=false;
			}
		}while(!segFlag);
		
		Accounts accounts = new Accounts(insuredName, insuredStreet, insuredCity, insuredState, insuredZip, businessSegment,
				userName);

		try {
			accountNumber = service.createAccount(accounts);

		} catch (IQGException e) {
			System.err.println(e.getMessage());
		}

		return accountNumber;

	}
}
