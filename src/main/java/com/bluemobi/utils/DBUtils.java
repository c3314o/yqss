package com.bluemobi.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.lang.StringUtils;

public class DBUtils {
	
	public static final String driverName = "com.mysql.jdbc.Driver";
	public static final String url= "jdbc:mysql://localhost:3306/yqss";
	public static final String username = "root";
	public static final String password = "root";
	
	public static Connection getConnection(String driverName,String url,String username,String password) {
		Connection conn = null;
		try {
			Class.forName(driverName);
			if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password) && StringUtils.isNotBlank(url)) {
				conn = DriverManager.getConnection(url, username, password);
			}
			else{
				throw new IllegalArgumentException("param is exception..");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn ,PreparedStatement ps,ResultSet rs) {
		try {
			if(conn != null) {
				conn.close();
			}
			if( ps != null ) {
				ps.close();
			}
			if( rs != null ) {
				
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(Connection conn ,PreparedStatement ps) {
		closeConnection(conn, ps, null);
	}
	
	/**
	 * 
     * @Title: getRate
     * @Description: 获取利息
     * @param @return    参数
     * @return double    返回类型
     * @throws
	 */
	public static double getRate() {
		Connection conn = getConnection(driverName, url, username, password);
		String sql = "SELECT rate FROM commons limit 1";
		PreparedStatement ps = null;
		ResultSet rs = null;
		double value = 0.0;
		try {
			ps = conn.prepareStatement(sql);
		    rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	value = rs.getDouble("rate");
			}
		   
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, ps, rs);
		}
		return (value == 0 ? 2 : value);
	}
	
	/**
	 * 逾期利息
	 * @return
	 */
	public static double getRate0() {
		Connection conn = getConnection(driverName, url, username, password);
		String sql = "SELECT overdue FROM commons limit 1";
		PreparedStatement ps = null;
		ResultSet rs = null;
		double value = 0.0;
		try {
			ps = conn.prepareStatement(sql);
		    rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	value = rs.getDouble("overdue");
			}
		   
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, ps, rs);
		}
		return (value == 0 ?  10 : value);
	}
}
