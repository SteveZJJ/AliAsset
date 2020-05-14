package com.eam;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class SqlServerTest {
	
	@Test
	public void testSqlServer(){
		Connection con = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String dbUrl = "jdbc:sqlserver://100.69.201.175:1433;DatabaseName=EAM";
			String username = "EAM";
			String passWd = "EAM";
			con = DriverManager.getConnection(dbUrl,username,passWd);
			System.out.println("connect successful!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if ( ! ( con==null ) ) {
					con.close();
				}
			}
			catch (SQLException ignored) {
			}
			
		}
	}
}
