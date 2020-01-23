package com.alex.utils.security;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import com.alex.utils.Conversion;
import com.google.common.hash.Hashing;

public class HashingUtils 
{
	//get bcrypt hash
    public static String bcryptHash(String password) {
    	return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
    
    //test if the passwords match the hash
    public static boolean findBcryptMatch(String password, String hash) {
    	//get if the passwords matched
        boolean matched = BCrypt.checkpw(password, hash);
        
        //return results
        return matched;
    }
    
    //hash into sha256
    public static String shaHash(String in) {
    	//get the hash
    	String sha256hex = Hashing.sha256()
    			  .hashString(in, StandardCharsets.UTF_8)
    			  .toString();
    	//print the hash
    	System.out.println(sha256hex);
    	
    	//return the hash
    	return sha256hex;
    }
}