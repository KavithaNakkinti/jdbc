package com.nk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Ps_LoginapApp {
	//prepared query
			private static final String LOGIN_DETAILS="SELECT COUNT(*) FROM USERLIST WHERE USERNAME=? AND PASSWORD=?";
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null;
		String password=null;
	 Connection con=null;
	 PreparedStatement ps=null;
	 ResultSet rs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
			//readings inputs from the user
			
			System.out.println("enter the username ::");
			name=sc.next();
			System.out.println("enter the password ::");
			password=sc.next();
			}//if
			//loading the drivermanager class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establishing the connection with db
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//prepareStatement obj creation
			if(con!=null)
				ps=con.prepareStatement(LOGIN_DETAILS);
			if(ps!=null) {
			
				
			ps.setString(1,name);
			ps.setString(2, password);
			}
			if(ps!=null)
			rs=ps.executeQuery();
				if(rs!=null) {
					rs.next();
					int count=rs.getInt(1);
					if(count==0)
						System.out.println("INVALID CREDENTIALS");
					else
						System.out.println("VALID CREDENTIALS");
			}//if
		}//try
	
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}

		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
			if(rs!=null)
				rs.close();
			}//try
			catch(SQLException se) {
			se.printStackTrace();	
			}
			try {
				if(ps!=null)
					ps.close();
				}//try
				catch(SQLException se) {
				se.printStackTrace();	
				}
			try {
				if(con!=null)
					con.close();
				}//try
				catch(SQLException se) {
				se.printStackTrace();	
				}
			try {
				if(sc!=null)
					sc.close();
				}//try
				catch(Exception e) {
				e.printStackTrace();	
				}//catch
		}//finally
	}//main

}//calss
