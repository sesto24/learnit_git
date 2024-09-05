package com.sesto.onlineshop.menu.impl;

import java.util.Scanner;

import com.sesto.onlineshop.configs.ApplicationContext;
import com.sesto.onlineshop.enteties.User;

import com.sesto.onlineshop.menu.Menu;

public class MainMenu implements Menu {

	public static final String MENU_COMMAND = "menu";

	private static final String MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER = "Please, enter number in console to proceed."
			+ System.lineSeparator() + "1. Sign Up" + System.lineSeparator() + "2. Sign In" + System.lineSeparator()
			+ "3. Product Catalog" + System.lineSeparator() + "4. My Orders" + System.lineSeparator() + "5. Settings"
			+ System.lineSeparator() + "6. Customer List";

	private static final String MAIN_MENU_TEXT_FOR_LOGGED_IN_USER = "Please, enter number in console to proceed."
			+ System.lineSeparator() + "1. Sign Up" + System.lineSeparator() + "2. Sign Out" + System.lineSeparator()
			+ "3. Product Catalog" + System.lineSeparator() + "4. My Orders" + System.lineSeparator() + "5. Settings"
			+ System.lineSeparator() + "6. Customer List";;

	private static final String INCCORECT_SELECTION_MESSAGE = "Only 1, 2, 3, 4, 5 is allowed. Try one more time.";

	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		this.printMenuHeader();
		context.setMainMenu(this);
		Scanner in = new Scanner(System.in);
		int selection = in.nextInt();
		switch (selection) {
		case 1: 
			SignUpMenu signUpMenu = new SignUpMenu();
			//context.setMainMenu(signUpMenu);
			signUpMenu.start();
			break;		
		case 2: 
			User user = context.getLoggedInUser();
			if (user == null) {
				SignInMenu signInMenu = new SignInMenu();
				// context.setMainMenu(signInMenu);
				signInMenu.start();
			} else {
				SignOutMenu signOutMenu = new SignOutMenu();
				//context.setMainMenu(signOutMenu);
				signOutMenu.start();
			}			
		break;		
		case 3: 			
			ProductCatalogMenu productCatalogMenu = new ProductCatalogMenu();
			// context.setMainMenu(productCatalogMenu);
			productCatalogMenu.start();
			break;
		case 4: 
			MyOrdersMenu myOrdersMenu = new MyOrdersMenu();
			// context.setMainMenu(myOrdersMenu);
			myOrdersMenu.start();
			break;
		case 5: 
			SettingsMenu settingsMenu = new SettingsMenu();
			// context.setMainMenu(settingsMenu);
			settingsMenu.start();
			break;
		case 6: 
			CustomerListMenu customerListMenu = new CustomerListMenu();
			// context.setMainMenu(customerListMenu);
			customerListMenu.start();
			break;
		default: 
			System.out.println(INCCORECT_SELECTION_MESSAGE);
			start();
			break;
		}

	}

	@Override
	public void printMenuHeader() {
		System.out.println("*** MAIN MENU ***");
		User user = context.getLoggedInUser();

		if (user == null) {
			System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER);
		} else {
			System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_IN_USER);
		}

	}

}
