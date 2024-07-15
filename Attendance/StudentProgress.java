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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class StudentProgress{
	DefaultTableModel model = new DefaultTableModel();
	Connection con;
	int check;
	JButton edit;
	JButton delete;
	JButton add;
	
	public void studentView() throws SQLException {
		StudentView st=new StudentView();
		
		Font text = new Font("Times New Roman", Font.PLAIN, 18);
		Font btn = new Font("Times New Roman", Font.BOLD, 20);

		JFrame frame = new JFrame();
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
		//-------------------Welcome---------------------------------
		JLabel welcome = new JLabel("STUDENT PROGRESS");
		welcome.setForeground(Color.decode("#DEE4E7"));
		welcome.setBounds(50, 50, 600, 120);
		welcome.setFont(new Font("Times New Roman", Font.BOLD, 35));
		frame.add(welcome);
	    //-----------------------------------------------------------
		
		//----------------TABLE---------------------------------
		@SuppressWarnings("serial")
		JTable table=new JTable(){
			public boolean isCellEditable(int row,int column){
				return false;
			}
		};
		model = (DefaultTableModel)table.getModel();
		model.addColumn("ID");
		model.addColumn("USERNAME");
		model.addColumn("NAME");
		tblupdt();
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		JScrollPane scPane=new JScrollPane(table);
		scPane.setBounds(500, 50, 480, 525);
		frame.add(scPane);
		//------------------------------------------------------
		//--------------------ID-----------------------------------
		JLabel id = new JLabel("ID : ");
		id.setFont(text);
		id.setBounds(25, 60, 40, 20);
		id.setForeground(Color.decode("#DEE4E7"));
		JTextField idbox= new JTextField();
		idbox.setBounds(60, 60, 50, 25);
		idbox.setBackground(Color.decode("#DEE4E7"));
		idbox.setFont(text);
		idbox.setForeground(Color.decode("#37474F"));
		idbox.setEditable(false);
		idbox.setText(String.valueOf(getid()));	
		//--------------------------------------------------------
				
		//---------------------USERNAME-------------------------
		JLabel user = new JLabel("USERNAME : ");
		user.setFont(text);
		user.setBounds(25, 240, 150, 20);
		user.setForeground(Color.decode("#DEE4E7"));
		frame.add(user);
		JTextField username= new JTextField();
		username.setBounds(25, 270, 400, 35);
		username.setBackground(Color.decode("#DEE4E7"));
		username.setFont(text);
		username.setForeground(Color.decode("#37474F"));
		username.setEditable(false);
		frame.add(username);
		//------------------------------------------------------
		
		//-------------------NAME----------------------------------
		JLabel nm = new JLabel("NAME : ");
		nm.setFont(text);
		nm.setBounds(25, 350, 150, 20);
		nm.setForeground(Color.decode("#DEE4E7"));
		frame.add(nm);
		JTextField name= new JTextField();
		name.setBounds(25, 380, 400, 35);
		name.setBackground(Color.decode("#DEE4E7"));
		name.setFont(text);
		name.setForeground(Color.decode("#37474F"));
		name.setEditable(false);
		frame.add(name);
		//--------------------------------------------------------
		
		//---------------------PASS--------------------------------
		JLabel pass = new JLabel("PASSWORD : ");
		pass.setFont(text);
		pass.setBounds(25, 350, 150, 20);
		pass.setForeground(Color.decode("#DEE4E7"));
		
		JTextField password= new JTextField();
		password.setBounds(25, 380, 400, 35);
		password.setBackground(Color.decode("#DEE4E7"));
		password.setFont(text);
		password.setForeground(Color.decode("#37474F"));
		password.setEditable(false);
		
		//-----------------------------------------------------------

		//------------------------DELETEBUTTON-----------------------
		delete = new JButton("ViewProgress");
		delete.setBounds(150, 500, 190, 50);
		delete.setFont(btn);
		delete.setBackground(Color.decode("#DEE4E7"));
		delete.setForeground(Color.decode("#37474F"));
		delete.setEnabled(false);
		frame.add(delete);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					st.stView(Integer.parseInt(idbox.getText()));
					frame.dispose();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
			}
		});
		//------------------------------------------------------------
		
		//-----------------TABLE ACTION----------------------------
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				idbox.setText(String.valueOf(table.getModel().getValueAt(row, 0)));
				username.setText(String.valueOf(table.getModel().getValueAt(row, 1)));
				name.setText(String.valueOf(table.getModel().getValueAt(row, 2)));
				username.setEditable(false);
				name.setEditable(false);
				delete.setEnabled(true);
			}
		});
		//-------------------------------------------------------------
		
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
	
	public void tblupdt() {
		try {
			ResultSet res = dbSearch();
			for(int i=0; res.next(); i++) {
				model.addRow(new Object[0]);
				model.setValueAt(res.getInt("id"), i, 0);
		        model.setValueAt(res.getString("username"), i, 1);
		        model.setValueAt(res.getString("name"), i, 2);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public int getid() throws SQLException {
        Statement stm = con.createStatement();
        ResultSet rst = stm.executeQuery("SELECT MAX(id) from user");
        if(rst.next()) {
            return rst.getInt("MAX(id)")+1;
        }
        else {
            return 1;
        }
    }
	
	public ResultSet dbSearch() throws SQLException {
		//ENTER PORT, USER, PASSWORD.
		String str1 = "SELECT user.id, user.username, students.name FROM user, students where user.id = students.id";
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PBL","root", "root");
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str1);
		return rst;
	}

	
}