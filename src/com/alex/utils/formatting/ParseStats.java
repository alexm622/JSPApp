package com.alex.utils.formatting;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.alex.utils.sql.ReadMaxAvg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.jsp.JspWriter;

public class ParseStats {
	private static HashMap<String, ArrayList<Pair<Long, Double>>> list;
	
	public static void format(JspWriter out) throws Exception{
		
		list = ReadMaxAvg.getMaxAvg();
		
		
	}
	
	private static HashMap<Long, ArrayList<Pair<String, Double>>> sort(HashMap<String, ArrayList<Pair<Long, Double>>> unsorted){
		HashMap<Long, ArrayList<Pair<String, Double>>> sorted = new HashMap<Long, ArrayList<Pair<String,Double>>>();
		
		Set<String> keySet = unsorted.keySet();
		
		//get the keys
		String[] keys = (String[]) keySet.toArray();
		
		
		
		for(String s : keys) {
			//get the arraylist
			ArrayList<Pair<Long, Double>> arrayList = unsorted.get(s);
			
			//iterate through arraylist
			
			
			for(Pair<Long, Double> p : arrayList) {
				// get the long and doubles from the pair
				Long l = p.getLeft();
				Double d = p.getRight();
				
				// variable that will be often overwritten
				ArrayList<Pair<String, Double>> byappid;
				
				
				if(!sorted.containsKey(l)) { //test if there is not an entry under that key (l)
					//make a new arraylist containing pairs
					byappid = new ArrayList<Pair<String,Double>>();
					
					//add the current value for the pair to the arraylist
					byappid.add(new MutablePair<String, Double>(s, d));
					
					//put the arraylist into the hashmap
					sorted.put(l, byappid);
				}else { // if the key does exist
					//get the value stored in the hashmap under the current key
					byappid = sorted.get(l);
					
					// add the current pair value
					byappid.add(new MutablePair<String, Double>(s, d));
					
					//replace the value with the updated arraylist
					sorted.replace(l, byappid);
				}
				
			}
			
		}
		
		
		return sorted;
	}
	
	private static String format(long appid,  HashMap<Long, ArrayList<Pair<String, Double>>> values) {
		return null;
	}
	
	
}
