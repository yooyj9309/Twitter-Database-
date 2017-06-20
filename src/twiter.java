import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
public class twiter implements ActionListener{
	JFrame frm=new JFrame("Twitter");//트위터용 플레임
	JPanel p1=new JPanel(){
		Image insideBack = Toolkit.getDefaultToolkit().getImage(
				"twit.PNG");

		public void paintComponent(Graphics g) {
			g.drawImage(insideBack, 0, 0, 300, 100, this);
		}
	};
	JPanel p2=new JPanel();
	JButton login = new JButton("LOG IN");
	JButton register = new JButton("REGISTER");
	JLabel id=new JLabel("I D      ");
	JLabel pw=new JLabel("P W      ");
	JLabel error = new JLabel();
	
	JTextField tf1= new JTextField();
	JPasswordField tf2= new JPasswordField();
	public void init()
	{
		frm.setBounds(300,100,300,400);
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		login.addActionListener(this);
		register.addActionListener(this);
		
		
		frm.add(p1);
		frm.add(p2);
		
		p1.setLayout(null);
		p2.setLayout(null);
		
		
		p2.add(id);
		p2.add(pw);
		
		p2.add(tf1);
		p2.add(tf2);
		
		p2.add(login);
		p2.add(register);
		p2.add(error);
		
		p1.setBounds(0,0,300,100);
		p1.setBackground(Color.cyan);
		
		p2.setBounds(0,100,300,300);
		p2.setBackground(Color.cyan);	
		id.setBounds(30,150,100,40);
		pw.setBounds(30,200,100,40);
		
		tf1.setBounds(90,150,150,30);
		tf2.setBounds(90,200,150,30);
		
		error.setBounds(10,250,300,40);
		
		login.setBounds(30,300,100,30);
		register.setBounds(150,300,100,30);
		
		frm.setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		twiter a= new twiter();
		a.init();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==login)
		{
			String id=tf1.getText();
			String pw=tf2.getText() ;
			
			Login l=new Login(id,pw);
			if(l.check()==1)
				{
					frm.setVisible(false);
					new mainboard(id);
				}
			else if(l.check()==-1)
				{
					error.setForeground(Color.red);
					error.setText("WRONG PASSWORD");
				}
			else if(l.check()==-2)
			{
				error.setForeground(Color.red);
				error.setText("Not exist ID.");
			}
		}
		
		if(arg0.getSource()==register)
		{
			new register();
		}
		
	}

}
