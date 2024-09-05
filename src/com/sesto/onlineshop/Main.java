package com.sesto.onlineshop;
import com.sesto.onlineshop.menu.*;
import com.sesto.onlineshop.menu.impl.*;
public class Main {

	public static final String EXIT_COMMAND = "exit";

	public static void main(String[] args) {
		Menu mainMenu = new MainMenu();
		mainMenu.start();
	}

}
