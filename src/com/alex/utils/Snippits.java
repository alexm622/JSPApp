package com.alex.utils;

import java.net.*;
import java.util.Date;
import java.io.*;

public class Snippits {
	
	public static java.sql.Date getNow(){
		Date d = new Date();
		return (new java.sql.Date(d.getTime()));
	}
	
	public static String getExternalIp() throws IOException  {
		
		// grab ip
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        
        //make buffered reader
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine();
            //return ip
            return ip;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
	public static String readPassword() throws IOException{
		//get the os type
		String os =System.getProperty("os.name").toLowerCase();
		File file;
		if(os.indexOf("win") >= 0) { //if it is windows
			file = new File("C:\\Data\\sqlpassword.txt");
		}else { //assume linux, because I will never touch a MAC
			file = new File("\\Data\\sqlpassword.txt");
		}
		
		//make buffered reader
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		//read the password
		String out = br.readLine();
		
		//close buffered reader
		br.close();
		
		//return password
		return out;
	}
	public static String readRecaptcha() throws IOException{
		//get os name
		String os =System.getProperty("os.name").toLowerCase();
		File file;
		if(os.indexOf("win") >= 0) { //if it is windows
			file = new File("C:\\Data\\recaptcha.txt");
		}else { // this has got to be linux, because MAC is dumb
			file = new File("/Data/recaptcha.txt");
		}
		
		//make buffered reader
		BufferedReader br = new BufferedReader(new FileReader(file));
		//get output
		String out = br.readLine();
		
		//close buffered reader
		br.close();
		
		//return recaptcha key
		return out;
	}
	
}
