package com.alex.utils.formatting;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspWriter;

import com.alex.beans.ServerStatus;
import com.alex.constants.Games;
import com.alex.constants.Icons;

public class ParseToOutput {
	
	private ArrayList<ServerStatus> status;
	Map<String, String> paths;
	private JspWriter out;
	
	private String logo, banner;
	
	public void parse(ArrayList<ServerStatus> status, JspWriter out){
		Games.init();
		Icons.init();
		this.out = out;
		
		for(ServerStatus s : status) {
			setBanner(s);
		}	
	}
	
	private void setBanner(ServerStatus s) {
		
		String path = Games.games.get(s.getAppID());
		
		banner = path + "banner.png";
		
		logo = path + "logo.png";
		
		String vac;
		
		if(s.isSecure()) {
			vac = Icons.icons.get("green-check");
		}else {
			vac = Icons.icons.get("red-x");
		}
		
		
		
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
				"			<th>" +
				"Vac Status: <img src=\"" + vac + "\" style=\"height: 50px; width: 50px;\"></img>" + 
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
