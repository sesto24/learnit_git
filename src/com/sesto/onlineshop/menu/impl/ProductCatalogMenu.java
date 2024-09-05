package com.sesto.onlineshop.menu.impl;

import com.sesto.onlineshop.enteties.Order;

import java.util.Arrays;
import java.util.Scanner;

import com.sesto.onlineshop.configs.ApplicationContext;
import com.sesto.onlineshop.enteties.Product;
import com.sesto.onlineshop.enteties.impl.DefaultOrder;
import com.sesto.onlineshop.menu.Menu;
import com.sesto.onlineshop.services.OrderManagementService;
import com.sesto.onlineshop.services.ProductManagementService;
import com.sesto.onlineshop.services.impl.DefaultOrderManagementService;
import com.sesto.onlineshop.services.impl.DefaultProductManagementService;

public class ProductCatalogMenu implements Menu {

	private static final String CHECKOUT_COMMAND = "checkout";
	private static final String INCORRECT_COMMAND_MESSAGE = "Please, enter product ID if you want to add product "
			+ "to cart. Or enter ‘checkout’ if you want to proceed with checkout. "
			+ "Or enter ‘menu’ if you want to navigate back to the main menu.";
	private ApplicationContext context;
	private ProductManagementService productManagementService;
	private OrderManagementService orderManagementService;

	{
		context = ApplicationContext.getInstance();
		productManagementService = DefaultProductManagementService.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}

	@Override
	public void start() {
		
		// Product[] orderProducts=new Product[0];
		printMenuHeader();

		while (true) {
			Scanner in = new Scanner(System.in);
			String userInput = in.next();
			if (userInput != null) {
				if (userInput.equals("menu")) {
					context.getMainMenu().start();
					context.getSessionCart().clear();
					break;

				} else if (userInput.equals("checkout")) {
					
					if (context.getLoggedInUser() == null) {
						System.out.println("You are not logged in. Please, sign in or create new account");
						context.getMainMenu().start();
						context.getSessionCart().clear();
						break;

					} else if (context.getSessionCart().isEmpty()) {
						System.out.println(
								"Your cart is empty. Please, add product to cart first and then proceed with checkout");
						printMenuHeader();
						continue;

					} else {
						// Proceed to checkout
						Menu checkoutMenu = new CheckoutMenu();
						checkoutMenu.start();
						break;
					}

				} else {
					int productId = 0;
					try {
						productId = Integer.parseInt(userInput);
						Product newProduct = productManagementService.getProductById(productId);
						if (newProduct != null) {
							context.getSessionCart().addProduct(newProduct);
							System.out.println("Product " + newProduct.getProductName()
									+ " has been added to your cart. "
									+ "If you want to add a new product - enter the product id. "
									+ "If you want to proceed with checkout - enter word ‘checkout’ to console’");
						} else {
							System.out.println(INCORRECT_COMMAND_MESSAGE);
						}
					} catch (Exception e) {
						System.out.println(INCORRECT_COMMAND_MESSAGE);
					}
				}

			} else {
				System.out.println(INCORRECT_COMMAND_MESSAGE);
			}

		}

	}

	@Override
	public void printMenuHeader() {
		System.out.println("*** PRODUCT CATALOG ***");
		for (Product availableProduct : productManagementService.getProducts()) {
			System.out.println(availableProduct.toString());

		}

		System.out.println("Enter product id to add it to the cart or ‘menu’");
	}

}
