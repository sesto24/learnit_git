package com.sesto.onlineshop.menu.impl;

import java.util.Scanner;

import com.sesto.onlineshop.configs.ApplicationContext;
import com.sesto.onlineshop.enteties.Cart;
import com.sesto.onlineshop.enteties.Order;
import com.sesto.onlineshop.enteties.impl.DefaultOrder;
import com.sesto.onlineshop.menu.Menu;
import com.sesto.onlineshop.services.OrderManagementService;
import com.sesto.onlineshop.services.impl.DefaultOrderManagementService;

public class CheckoutMenu implements Menu {

	private ApplicationContext context;
	private OrderManagementService orderManagementService;
	
	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}
	
	@Override
	public void start() {
		System.out.println("Enter your credit card number without spaces "
				+ "and press enter if you confirm purchase");
		Scanner in = new Scanner(System.in);
		while(true) {
			String userInput = in.next();			
			Order order = new DefaultOrder();
			if(order.isCreditCardNumberValid(userInput)) {
				order.setCustomerId(context.getLoggedInUser().getId());
				order.setProducts(context.getSessionCart().getProducts());
				order.setCreditCardNumber(userInput);
				orderManagementService.addOrder(order);
				System.out.println("Thanks a lot for your purchase. Details about order delivery are sent to your email.");
				context.getSessionCart().clear();
				context.getMainMenu().start();					
				break;							
			}else {
				System.out.println("You entered invalid credit card number. "
						+ "Valid credit card should contain 16 digits. Please, try one more time.");
				continue;
			}
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("*** CHECKOUT ***");
	}

}
