
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Graphics;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



public class gui extends JFrame{
	
	private static final long serialVersionUID = 5193460907470526697L;
	
	public JFrame jF;
	public JTabbedPane tabbedPane;
	public JPanel panel1;
	public JPanel panel2;
	private JPanel panel3;
	private JPanel Map_panel;
	private JPanel testmapPanel;
	private JPanel testmap;
	//private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	
	
	//------------------MAP-----------------------
	Connection con;
	java.sql.Statement st;
	ResultSet rs;
	
	private JComboBox combo_d = new JComboBox();
	private JComboBox combo_u = new JComboBox();
	private JButton D_Button = new JButton();
	private JButton U_Button = new JButton();
	private JButton T_Button = new JButton();
	private JButton TT_Button = new JButton();
	private JButton A_Button = new JButton();
	private JButton AA_Button = new JButton();
	
	private JButton do_Button = new JButton();
	
	
	//---------------------------------------------------
	private JComboBox<String> combo_d2 = new JComboBox();
	private JComboBox<String> combo_u2 = new JComboBox();
	private JButton D_Button2 = new JButton();
	private JButton U_Button2 = new JButton();
	private JButton T_Button2 = new JButton();
	private JButton TT_Button2 = new JButton();
	private JButton A_Button2 = new JButton();
	private JButton AA_Button2 = new JButton();
	
	private JButton do_Button2 = new JButton();
	//---------------------------------------------------
	private String date = "";
	private String user;
	private String temp = "";
	
	ImageIcon icon;
	public Map p2;
	//--------------------------------
	//JJ
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;

	JComboBox<String> box1;
	JComboBox<String> box2;
	JComboBox<Double> box3;
	JPanel p1;
	JPanel p3;
	ChartPanel CP;
	
	JRadioButton rb1;
	JRadioButton rb2;
	JRadioButton rb3;
	ButtonGroup Bgroup;

	Font f;
	String date2;
	String user2;
	String[][] user_date;
	
	int sel_state, sel_state2 , count;
	
