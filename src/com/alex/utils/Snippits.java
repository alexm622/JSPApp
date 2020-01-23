package com.alex.utils;

import java.net.*;
import java.io.*;

public class Snippits {
	
	public static String getExternalIp() throws IOException  {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine();
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
		System.out.println();
		String os =System.getProperty("os.name").toLowerCase();
		File file;
		if(os.indexOf("win") >= 0) {
			file = new File("C:\\Data\\sqlpassword.txt");
		}else {
			file = new File("\\Data\\sqlpassword.txt");
		}
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String out = br.readLine();
		
		return out;
	}
	public static String readRecaptcha() throws IOException{
		String os =System.getProperty("os.name").toLowerCase();
		File file;
		if(os.indexOf("win") >= 0) {
			file = new File("C:\\Data\\recaptcha.txt");
		}else {
			file = new File("/Data/recaptcha.txt");
		}
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String out = br.readLine();
		
		return out;
	}
	
}
