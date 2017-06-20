import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import javax.swing.*;

public class register implements ActionListener{
	JFrame frm= new JFrame("REGISTER");
	JPanel p=new JPanel();
	JLabel[] article = new JLabel[6];
	JLabel[] error = new JLabel[2];
	JTextArea[] text= new JTextArea[4];
	JPasswordField[] text1 = new JPasswordField[2];
	JButton reg= new JButton("REGISTER");
	
	public register()
	{
		
		//frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		p.setLayout(null);
		frm.setBounds(700,100,300,500);
		frm.add(p);
		
		p.setBounds(0,0,300,500);
		p.setBackground(Color.cyan);
		
		article[0] = new JLabel("I  D ");
		article[1] = new JLabel("NAME ");
		article[2] = new JLabel("P  W ");
		article[3] = new JLabel("REPW ");
		article[4] = new JLabel("ADDR ");
		article[5] = new JLabel("Phone ");
		
		for(int i=0;i<4;i++)
		{
			text[i] = new JTextArea();
			p.add(text[i]);	
			
		}
		
		for(int i=0;i<2;i++)
		{
			text1[i] = new JPasswordField();
			error[i] = new JLabel();
			p.add(text1[i]);
			p.add(error[i]);
		}
		error[0].setBounds(90,55,150,25);
		error[0].setText("Please not over length(20)");
		error[1].setBounds(30,350,300,40);
		error[1].setForeground(Color.red);
		error[1].setText("");
		for(int i=0;i<6;i++)
		{
			p.add(article[i]);
			article[i].setBounds(40,20+(60*i),40,40);
		}
		
		text[0].setBounds(100,30,150,25);
		text[1].setBounds(100,85,150,25);
		text[2].setBounds(100,265,150,25);
		text[3].setBounds(100,325,150,25);
		
		text1[0].setBounds(100,145,150,25);
		text1[1].setBounds(100,205,150,25);
		
		p.add(reg);
		reg.addActionListener(this);
		reg.setBounds(150, 400, 100, 40);
		frm.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==reg)
		{
			String id = text[0].getText();
			String name = text[1].getText();
			String pw = text1[0].getText();
			String rpw = text1[1].getText();
			String addr = text[2].getText();
			String phone = text[3].getText();
			
			boolean check=true;
			
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con= (Connection)DriverManager.getConnection("jdbc:mysql://localhost/twitter","root","123456");
					Statement stmt2 = con.createStatement();
					ResultSet rs1 = stmt2.executeQuery("select ID from User;");
					ResultSetMetaData rsmd = rs1.getMetaData();
					int colnum = rsmd.getColumnCount();
					while (rs1.next()) {
						for (int i = 1; i <= colnum; i++){
							if(rs1.getString(i).equals(id))
								{
									error[1].setText("REDUNDUNT ID");
									text[0].setText("");
									check=false;
								}
						}
					}
					rs1.close();
					
					if(id.length()>20)
					{
						error[1].setText("Please input not over length(20)");
						text[0].setText("");
						check=false;
					}
					if(!pw.equals(rpw))
					{
						error[1].setText("Check your password and repassword");
						text1[0].setText("");
						text1[1].setText("");
						check=false;
					}
					if(id.equals("")||pw.equals("")||rpw.equals("")||addr.equals("")||phone.equals("")||name.equals(""))
					{
						error[1].setText("Please input your information");
						check=false;
					}
					
					if(check){
					CallableStatement cStmt=con.prepareCall("{call GetUsers(?, ?, ?, ?, ?)}");
					cStmt.setString(1,id);
					cStmt.setString(2,addr);
					cStmt.setString(3,phone);
					cStmt.setString(4,pw);
					cStmt.setString(5,name);
					
					ResultSet rs = cStmt.executeQuery();
					error[1].setForeground(Color.magenta);
					error[1].setText("Thank you!! Welcome my twitter!!");
					
					rs.close();
					cStmt.close();
					con.close();
					}
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				}
			
		}
	}
}
