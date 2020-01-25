package com.alex.utils.formatting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.jsp.JspWriter;

import com.alex.beans.ServerStatus;
import com.alex.constants.Games;
import com.alex.constants.Icons;

public class ParseToOutput {
	
	//list of statuses

	
	//hashmap of the paths
	Map<String, String> paths;
	
	//output jsp writer
	private JspWriter out;
	
	//set the logo and banner vars
	private String logo;
	
	//parse and set the icons
	public void parse(ArrayList<ServerStatus> status, JspWriter out){
		//init the list of games and their appids
		Games.init();
		
		//init the list of icons and their games
		Icons.init();
		
		//save the jspwriter variable
		this.out = out;
		
		//iterate through teh serverstatus in the list
		for(ServerStatus s : status) {
			setBanner(s);
		}	
	}
	
	private void setBanner(ServerStatus s) {
		
		//get the paths
		String path = Games.games.get(s.getAppID());
		
		//set the logo path
		logo = path + "logo.png";
		
		//vac status icon
		String vac;
		
		//set the vac status icon to the green checkmark if it is secure
		//if not then set it to the red X
		if(s.isSecure()) {
			vac = Icons.icons.get("green-check");
		}else {
			vac = Icons.icons.get("red-x");
		}
		
		
		//the string to add to the jsp file
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
			//attempt to print to the page
			out.println(print);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
