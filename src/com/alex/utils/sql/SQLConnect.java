package com.alex.utils.sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.alex.utils.Snippits;

public class SQLConnect {
	
	public static Connection getCon(String db,String uname,String passwd) throws IOException ,SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con;
		String extern = "73.17.34.121";
		if(Snippits.getExternalIp().equals(extern)) {
			//connect across local network
			con = DriverManager.getConnection("jdbc:mysql://10.0.0.6:3306/" + db + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EST",uname, passwd);
		}else {
			//access using special remote account
			con = DriverManager.getConnection("jdbc:mysql://192.168.15.4:3306/" + db + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EST", uname, passwd);
		}
		return con;
	}
	
}
