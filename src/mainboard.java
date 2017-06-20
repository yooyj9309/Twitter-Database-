import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import javax.swing.*;

import java.util.Date;
public class mainboard extends JFrame implements ActionListener{
	String id= new String();
	JFrame frm= new JFrame();
	Date d=new Date();
	JPanel allpanel= new JPanel();
	JPanel find = new JPanel();
	JPanel following = new JPanel();
	JPanel follower = new JPanel();
	JPanel twit = new JPanel();
	
	JLabel ff= new JLabel("Freind's ID");
	JLabel sm= new JLabel("Find your friends!");
	JLabel fr= new JLabel("FOLLOWER");
	JLabel fn= new JLabel("FOLLOWING");
	JLabel mb= new JLabel("B O A R D");
	JLabel mt= new JLabel("M E N T I O N");
	
	JScrollPane scrollPane = new JScrollPane();
	JScrollPane sp1 = new JScrollPane();
	JScrollPane sp2 = new JScrollPane();
	JScrollPane sp3 = new JScrollPane();
	
	JButton btn = new JButton("C l i c k");
	JButton cbtn = new JButton("C h a n g e  PW");
	JButton fbtn = new JButton("V I S I T");
	JButton trans = new JButton("C L I C K");
	
	JTextArea ta1= new JTextArea();
	JTextArea ta2=new JTextArea();
	JTextArea myboard = new JTextArea();
	JTextArea menboard = new JTextArea();
	
	JTextField myText = new JTextField();
	JTextField  fftf = new JTextField();
	Color bg_c = new Color(222, 235, 247);

	public mainboard(String id)
	{
		this.id=id;

		frm.setBounds(100,40,1000,600);
		frm.setTitle("HELLO" +" " +id +" "+"WELCOME!!");
		allpanel.setLayout(null);
		find.setLayout(null);
		follower.setLayout(null);
		following.setLayout(null);
		twit.setLayout(null);
		
		frm.add(allpanel);
		
		allpanel.add(find);
		allpanel.add(follower);
		allpanel.add(following);
		allpanel.add(twit);
		
		allpanel.setBounds(0, 0,1000,600);
		
		find.setBounds(0,0,750,120);
		find.setBackground(bg_c);
		find.add(ff);
		find.add(fftf);
		find.add(sm);
		find.add(btn);
		find.add(cbtn);
		find.add(fbtn);
		
		
		follower.setBounds(750,0,250,300);
		follower.setBackground(bg_c);
		//follower.add(ta1);
		follower.add(fr);
		
		
		ff.setBounds(20,20,80,30);
		fftf.setBounds(100,20,200,30);
		fftf.addActionListener(this);
		fbtn.setBounds(320,20,80,30);
		btn.addActionListener(this);
		cbtn.setBounds(580,70,120,40);
		cbtn.setBorderPainted(false);
		cbtn.setBackground(Color.white);
		sm.setBounds(50,60,150,30);
		btn.setBounds(220,60,80,40);
		
		
		btn.setVisible(false);
		btn.setBorderPainted(false);
		btn.setBackground(Color.white);
		
		fbtn.setVisible(false);
		fbtn.setBorderPainted(false);
		fbtn.setBackground(Color.white);
		
		cbtn.addActionListener(this);
		fbtn.addActionListener(this);
		
		fr.setBounds(30,10,100,20);
		this.showfollower();
		
		following.setBounds(750,250,250,320);
		following.setBackground(bg_c);
		
		following.add(ta2);
		following.add(fn);
		
		
		ta2.setBackground(Color.white);
		fn.setBounds(30,55,100,20);
		
		this.showfollowing();
		
		twit.setBounds(0,100,750,500);
		twit.setBackground(Color.white);
		twit.add(myboard);
		twit.add(myText);
		twit.add(mb);
		twit.add(trans);
		twit.add(sp3);
		twit.add(mt);
		
		myboard.setBackground(bg_c);
		
		myText.setBounds(40,380,300,30);
		mb.setBounds(50,30,100,30);
		mt.setBounds(420,30,100,30);
		
		scrollPane.setViewportView(myboard);
		scrollPane.setBounds(40,70,300,300);
		
		sp1.setViewportView(ta1);
		sp1.setBounds(20,30,190,250);
		
		sp2.setViewportView(ta2);
		sp2.setBounds(20,80,190,200);
		
		sp3.setViewportView(menboard);
		sp3.setBounds(400,70,300,100);
		
		menboard.setBackground(bg_c);
		twit.add(scrollPane);
		follower.add(sp1);
		following.add(sp2);
		
		trans.setBounds(260,420,80,30);
		trans.setBorderPainted(false);
		trans.setBackground(bg_c);
		
		trans.addActionListener(this);
		myboard.setLineWrap(true);
		
		showFollowmemo();
		frm.setVisible(true);
	}
	
