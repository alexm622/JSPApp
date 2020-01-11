package com.alex.utils.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.alex.utils.Snippits;



public class ReadMaxAvg {
	
	public static HashMap<String, ArrayList<Pair<Long , Double>>> getMaxAvg() throws Exception{
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con;
		String extern = "73.17.34.121";
		if(Snippits.getExternalIp().equals(extern)) {
			//connect across local network
			con = DriverManager.getConnection("jdbc:mysql://10.0.0.6:3306/gameserver","server", "server");
		}else {
			//access using special remote account
			con = DriverManager.getConnection("jdbc:mysql://73.17.34.121:3306/gameserver", "remote", Snippits.readPassword());
		}
		
		HashMap<String, ArrayList<Pair<Long , Double>>> map = new HashMap<String, ArrayList<Pair<Long , Double>>>();
		
		map.put("daily_avg", getDailyAvg(con));
		map.put("weekly_avg", getWeeklyAvg(con));
		map.put("monthly_avg", getMonthlyAvg(con));
		map.put("alltime_avg", getAllTimeAvg(con));
		
		map.put("daily_max", getDailyMax(con));
		map.put("weekly_max", getWeeklyMax(con));
		map.put("monthly_max", getMonthlyMax(con));
		map.put ("alltim_max", getAllTimeMax(con));
		
		
		
		
		
		
		return map;
	}
	
	/*
	 * This method is the same as the next few
	 * what it does is it querys the sql server for stats
	 */
	private static ArrayList<Pair<Long, Double>> getDailyAvg(Connection con) throws Exception{
		// variable declaration
		ArrayList<Pair<Long, Double>> pairs = new ArrayList<Pair<Long, Double>>();
		
		//get the appids
		String sql = "SELECT DISTINCT appid FROM daily_table";
		
		//start preparing the statement
		PreparedStatement stmt = con.prepareStatement(sql);
		
		//get the result set
		ResultSet rs = stmt.executeQuery();
		
		//make an empty arraylist to contain the appids
		ArrayList<Long> appids = new ArrayList<Long>();
		
		while(rs.next()) {
			
			// put the appids into the arraylist
			appids.add(rs.getLong(1));
		}
		
		// iterate through the appids
		for(Long l : appids) {
			
			//average
			double avg = 0.0;
			
			//an arraylist full of the averages that were previously recorded
			ArrayList<Double> avgs = new ArrayList<Double>();
			
			//get the number of player where the appid is equal to the current cycle
			sql = "SELECT numplayers FROM daily_table WHERE appid = ?";
			
			//prepare the statement
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, l);
			
			//execute and store results
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				//add to the averages table
				avgs.add((double)rs.getFloat(1));
			}
			
			//get the average
			avg = getAverage((Double[])avgs.toArray());
			
			//save the average in a pair			
			Pair<Long , Double> pair = new MutablePair<>(l, avg);
			
