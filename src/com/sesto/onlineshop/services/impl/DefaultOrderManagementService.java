package com.sesto.onlineshop.services.impl;


import com.sesto.onlineshop.enteties.Order;
import com.sesto.onlineshop.services.OrderManagementService;

public class DefaultOrderManagementService implements OrderManagementService {

	private static final int DEFAULT_ORDER_CAPACITY = 10;

	private static DefaultOrderManagementService instance;

	Order[] orders = new Order[0];

	public static OrderManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultOrderManagementService();
		}
		return instance;
	}

	@Override
	public void addOrder(Order order) {
		if (orders.length < DEFAULT_ORDER_CAPACITY) {
			Order[] newOrders = new Order[orders.length + 1];
			System.arraycopy(orders, 0, newOrders, 0, orders.length);
			newOrders[newOrders.length - 1] = order;
			orders = newOrders;
		}
	}

	@Override
	public Order[] getOrdersByUserId(int userId) {
		int userCounter = 0;
		for (Order order : orders) {
			if (order.getCustomerId() == userId) {
				userCounter++;
			}
		}
		if (userCounter > 0) {
			Order[] resultOrders = new Order[userCounter];
			int i = 0;
			for (Order order : orders) {
				if (order.getCustomerId() == userId) {
					resultOrders[i] = order;
					i++;
				}
			}
			return resultOrders;

		} else {
			return null;
		}

	}

	@Override
	public Order[] getOrders() {
		return orders;
	}

	void clearServiceState() {
		orders = new Order[0];
	}

}
