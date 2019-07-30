package com.cg.iqg.support;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.iqg.model.Policy;
import com.cg.iqg.model.PolicyDetails;
import com.cg.iqg.service.InsuranceService;
import com.cg.iqg.serviceimpl.InsuranceServiceImpl;
import com.cg.iqgexception.IQGException;

public class CreatePolicy {
	InsuranceService service = new InsuranceServiceImpl();

	public void createPolicy(int accountNumber,String userName) {
		double premiumSum = 0;
		List<String> quesList = null;
		List<String> quesIdList = null;
		Scanner scanner = null;
		try {
			String businessSegment = service.getBuisnessSegment(accountNumber);

			String buisnessSegId = service.getBuisnessSegmentId(businessSegment);

			quesList = service.getQuestions(buisnessSegId);
			quesIdList = service.getQuestionId(buisnessSegId);

			String answer[] = new String[quesList.size()];
			String quesId[] = new String[quesList.size()];
			int size = quesList.size();
			int choice = 0;
			boolean choiceFlag = false;
			List<String> ansList = new ArrayList<>();
			System.out.println("*****************List of Questions*********************");
			for (int i = 0; i < size; i++) {
				System.out.println("Question " + (i + 1) + " " + quesList.get(i));
				ansList = service.getAnswer(quesList.get(i));
				System.out.println("1." + ansList.get(0));
				System.out.println("2." + ansList.get(1));
				System.out.println("3." + ansList.get(2));
				do {
					scanner = new Scanner(System.in);
					System.out.println("Give your answer(1 or 2 or 3): ");
					try {
						choice = scanner.nextInt();
						choiceFlag = true;

						switch (choice) {
						case 1:
							premiumSum = premiumSum + (int) service.getWeightage(quesList.get(i), ansList.get(0));
							answer[i] = ansList.get(0);
							break;
						case 2:
							premiumSum = premiumSum + (int) service.getWeightage(quesList.get(i), ansList.get(1));
							answer[i] = ansList.get(1);
							break;
						case 3:
							if(ansList.get(2) == null) {
			
								ansList.add(2, "null");
							}
							premiumSum = premiumSum + (int) service.getWeightage(quesList.get(i), ansList.get(2));
							answer[i] = ansList.get(2);
							break;
						default:
							System.err.println("Enter choice within 1 to 3!!!");
							;
							choiceFlag = false;
							break;
						}
					} catch (InputMismatchException e) {
						System.err.println("Enter digits only..");
						choiceFlag = false;
					}

				} while (!choiceFlag);

			}
			Policy policy = new Policy(premiumSum, accountNumber,userName);
			try {
				int policyNumber = service.insertPolicy(policy);
				System.out.println(
						"Policy Created With Policy number: " + policyNumber + " And Premium is: " + premiumSum);
				int k = 0;
				for (String questionId : quesIdList) {
					quesId[k] = questionId;
					k++;
				}
				PolicyDetails policyDetails = null;
				for (int i = 0; i < size; i++) {
					policyDetails = new PolicyDetails(policyNumber, quesId[i], answer[i]);
					try {
						service.insertPolicyDetails(policyDetails);
					} catch (IQGException e) {
						System.err.println(e.getMessage());
					}
				}
				System.out.println("Policy details inserted.....");
			} catch (IQGException e) {
				System.err.println(e.getMessage());
			}
		} catch (IQGException e) {
			System.err.println(e.getMessage());
		}

	}

}
