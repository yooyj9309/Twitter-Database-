import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import javax.swing.*;

public class transmission {
	private JTextArea myboard=new JTextArea();
	private String id= new String();
	private String ment = new String();
	public transmission(String id,String memo,JTextArea a)
	{
		this.id=id;
		this.myboard=a;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= (Connection)DriverManager.getConnection("jdbc:mysql://localhost/twitter","root","123456");
			CallableStatement cStmt=con.prepareCall("{call GetMemo(?, ?)}");
			cStmt.setString(1,id);
			cStmt.setString(2,memo);
			
			ResultSet rs = cStmt.executeQuery();
			Statement stmt2 = con.createStatement();
			
			
			ResultSet rs1 = stmt2.executeQuery("select distinct memo.id,text,time from ((select following as writer from follow where ID='"+id+"') union (select user.id from memo,user where user.id='"+id+"'and memo.id='"+id+"'))as T natural join memo where writer = memo.id order by time desc;");
			
			ResultSetMetaData rsmd= rs1.getMetaData();
			
			while (rs1.next()) 
			{
				System.out.println(rs1.getString(1));
				myboard.append(rs1.getString(2)+"\n");
				myboard.append("Receiver : "+rs1.getString(1)+"  "+rs1.getString(3)+"\n");
			}
			rs1.close();
			
			rs.close();
			
			cStmt.close();
			con.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
