package com.sesto.onlineshop.enteties;

public interface User  {
	
	String getFirstName();
	String getLastName();
	String getPassword();
	String getEmail();
	int getId();
	
	User clone();
	void setPassword(String newPassword);
	void setEmail(String newEmail);
	
	
}
