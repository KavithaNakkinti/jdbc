package com.nk.jdbc.rs;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI_RSTESTApp extends JFrame{
private static final String GET_DATA="SELECT EMPID,ENAME,SAL,LOCATION FROM EMP";
private JLabel lempid,lename,lsal,lloc ;
private JTextField teno,tname,tsal,tloc;
private  JButton bfrist,bnext,bPrevious,blast;
	
public GUI_RSTESTApp() {
	System.out.println("constractor");
	setTitle("GUI-FRONT END");
	setSize(300,300);
	setBackground(Color.cyan);
	setLayout(new FlowLayout());
	//add components
	lempid=new JLabel("eid");
	add(lempid);
	teno=new JTextField(10);
	add(teno);
	
	lename=new JLabel("name");
	add(lename);
	tname=new JTextField(10);
	add(tname);
	
	lsal=new JLabel("sal");
	add(lsal);
	tsal=new JTextField(10);
	add(tsal);
	
	lloc=new JLabel("loc");
	add(lempid);
	tloc=new JTextField(10);
	add(tloc);

}		
}