	public void showfollowing()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost/twitter","root","123456");
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("select distinct following from Follow where id = '"+id+"';");
			ResultSetMetaData rsmd = rs.getMetaData();
			int colnum = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colnum; i++){
					ta2.append("");
					ta2.append("   "+rs.getString(i) + "\n");
					ta2.append("");
				}
				System.out.println();
			}

			stmt.close();
			con.close();
		}catch(SQLException e1){
			e1.printStackTrace();
		}catch(ClassNotFoundException e2){
			e2.printStackTrace();
		}
	}

	public void showFollowmemo()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost/twitter","root","123456");
			Statement stmt = con.createStatement();
			
			ResultSet rs=stmt.executeQuery("select distinct memo.id,text,time from (select following as writer from follow where following='"+id+"'or ID='"+id+"')as T natural join memo where writer = memo.id order by time desc;");
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int colnum = rsmd.getColumnCount();
			while (rs.next()) {
				myboard.append(rs.getString(2)+"\n");
				myboard.append("Receiver : "+rs.getString(1)+"  "+rs.getString(3)+"\n");
			}
			stmt.close();
			con.close();
		}catch(SQLException e1){
			e1.printStackTrace();
		}catch(ClassNotFoundException e2){
			e2.printStackTrace();
		}
	}
	public void showfollower()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost/twitter","root","123456");
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("select distinct ID from Follow where following = '"+id+"';");
			ResultSetMetaData rsmd = rs.getMetaData();
			int colnum = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colnum; i++){
					ta1.append("");
					ta1.append("   "+rs.getString(i) + "\n");
					ta1.append("");
				}
				System.out.println();
			}

			stmt.close();
			con.close();
		}catch(SQLException e1){
			e1.printStackTrace();
		}catch(ClassNotFoundException e2){
			e2.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new mainboard("bsm");
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String f = new String();
		f=fftf.getText();
		following a=new following(id,f);
		if(arg0.getSource()==fftf)
		{
			if(a.Canfollow()==1&&!a.already())
			{
				sm.setText("Could you add friend?");
				fbtn.setVisible(true);
				btn.setVisible(true);
			}
			else if(a.already())
			{
				sm.setText("Already your friend!");
				fbtn.setVisible(true);
				btn.setVisible(false);
			}
			else 
				sm.setText("NO TWIT MEMBER!");
			
		}
		if(arg0.getSource()==btn)
		{
			
			a.getFollow();
			sm.setText("NOW BE FRIEND!!");
			ta2.append("   "+f+"\n");
			fftf.setText("");
			
			btn.setVisible(false);
		}
		if(arg0.getSource()==cbtn)
		{
			new changepw(id);
		}
		if(arg0.getSource()==fbtn)
		{
			new mainboard(f);
		}
		if(arg0.getSource()==trans)
		{
			String memo=new String();
			memo= myText.getText();
			if(memo.startsWith("@"))
			{
				
				menboard.setText("");
				int i=memo.indexOf(" ");
				String ment=new String();
				String mention=new String();
				ment = memo.substring(1,i);
				following b=new following(id,ment);
				if(b.already()){
				mention = id+" -> "+" "+ment+" "+memo.substring(i,memo.length());
				new mention(ment,mention,id,null);
				menboard.append(mention+"\n");
				//myboard.append("Receiver : "+id+"  "+ment+"\n");
				myText.setText("");
				}
				else
				{
					menboard.append("You should follow "+ment+"  first!!");
					fftf.setText(ment);
					btn.setVisible(true);
					fbtn.setVisible(true);
					sm.setText("Could you add friend?");
				}
			}
			else{
				if(!myText.getText().equals("")&&memo.length()<=140)
				{
					myboard.setText("");
					new transmission(id,memo,myboard);
					myText.setText("");
				}
				else if(memo.length()>140)
				{
					myText.setText("Please don't over 140 letters");
				}
			}
		}
	}

}
