package com.sesto.onlineshop.menu.impl;

import java.util.Scanner;

import com.sesto.onlineshop.configs.ApplicationContext;
import com.sesto.onlineshop.enteties.Product;
import com.sesto.onlineshop.enteties.User;
import com.sesto.onlineshop.menu.Menu;

public class SettingsMenu implements Menu {

	private static final String SETTINGS = "1. Change Password" + System.lineSeparator() + "2. Change Email";

	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		User loggedInUser = context.getLoggedInUser();
		if (loggedInUser==null) {
			System.out.println("Please, log in or create new account to change your account settings");
			context.getMainMenu().start();
			return;
		}
		System.out.println("*** SETTINGS ***");
		printMenuHeader();
		Scanner in = new Scanner(System.in);
		String userInput = in.next();
		
		int selectionNumber=0;
		try {
			selectionNumber=	Integer.parseInt(userInput);
			switch (selectionNumber) {
			case 1: 
				System.out.println("Type the new password");
				userInput = in.next();
				loggedInUser.setPassword(userInput);
				System.out.println("Your password has been successfully changed");
				context.getMainMenu().start();
				break;
			
			case 2:
				System.out.println("Type the new email");
				userInput = in.next();				
				loggedInUser.setEmail(userInput);
				System.out.println("Your email has been successfully changed");
				context.getMainMenu().start();
				break;
			default:
				printMenuHeader();
			}
			
		} catch (Exception e) {
			if (userInput=="menu") {
				context.getMainMenu().start();
			} else {
				printMenuHeader();
			}
				
		}
			
	
	}

	@Override
	public void printMenuHeader() {

		System.out.println(SETTINGS);
	}

}
