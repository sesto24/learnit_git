package com.sesto.onlineshop.menu.impl;

import com.sesto.onlineshop.configs.ApplicationContext;
import com.sesto.onlineshop.enteties.User;
import com.sesto.onlineshop.menu.Menu;
import com.sesto.onlineshop.services.UserManagementService;
import com.sesto.onlineshop.services.impl.DefaultUserManagementService;

public class CustomerListMenu implements Menu {

	private ApplicationContext context;
	private UserManagementService userManagementService;
	
	{
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		printMenuHeader();
		for (User user : userManagementService.getUsers()) {
			System.out.println(user.toString());
		}
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("*** CUSTOMER LIST ***");
	}

}
