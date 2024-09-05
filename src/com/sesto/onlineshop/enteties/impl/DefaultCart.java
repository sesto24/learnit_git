package com.sesto.onlineshop.enteties.impl;


import com.sesto.onlineshop.enteties.Cart;
import com.sesto.onlineshop.enteties.Product;

public class DefaultCart implements Cart {

	private Product[] cartProducts = new DefaultProduct[0];
	
	@Override
	public boolean isEmpty() {
		if (cartProducts==null || cartProducts.length==0 ){
			return true;
		} else {
			return false;
		}
			
	}

	@Override
	public void addProduct(Product product) {
		if (product!=null) {
			Product[] newCartProducts = new Product[cartProducts.length+1];
			System.arraycopy(cartProducts, 0, newCartProducts, 0, cartProducts.length);
			newCartProducts[newCartProducts.length-1]= product;
			cartProducts=newCartProducts;
		}
	}

	@Override
	public Product[] getProducts() {
		return cartProducts;
		
		//return null;
	}

	@Override
	public void clear() {
		cartProducts=new Product[0];
		
	}

}
