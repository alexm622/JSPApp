package com.alex.utils;

import java.util.ArrayList;

import com.alex.beans.ServerStatus;

public class ParseToOutput {
	
	public static ArrayList<String> parse(ArrayList<ServerStatus> status){
		ArrayList<String> output = new ArrayList<String>();
		if(status.size() == 0) {
			output.add("");
			return output;
		}
		
		for(ServerStatus s : status) {
			output.add("<div class=\"status-entry\">"  + parse(s) + "<br></div><br>");
		}
		
		return output;
	}
	
	private static String parse(ServerStatus s) {
		
		return s.toString();
	}
	
	

}
