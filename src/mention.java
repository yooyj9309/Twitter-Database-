import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;


public class mention {
	private JTextArea myboard=new JTextArea();
	private String id= new String();
	private String ment = new String();
	public mention(String id,String memo,String ment,JTextArea a)
	{
		this.id=id;
		this.myboard=a;
		this.ment=ment;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= (Connection)DriverManager.getConnection("jdbc:mysql://localhost/twitter","root","123456");
			CallableStatement cStmt=con.prepareCall("{call GetMemo(?, ?)}");
			cStmt.setString(1,id);
			cStmt.setString(2,memo);
			
			ResultSet rs = cStmt.executeQuery();
			Statement stmt2 = con.createStatement();
			
			ResultSet rs1 = stmt2.executeQuery("select distinct memo.id,text,time from memo where id='"+ment+"'order by time desc;");
			System.out.println(ment);
			ResultSetMetaData rsmd= rs1.getMetaData();
			
			/*while (rs1.next()) 
			{
				System.out.println(ment+" "+rs1.getString(2));
				myboard.append(rs1.getString(2)+"\n");
				myboard.append("Receive : "+ment+"  "+rs1.getString(3)+"\n");
				
			}*/
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
