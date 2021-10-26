package com.nk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class PsDateRetrive {
	private static final String GET_DATES="SELECT * FROM CITIZEN_DETAILS";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//loading driver manager class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//creating preparedStatement
			if(con!=null)
				ps=con.prepareStatement(GET_DATES);
			if(ps!=null)
				rs=ps.executeQuery();
			//process the results
			if(ps!=null) {
				while(rs.next()) {
					int cno=rs.getInt(1);
					String cname=rs.getString(2);
					String cadd=rs.getString(3);
					java.sql.Date sqdod=rs.getDate(4);
					java.sql.Date sqdom=rs.getDate(5);
					java.sql.Date sqdoj=rs.getDate(6);
					//converting java.sql.Date class objd to String data value in the required pattern
					SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
					String sdob=sdf.format(sqdod);
					String sdom=sdf.format(sqdom);
					String sdoj=sdf.format(sqdoj);
					System.out.println(cno+"\t"+cname+"\t"+cadd+"\t"+sdob+"\t"+sdom+"\t"+sdoj);

				}//while
			}//if
			
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
				

		}//finally


		
	}

}
