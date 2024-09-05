package com.sesto.onlineshop.menu.impl;

import java.util.Scanner;

import com.sesto.onlineshop.configs.ApplicationContext;
import com.sesto.onlineshop.enteties.User;
import com.sesto.onlineshop.menu.Menu;
import com.sesto.onlineshop.services.UserManagementService;
import com.sesto.onlineshop.services.impl.DefaultUserManagementService;

public class SignInMenu implements Menu {
	private static final String ENTER_PASSWORD_MESSAGE = "Please enter your password.";
	private static final String ENTER_EMAIL_MESSAGE = "Please enter your email.";
	private static final String ERROR_IVALID_INPUT_MESSAGE = "Please enter a valid string";
	private static final String ERROR_INVALID_CREDENTIALS_MESSAGE ="Unfortunately, such login and password doesn’t exist";

	private ApplicationContext context;
	private UserManagementService userManagementService;
	private int signInProccessIndex = 1;
	private String userEmail;
	private String userPassword;
	{
		context = ApplicationContext.getInstance();
		userManagementService = DefaultUserManagementService.getInstance();
	}

	@Override
	public void start() {
		System.out.println("*** SIGN IN ***");
		while (signInProccessIndex < 3) {
			printMenuHeader();
			Scanner in = new Scanner(System.in);
			String userInput = in.next();
			if (userInput == null || userInput.length() == 0) {
				System.out.println(ERROR_IVALID_INPUT_MESSAGE);
				continue;
			}
			switch (signInProccessIndex) {
			case 1:
				this.userEmail = userInput;
				break;
			case 2:
				this.userPassword = userInput;
				break;
			default:
				break;
			}

			signInProccessIndex++;
			if (signInProccessIndex > 2) {
				User myUser = userManagementService.getUserByEmail(userEmail);
				
				if (myUser != null && myUser.getPassword().equals(userPassword) ) {

					System.out.println("Glad to see you back "+ myUser.getFirstName() + " " + myUser.getLastName());
					context.setLoggedInUser(myUser);
					context.getSessionCart().clear();
					context.getMainMenu().start();
					break;
				} else {
					System.out.println(ERROR_INVALID_CREDENTIALS_MESSAGE);
					context.getMainMenu().start();
					break;
				}

			}
		}

	}

	@Override
	public void printMenuHeader() {
		switch (signInProccessIndex) {

		case 1:
			System.out.println(ENTER_EMAIL_MESSAGE);
			
			break;
		case 2:
			System.out.println(ENTER_PASSWORD_MESSAGE);
			break;
		default:
			break;
		}
	}

}
