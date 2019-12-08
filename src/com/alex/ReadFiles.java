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
			//create dir if it does not exist already
			File savedir = new File("/tmp/servervars");
			boolean exists = savedir.exists();
			if(!exists) {
				savedir.mkdirs();
			}
			File save = new File("/tmp/servervars/serverstatus.ser");
			exists = save.exists();
			if(!exists) {
				throw new Error("server status is not currently known");
			}
			fis = new FileInputStream(save);
			ois = new ObjectInputStream(fis);
			result = (ServerStatus) ois.readObject();
			
			ois.close();
			return result;
		} catch ( IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(e.getClass().equals(IOException.class)) {
				ErrorType error =ErrorType.getError(whatErrorType(fis, ois, result));
				throw new Error("unable to obtain server status");
			}else {
				throw new Error("unable to obtain server status");
			}
			
		} 
		
		
	}
	
	
	public static enum ErrorType{
		
		NO_FILE(0),
		NO_SERVERS(1),
		NO_CONNECTION(2),
		SOMETHING_ELSE(3);
		private int id;
		
		public int getId() {
			return this.id;
		}
		public void setId(int id) {
			this.id = id;
		}
		private ErrorType(int id) {
			this.id = id;
			
		}
		
		public static ErrorType getError(int errorIndex) {
			for (ErrorType e : ErrorType.values()) {
				if(e.getId() == errorIndex) return e;
			}
			throw new IllegalArgumentException("ErrorType with id: " + errorIndex + " does not exist!!");
		}
		
		
	}
	
	private static int whatErrorType(FileInputStream fis, ObjectInputStream ois, ServerStatus result){
		int errorType;
		FileInputStream fi = fis;
		ObjectInputStream os = ois;
		ServerStatus stat = result;
		try {
			File save = new File("/tmp/servervars/serverstatus.ser");
			fi = new FileInputStream(save);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			try {
				fi.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				
			}
			return 0;
		}
		try {
			os = new ObjectInputStream(fi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				fi.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				
			}
			return 2;
		}
		try {
			result = (ServerStatus) os.readObject();
			os.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				fi.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				
			}
			return 3;
		}
		try {
			fi.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
		
			
		return 0; 
	}
	
}
