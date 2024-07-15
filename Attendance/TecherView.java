package Attendance;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class TeacherView {
	public void tcView(int id) throws SQLException {
		Login login = new Login();
		JFrame frame = new JFrame();
		Font btn = new Font("Times New Roman", Font.BOLD, 20);

		ImageIcon ic=new ImageIcon("F:\\digit_to_text_coverter\\AJ@.jpg");
		JLabel jl=new JLabel(ic);
		jl.setBounds(0,0,1000,800);
		
		//------------------------CLOSE---------------------------
		JLabel x = new JLabel("X");
		x.setForeground(Color.decode("#37474F"));
		x.setBounds(965, 10, 100, 20);
		x.setFont(new Font("Times New Roman", Font.BOLD, 20));
		frame.add(x);
		x.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		//---------------------------------------------------------
		
		//---------------------LoginPage-----------------------------
		JLabel lp=new JLabel("LoginPage");
		lp.setForeground(Color.decode("#37474F"));
		lp.setBounds(835,0,100,35);
		lp.setFont(new Font("Verdana", Font.BOLD, 14));
		frame.add(lp);
		lp.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				login.loginView();
				frame.dispose();
			}
			
		});
		//----------------------------------------------------------
				
		//-----------------------MINIMIZE-----------------------------
		JLabel min = new JLabel("_");
		min.setForeground(Color.decode("#37474F"));
		min.setBounds(935, 0, 100, 20);
		frame.add(min);
		min.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		//-------------------------------------------------------------
		
		//------------------Panel----------------------------------
		JPanel panel = new  JPanel();
		panel.setBounds(0, 0, 1000, 35);
		panel.setBackground(Color.decode("#DEE4E7"));
		frame.add(panel);
		//---------------------------------------------------------
		
		//-------------------Welcome---------------------------------
		JLabel welcome = new JLabel("Welcome "+getUser(id)+",");
		welcome.setForeground(Color.decode("#DEE4E7"));
		welcome.setBounds(10, 50, 250, 20);
		welcome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.add(welcome);
		//-----------------------------------------------------------
		
		//----------------------ADDATTENDANCE----------------------------
		JButton addattendance = new JButton("ADD ATTENDANCE");
		addattendance.setBounds(150, 125, 700, 60);
		addattendance.setFont(btn);
		addattendance.setBackground(Color.decode("#DEE4E7"));
		addattendance.setForeground(Color.decode("#37474F"));
		frame.add(addattendance);
		addattendance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddAttendance addatt = new AddAttendance();
				try {
					addatt.addView();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	
		//----------------------------------------------------------
		
		//----------------------EDITATTENDANCE----------------------------
		JButton editattendance = new JButton("EDIT ATTENDANCE");
		editattendance.setBounds(150, 375, 700, 60);
		editattendance.setFont(btn);
		editattendance.setBackground(Color.decode("#DEE4E7"));
		editattendance.setForeground(Color.decode("#37474F"));
		frame.add(editattendance);
		editattendance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditAttendance editatt = new EditAttendance();
				try {
					editatt.editView();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		//----------------------------------------------------------
		//---------------STUDENTSPROGRESS----------------------------
		JButton progress = new JButton("VIEW STUDENT PROGRESS");
		progress.setBounds(150, 250,  700, 60);
		progress.setFont(new Font("Times New Roman", Font.BOLD, 20));
		progress.setBackground(Color.decode("#DEE4E7"));
		progress.setForeground(Color.decode("#37474F"));
		frame.add(progress);
		progress.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentProgress stp= new StudentProgress();
				try {
					stp.studentView();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		//----------------------------------------------------------

		//-------------------------------------------------------
		frame.add(jl);
		frame.setSize(1000,600);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);  
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.getContentPane();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//--------------------------------------------------------------
	}
	
	public String getUser(int id) throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PBL","root", "root");
		String str = "SELECT name FROM user WHERE id = "+id;
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str);
		rst.next();
		return rst.getString("name");
	}
}