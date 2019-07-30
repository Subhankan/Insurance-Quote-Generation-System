package com.cg.iqg.classes;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.iqg.model.Policy;
import com.cg.iqg.presentation.IQGMain;
import com.cg.iqg.service.InsuranceService;
import com.cg.iqg.serviceimpl.InsuranceServiceImpl;
import com.cg.iqg.support.AccountCreation;
import com.cg.iqg.support.ViewPolicy;
import com.cg.iqgexception.IQGException;

public class Insured {

	AccountCreation accountCreation = new AccountCreation();
	ViewPolicy viewPolicy = new ViewPolicy();
	IQGMain main = new IQGMain();

	public void insured(String userName) {
		Scanner scanner = null;
		InsuranceService service = new InsuranceServiceImpl();
		int choice = 0;
		boolean choiceFlag = false;
		do {
			scanner = new Scanner(System.in);
			System.out.println("1.Create Account");
			System.out.println("2.View Policy");
			System.out.println("3.Log out.");
			System.out.println("4.Exit");
			System.out.println();
			System.out.println("Enter Choice: ");
			try {
				choice = scanner.nextInt();
				if (choice == 1) {
					boolean exist = service.getUserName(userName);
					System.out.println("Your account is already created....");
					if (exist == true) {
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
					}

				}

				else if (choice == 2) {
					// Policy policy = null;
					ViewPolicy viewPolicy = new ViewPolicy();
					try {
						List<Policy> policyList = viewPolicy.getPolicy(userName);
						if (!policyList.isEmpty()) {
							System.out.println("*************Policy View*******************");
							String format = String.format("%-20s %-20s %s", "Policy Number", "Premium Amount",
									"Account Number");
							System.out.println(format);
							for (Policy policy : policyList) {
								System.out.println(String.format("%-20s %-20s %s", policy.getPolicyNumber(),
										policy.getPolicyPremium(), policy.getAccountNumber()));
							}
						} else {
							System.err.println("No data found");
						}

					} catch (IQGException e) {
						System.err.println(e.getMessage());
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

				} else if (choice == 3) {
					main.logIn();
				} else if (choice == 4) {
					System.out.println("Thank you visit again.....");
					System.exit(0);
				}

				else {
					System.out.println("Enter choice (1-2)");
					choiceFlag = false;

				}

			} catch (InputMismatchException e) {
				System.err.println("Enter digits only....");
				choiceFlag = false;
			} catch (Exception e) {
				int accountNumber = accountCreation.createAccount(userName);
				System.out.println("Account created with account number: " + accountNumber);
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
			}

		} while (!choiceFlag);
		scanner.close();
	}
}
