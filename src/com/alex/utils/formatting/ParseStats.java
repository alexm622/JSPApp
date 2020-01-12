package com.alex.utils.formatting;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.alex.constants.Games;
import com.alex.constants.Icons;
import com.alex.utils.sql.ReadMaxAvg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.jsp.JspWriter;

public class ParseStats {
	private static HashMap<String, ArrayList<Pair<Long, Double>>> list;
	
	public static void format(JspWriter out) throws Exception{
		
		list = ReadMaxAvg.getMaxAvg();
		
		HashMap<Long, ArrayList<Pair<String, Double>>> sorted = sort(list);
		
		format(sorted, out);
		
		
		
	}
	
	private static void format(HashMap<Long, ArrayList<Pair<String, Double>>> values, JspWriter out) throws Exception{
		
		//get the keys
		Set<Long> keySet = values.keySet();
		Long[] keys = new Long[keySet.size()];
		keys = keySet.toArray(keys);
		//iterate through the keys
		for(Long key : keys) {
			//print out the list of the tables
			out.print(getString(values.get(key), key));
		}
		
	}
	
	private static HashMap<Long, ArrayList<Pair<String, Double>>> sort(HashMap<String, ArrayList<Pair<Long, Double>>> unsorted){
		HashMap<Long, ArrayList<Pair<String, Double>>> sorted = new HashMap<Long, ArrayList<Pair<String,Double>>>();
		
		Set<String> keySet = unsorted.keySet();
		
		//get the keys
		
		String[] keys = new String[keySet.size()];
		keys = keySet.toArray(keys);
		
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
	
	private static String getString(ArrayList<Pair<String, Double>> app, Long appid) {
		Games.init();
		//get game images path
		String path = Games.games.get(appid);
		
		
		//set the logo
		String logo = path + "logo.png";
		
		HashMap<DataType,ArrayList<Pair<String, Double>>> values = new HashMap<DataType, ArrayList<Pair<String, Double>>>();
		
		//iterate through the arraylist of values
		
		for(Pair<String, Double> pair : app){
			if(pair.getKey().contains(DataType.MAX.getType())){	//if the pair is for a max value
				
				//make an arraylist variable
				ArrayList<Pair<String, Double>> arrayList = new ArrayList<Pair<String,Double>>();
				
				//testing to see if it exists or not
				if(!values.containsKey(DataType.MAX)) { //if there is not a value for max already
					arrayList.add(pair);
					values.put(DataType.MAX, arrayList);
				}else {
					arrayList = values.get(DataType.MAX);
					arrayList.add(pair);
					values.replace(DataType.MAX, arrayList);
				}
			}else{ //if the pair value is an average
				
				//make an arraylist variable
				ArrayList<Pair<String, Double>> arrayList = new ArrayList<Pair<String,Double>>();
				if(!values.containsKey(DataType.AVG)) { //if there is not a value for average already
					// add the current pair to the arraylist
					arrayList.add(pair);
					
					//add the current arraylist to the hashmap with the key as an enum
					values.put(DataType.AVG, arrayList);
				}else {
					
					//set the arraylist above to the stored arraylist
					arrayList = values.get(DataType.AVG);
					
					//add the current pair to the arraylist
					arrayList.add(pair);
					
					//replace the value in the hashmap
					values.replace(DataType.AVG, arrayList);
				}
			}
		}
		
		//make the final hashmap that contains all the data
		HashMap<DataType, HashMap<DataTime, Double>> data = new HashMap<ParseStats.DataType, HashMap<DataTime,Double>>();
		
		
		//populate the hashmap
		try {
			data.put(DataType.MAX, convertToEnum(values.get(DataType.MAX)));
			data.put(DataType.AVG, convertToEnum(values.get(DataType.AVG)));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		/*
		map.put("daily_avg", getDailyAvg(con));
		map.put("weekly_avg", getWeeklyAvg(con));
		map.put("monthly_avg", getMonthlyAvg(con));
		map.put("alltime_avg", getAllTimeAvg(con));
		
		map.put("daily_max", getDailyMax(con));
		map.put("weekly_max", getWeeklyMax(con));
		map.put("monthly_max", getMonthlyMax(con));
		map.put ("alltim_max", getAllTimeMax(con));
		 * 
		 * 
		 */
		
		
		
		String print;
		
		print = "<div class=\"status-entry color1\">" + 
				 
				"	<table>" +
				"		<tr>" +
				"			<th>" +
				"				<img src=\"" + logo + "\" style=\"height: 50px; width: 50px;\"></img>" + 
				"			</th>" + 
				"			<th class =\"color1\" style=\"width: 100%; \">" + 
				"               Daily Average: " + data.get(DataType.AVG).get(DataTime.DAILY).toString() + 
				"			</th>" +
				"			<th class =\"color1\" style=\"width: 100%; \">" +
				"               Daily Max: " + data.get(DataType.MAX).get(DataTime.DAILY) + 
				"			</th>" +
				"			<th class =\"color1\" style=\"width: 100%; \">" +
				"               Weekly Average: " + data.get(DataType.AVG).get(DataTime.WEEKLY) + 
				"			</th>" +
				"			<th class =\"color1\" style=\"width: 100%; \">" +
				"               Weekly Max: " + data.get(DataType.MAX).get(DataTime.WEEKLY) + 
				"			</th>" +
				"           <br> " +
				"			<th class =\"color1\" style=\"width: 100%; \">" +
				"               Monthly Average: " + data.get(DataType.AVG).get(DataTime.MONTHLY) + 
				"			</th>" +
				"			<th class =\"color1\" style=\"width: 100%; \">" +
				"               Monthly Max: " + data.get(DataType.MAX).get(DataTime.MONTHLY) + 
				"			</th>" +
				"			<th class =\"color1\" style=\"width: 100%; \">" +
				"               All-Time Average: " + data.get(DataType.AVG).get(DataTime.ALLTIME) + 
				"			</th>" +
				"			<th class =\"color1\" style=\"width: 100%; \">" +
				"               All-Time Max: " + data.get(DataType.MAX).get(DataTime.ALLTIME) + 
				"			</th>" +
				"	</table>" + 
				"</div>" +
				"<br>";
		return print;
		
	}
	
	enum DataType{
		
		MAX("max"), AVG("avg");
		
		private CharSequence type;
		
		DataType(String type) {
			this.type = type;
		}
		
		public CharSequence getType() {
			return this.type;
		}
		
		
	}
	
	enum DataTime{
		DAILY("daily"), WEEKLY("weekly"), MONTHLY("monthly"), ALLTIME("alltime");
		
		private CharSequence time;
		
		DataTime(String time){
			this.time = time;
		}
		
		public CharSequence getTime() {
			return this.time;
		}
		
	}
	
	private static HashMap<DataTime, Double> convertToEnum(ArrayList<Pair<String, Double>> values) throws Exception{
		//get the output hashmap
		HashMap<DataTime, Double> out = new HashMap<ParseStats.DataTime, Double>();
		
		//iterate through the pairs
		for(Pair<String, Double> pair : values) {
			
			if(pair.getLeft().contains(DataTime.DAILY.getTime())) { //is it a daily value
				out.put(DataTime.DAILY, pair.getRight()); //add to the out hashmap
			}else if(pair.getLeft().contains(DataTime.MONTHLY.getTime())) { //is it a monthly value
				out.put(DataTime.MONTHLY, pair.getRight());
			}else if(pair.getLeft().contains(DataTime.WEEKLY.getTime())) { //is it a weekyl value
				out.put(DataTime.WEEKLY, pair.getRight());
			}else if(pair.getLeft().contains(DataTime.ALLTIME.getTime())) { //is it an all time value
				out.put(DataTime.ALLTIME, pair.getRight());
			}
			
			
		}
		
		return out;
	}
	
	
	
}


