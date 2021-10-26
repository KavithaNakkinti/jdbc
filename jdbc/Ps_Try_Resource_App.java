package com.nk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Ps_Try_Resource_App {
	 static final String GET_DOB_BY_ID="SELECT DOB FROM CITIZEN_DETAILS WHERE CID =? ";
	public static void main(String[] args) {
		int cno=0;
		try(Scanner sc=new Scanner(System.in)){
			//reds inputs from the user
			System.out.println("Enter the cid number");
			cno=sc.nextInt();
		}//Scanner try
		catch(Exception e){
			e.printStackTrace();
		}
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				PreparedStatement ps=con.prepareStatement(GET_DOB_BY_ID)){
			if(ps!=null)
				ps.setInt(1, cno);
			try(ResultSet rs=ps.executeQuery()){
			if(rs!=null) {
				if(rs.next()) {
				java.sql.Date sqdob=rs.getDate(1);
			long dobMs=sqdob.getTime();
			long sysDateMs=System.currentTimeMillis();
			double age=(sysDateMs-dobMs)/(1000.0*60.0*60.0*24.0*365.25);
			System.out.println("Person age"+age);
		}
			else {
			System.out.println("Person not found");
		}	
		}
		}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	}

