package com.alex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Optional;

public class ReadFiles {
	
	private static FileInputStream fis;
	private static ObjectInputStream ois;
	private static ServerStatus result;
	public static ServerStatus readStatus() throws Error{
		try {
			// create dir if it does not exist already
			File savedir = new File("/tmp/servervars");
			boolean exists = savedir.exists();
			// create dirs if not existing
			if(!exists) {
				savedir.mkdirs();
			}
			File save = new File("/tmp/servervars/serverstatus.ser");
			exists = save.exists();
			// test to see if the file exists
			if(!exists) {
				throw new Error("server status is not currently known");
			}
			
			// create streams
			fis = new FileInputStream(save);
			ois = new ObjectInputStream(fis);
			result = (ServerStatus) ois.readObject();
			
			
			// close stream
			ois.close();
			return result;
		} catch ( IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(e.getClass().equals(IOException.class)) {
				throw new Error("IO error");
			}else {
				throw new Error("class not found");
			}
			
		} 
		
		
	}
	
	
	
	
	
	
	
}
