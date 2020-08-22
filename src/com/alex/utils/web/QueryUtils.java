package com.alex.utils.web;

import java.util.HashMap;

import com.alex.utils.Debug;

public class QueryUtils {

	public static HashMap<String, String> splitQuery(String s){
		
		//make the hashmap
		HashMap<String, String> hm = new HashMap<String, String>();
		
		//if s is null return empty hashmap
		if(s == null) {
			return hm;
		}
		
		//split all the values
		String[] keys = s.split("&");
		
		//split up on the equals key
		for(String value : keys) {
			Debug.debug("the value of value is " + value);
			String k = value.split("=")[0];
			Debug.debug("the value of k is " + k);
			String v = value.split("=")[1];
			
			//add the key and value
			hm.put(k, v);
		}
		//return the hashmap
		return hm;
	}
	
	
}
