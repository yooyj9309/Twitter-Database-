import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import javax.swing.*;
public class Login {

	String id= new String();
	String pw= new String();
	public Login(String id,String pw)
	{
		this.id=id;
		this.pw=pw;
	}
	public int check()
	{
		int ch=0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= (Connection)DriverManager.getConnection("jdbc:mysql://localhost/twitter","root","123456");
			Statement stmt2 = con.createStatement();
			ResultSet rs = stmt2.executeQuery("select ID,password from User;");
			ResultSetMetaData rsmd = rs.getMetaData();
			int colnum = rsmd.getColumnCount();
			while (rs.next())
			{
				if(rs.getString(1).equals(id))
				{
					if(rs.getString(2).equals(pw))
					{
						ch=1;
						break;
					}
					else {
						ch=-1;
						break;
					}
				}
				else
				{
					ch=-2;
				}
			}

		}catch(SQLException e)
		{
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return ch;
	}
	

}
