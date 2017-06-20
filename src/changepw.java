import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import javax.swing.*;
public class changepw implements ActionListener{
	String id=new String();
	JFrame frm = new JFrame("CHANGE PASSWORD");
	JFrame frm1 = new JFrame();
	JTextField cpw=new JTextField();
	JLabel tmp=new JLabel("Write your new password !");
	JLabel tmp1=new JLabel("##Change your password!!##");
	JButton btn=new JButton("Change");
	
	public changepw(String id)
	{
		this.id=id;
		frm.setBounds(500,40,280,200);
		frm1.setBounds(500,40,280,200);
		frm.setBackground(Color.cyan);
		frm.setLayout(null);
		frm.add(tmp);
		frm.add(btn);
		frm.add(cpw);
		frm1.add(tmp1);
		tmp1.setForeground(Color.magenta);
		btn.addActionListener(this);
		tmp.setBounds(20,20,200,40);
		cpw.setBounds(20,70,200,30);
		btn.setBounds(150,120,80,30);
		
		frm.setVisible(true);
		frm1.setVisible(false);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new changepw("dd");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btn)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost/twitter","root","123456");
				Statement stmt = con.createStatement();
				stmt.executeUpdate("update User set password =" +"'"+cpw.getText()+"'"+" where ID ="+"'"+id+"';");
				frm.setVisible(false);
				frm1.setVisible(true);
				stmt.close();
				con.close();
			}catch(SQLException e1){
				e1.printStackTrace();
			}catch(ClassNotFoundException e2){
				e2.printStackTrace();
			}
		}
	}

}
