package com.nk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class ps_InsertingData {

	private  static final String S_QUERY ="INSERT INTO STUDENT VALUES(?,?)";
	public static void main(String[] args) {
		
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		int no=0;
		String name=null;
		try {
			
			//reading inputs from the users
			sc=new Scanner(System.in); 
			System.out.println("enter sid of student::");
			 no=sc.nextInt();
			 System.out.println("enter sid of student::");
			 name=sc.next();
			 
			//loading driverclass;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//creating statement obj
			if(con!=null)
				ps=con.prepareStatement(S_QUERY);
			ps.setInt(1,no );
			ps.setString(2, name);
			int result=ps.executeUpdate();
				if (result==0)
						System.out.println("no records are found");
					else
						System.out.println(result+"no of records are effected");
			
		}//try
		catch(SQLException se){
			se.printStackTrace();
			
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		finally {
			try {
			if(ps!=null)
			ps.close();
			}
			catch(SQLException se){
				se.printStackTrace();
				
			}
			try {
			if(con!=null)
				ps.close();
				}
				catch(SQLException se){
					se.printStackTrace();
					
				}
			
			try{
				
			if(sc!=null)
				sc.close();
				}
				catch(Exception e){
					e.printStackTrace();
					
				}
		}//finally
		
	}//main

}//class
