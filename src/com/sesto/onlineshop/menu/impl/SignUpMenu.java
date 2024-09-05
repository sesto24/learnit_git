package com.sesto.onlineshop.menu.impl;

import java.util.Scanner;

import com.sesto.onlineshop.configs.ApplicationContext;
import com.sesto.onlineshop.enteties.impl.DefaultUser;
import com.sesto.onlineshop.menu.Menu;
import com.sesto.onlineshop.services.UserManagementService;
import com.sesto.onlineshop.services.impl.DefaultUserManagementService;
import com.sesto.onlineshop.enteties.User;

public class SignUpMenu implements Menu {
	private static final String ENTER_FIRST_NAME_MESSAGE = "Please enter your first name.";
	private static final String ENTER_LAST_NAME_MESSAGE = "Please enter your last name.";
	private static final String ENTER_PASSWORD_MESSAGE = "Please enter your password.";
	private static final String ENTER_EMAIL_MESSAGE = "Please enter your email.";
	private static final String ERROR_IVALID_INPUT_MESSAGE = "Please enter a valid string";
	private static final String NEW_USER_CREATER_MESSAGE = "new user is created";
	private UserManagementService userManagementService;
	private ApplicationContext context;
	
	int registrationProgressIndex = 1;

	{
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		System.out.println("*** SIGN UP ***");
		DefaultUser newUser = new DefaultUser();
		while (registrationProgressIndex < 5) {
			printMenuHeader();
			Scanner in = new Scanner(System.in);
			String userInput = in.next();
			if (userInput == null || userInput.length() == 0) {
				System.out.println(ERROR_IVALID_INPUT_MESSAGE);
				continue;
			}
			switch (registrationProgressIndex) {
			case 1:
				newUser.setFirstName(userInput);
				break;
			case 2:
				newUser.setLastName(userInput);
				break;
			case 3:
				newUser.setPassword(userInput);
				break;
			case 4:			
					newUser.setEmail(userInput);	
				break;
			default:
				break;
			}

			registrationProgressIndex++;
			if (registrationProgressIndex > 4) {
				String registrationResultString =  userManagementService.registerUser(newUser);
				if (registrationResultString==null || registrationResultString=="") {
					

					System.out.println(NEW_USER_CREATER_MESSAGE);
					context.setLoggedInUser(newUser);
					context.getMainMenu().start();
					break;
				}
				else {
					System.out.println(registrationResultString);
					context.getMainMenu().start();
					break;				
				}
				
			}
		}

	}

	@Override
	public void printMenuHeader() {
		switch (registrationProgressIndex) {
		case 1: 
			System.out.println(ENTER_FIRST_NAME_MESSAGE);
			break;		
		case 2: 
			System.out.println(ENTER_LAST_NAME_MESSAGE);
			break;		
		case 3: 
			System.out.println(ENTER_PASSWORD_MESSAGE);
			break;		
		case 4: 
			System.out.println(ENTER_EMAIL_MESSAGE);
			break;			
		default:
			break;
		}
	}

}
