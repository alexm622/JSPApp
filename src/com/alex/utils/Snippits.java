package com.alex.utils;

import java.net.*;
import java.io.*;

public class Snippits {
	
	public static String getExternalIp() throws Exception{
		

		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = new BufferedReader(new InputStreamReader(
		                whatismyip.openStream()));

		String ip = in.readLine(); //you get the IP as a String
		return ip;
	}
	
	public static String readPassword() throws Exception{
		File file = new File("C:\\Data\\sqlpassword.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String out = br.readLine();
		
		return out;
	}
	
}
