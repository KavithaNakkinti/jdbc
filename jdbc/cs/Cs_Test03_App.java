package com.nk.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*create or replace procedure P_AUTH
(no in VARCHAR2,
NAME out VARCHAR2,
SALARY out FLOAT) AS
begin
select  ename,sal INTO name,salary from emp where empid=no;
end P_AUTH;*/
public class Cs_Test03_App {
private static final String CALL_PROCEDURE= "{ CALL P_AUTH(?,?,?)}";

	
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
					cs.registerOutParameter(3,Types.FLOAT );
					//setting input parameters
					cs.setInt(1,no);
					//excute the callable statement
					if(cs!=null)
						cs.execute();
					
					if (cs!=null) {
						String result=null;
					float res=0.0f;
					result=cs.getString(2);
					res =cs.getFloat(3);
					System.out.println("employee name is:"+result +"\n salary of employee"
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
