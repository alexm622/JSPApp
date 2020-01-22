package com.alex.utils.security;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import com.alex.utils.Conversion;
import com.google.common.hash.Hashing;

public class HashingUtils 
{
    
    
    public static boolean findBcryptMatch(String password, String hash2) {

        boolean matched = BCrypt.checkpw(password, hash2);
        return matched;
    }
    
    public static String shaHash(String in) {
    	String sha256hex = Hashing.sha256()
    			  .hashString(in, StandardCharsets.UTF_8)
    			  .toString();
    	
    	System.out.println(sha256hex);
    	return sha256hex;
    }
}