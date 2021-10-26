package com.nk.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*  create or replace procedure P_refcursor(deg1 in varchar2,
  2                               deg2 in varchar2,
  3                                deg3 in varchar2,
  4                                  details out sys_refcursor) as
  5  begin
  6  open details for
  7  select empid,ename,sal,location  from emp where job in(deg1,deg2,deg3) order by job;
  8* end p_refcursor;;*/
public class Cs_rescursor_App {
private static final String CALL_PROCEDURE= "{ CALL P_refcursor(?,?,?,?)}";

	
		public static void main(String[] args) {
		String deg1=null,deg2=null,deg3=null;
		// reading inputs
				try(Scanner sc=new Scanner(System.in);){
					System.out.println("enter the deg1 ::");
					deg1=sc.next();
					System.out.println("enter the deg2 ::");
					deg2=sc.next();
					System.out.println("enter the deg3 ::");
					deg3=sc.next();
					
				}//try
				catch(Exception e) {
					e.printStackTrace();
				}//catch
				try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
					CallableStatement cs=con.prepareCall(CALL_PROCEDURE);){
					//setting the output parameters
					cs.registerOutParameter(4,OracleTypes.CURSOR);
					if(cs!=null){
					//setting input parameters
					cs.setString(1,deg1);
					cs.setString(2,deg2);
					cs.setString(3,deg3);
					}
					//excute the callable statement
					if(cs!=null)
						cs.execute();
					
					if (cs!=null) {
					ResultSet rs=(ResultSet)cs.getObject(4);
					//process the result
					if(cs!=null) {
						boolean flag=true;
					while(rs.next()) {
						flag=false;
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3)+"\t"+rs.getString(4));
					}
					if(flag==false)
						System.out.println("No records found");
					}
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
