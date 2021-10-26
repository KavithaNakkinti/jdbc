package com.nk.jdbc.rs;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class rs_readonly_RSTest {
   private static final String GET_EMPS_QUERY="SELECT EMPID,ENAME,SAL,LOCATION FROM EMP";
	public static void main(String[] args)  {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet rs=st.executeQuery(GET_EMPS_QUERY)){
			System.out.println("Records form top to bottom");
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+""+rs.getString(4));
				}
			}
			rs.afterLast();
			System.out.println("records get from bottom to top");
			if(rs!=null) {
				while(rs.previous()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+""+rs.getString(4));
				}
				System.out.println();
			}
		}//try
		catch(SQLException se) {
		se.printStackTrace();	
		}
		catch(Exception e) {
		e.printStackTrace();	
		}
		
		
	}

}
