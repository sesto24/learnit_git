package com.sesto.onlineshop.menu.impl;

import java.lang.reflect.Array;

import com.sesto.onlineshop.configs.ApplicationContext;
import com.sesto.onlineshop.enteties.Order;
import com.sesto.onlineshop.enteties.User;
import com.sesto.onlineshop.menu.Menu;
import com.sesto.onlineshop.services.OrderManagementService;
import com.sesto.onlineshop.services.impl.DefaultOrderManagementService;

public class MyOrdersMenu implements Menu {

	private ApplicationContext context;
	private OrderManagementService orderManagementService;

	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}

	@Override
	public void start() {
		User user = context.getLoggedInUser();
		if (user==null) {
			System.out.println("Please, log in or create new account to see list of your orders");
			context.getMainMenu().start();
		} else {
			Order[] userOrders = orderManagementService.getOrdersByUserId(user.getId());
			if (userOrders==null || userOrders.length==0) {
				System.out.println("Unfortunately, you don’t have any orders yet. "
						+ "Navigate back to main menu to place a new order");
				context.getMainMenu().start();				
			} else {
				for (Order order : userOrders) {
					System.out.println(order.toString());											
				}
				context.getMainMenu().start();		
			}
			
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("*** MY ORDERS ***");
	
	}

}
