package com.alex.utils.exceptions;

public class UserExists extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7536645314245864946L;

	public UserExists(String errorMessage) {
		super(errorMessage);
	}
}
