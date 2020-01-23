package com.alex.utils.exceptions;

public class UserExists extends Exception{
	public UserExists(String errorMessage) {
		super(errorMessage);
	}
}
