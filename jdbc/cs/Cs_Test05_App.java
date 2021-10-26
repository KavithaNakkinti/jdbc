package com.nk.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/* create or replace procedure C_GETT(eid in number,
  2                                         name out varchar2,
  3                                            dno out number)as
  4  begin
  5  select ename,deptno into name,dno from emp where empid=eid;
  6* end C_GETT;
SQL> /;*/
public class Cs_Test05_App {
private static final String CALL_PROCEDURE= "{ CALL C_GETT(?,?,?)}";

	
		public static void main(String[] args) {
			int no=0;
		// reading inputs
				try(Scanner sc=new Scanner(System.in);){
					System.out.println("enter the employee number ::");
					no=sc.nextInt();
					
				}//try
				catch(Exception e) {
					e.printStackTrace();
				}//catch
				try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
					CallableStatement cs=con.prepareCall(CALL_PROCEDURE);){
					//setting the output parameters
					cs.registerOutParameter(2,Types.VARCHAR );
					cs.registerOutParameter(3,Types.INTEGER );
					//setting input parameters
					cs.setInt(1,no);
					//excute the callable statement
					if(cs!=null)
						cs.execute();
					
					if (cs!=null) {
						String result=null;
					int res=0;
					result=cs.getString(2);
					res =cs.getInt(3);
					System.out.println("employee location is:"+result +"\n address of employee"
							+ " is :"+res);
					}
							
						}//try
				catch(SQLException se) {
				se.printStackTrace();	
				}//catch
				catch(Exception e) {
					e.printStackTrace();	
					}//catch
			
	}

}
