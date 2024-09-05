package com.sesto.onlineshop.enteties.impl;

import com.sesto.onlineshop.enteties.User;
import com.sesto.onlineshop.enteties.UserStateEnum;

public class DefaultUser implements User,Cloneable {

	private static int globalId = 0;
	private int id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private UserStateEnum userState;
	{
		this.id = ++globalId;
	}

	public DefaultUser() {
	}

	@Override
	public User clone()  {
		DefaultUser user = new DefaultUser();
		globalId--;
		user.firstName=this.firstName;
		user.id=this.id;
		user.lastName=this.lastName;
		user.email=this.email;
		user.password=password;
				
		return user;
	}

	public DefaultUser(String firstName, String lastName, String password, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	
	
	@Override
	public String toString() {
		return "DefaultUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName 
				+ ", email=" + email + "]";
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setEmail(String newEmail) {
		this.email=newEmail;
	}

	void clearState() {
		globalId=0;
	}

	
}
