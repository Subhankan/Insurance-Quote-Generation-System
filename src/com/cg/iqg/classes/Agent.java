package com.cg.iqg.classes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.iqg.model.Policy;
import com.cg.iqg.presentation.IQGMain;
import com.cg.iqg.service.InsuranceService;
import com.cg.iqg.serviceimpl.InsuranceServiceImpl;
import com.cg.iqg.support.AccountCreation;
import com.cg.iqg.support.CreatePolicy;
import com.cg.iqg.support.GenerateReport;
import com.cg.iqg.support.ProfileCreation;
import com.cg.iqg.support.ViewPolicy;
import com.cg.iqgexception.IQGException;

public class Agent {

	AccountCreation accountCreation = new AccountCreation();
	InsuranceService service = new InsuranceServiceImpl();
	ProfileCreation profileCreation = new ProfileCreation();
	CreatePolicy createPolicy = new CreatePolicy();
	GenerateReport generateReport = new GenerateReport();
	IQGMain main=new IQGMain();

	public void agent(String userName) {
		Scanner scanner = null;
		int choice = 0;
		boolean choiceFlag = false;
		do {
			scanner = new Scanner(System.in);
			System.out.println("1.Create Account");
			System.out.println("2.Policy creation");
			System.out.println("3.View policy");
			System.out.println("4.Log out");
			System.out.println("5.Exit");
			System.out.println();
			System.out.println("Enter choice: ");

			try {
				choice = scanner.nextInt();
				choiceFlag = true;
				switch (choice) {
				case 1:
					try {
						int accountNumber = accountCreation.createAccount(userName);
						System.out.println("Account created with account number " + accountNumber);

						boolean continueFlag = false;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Do you want to still continue (Y/N): ");
							String wish = scanner.next();
							wish = wish.toLowerCase();
							if (wish.equals("y")) {

								choiceFlag = false;
								continueFlag = true;
							} else {
								if (wish.equals("n")) {
									choiceFlag = true;
									continueFlag = true;
								} else {
									choiceFlag = true;
									continueFlag = false;
									System.err.println("Enter Y or N.");
								}
							}
						} while (!continueFlag);

					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					boolean accountFlag = false;
					int accountNumber = 0;

					do {
						scanner = new Scanner(System.in);
						System.out.println("Enter account number for which you want to create policy: ");
						try {
							accountNumber = scanner.nextInt();
							try {
								boolean validAccountNumber = service.validAccountNumber(accountNumber);
								if (validAccountNumber == true) {
									accountFlag = true;
									boolean existAccount = service.existAccount(accountNumber);
									if (existAccount) {
										createPolicy.createPolicy(accountNumber,userName);
									} else {
										System.err.println("Account number does not exist.....");
									}
									boolean continueFlag = false;
									do {

										System.out.println("Do you want to still continue (Y/N): ");

										scanner = new Scanner(System.in);
										String wish = "";
										wish = scanner.nextLine();
										wish = wish.toLowerCase();
										if (wish.equals("y")) {

											choiceFlag = false;
											continueFlag = true;
										} else {
											if (wish.equals("n")) {
												choiceFlag = true;
												continueFlag = true;
											} else {
												choiceFlag = true;
												continueFlag = false;
												System.err.println("Enter Y or N.");
											}
										}
									} while (!continueFlag);
								} else {
									System.err.println("Account number should be of 7 digits..");
								}
							} catch (IQGException e) {
								System.err.println(e.getMessage());
							}
						} catch (InputMismatchException e) {
							System.err.println("Enter digits only...");
							accountFlag = false;
						}
					} while (!accountFlag);
					break;
				case 3:
					ViewPolicy viewPolicy = new ViewPolicy();
					List<Policy> list = new ArrayList<>();
					try {

						list = viewPolicy.viewPolicyDetails(userName);
						if (!list.isEmpty()) {
							System.out.println("***********************Policy View******************************");
							String format = String.format("%-20s %-20s %s", "Policy Number", "Premium Amount",
									"Account Number");
							System.out.println(format);
							for (Policy policy : list) {
								System.out.println(String.format("%-20s %-20s %s", policy.getPolicyNumber(),
										policy.getPolicyPremium(), policy.getAccountNumber()));
							}
						} else {
							System.out.println("No data Found...");
						}
						boolean continueFlag = false;
						do {
							System.out.println("Do you want to still continue (Y/N): ");
							String wish = scanner.next();
							wish = wish.toLowerCase();
							if (wish.equals("y")) {

								choiceFlag = false;
								continueFlag = true;
							} else {
								if (wish.equals("n")) {
									choiceFlag = true;
									continueFlag = true;
								} else {
									choiceFlag = true;
									continueFlag = false;
									System.err.println("Enter Y or N.");
								}
							}
						} while (!continueFlag);

					} catch (IQGException e) {
						System.err.println(e.getMessage());
						choiceFlag = false;
					}
					break;
				case 4:
					main.logIn();
				case 5:
					System.out.println("Thank you visit again........");
					System.exit(0);
				default:
					System.err.println("Enter choice (1-4)");
					choiceFlag = false;
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Enter digits only..");
				choiceFlag = false;
			}

		} while (!choiceFlag);
		System.out.println("Thank you visit again");
		scanner.close();
	}
}
