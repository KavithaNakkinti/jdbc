package com.nk.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class C_sum {
private static final String CALL_PROCEDURE="{CALLP_SUM(?,?,?)}";
	public static void main(String[] args) {
	int frist=0,second=0;
// reading inputs
		try(Scanner sc=new Scanner(System.in);){
			System.out.println("enter the frist value ::");
			frist=sc.nextInt();
			System.out.println("enter second value ::");
			second =sc.nextInt();
		}//try
		catch(Exception e) {
			e.printStackTrace();
		}//catch
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			CallableStatement cs=con.prepareCall(CALL_PROCEDURE);){
			//setting the output parameters
			cs.registerOutParameter(3,Types.INTEGER );
			//setting input parameters
			cs.setInt(1,frist);
			cs.setInt(2, second);
		
			//excute the callable statement
			if(cs!=null)
				cs.execute();
			
			if (cs!=null) {
				int result=0;
			result=cs.getInt(3);
			System.out.println("sum :"+result);
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
