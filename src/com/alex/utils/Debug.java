package com.alex.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Debug {
	public static void debug(Object msg, boolean err) {
		try {
			//date formatting
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			Date date = new Date();

			//log file output
			PrintWriter out = new PrintWriter(new FileWriter(
				"\\tmp\\server\\logs\\" + dateFormat.format(date) + ".txt", true));
			//create the dateformat
			dateFormat = new SimpleDateFormat("[yyyy:MM:dd HH:mm:ss] -- ");

			//stack trace stuff
			String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
			String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
			String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
			int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
			//the date
			String temp = dateFormat.format(date);

			//make the string
			temp += className;
			temp += "." + methodName + "():";
			temp += Integer.toString(lineNumber) + ": ";
			temp += msg;
			temp += " \n";
			String sep = "----------------------------------";
			if(err){
				sep = "#############################";
				
			}

			//get to printing
			System.out.println();
			
			System.out.println(temp);


			out.println();
			out.println();
			out.println(sep);

			out.println(temp);

			out.println(sep);
			out.close();
		} catch (IOException e) {
			//make folders assuming that it doesn't exist
			File f = new File("\\tmp\\server\\logs\\");
			f.mkdirs();
			debug("attempting to create logs folder to fix issue");
			
		}
		
		
	}

	public static void debug(Object msg){
		try {
			//date formatting
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			Date date = new Date();

			//log file output
			PrintWriter out = new PrintWriter(new FileWriter(
				"\\tmp\\server\\logs\\" + dateFormat.format(date) + ".txt", true));
			//create the dateformat
			dateFormat = new SimpleDateFormat("[yyyy:MM:dd HH:mm:ss] -- ");

			//stack trace stuff
			String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
			String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
			String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
			int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
			//the date
			String temp = dateFormat.format(date);

			//make the string
			temp += className;
			temp += "." + methodName + "():";
			temp += Integer.toString(lineNumber) + ": ";
			temp += msg;
			temp += " \n";
			String sep = "----------------------------------";

			//get to printing
			System.out.println();
			
			System.out.println(temp);


			out.println();
			out.println();
			out.println(sep);

			out.println(temp);

			out.println(sep);
			out.close();
		} catch (IOException e) {
			//make folder assuming it doesn't exist
			File f = new File("\\tmp\\server\\logs\\");
			f.mkdirs();
			debug("attempting to create logs folder to fix issue");
		}
	}
}
