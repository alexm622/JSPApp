package com.alex.constants;

import java.util.HashMap;
import java.util.Map;

public class Icons {
	public static Map<String, String> icons = new HashMap<String, String>(); //name, path
	private static final String path = "images/icons/";
	
	
	public static void init() {
		
		icons.put("green-check", path + "check.png");
		icons.put("red-x", path + "x-mark.png");
		
	}
	

}