	public gui() throws IOException, SQLException
	{

		tabbedPane  = new JTabbedPane();	
		panel1 = new JPanel();
		
		b1 = new JButton();
		b2 = new JButton();
		b3 = new JButton();
		b4 = new JButton();
		
		box1 = new JComboBox<String>();
		box2 = new JComboBox<String>();
		box3 = new JComboBox<Double>();
		//p1 = new JPanel(new GridBagLayout());
		CP = new ChartPanel(null);
		p3 = new JPanel();
		rb1 = new JRadioButton();
		rb2 = new JRadioButton();
		rb3 = new JRadioButton();
		Bgroup = new ButtonGroup();
		
		
		testmapPanel = new JPanel();
		testmap = new JPanel();
		user2 = null;
		date2 = null;
		
		user_date = new String[2][];
		user_date[0] = new String[1000];
		user_date[1] = new String[1000];
			
	
		sel_state = 0;
		sel_state2 = 0;
		count = 0;
		
		try {
			//sb
			//con = DriverManager.getConnection("jdbc:mysql://localhost","root", "1234"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC","root", "1234");
			st = con.createStatement();
			//sb
			rs = st.executeQuery("use newschema5");
			//rs = st.executeQuery("use newschema");
			//rs = st.executeQuery("use testschema");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		icon = new ImageIcon("Seoul.JPG");

		tabbedPane  = new JTabbedPane();
				
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel2.setLayout(null);
		
		panel3 = make_panel3.make(jF);
		
				
		// add chart in JPanel
		
		p2 = new Map();
		//panel2 = p2.Map_init();

		//panel1.setLayout(null);
		//ChartPanel CP = new ChartPanel(jP.DrawMyChart(database_load.dload()));

		panel1.setLayout(null);
		panel1_init();

		
		
		//--------MAP--------------------------------------------------------------------------
						
		//1290,820
		//p2 = new Map();
	
		
		Map_panel = p2.Map_init("0","0","init");
		Map_panel.setBounds(150, 0, 1290, 820);
		Map_panel.setVisible(true);

		
		testmap = new OSM().OSM_init(null,null,0);
		testmap.setBounds(150, 0, 1290, 800);
		testmap.setVisible(true);
		
		testmapPanel.setLayout(null);
		testmapPanel_init();
		testmapPanel.add(testmap);
		testmapPanel.setVisible(true);
		panel2.add(Map_panel);
		
/////////////////////////////////////////////////////
		Map_panel = p2.Map_init("0","0","init");



		Map_panel.setBounds(150, 0, 1290, 820);



		Map_panel.setVisible(true);

		

		panel2.add(Map_panel);
////////////////////////////////////////////////////
		
				
		setUser();

		//----------------------------------------------------------------------------------------------
		
		tabbedPane.add("Data", panel1);
		tabbedPane.add("Map", panel2);
		tabbedPane.add("Statistics", panel3);
		tabbedPane.addTab("test", testmapPanel);
		tabbedPane.setFont( new Font( "Dialog", 0 , 20 ) );
		
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension di  = tk.getScreenSize();

		jF = new JFrame();
		jF.add(tabbedPane);
		jF.setSize((int)di.getWidth(),(int)di.getWidth()-200); // Full Screen
		jF.setTitle("Chois Tech");
		jF.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		jF.setVisible(true);
	
	}

	public void panel1_init() throws SQLException, ClassCastException, IllegalArgumentException, IOException
	{
				
		b1 = new JButton();
		b1.setText("User List");
		b1.setBounds(0, 0,130,50);
		b1.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
		b1.addActionListener(new MyActionListener2()); 
		panel1.add(b1);
		
		b2 = new JButton();
		b2.setText("Show Chart");
		b2.setBounds(0, 200,130,50);
		b2.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
		b2.addActionListener(new MyActionListener2()); 
		panel1.add(b2);
		
		b3 = new JButton();
		b3.setText("Date List");
		b3.setBounds(0, 100, 130, 50);
		b3.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
		b3.addActionListener(new MyActionListener2()); 
		panel1.add(b3);
		
		b4.setText("High Temp");
		b4.setBounds(0, 270, 130, 50);
		b4.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
		b4.addActionListener(new MyActionListener2());
		panel1.add(b4);
		//b4.//
		
		
		
		//Radio Button--------------------------------------
		
		p3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Option", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0,0,0)));
		p3.setBounds(5, 400, 100, 115);
		p3.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		rb1.setText("All");
		//rb1.setBounds(0, 400, 70, 20);
		rb1.setVisible(true);
		rb1.setSelected(true);
		rb1.addActionListener(new MyActionListener3());
		p3.add(rb1);
		
		rb2.setText("Male");
		//rb2.setBounds(0, 450, 70, 20);
		rb2.setVisible(true);
		rb2.addActionListener(new MyActionListener3());
		p3.add(rb2);
		
		rb3.setText("Female");
		//rb3.setBounds(0, 500, 70, 20);
		rb3.setVisible(true);
		rb3.addActionListener(new MyActionListener3());
		p3.add(rb3);
		p3.setVisible(true);
		Bgroup.add(rb1);
		Bgroup.add(rb2);
		Bgroup.add(rb3);
		panel1.add(p3);
		


		rs = st.executeQuery("SELECT DISTINCT id FROM tp");
		while(rs.next()){
			box1.addItem(rs.getString("id"));
		}
		box3.addItem(36.0);
		box3.addItem(36.5);
		box3.addItem(37.0);
		box3.addItem(37.5);
		box3.addItem(38.0);
		box3.addItem(38.5);
		box3.addItem(39.0);
		box3.addItem(39.5);
		box3.addItem(40.0);


		CP = new ChartPanel(new jPanel01().DrawMyChart2("0", "2016-04-08", 37.9));
		CP.setBounds(130, 0, 1310, 800);
		panel1.add(CP);

		box1.setBounds(0,50,130,50);
		box1.setVisible(false);

		
		panel1.add(box1);
		

		box2.setBounds(0,150,130,50);
		box2.setVisible(false);
		
		panel1.add(box2);

		b2 = new JButton();
		b2.setText("Check");
		b2.setBounds(150, 50,100,20);
		b4.setBounds(0, 270, 130, 50);
		box3.setBounds(0, 320, 130, 50);
		box3.setVisible(false);
		panel1.add(box3);

		panel1.setVisible(true);
	}
	
	public void testmapPanel_init()
	{
		/*
		private JComboBox combo_d2 = new JComboBox();
		private JComboBox combo_u2 = new JComboBox();
		private JButton D_Button2 = new JButton();
		private JButton U_Button2 = new JButton();
		private JButton T_Button2 = new JButton();
		private JButton TT_Button2 = new JButton();
		private JButton A_Button2 = new JButton();
		private JButton AA_Button2 = new JButton();
		
		private JButton do_Button2 = new JButton();
		*/
		try {
			st = con.createStatement();
			
			String buf_f = null;
			rs = st.executeQuery("select distinct date from tp ORDER BY date");
			
			D_Button2.setText("Date");
	    	D_Button2.setBounds(0, 0, 150, 50);
	    	D_Button2.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
	    	D_Button2.addActionListener(new MyActionListener4());
	    	combo_d2.setBounds(0, 50, 150, 50);
	    	
			while(rs.next()){
				if(rs.getString("date").substring(0, 10).equals(buf_f))
				{
					continue;
				}else{
					buf_f = rs.getString("date").substring(0, 10);
					combo_d2.addItem(buf_f);
					//System.out.println("test : "+buf_f);
				}
			}
			
			combo_d2.setVisible(false);
			combo_u2.setBounds(0, 200, 150, 50);
			combo_u2.setVisible(false);
			
			U_Button2.setText("User");
			U_Button2.setBounds(0, 150, 150, 50);
			U_Button2.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
			U_Button2.setVisible(true);
			U_Button2.addActionListener(new MyActionListener4());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		do_Button2.setText("Start");
		do_Button2.setBounds(0,450,150,50);
		do_Button2.setVisible(true);
		do_Button2.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
		do_Button2.addActionListener(new MyActionListener4());
		
		A_Button2.setText("Total tmp");
		A_Button2.setBounds(0,300,150,50);
		A_Button2.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
		A_Button2.addActionListener(new MyActionListener4());
		
		AA_Button2.setText("Total high tmp");
		AA_Button2.setBounds(0,350,150,50);
		AA_Button2.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
		AA_Button2.addActionListener(new MyActionListener4());
				
		testmapPanel.add(D_Button2);
		testmapPanel.add(U_Button2);
		testmapPanel.add(combo_d2);
		testmapPanel.add(combo_u2);
		testmapPanel.add(do_Button2);
		testmapPanel.add(A_Button2);
		testmapPanel.add(AA_Button2);
		//testmapPanel.setVisible(true);

	
		
	}
	
	public void setUser(){
		
		try {
			st = con.createStatement();
			
			String buf_f = null;
			rs = st.executeQuery("select distinct date from tp");
			
			D_Button.setText("Date");
	    	D_Button.setBounds(0, 0, 150, 50);
	    	D_Button.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
	    	D_Button.addActionListener(new MyActionListener());
	    	combo_d.setBounds(0, 50, 150, 50);
			while(rs.next()){
				if(rs.getString("date").substring(0, 10).equals(buf_f))
				{
					continue;
				}else{
					buf_f = rs.getString("date").substring(0, 10);
					combo_d.addItem(buf_f);
					//System.out.println("test : "+buf_f);
				}
			}
			
			combo_d.setVisible(false);
			
			rs = st.executeQuery("select distinct id from tp");
			
			U_Button.setText("User");
			U_Button.setBounds(0, 150, 150, 50);
			U_Button.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
			U_Button.addActionListener(new MyActionListener());
			combo_u.setBounds(0, 200, 150, 50);
			combo_u.setVisible(false);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		A_Button.setText("Total tmp");
		A_Button.setBounds(0,300,150,50);
		A_Button.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
		A_Button.addActionListener(new MyActionListener());
		
		AA_Button.setText("Total high tmp");
		AA_Button.setBounds(0,350,150,50);
		AA_Button.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
		AA_Button.addActionListener(new MyActionListener());
		
		do_Button.setText("Start");
		do_Button.setBounds(0,450,150,50);
		do_Button.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
		do_Button.addActionListener(new MyActionListener());
		
		T_Button.setText("personal tmp");
		T_Button.setBounds(0, 550, 150, 50);
		T_Button.setFont( new Font( "Dialog", Font.BOLD , 13 ) );
		T_Button.addActionListener(new MyActionListener());
		
		TT_Button.setText("personal high tmp");
		TT_Button.setBounds(0, 600, 150, 50);
		TT_Button.setFont( new Font( "Dialog", Font.BOLD , 13 ) );
		TT_Button.addActionListener(new MyActionListener());
		
		
		panel2.add(D_Button);
		panel2.add(U_Button);
		panel2.add(T_Button);
		panel2.add(TT_Button);
		panel2.add(do_Button);
		panel2.add(A_Button);
		panel2.add(AA_Button);
		panel2.add(combo_d);
		panel2.add(combo_u);
    }
		
    private class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	//------------------date-----------------
            JButton b = (JButton) e.getSource();
            if (b.getText().equals("Date")){
            	b.setText("Date select");
            	combo_d.setVisible(true);
            }      	
            else if(b.getText().equals("Date select")){
            	b.setText("Date");
            	date = combo_d.getSelectedItem().toString();            
            	setTitle(b.getText());
            	            	
            	combo_d.setVisible(false);
            	// InnerClassListener�뜝�럩踰� 嶺뚮、�걞�떋占썲뜝�럡�룎 JFrame�뜝�럩踰� 嶺뚮、�걞�떋占� �뜝�럩源덌옙鍮듿뜝占�
            }
            //---------------user---------------------
            if (b.getText().equals("User")){
            	if(date.equals("")){
            		b.setText("User");
            		combo_u.setVisible(false);
            	}else{
            		b.setText("User select");
            		set_User(date);
                	combo_u.setVisible(true);
            	}
            }      	
            else if(b.getText().equals("User select")){
            	b.setText("User");
            	user = combo_u.getSelectedItem().toString();
            	setTitle(b.getText());
            	
          		combo_u.removeAllItems();
          		combo_u.setVisible(false);
            }
            
            //--------------temp-----------------
            if (b.getText().equals("Total tmp")){
            	temp = b.getText();
            }      	
            
            if(b.getText().equals("Total high tmp")){
            	temp = b.getText();
            }
            
            if (b.getText().equals("Start")){
            	// �뜝�럡�뀏嶺뚯쉻�삕, �뜝�럡�뀬�뜝�럩肉�, 占썩뫅�삕-�뜝�룞�삕�뜝�럩沅�
            	// date, user, temp
            	showMap(date, user, temp);
            } 
            
            if(b.getText().equals("personal tmp")){
            	temp = b.getText();
            }   
            
            if(b.getText().equals("personal high tmp")){
            	temp = b.getText();
            }   

        }
    }
    
    private void showMap(String d, String u, String t){
    	
    	Map_panel.setVisible(false);
    	jF.getContentPane().remove(Map_panel);
    	
    	p2 = new Map();
		Map_panel = p2.Map_init(d, u, t);
		Map_panel.setBounds(150, 0, 1290, 820);
		Map_panel.setVisible(true);
		
		panel2.add(Map_panel);
		tabbedPane.add("Map", panel2);
		tabbedPane.add("Statistics", panel3);
		jF.add(tabbedPane);
    	
    }
    
    private void set_User(String d){
    	
    	String id_buf = "";
    	
    	try {
			st = con.createStatement();			
			rs = st.executeQuery("select id,date from tp");
			
			while(rs.next()){
				
				if(rs.getString("date").substring(0, 10).equals(d))
				{
					if(rs.getString("id").equals(id_buf)){
						continue;
					}else{
						id_buf = rs.getString("id");
						combo_u.addItem(id_buf);
						//System.out.println("test : "+id_buf);
					}
				}else{
					continue;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	    
    }
    
	private class MyActionListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            if (b.getText().equals("User List"))
            {
            	box1.setVisible(true);
            	b1.setText("Select User");
            }
            else if (b.getText().equals("Select User")){
            	b1.setText("User List");
            	box1.setVisible(false);
            	box2.removeAllItems();
        		String buf_f = null;
        		String id = null;
        		
        		id = box1.getSelectedItem().toString();
        		try{
	        		rs = st.executeQuery("SELECT DISTINCT date FROM tp WHERE id="+ id +" ORDER BY date");
	        		while(rs.next()){
	        			if(rs.getString("date").substring(0, 10).equals(buf_f))
	        			{
	        				continue;
	        			}else{
	        				buf_f = rs.getString("date").substring(0, 10);
	        				box2.addItem(buf_f);
	        			}
	        		}
        		} catch (SQLException se) {
        			// TODO Auto-generated catch block
        			se.printStackTrace();
        		}
            }
            else if (b.getText().equals("Date List"))
            {
            	b3.setText("Select Date");
            	box2.setVisible(true);
            	
            }
            else if (b.getText().equals("Select Date"))
            {
            	box2.setVisible(false);
            	b3.setText("Date List");
            }
            
            else if(b.getText().equals("Show Chart")){

            	//Float[][] new_data = new Float[1][1000];

            	System.out.println("Redraw");
            
            	try {
					//new_data = database_load.dload(box1.getSelectedItem().toString(), box2.getSelectedItem().toString());
					System.out.println(box1.getSelectedItem().toString() + " / " + box2.getSelectedItem().toString());
					//ChartPanel NCP = new ChartPanel(new jPanel01().DrawMyChart(new_data));
					//NCP.setBounds(300, 10, 1610, 980);
					panel1.remove(CP);
					
					CP = new ChartPanel(new jPanel01().DrawMyChart2(box1.getSelectedItem().toString(), box2.getSelectedItem().toString(), 36.0));
					
					CP.setBounds(130, 0, 1310, 800);
					panel1.add(CP);
					panel1.repaint();
					panel1.setVisible(true);


				}  catch (ClassCastException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} /*catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/ catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            else if(b.getText().equals("High Temp"))
            {
            	box3.setVisible(true);
            	b4.setText("Temp Select");
            	
            }
            else if(b.getText().equals("Temp Select"))
            {
            	box3.setVisible(false);
            	b4.setText("High Temp");
            	
				panel1.remove(CP);
				
				try {
					CP = new ChartPanel(new jPanel01().DrawMyChart2(box1.getSelectedItem().toString(), box2.getSelectedItem().toString(), (Double)box3.getSelectedItem()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				CP.setBounds(130, 0, 1310, 800);
				panel1.add(CP);
				panel1.repaint();
				panel1.setVisible(true);
            	
            }
        }
    }
	
	private class MyActionListener3 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JRadioButton rb = (JRadioButton) e.getSource();
            if (rb.getText().equals("All"))
            {
        		try {
        			box1.removeAllItems();
					rs = st.executeQuery("SELECT DISTINCT id FROM tp");
					while(rs.next()){
	        			box1.addItem(rs.getString("id"));
	        		}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            else if (rb.getText().equals("Male"))
            {
        		try {
        			box1.removeAllItems();
					rs = st.executeQuery("SELECT DISTINCT id FROM tp WHERE sex = 0");
					while(rs.next()){
	        			box1.addItem(rs.getString("id"));
	        		}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            
            else if (rb.getText().equals("Female"))
            {
        		try {
        			box1.removeAllItems();
					rs = st.executeQuery("SELECT DISTINCT id FROM tp WHERE sex = 1");
					while(rs.next()){
	        			box1.addItem(rs.getString("id"));
	        		}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        }
	}
		
    private class MyActionListener4 implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        	//------------------date-----------------
            JButton b = (JButton) e.getSource();
            if (b.getText().equals("Date")){
            	b.setText("Date select");
            	combo_d2.setVisible(true);
            }      	
            else if(b.getText().equals("Date select")){
            	b.setText("Date");
            	
            	date2 = combo_d2.getSelectedItem().toString();            
            	combo_d2.setVisible(false);
            	combo_u2.removeAllItems();

        		try {
					rs = st.executeQuery("SELECT DISTINCT id FROM tp WHERE date = '" + date2 + "'");
					while(rs.next()){
						combo_u2.addItem(rs.getString("id"));
	        		}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		sel_state = 0;
            }
            
            //---------------user---------------------
            if (b.getText().equals("User")){
            		b.setText("User select");
                	combo_u2.setVisible(true);
                	sel_state2 = 1;
            }      	
            else if(b.getText().equals("User select")){
            	b.setText("User");
            	user2 = combo_u2.getSelectedItem().toString();
            	setTitle(b.getText());
            	
          		//combo_u.removeAllItems();
          		combo_u2.setVisible(false);
            }
            
            //--------------temp-----------------
            if (b.getText().equals("Total tmp")){
            	int idx = 0;
            	sel_state = 1;
            	sel_state2 = 1;
        		try {
					rs = st.executeQuery("SELECT DISTINCT id,LEFT(date,10) FROM tp;");
					rs.next();
					while(rs.next()){
						user_date[0][idx] = rs.getString(1);
						user_date[1][idx] = rs.getString(2);
						
						System.out.println("id : " + user_date[0][idx].toString() + " // date : " +  user_date[1][idx].toString());
						idx++;
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }      	
            
            if(b.getText().equals("Total high tmp")){
        		testmap.repaint();
        		System.out.println("repaint");
        		testmap.setBounds(150, 0, 1290, 800);
        		testmap.setVisible(true);
        		

        		testmapPanel.add(testmap);
        		testmapPanel.repaint();
        		testmapPanel.setVisible(true);
        		//System.out.println("repaint");
            }
            
            if (b.getText().equals("Start")){
            	if (sel_state2 == 1)
            	{
	            	testmapPanel.remove(testmap);
	            	testmap.setVisible(false);
	            	testmap.removeAll();
	            	if (sel_state == 0)
	            	{
	            		testmap = new OSM().OSM_init(date2,user2,1);
	            	}
	            	else if (sel_state == 1)
	            	{
	            		testmap = new OSM().OSM_init(user_date, 1);
	            	}
	        		testmap.repaint();
	        		
	        		testmap.setBounds(150, 0, 1290, 800);
	        		testmap.setVisible(true);
	        		
	
	        		testmapPanel.add(testmap);
	        		testmapPanel.repaint();
	        		testmapPanel.setVisible(true);
            	}
            	else if (sel_state2 == 2)
            	{
	    			testmapPanel.remove(testmap);
	    			testmap.setVisible(false);
	    			testmap.removeAll();
	    			if (sel_state == 0)
	    			{
	    				testmap = new OSM().OSM_init(date2,user2,1);
	    			}
	    			else if (sel_state == 1)
	    			{
	    				testmap = new OSM().OSM_init(user_date, 1);
	    			}
	            	Runnable r = new runnableImplements();
	            	Thread t = new Thread(r);
	            	t.start();
            	}
            } 
            
            if(b.getText().equals("personal tmp")){
            	temp = b.getText();
            }   
            
            if(b.getText().equals("personal high tmp")){
            	temp = b.getText();
            }   

        }
    }

    

	public void start() throws SQLException
	{
		
	}

	class runnableImplements implements Runnable{
		public void run() {
			
			// TODO Auto-generated method stub
			while(OSM.test_thread1.getState() != Thread.State.TERMINATED);

			
			testmap = OSM.mapViewer;
			testmap.repaint();
			
			testmap.setBounds(150, 0, 1290, 800);
			testmap.setVisible(true);
			
			
			testmapPanel.add(testmap);
			testmapPanel.repaint();
			testmapPanel.setVisible(true);
			OSM.temp2 = 0;
			System.out.println("done2");
		}
	}


	
}
