package com.cg.iqg.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.iqg.classes.Admin;
import com.cg.iqg.classes.Agent;
import com.cg.iqg.classes.Insured;
import com.cg.iqg.model.UserRole;
import com.cg.iqg.service.InsuranceService;
import com.cg.iqg.serviceimpl.InsuranceServiceImpl;
import com.cg.iqgexception.IQGException;

public class IQGMain {
	static Logger logger = Logger.getLogger(IQGMain.class);
	
	
	public void logIn() {
		PropertyConfigurator.configure("resources/log4j.properties");
		logger.info("log4j file configured..");

		Insured insured = new Insured();
		Admin admin = new Admin();
		Agent agent = new Agent();
		InsuranceService service = new InsuranceServiceImpl();
		System.out.println("*********************Insurance Quote Generation System************************");
		Scanner scanner=null;

		boolean choiceFlag = false;
		do {

			scanner = new Scanner(System.in);
			System.out.println("1. Login");
			System.out.println("2. Exit");
			System.out.println("Enter choice: ");
			int input = 0;
			try {
				input = scanner.nextInt();
				choiceFlag = true;
				switch (input) {
				case 1:
					scanner.nextLine();
					String userName = "";
					String password = "";
					String validUser = "";
					System.out.println("Enter Username: ");
					userName = scanner.nextLine();
					System.out.println("Enter password: ");
					password = scanner.nextLine();
					UserRole role = new UserRole(userName, password);
					try {
						validUser = service.validUser(role);
						if (validUser == null) {
							System.err.println("User not found");
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
							System.out.println("You are logged in as " + validUser);

							validUser = validUser.trim();
							if (validUser.equalsIgnoreCase("Insured")) {
								insured.insured(userName);

							} else if (validUser.equalsIgnoreCase("Agent")) {
								agent.agent(userName);
							} else {
								admin.admin(userName);
							}
						}
					} catch (IQGException e) {
						System.err.println(e.getMessage());
					}
					break;
				case 2:
					System.out.println("Thank you visit again.....");
					System.exit(0);
				default:
					System.err.println("Enter choice(1-2)");
					choiceFlag=false;
					break;
				}

			} catch (InputMismatchException e) {

				System.err.println("Enter only digits(1-2)");
				choiceFlag = false;
			}

		} while (!choiceFlag);
		scanner.close();
	}

	

	public static void main(String[] args) {
		IQGMain main=new IQGMain();
		main.logIn();
	}
}
