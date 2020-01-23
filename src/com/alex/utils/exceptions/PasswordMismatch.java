package com.alex.utils.exceptions;

import com.alex.utils.security.HashingUtils;

public class PasswordMismatch extends Exception {
	
	/**
	 * serial ID
	 */
	private static final long serialVersionUID = 9110315068632161584L;

	public PasswordMismatch(String pw1, String pw2) {
		super( HashingUtils.bcryptHash(pw1) + " is not the same as " + HashingUtils.bcryptHash(pw2));
	}
	
}
