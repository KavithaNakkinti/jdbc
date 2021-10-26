package com.nk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AgeCalculator_App {
	private static final String AGE_CAL_QUERY="SELECT (SYSDATE-DOB)/365.0 FROM CITIZEN_DETAILS WHERE CID=?";

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
		int	cno=0;
			//reading citizen id number
			sc=new Scanner(System.in);
			System.out.println("Enter the identity number ::");
			cno=sc.nextInt();
			//loading driver manager cladd
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//creating preparedStatement obj
			if(con!=null)
			ps=con.prepareStatement(AGE_CAL_QUERY);
			if(ps!=null)
				ps.setInt(1, cno);
				//excuteing the pre-complied query
			if(ps!=null)
				rs=ps.executeQuery();
				//process the query
				if(rs.next()) {
					float age=rs.getFloat(1);
					System.out.println("person age is :"+age);
				}else {
					System.out.println("Person not found");
				}
					
		}
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

	}

}
