package com.cg.iqg.classes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.iqg.model.Policy;
import com.cg.iqg.model.ReportGeneration;
import com.cg.iqg.presentation.IQGMain;
import com.cg.iqg.service.InsuranceService;
import com.cg.iqg.serviceimpl.InsuranceServiceImpl;
import com.cg.iqg.support.AccountCreation;
import com.cg.iqg.support.CreatePolicy;
import com.cg.iqg.support.GenerateReport;
import com.cg.iqg.support.ProfileCreation;
import com.cg.iqg.support.ViewPolicy;
import com.cg.iqgexception.IQGException;

public class Admin {

	AccountCreation accountCreation = new AccountCreation();
	InsuranceService service = new InsuranceServiceImpl();
	ProfileCreation profileCreation = new ProfileCreation();
	CreatePolicy createPolicy = new CreatePolicy();
	GenerateReport generateReport = new GenerateReport();
	IQGMain main=new IQGMain();

	public void admin(String userName) {
		Scanner scanner = null;
		int choice = 0;
		boolean choiceFlag = false;
		do {
			scanner = new Scanner(System.in);
			System.out.println("1.Create new profile.");
			System.out.println("2.Create Account");
			System.out.println("3.Policy creation");
			System.out.println("4.View policy");
			System.out.println("5.Report generation");
			System.out.println("6.Log out");
			System.out.println("7.Exit");
			System.out.println();
			System.out.println("Enter choice: ");

			try {
				choice = scanner.nextInt();
				choiceFlag = true;
				switch (choice) {
				case 1:
					try {
						profileCreation.profileCreation();
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
					} catch (IQGException e) {
						System.err.println(e.getMessage());
					}

					break;
				case 2:
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
				case 3:
					boolean accountFlag = false;
					int accountNumber = 0;
					// int premiumNumber = 0;
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
				case 4:
					ViewPolicy viewPolicy = new ViewPolicy();
					List<Policy> list = new ArrayList<>();
					try {

						list = viewPolicy.viewPolicyDetails();
						if (!list.isEmpty()) {
							System.out.println("*************Policy View*******************");
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
				case 5:
					boolean reportFlag = false;
					int accountNumber1 = 0;
					List<ReportGeneration> reportList = new ArrayList<>();
					do {
						scanner = new Scanner(System.in);
						System.out.println("Enter account number for which you want to generate report: ");
						try {
							accountNumber1 = scanner.nextInt();
							try {
								boolean validAccountNumber = service.validAccountNumber(accountNumber1);
								if (validAccountNumber == true) {
									reportFlag = true;
									boolean existAccount = service.existAccount(accountNumber1);
									if (existAccount) {
										reportList = generateReport.generateReport(accountNumber1);
										if (!reportList.isEmpty()) {
											System.out.println(
													"******************Generated Report************************");
											String format = String.format(
													"%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %s ",
													"Insured Name", "Insured Street", "Insured City", "Insured State",
													"Insured Zip", "Business Segment", "Policy Question", "Answer",
													"Premium");
											System.out.println(format);

											for (ReportGeneration generation : reportList) {
												System.out.println(String.format(
														"%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %s ",
														generation.getInsuredName(), generation.getInsuredStreet(),
														generation.getInsuredCity(), generation.getInsuredState(),
														generation.getInsuredZip(), generation.getBusinessSegment(),
														generation.getPolicyQuestion(), generation.getPolicyAnswer(),
														generation.getPolicyPremium()));
											}
										} else {
											System.err.println("No data found..");
										}

									} else {
										System.err.println("Account number does not exist.....");
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
								} else {
									System.err.println("Account number should be of 7 digits..");
								}
							} catch (IQGException e) {
								System.err.println(e.getMessage());
							}
						} catch (InputMismatchException e) {
							System.err.println("Enter digits only...");
							reportFlag = false;
						}
					} while (!reportFlag);
					break;
				case 6:
					main.logIn();
				case 7:
					System.out.println("Thank you visit again...");
					System.exit(0);
				default:
					System.err.println("Enter choice (1-7)");
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
