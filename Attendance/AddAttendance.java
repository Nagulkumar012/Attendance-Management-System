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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AddAttendance {
	Connection con;
	DefaultTableModel model = new DefaultTableModel();
	public void addView() throws SQLException {
		connect();
		JFrame frame = new JFrame();
		Font text = new Font("Times New Roman", Font.PLAIN, 18);
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
		//----------------------------------------------------------
		
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
		
		//----------------TABLE---------------------------------
		@SuppressWarnings("serial")
		JTable table=new JTable(){
			public boolean isCellEditable(int row,int column){
					return false;
			}
		};
		model = (DefaultTableModel)table.getModel();
		model.addColumn("ID");
		model.addColumn("NAME");
		model.addColumn("STATUS");
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		JScrollPane scPane=new JScrollPane(table);
		scPane.setBounds(500, 50, 480, 525);
		frame.add(scPane);
		//------------------------------------------------------
		
		//-------------------------DATE-------------------------
		JLabel dt = new JLabel("DATE : ");
		dt.setFont(text);
		dt.setBounds(25, 60, 75, 20);
		dt.setForeground(Color.decode("#DEE4E7"));
		frame.add(dt);
		JTextField dtbox= new JTextField();
		dtbox.setBounds(100, 60, 150, 25);
		dtbox.setBackground(Color.decode("#DEE4E7"));
		dtbox.setFont(text);
		dtbox.setForeground(Color.decode("#37474F"));
		String date =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		dtbox.setText(date);
		frame.add(dtbox);
		//-------------------------------------------------------
		
		//---------------------ALREADY ADDED------------------------
		JLabel txt = new JLabel("");
		txt.setFont(text);
		txt.setBounds(125, 525, 350, 20);
		txt.setForeground(Color.red);
		frame.add(txt);
		//-------------------------------------------------------------
		
		//----------------------VIEWBUTTON-----------------------
		JButton view = new JButton("VIEW");
		view.setBounds(75, 275, 150, 50);
		view.setFont(btn);
		view.setBackground(Color.decode("#DEE4E7"));
		view.setForeground(Color.decode("#37474F"));
		frame.add(view);
		view.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(check(String.valueOf(dtbox.getText())))
						txt.setText("Attendance Already marked!!!");
					else
						tblupdt(String.valueOf(dtbox.getText()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		//-------------------------------------------------------
		
		//----------------------ABSENTBUTTON-----------------------
		JButton ab = new JButton("ABSENT");
		ab.setBounds(75, 365, 150, 50);
		ab.setFont(btn);
		ab.setBackground(Color.decode("#DEE4E7"));
		ab.setForeground(Color.decode("#37474F"));
		frame.add(ab);
		ab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table.setValueAt("Absent", table.getSelectedRow(), 2);
			}
		});
		//-------------------------------------------------------
		
		//----------------------ONDUTY-----------------------
		JButton od = new JButton("ONDUTY");
		od.setBounds(275, 275, 150, 50);
		od.setFont(btn);
		od.setBackground(Color.decode("#DEE4E7"));
		od.setForeground(Color.decode("#37474F"));
		frame.add(od);
		od.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table.setValueAt("Onduty", table.getSelectedRow(), 2);
			}
		});
		//-----------------------------------------------------
		
		//----------------------PRESENTBUTTON-----------------------
		JButton pre = new JButton("PRESENT");
		pre.setBounds(275, 365, 150, 50);
		pre.setFont(btn);
		pre.setBackground(Color.decode("#DEE4E7"));
		pre.setForeground(Color.decode("#37474F"));
		frame.add(pre);
		pre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(table.getSelectedRow());
				table.setValueAt("Present", table.getSelectedRow(), 2);
			}
		});
		//-------------------------------------------------------
		
		//----------------------SUBMITBUTTON-----------------------
		JButton sbmt = new JButton("SUBMIT");
		sbmt.setBounds(175, 450, 150, 50);
		sbmt.setFont(btn);
		sbmt.setBackground(Color.decode("#DEE4E7"));
		sbmt.setForeground(Color.decode("#37474F"));
		frame.add(sbmt);
		sbmt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i= 0; i<table.getRowCount(); i++) {
					try {
						addItem(Integer.parseInt(String.valueOf(table.getValueAt(i, 0))),String.valueOf(table.getValueAt(i, 1)), String.valueOf(table.getValueAt(i, 2)), dtbox.getText());
					} catch (NumberFormatException | SQLException e1) {
						e1.printStackTrace();
					}
				}
				for (int i=0; i < model.getRowCount(); i++) {
				    model.removeRow(i);
				    model.setRowCount(0);
				}
			}
		});
		//-------------------------------------------------------
		
		
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
	
	public void connect() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PBL","root", "root");
	}
	
	public ResultSet dbSearch(String date) throws SQLException {
		String str1 = "SELECT * from students";
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str1);
		return rst;
	}
	
	public void tblupdt(String date) {
		for (int i=0; i < model.getRowCount(); i++) {
		    model.removeRow(i);
		    model.setRowCount(0);
		}
		try {
			ResultSet res = dbSearch(date);
			for(int i=0; res.next(); i++) {
				model.addRow(new Object[0]);
				model.setValueAt(res.getInt("id"), i, 0);
		        model.setValueAt(res.getString("name"), i, 1);
		        model.setValueAt("Present", i, 2);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void addItem(int id,String name, String status, String date) throws SQLException {
		String adding = "INSERT INTO attend values("+id+", '"+name+"','"+date+"', '"+status+"')";
		Statement stm = con.createStatement();
        stm.executeUpdate(adding);
	}
	
	public boolean check(String date) throws SQLException {
		String str1 = "select * from attend where dt = '"+date+"'";
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str1);
		if(rst.next())
			return true;
		else 
			return false;
	}
}