package com.nk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Ps_DateApp_Sequence {
private static final String INSERT_DETAILS="INSERT INTO CITIZEN_DETAILS VALUES (CID_SEQ.NEXTVAL,?,?,?,?,?)";
	public static void main(String[] args) {
//		          Name                                     Null?    Type
		//	 ------------------------------------ -------- ------------------------------------
		/*		 CID                                       NOT NULL NUMBER(10)
				 CNAME                                               VARCHAR2(20)
				 CADD                                                VARCHAR2(20)
				 DOB                                                  DATE
				 DOM                                                  DATE
				 DOJ                                                  DATE
*/
		Connection con=null;
		PreparedStatement ps=null;
		Scanner sc=null;
		try {
			
			//reading inputs from user
			sc=new Scanner(System.in);
			int cno=0;
			String name=null;
			String add=null;
			String dob=null;
			String dom=null;
			String doj=null;
			

			if(sc!=null) {
				
				System.out.println("enter the name  ::");
				name=sc.next();
				System.out.println("enter the address ::");
				add=sc.next();
				System.out.println("enter the date of birth ::");
				dob=sc.next();
				System.out.println("enter the date of marrige  ::");
				dom=sc.next();
				System.out.println("enter the date of joining  ::");
				doj=sc.next();
			}
			//convert DOB(dd-MM-yyyy)to java.sql.Data class obj
			SimpleDateFormat sd1=new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udob=sd1.parse(dob);
			long ms=udob.getTime();
			java.sql.Date sqdob=new java.sql.Date(ms);
			//convert dom (MM-dd-yyyy)to java.sql.Date class obj
			SimpleDateFormat sd2=new SimpleDateFormat("MM-dd-yyyy");
			java.util.Date udom=sd1.parse(dom);
			java.sql.Date sqdom=new java.sql.Date(udom.getTime());
			//convert doj to java.sql.Date class obj
            java.sql.Date sqdoj=java.sql.Date.valueOf(doj);
            
			//loading driver manager class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//creating preparedStatement
			if(con!=null)
				ps=con.prepareStatement(INSERT_DETAILS);
			if(ps!=null) {
				
			ps.setString(1, name);
			ps.setString(2,add);
			ps.setDate(3,sqdob);
			ps.setDate(4,sqdom);
			ps.setDate(5,sqdoj);
			}//if
			//execute the pre-compiled SQL query
			int result=ps.executeUpdate();
			//processing the result
			if(result==0)
			System.out.println("Action (records insertion) id wasted");
			else
				System.out.println("Action record is inserted");
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
