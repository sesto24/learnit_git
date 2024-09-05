package com.sesto.onlineshop.configs;

import com.sesto.onlineshop.enteties.Cart;
import com.sesto.onlineshop.enteties.User;
import com.sesto.onlineshop.enteties.impl.DefaultCart;
import com.sesto.onlineshop.enteties.impl.DefaultUser;
import com.sesto.onlineshop.menu.Menu;

public class ApplicationContext {
	
	private static ApplicationContext instance;
	
	private User loggedInUser;
	private Menu mainMenu;
	private Cart sessionCart;
	
	private ApplicationContext() {
	}
	
	public void setLoggedInUser(User user)   {
		if (this.sessionCart != null) {
			this.sessionCart.clear(); // we have to clear session cart when new user is logged in
		}
		if (user==null) {
			this.loggedInUser = null;
		}
		else {		
			this.loggedInUser =  user.clone();
		}
	
	}
	
	public User getLoggedInUser() {
		return this.loggedInUser;
	}
	
	public void setMainMenu(Menu menu) {
		this.mainMenu = menu;
	}
	
	public Menu getMainMenu() {
		return this.mainMenu;
	}

	public static ApplicationContext getInstance() {
		if (instance == null) {
			instance = new ApplicationContext();
		}
		return instance;
	}

	public Cart getSessionCart() {
		if (this.sessionCart == null) {
			this.sessionCart = new DefaultCart();
		}
		return this.sessionCart;
	}
	
	
	

}
