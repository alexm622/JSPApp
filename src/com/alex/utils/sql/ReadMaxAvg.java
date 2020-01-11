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
	
	public static HashMap<String, ArrayList<Pair<Long , Double>>> getMaxes() throws Exception{
		
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
		
		
		
		
		
		
		
		return null;
	}
	
	
	private static ArrayList<Pair<Long, Double>> getDailyAvg(Connection con) throws Exception{
		ArrayList<Pair<Long, Double>> pairs = new ArrayList<Pair<Long, Double>>();		
		String sql = "SELECT DISTINCT appid FROM daily_table";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Long> appids = new ArrayList<Long>();
		
		while(rs.next()) {
			appids.add(rs.getLong(1));
		}
		
		for(Long l : appids) {
			
			double avg = 0.0;
			
			ArrayList<Double> avgs = new ArrayList<Double>();
			
			
			sql = "SELECT numplayers FROM daily_table WHERE appid = ?";
			
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
	
	private static ArrayList<Pair<Long, Double>> getDailyMax(Connection con) throws Exception{
		ArrayList<Pair<Long, Double>> pairs = new ArrayList<Pair<Long, Double>>();		
		String sql = "SELECT DISTINCT appid FROM daily_table";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Long> appids = new ArrayList<Long>();
		
		while(rs.next()) {
			appids.add(rs.getLong(1));
		}
		
		for(Long l : appids) {
			
			double max = 0.0;
			
			ArrayList<Double> maxes = new ArrayList<Double>();
			
			
			sql = "SELECT numplayers FROM daily_table WHERE appid = ?";
			
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

	private static double getAverage(Double[] maxes) throws Exception{
		
		double out  = 0.0;
		double count = maxes.length;
		
		for(double max : maxes) {
			out += max;
		}
		out /= count;
		
		return out;
	}
	
	private static double getMax(Double[] maxes) {
		double out = 0.0;
		return out;
	}

}
