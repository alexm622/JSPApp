package com.alex.utils.exceptions;

import com.alex.utils.security.HashingUtils;

public class PasswordMismatch extends Exception {
	
	public PasswordMismatch(String pw1, String pw2) {
		super( HashingUtils.bcryptHash(pw1) + " is not the same as " + HashingUtils.bcryptHash(pw2));
	}
	
}
