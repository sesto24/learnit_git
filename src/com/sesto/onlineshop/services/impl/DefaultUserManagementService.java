package com.sesto.onlineshop.services.impl;

import com.sesto.onlineshop.enteties.User;
import com.sesto.onlineshop.services.UserManagementService;

public class DefaultUserManagementService implements UserManagementService {

	private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
	private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
	private static final String NO_ERROR_MESSAGE = "";
	private static final String OUT_OF_CAPACITY_ERROR_MESSAGE = "Maximum user capacity has been reached.";
	private static final int DEFAULT_USERS_CAPACITY = 10;

	private static DefaultUserManagementService instance;

	private User[] users = new User[0];

	static {
		instance = new DefaultUserManagementService();
	}

	private DefaultUserManagementService() {

	}

	@Override
	public String registerUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException();
		}
		if (user.getEmail() == null || user.getEmail().length() == 0) {
			return EMPTY_EMAIL_ERROR_MESSAGE;
		} else if (getUserByEmail(user.getEmail()) != null) {
			return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
		} else if (users.length >= DEFAULT_USERS_CAPACITY) {
			return OUT_OF_CAPACITY_ERROR_MESSAGE;
		} else {

			User[] newUsers = new User[this.users.length + 1];
			if (this.users.length > 0) {
				System.arraycopy(this.users, 0, newUsers, 0, this.users.length);
			}
			newUsers[newUsers.length - 1] = user;
			this.users = newUsers;

			return NO_ERROR_MESSAGE;
		}
	}

	public static UserManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultUserManagementService();
		}
		return instance;
	}

	@Override
	public User[] getUsers() {
		return this.users;
	}

	@Override
	public User getUserByEmail(String userEmail) {
		for (User user : users) {

			if (user != null && user.getEmail().equals(userEmail)) {
				return user;
			}

		}

		return null;
	}

	void clearServiceState() {
		this.users = new User[0];
	}
}
