import java.util.Date;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
class login extends JFrame implements ActionListener
{
	JTextField t1,t3;
	JPasswordField t2;
	JLabel l1,l2,log,l3;
	JButton b1,b2;
	ImageIcon i,i1;
	Connection cn=null;
	ResultSet rs;
	Statement stm;
	PreparedStatement p;
	String sql;
	JComboBox cmb;
	Date d;
	Panel p1;
	login()
	{
		super("AEMS LEAVING SOFTWARE ");
		setSize(1500,1500);
		setVisible(true);
		setLayout(null);
		
		
		t1=new JTextField();t2=new JPasswordField();t3=new JTextField();
		l1=new JLabel("Username:"); l2=new JLabel("Passward:");log=new JLabel("Admin Login");l3=new JLabel("Adademic Year:");
		b1=new JButton("Submit"); b2=new JButton("clear");cmb=new JComboBox();
		
		p1=new Panel();
		
		
	    
		add(p1);
		p1.setBounds(0,0,1500,1500);
		p1.setBackground(Color.PINK);
		p1.setLayout(null);
		p1.add(t1);p1.add(t2);p1.add(l1);p1.add(l2);p1.add(l3);p1.add(b1);p1.add(b2);p1.add(log);p1.add(cmb);
		
		d=new Date();
		int year=d.getYear()+1900;
		int month=d.getMonth()+1;
		if(month<=3)
			year=year-1;
		
		for(int i=0;i<5;i++)
		{
			int last=year-i;
			cmb.addItem(last);
		}
		log.setFont(new Font("Arial",Font.BOLD,30));
		log.setForeground(Color.RED);
		// setBounds
		log.setBounds(600,150,280,40);
		l1.setBounds(600,210,70,40);t1.setBounds(680,210,150,30);
		l2.setBounds(600,260,70,40);t2.setBounds(680,260,150,30);
		l3.setBounds(580,310,100,40);cmb.setBounds(680,310,150,30);
		b1.setBounds(600,360,110,40);b2.setBounds(720,360,110,40);
		b1.setMnemonic('s');
		b2.setMnemonic('c');
		try
		{
			cn=DriverManager.getConnection("jdbc:mysql:///login","root","root");
		}catch(Exception e)
		{}
		// addListener
		b1.addActionListener(this);
		b2.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			if (e.getSource() == b1) {
			try {
				b1.requestFocus();
				stm = cn.createStatement();
				
				// Your SQL UPDATE statement
				int newYearValue = (int) cmb.getSelectedItem();
				sql = "UPDATE login SET year = ? WHERE user = ? and pass=?";
				
				// Prepare the statement with placeholders
				p = cn.prepareStatement(sql);
				p.setInt(1, newYearValue);
				p.setString(2, t1.getText().toLowerCase());
				p.setString(3, t2.getText());
				
				// Execute the prepared statement
				int updatedRows = p.executeUpdate();
				
				if (updatedRows > 0) {
					// The 'year' column was successfully updated
					dispose();
					new mainpage();
				} else {
					JOptionPane.showMessageDialog(null, "Check UserName And Passward....");
				}
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(null, "All fields required....");
				System.out.println(ee);
		  }
	  }

		}
		if(e.getSource()==b2)
		{
			t1.setText("");t2.setText("");
		}
		
	}
	public static void main(String args[])
	{
		new login();
	}
}