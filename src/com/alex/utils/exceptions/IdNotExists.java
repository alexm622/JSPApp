package com.alex.utils.exceptions;

public class IdNotExists extends Exception{
	private static final long serialVersionUID = -142692487422087720L;

	/**
	 * 
	 */

	public IdNotExists(long id) {
		super("User with id: " + id + " does not exist");
	}
}
