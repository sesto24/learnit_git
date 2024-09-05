package com.sesto.onlineshop.menu.impl;

import com.sesto.onlineshop.configs.ApplicationContext;
import com.sesto.onlineshop.menu.Menu;

public class SignOutMenu implements Menu {

	private ApplicationContext context;
	
	{
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		printMenuHeader();
		context.setLoggedInUser(null);
		context.getSessionCart().clear();
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("*** SIGN OUT ***");
		System.out.println("Have a nice day! Look forward to welcoming back!");
		
	}

}
