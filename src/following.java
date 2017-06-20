import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import javax.swing.*;
public class following {

	private String id= new String();
	private String you=new String();
	public following(String me,String you)
	{
		this.id=me;	
		this.you=you;
	}
	public boolean already()
	{
		boolean can=false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost/twitter","root","123456");
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select ID,following from follow;");
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()){
					if(rs.getString(1).equals(id)&&rs.getString(2).equals(you))
					{
						can= true;
						break;
					}
					else{
						can= false;
					}
			}
			
			rs.close();
			stmt.close();
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return can;
	}
	public int Canfollow()
	{
		int can=0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost/twitter","root","123456");
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select ID from User;");
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()){
					if(rs.getString(1).equals(you))
					{
						can= 1;
						break;
					}
					else{
						can= -1;
					}
			}
			
			rs.close();
			stmt.close();
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return can;
	}
	public void getFollow()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost/twitter","root","123456");
			CallableStatement cStmt = con.prepareCall("{call GetFollow(?,?)}");
			cStmt.setString(1,id);
			cStmt.setString(2,you);
			
			ResultSet rs = cStmt.executeQuery();
			
			rs.close();
			cStmt.close();
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}


}
