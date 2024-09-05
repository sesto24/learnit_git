package com.sesto.onlineshop.enteties.impl;

import java.util.Arrays;

import com.sesto.onlineshop.enteties.Order;
import com.sesto.onlineshop.enteties.Product;

public class DefaultOrder implements Order {

	private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;
	
	private String creditCardNumber;
	private Product[] products;
	private int customerId;

	@Override
	public boolean isCreditCardNumberValid(String creditCardNumber) {
		if (creditCardNumber==null || creditCardNumber.length()!= AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER){
			return false;
		} else {
			return true;
		}
		
	}

	@Override
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber=creditCardNumber;
	}

	@Override
	public void setProducts(Product[] products) {
		this.products=products;
	}

	@Override
	public void setCustomerId(int customerId) {
		this.customerId=customerId;
	}


	@Override
	public int getCustomerId() {
		return this.customerId;
	}

	@Override
	public String toString() {
		return "DefaultOrder [creditCardNumber=" + creditCardNumber + ", products=" + Arrays.toString(products)
				+ ", customerId=" + customerId + "]";
	}
	
	

	
	
	

}
