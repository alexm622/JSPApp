package com.alex.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspWriter;

import com.alex.beans.ServerStatus;
import com.alex.constants.Games;

public class ParseToOutput {
	
	private ArrayList<ServerStatus> status;
	Map<String, String> paths;
	private JspWriter out;
	
	private String logo, banner;
	
	public void parse(ArrayList<ServerStatus> status, JspWriter out){
		
		this.out = out;
		
		for(ServerStatus s : status) {
			setBanner(s);
		}	
	}
	
	private void setBanner(ServerStatus s) {
		
		String path = Games.games.get(s.getAppID());
		
		banner = path + "banner.png";
		
		logo = path + "logo.png";
		
		String print;
		
		print = "<div class=\"status-entry color1\">" + 
				 
				"	<table>" +
				"		<tr>" +
				"			<th>" +
				"				<img src=\"" + logo + "\" style=\"height: 50px; width: 50px;\"></img>" + 
				"			</th>" + 
				"			<th style=\"width: 100%; \">" + 
				s.toString() + 
				"			</th>" +
				"		</tr>" + 
				"	</table>" + 
				"</div>" +
				"<br>";
		try {
			out.println(print);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