			//add it to the final arraylist
			pairs.add(pair);
		}
		
		//return the final arrraylist
		return pairs;
	}
	
	private static ArrayList<Pair<Long, Double>> getWeeklyAvg(Connection con) throws Exception{
		ArrayList<Pair<Long, Double>> pairs = new ArrayList<Pair<Long, Double>>();		
		String sql = "SELECT DISTINCT appid FROM weekly_table";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Long> appids = new ArrayList<Long>();
		
		while(rs.next()) {
			appids.add(rs.getLong(1));
		}
		
		for(Long l : appids) {
			
			double avg = 0.0;
			
			ArrayList<Double> avgs = new ArrayList<Double>();
			
			
			sql = "SELECT numplayers FROM weekly_table WHERE appid = ?";
			
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, l);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				avgs.add((double)rs.getFloat(1));
			}
			
			avg = getAverage((Double[])avgs.toArray());
			
			Pair<Long , Double> pair = new MutablePair<>(l, avg);
			
			pairs.add(pair);
		}
		
		return pairs;
	}
	
	private static ArrayList<Pair<Long, Double>> getMonthlyAvg(Connection con) throws Exception{
		ArrayList<Pair<Long, Double>> pairs = new ArrayList<Pair<Long, Double>>();		
		String sql = "SELECT DISTINCT appid FROM monthly_table";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Long> appids = new ArrayList<Long>();
		
		while(rs.next()) {
			appids.add(rs.getLong(1));
		}
		
		for(Long l : appids) {
			
			double avg = 0.0;
			
			ArrayList<Double> avgs = new ArrayList<Double>();
			
			
			sql = "SELECT numplayers FROM monthly_table WHERE appid = ?";
			
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, l);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				avgs.add((double)rs.getFloat(1));
			}
			
			avg = getAverage((Double[])avgs.toArray());
			
			Pair<Long , Double> pair = new MutablePair<>(l, avg);
			
			pairs.add(pair);
		}
		
		return pairs;
	}
	
	
	
	private static ArrayList<Pair<Long, Double>> getAllTimeAvg(Connection con) throws Exception{
		
		ArrayList<Pair<Long, Double>> pairs = new ArrayList<Pair<Long, Double>>();		
		String sql = "SELECT DISTINCT appid FROM yearly_table";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Long> appids = new ArrayList<Long>();
		
		while(rs.next()) {
			appids.add(rs.getLong(1));
		}
		
		for(Long l : appids) {
			
			double avg = 0.0;
			
			ArrayList<Double> avgs = new ArrayList<Double>();
			
			
			sql = "SELECT numplayers FROM yearly_table WHERE appid = ?";
			
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, l);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				avgs.add((double)rs.getFloat(1));
			}
			
			avg = getAverage((Double[])avgs.toArray());
			
			Pair<Long , Double> pair = new MutablePair<>(l, avg);
			
			pairs.add(pair);
		}
		
		return pairs;
	}
	
	/*
	 *  this function is much like the next few ones
	 */
	private static ArrayList<Pair<Long, Double>> getDailyMax(Connection con) throws Exception{
		
		//define vars
		ArrayList<Pair<Long, Double>> pairs = new ArrayList<Pair<Long, Double>>();		
		String sql = "SELECT DISTINCT appid FROM daily_table";
		
		//prepare statement
		PreparedStatement stmt = con.prepareStatement(sql);
		
		//get results
		ResultSet rs = stmt.executeQuery();
		
		//appid list
		ArrayList<Long> appids = new ArrayList<Long>();
		
		while(rs.next()) {
			//iterate through results and store them
			appids.add(rs.getLong(1));
		}
		
		//iterate through appids
		for(Long l : appids) {
			//max variable
			double max = 0.0;
			
			//list of maxes
			ArrayList<Double> maxes = new ArrayList<Double>();
			
			//get maxes stored in sql db
			sql = "SELECT numplayers FROM daily_table WHERE appid = ?";
			
			//prepare statement
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, l);
			
			//get results
			rs = stmt.executeQuery();
			
			//iterate through results
			while(rs.next()) {
				//store result
				maxes.add((double)rs.getFloat(1));
			}
			
			//store the max
			max = getMax((Double[])maxes.toArray());
			
			//store into pair
			Pair<Long , Double> pair = new MutablePair<>(l, max);
			
			//add to final list
			pairs.add(pair);
		}
		
		//return final list
		return pairs;
	}
	
	private static ArrayList<Pair<Long, Double>> getWeeklyMax(Connection con) throws Exception{
		ArrayList<Pair<Long, Double>> pairs = new ArrayList<Pair<Long, Double>>();		
		String sql = "SELECT DISTINCT appid FROM weekly_maxes";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Long> appids = new ArrayList<Long>();
		
		while(rs.next()) {
			appids.add(rs.getLong(1));
		}
		
		for(Long l : appids) {
			
			double max = 0.0;
			
			ArrayList<Double> maxes = new ArrayList<Double>();
			
			
			sql = "SELECT numplayers FROM weekly_maxes WHERE appid = ?";
			
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, l);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				maxes.add((double)rs.getFloat(1));
			}
			
			max = getAverage((Double[])maxes.toArray());
			
			Pair<Long , Double> pair = new MutablePair<>(l, max);
			
			pairs.add(pair);
		}
		
		return pairs;
	}
	
	private static ArrayList<Pair<Long, Double>> getMonthlyMax(Connection con) throws Exception{
		ArrayList<Pair<Long, Double>> pairs = new ArrayList<Pair<Long, Double>>();		
		String sql = "SELECT DISTINCT appid FROM monthly_maxes";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Long> appids = new ArrayList<Long>();
		
		while(rs.next()) {
			appids.add(rs.getLong(1));
		}
		
		for(Long l : appids) {
			
			double max = 0.0;
			
			ArrayList<Double> maxes = new ArrayList<Double>();
			
			
			sql = "SELECT numplayers FROM monthly_maxes WHERE appid = ?";
			
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, l);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				maxes.add((double)rs.getFloat(1));
			}
			
			max = getAverage((Double[])maxes.toArray());
			
			Pair<Long , Double> pair = new MutablePair<>(l, max);
			
			pairs.add(pair);
		}
		
		return pairs;
	}
	
	
	
	private static ArrayList<Pair<Long, Double>> getAllTimeMax(Connection con) throws Exception{
		
		ArrayList<Pair<Long, Double>> pairs = new ArrayList<Pair<Long, Double>>();		
		String sql = "SELECT DISTINCT appid FROM yearly_maxes";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Long> appids = new ArrayList<Long>();
		
		while(rs.next()) {
			appids.add(rs.getLong(1));
		}
		
		for(Long l : appids) {
			
			double max = 0.0;
			
			ArrayList<Double> maxes = new ArrayList<Double>();
			
			
			sql = "SELECT numplayers FROM yearly_maxes WHERE appid = ?";
			
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, l);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				maxes.add((double)rs.getFloat(1));
			}
			
			max = getMax((Double[])maxes.toArray());
			
			Pair<Long , Double> pair = new MutablePair<>(l, max);
			
			pairs.add(pair);
		}
		
		return pairs;
	}
	
	//get average value
	private static double getAverage(Double[] maxes) throws Exception{
		
		double out  = 0.0;
		double count = maxes.length;
		
		for(double max : maxes) {
			out += max;
		}
		out /= count;
		
		return out;
	}
	
	//get max value
	private static double getMax(Double[] maxes) {
		double out = 0.0;
		for(double d : maxes) {
			if(d > out) {
				out = d;
			}
		}
		return out;
	}

}
