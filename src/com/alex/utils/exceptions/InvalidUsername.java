package com.alex.utils.exceptions;

public class InvalidUsername extends Exception{
	private static final long serialVersionUID = -142692487422087720L;

	/**
	 * 
	 */

	public InvalidUsername(String uname) {
		super("Username " + uname + " does not exist");
	}
}
