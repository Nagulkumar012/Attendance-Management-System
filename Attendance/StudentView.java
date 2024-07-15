package Attendance;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StudentView {
	
	Connection con;
	JFrame frame = new JFrame();
	DefaultTableModel model = new DefaultTableModel();
	
	public void stView(int id) throws SQLException {
		Login login = new Login();
		
		ImageIcon ic=new ImageIcon("F:\\digit_to_text_coverter\\AJ@.jpg");
		JLabel jl=new JLabel(ic);
		jl.setBounds(0,0,1000,800);
		//-------------------LoginPage----------------------------
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
		//-----------------------BACK---------------------------------
		JLabel back = new JLabel("< BACK");
		back.setForeground(Color.decode("#37474F"));
		back.setFont(new Font("Times New Roman", Font.BOLD, 17));
		back.setBounds(18, 10, 100, 20);
		frame.add(back);
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});		
		//--------------------------------------------------------------
		
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
		
		
		//----------------TABLE---------------------------------
		JTable table=new JTable();
		model = (DefaultTableModel)table.getModel();
		model.addColumn("DATE");
		model.addColumn("STATUS");
		JScrollPane scPane=new JScrollPane(table);
		scPane.setBounds(500, 50, 480, 525);
		table.setFont(new Font("Times New Roman", Font.BOLD, 20));
		table.setRowHeight(50);
		frame.add(scPane);
		//------------------------------------------------------
		
		//--------------------------INFO------------------------
		JLabel totalclass = new JLabel("TOTAL CLASSES : ");
		totalclass.setBounds(25, 180, 250, 20);
		totalclass.setForeground(Color.decode("#DEE4E7"));
		totalclass.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.add(totalclass);
		JLabel ttbox = new JLabel("");
		ttbox.setBounds(60, 230, 250, 20);
		ttbox.setForeground(Color.decode("#DEE4E7"));
		ttbox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.add(ttbox);
		JLabel classAtt = new JLabel("CLASSES ATTENDED : ");
		classAtt.setBounds(25, 280, 250, 20);
		classAtt.setForeground(Color.decode("#DEE4E7"));
		classAtt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.add(classAtt);
		JLabel atbox = new JLabel("");
		atbox.setBounds(60, 330, 250, 20);
		atbox.setForeground(Color.decode("#DEE4E7"));
		atbox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.add(atbox);
		JLabel classAbs = new JLabel("CLASSES MISSED : ");
		classAbs.setBounds(25, 380, 250, 20);
		classAbs.setForeground(Color.decode("#DEE4E7"));
		classAbs.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.add(classAbs);
		JLabel mtbox = new JLabel("");
		mtbox.setBounds(60, 430, 250, 20);
		mtbox.setForeground(Color.decode("#DEE4E7"));
		mtbox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.add(mtbox);
		JLabel AttPer = new JLabel("ATTENDANCE PERCENTAGE : ");
		AttPer.setBounds(25, 480, 300, 20);
		AttPer.setForeground(Color.decode("#DEE4E7"));
		AttPer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.add(AttPer);
		JLabel prbox = new JLabel("");
		prbox.setBounds(60, 530, 250, 20);
		prbox.setForeground(Color.decode("#DEE4E7"));
		prbox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.add(prbox);
		//------------------------------------------------------
		
		//----------------------SETVALUES---------------------------
		int[] arr = stat(id);
		ttbox.setText(String.valueOf(arr[0]));
		atbox.setText(String.valueOf(arr[1]));
		mtbox.setText(String.valueOf(arr[2]));
		prbox.setText(String.valueOf(arr[3])+"%");
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
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PBL","root", "root");
		String str = "SELECT name FROM user WHERE id = "+id;
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str);
		rst.next();
		return rst.getString("name");
	}
	
	public void tblupdt(int id) {
		try {
			ResultSet res = dbSearch(id);
			for(int i=0; res.next(); i++) {
				model.addRow(new Object[0]);
				model.setValueAt(res.getString("dt"), i, 0);
		        model.setValueAt(res.getString("status"), i, 1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public int[] stat(int id) throws SQLException {
		String str = "SELECT COUNT(*) AS pre FROM attend WHERE stid = "+id+" AND status = 'Present'";
		String str2 = "SELECT COUNT(*) AS abs FROM attend WHERE stid = "+id+" AND status = 'Absent'";
		String str3 = "SELECT COUNT(*) AS od FROM attend WHERE stid = "+id+" AND status = 'Onduty'";
		int[] x = new int[4];
		int[] y=new int[2];
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str);
		rst.next();
		y[1] = rst.getInt("pre");
		rst = stm.executeQuery(str2);
		rst.next();
		x[2] = rst.getInt("abs");
		rst = stm.executeQuery(str3);
		rst.next();
		y[0] = rst.getInt("od");
		x[1] = y[1] + y[0];
		x[0] = y[1] + x[2] + y[0];
		x[3] = (x[1]*100)/x[0];
		tblupdt(id);
		return x;
	}
	
	public ResultSet dbSearch(int id) throws SQLException {
		String str1 = "SELECT * from attend where stid = "+id+" ORDER BY dt desc";
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str1);
		return rst;
	}
}