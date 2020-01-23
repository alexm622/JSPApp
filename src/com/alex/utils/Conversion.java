package com.alex.utils;

public class Conversion {
	//this converts hex to ascii
	public static String hexToAscii(String hexStr) {
		//make a stringbuilder
	    StringBuilder output = new StringBuilder("");
	     
	    //iterate through the hexstring
	    for (int i = 0; i < hexStr.length(); i += 2) {
	    	//grab the substring
	        String str = hexStr.substring(i, i + 2);
	        //add to the substring
	        output.append((char) Integer.parseInt(str, 16));
	    }
	    
	    //return the string
	    return output.toString();
	}
	
}
