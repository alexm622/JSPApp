package com.alex.utils.formatting;

import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;

import com.alex.beans.ServerStatus;
import com.alex.utils.ReadFiles;

public class StatusFormatter {
	
	private JspWriter out;
	private ArrayList<ServerStatus> ssList;
	
	public StatusFormatter(JspWriter out) {
		this.out = out;
		try {
			ssList = ReadFiles.readStatus();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void format() {
		
	}
}
