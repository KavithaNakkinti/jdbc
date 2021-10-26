package com.nk.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BLOB_Insert_App {
private static final String BLOB_INSERT_QUERY="INSERT INTO ACTOR_INFO VALUES(ACTORID_SEQ.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		//sql table query
		//create table Actor_Info ( ActorID number(10) not null,ActorName varchar2(10),ActorAdds varchar2(10),photo BLOB);
		String name=null, adds=null ,photolocation=null; 
		try(Scanner sc=new Scanner(System.in);){
			System.out.println("Enter the Actor Name :");
			name=sc.next();
			System.out.println("Enter the Actor address :");
			adds=sc.next();
			System.out.println("Enter the Actor photo :");
			photolocation=sc.next().replace("?", "");
			
		}//try
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				PreparedStatement ps=con.prepareStatement(BLOB_INSERT_QUERY);
				InputStream is=new FileInputStream(photolocation);
				){
		if(ps!=null) {
			ps.setString(1,name);
			ps.setString(2,adds);
			ps.setBinaryStream(3,is);
		}//if
			
		//excute Query
		int result=0;
		if(ps!=null)
			result=ps.executeUpdate();
		if(result==0)
			System.out.println("recored not inserted");
		else 
			System.out.println(result+" record is inserted");
		}//try
		catch(SQLException se){
		se.printStackTrace();	
		}
		catch(Exception e){
			e.printStackTrace();	
			}
	}//main

}//class
