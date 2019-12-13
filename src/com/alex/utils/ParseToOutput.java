package com.alex.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.alex.beans.ServerStatus;
import com.alex.constants.Games;

public class ParseToOutput {
	
	private ArrayList<ServerStatus> status;
	Map<String, String> paths;
	
	public ArrayList<String> parse(ArrayList<ServerStatus> status){
		
		paths = new HashMap<String, String>();
		
		ArrayList<String> output = new ArrayList<String>();
		if(status.size() == 0) {
			output.add("");
			return output;
		}
		
		for(ServerStatus s : status) {
			setBanner(s);
			output.add("<div class=\"status-entry\">"  + parse(s) + "<br></div><br>");
		}
		
		return output;
	}
	
	private static String parse(ServerStatus s) {
		
		return s.toString();
	}
	
	private static void setBanner(ServerStatus s) {
		
		String path = Games.games.get(s.getAppID());
		
		
		
		
		
		
	}
	
	

}
