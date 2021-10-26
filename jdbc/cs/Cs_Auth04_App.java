package com.nk.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*create or replace procedure C_AUTH(uname in varchar2,
		  2  pwd in varchar2,
		  3  result out varchar2)
		  4  as
		  5  cnf number(2);
		  6  begin
		  7  select count(*) into cnf from userlist where username=uname and password=pwd;
		  8  if(cnf<>0)then
		  9  result:='Invalid Credentials';
		 10  else
		 11  result:='Valid credentials';
		 12  end if;
		 13* end;*/

public class Cs_Auth04_App {
private static final String CALL_PROCEDURE= "{ CALL C_AUTH(?,?,?)}";

	
		public static void main(String[] args) {
			String u=null,p=null;
		// reading inputs
				try(Scanner sc=new Scanner(System.in);){
					System.out.println("enter the username ::");
					u=sc.next();
					System.out.println("enter the password::");
					p=sc.next();
					
				}//try
				catch(Exception e) {
					e.printStackTrace();
				}//catch
				try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
					CallableStatement cs=con.prepareCall(CALL_PROCEDURE);){
					//setting the output parameters
					cs.registerOutParameter(3,Types.VARCHAR );
					
					//setting input parameters
					cs.setString(1,u);
					cs.setString(2,p);
					//excute the callable statement
					if(cs!=null)
						cs.execute();
					
					if (cs!=null) {
						String result=null;
				
					result=cs.getString(3);
					
					System.out.println(result);
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
