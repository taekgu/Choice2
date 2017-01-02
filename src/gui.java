
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



public class gui extends JFrame {
	
	private static final long serialVersionUID = 5193460907470526697L;
	
	public JFrame jF;
	public JTabbedPane tabbedPane;
	public JPanel panel1;
	public JPanel panel2;
	private JPanel panel3;
	private JPanel Map_panel;
	
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

		//f = new JTabbedPane().getFont();
		/*
	    Font currentFont = super.getFont();
	    String fontName = currentFont.getFontName();
	    int fontStyle = currentFont.getStyle();
	    int fontSize = currentFont.getSize() + 4;
	    super.setFont(new Font(fontName, fontStyle, fontSize));*/
		//f = super.getFont();
		
		
		
		//Bgroup2 = new ButtonGroup();w
		
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
						
		//p2 = new Map();
		Map_panel = p2.Map_init("0","0","init");
		Map_panel.setBounds(150, 0, 1770, 1000);
		Map_panel.setVisible(true);
		
		panel2.add(Map_panel);
				
		setUser();

		//----------------------------------------------------------------------------------------------
		
		tabbedPane.add("Data", panel1);
		tabbedPane.add("Map", panel2);
		tabbedPane.add("Statistics", panel3);
		tabbedPane.setFont( new Font( "Dialog", 0 , 20 ) );
		
		//Font nf = get_Font();
		//tabbedPane.setFont(nf);
		//tabbedPane.setFont( new Font( "Dialog", Font.BOLD|Font.ITALIC, 24 ) );
		//tabbedPane.setFont(new Font())
		//tabbedPane.add("Th)
		
		//�뜝�룞�삕�겕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝占�
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension di  = tk.getScreenSize();
		//�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
		jF = new JFrame();
		jF.add(tabbedPane);
		jF.setSize((int)di.getWidth(),(int)di.getWidth()-200); // Full Screen
		//jF.setSize(800, 600);
		jF.setTitle("Chois Tech"); // 李� �뜝�룞�삕�뜝�룞�삕
		jF.setExtendedState(JFrame.MAXIMIZED_BOTH); // �뜝�뙇�뙋�삕�솕
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
		

		//panel1.add(p4);
		//---------------------------------------------------
		
		
		//panel1.add(Bgroup);
		//CP = new ChartPanel(new jPanel01().DrawMyChart(database_load.dload("0", "2016-04-07"), "0", "2016-04-07", null));
				
		//CP.setBounds(130, 0, 1780, 980);
		//panel1.add(CP);
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
		
		
			/*
			Float[][] arrdata = database_load.dload("0", "2016-04-07");
			XYSeries series = new XYSeries("total");
			XYSeriesCollection dataset = new XYSeriesCollection();
			XYDataset xydataset = dataset;

			for(int i=0;i<288;i++){
	               series.add(i*300000-32400000,arrdata[0][i]);
	        }

			dataset.addSeries(series);*/

			CP = new ChartPanel(new jPanel01().DrawMyChart2("0", "2016-04-08", 37.9));
			CP.setBounds(130, 0, 1780, 980);
			panel1.add(CP);
		
		
		//box1.addActionListener(box1);
		//JLabel label1 = new JLabel("User Select : ");
		//label1.setBounds(10, 0, 100, 20);
		//panel1.add(label1);
		box1.setBounds(0,50,130,50);
		box1.setVisible(false);
		
		panel1.add(box1);
		
		//JButton b1 = new JButton();
		//b1 = new JButton();
		//b1.setText("Select");
		//b1.setBounds(150, 0,70,20);
		//b1.addActionListener(new MyActionListener2()); 

	
		

		//JLabel label2 = new JLabel("Date : ");
		//label2.setBounds(10, 50, 50, 20);
		//panel1.add(label2);
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
	public Font get_Font()
	{
			//Font cf, nf;
			//cf= super.getFont();
			//String fontName = cf.get().toString();
			//System.out.println(fontName);
			//int fontStyle = cf.getStyle();
			//int fontSize = cf.getSize() + 4;
			//nf = new Font(super.getFont().getFontName(), super.getFont().getStyle(), super.getFont().getSize() + 4);
			
			return null;
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
			U_Button.setBounds(0, 500, 150, 50);
			U_Button.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
			U_Button.addActionListener(new MyActionListener());
			combo_u.setBounds(0, 550, 150, 50);
			combo_u.setVisible(false);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		A_Button.setText("Total tmp");
		A_Button.setBounds(0,620,150,50);
		A_Button.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
		A_Button.addActionListener(new MyActionListener());
		
		AA_Button.setText("Total high tmp");
		AA_Button.setBounds(0,670,150,50);
		AA_Button.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
		AA_Button.addActionListener(new MyActionListener());
		
		do_Button.setText("Start");
		do_Button.setBounds(0,770,150,50);
		do_Button.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
		do_Button.addActionListener(new MyActionListener());
		
		T_Button.setText("personal tmp");
		T_Button.setBounds(0, 870, 150, 50);
		T_Button.setFont( new Font( "Dialog", Font.BOLD , 13 ) );
		T_Button.addActionListener(new MyActionListener());
		
		TT_Button.setText("personal high tmp");
		TT_Button.setBounds(0, 920, 150, 50);
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
            	// InnerClassListener占쎌벥 筌롢끇苡�占쎄돌 JFrame占쎌벥 筌롢끇苡� 占쎌깈�빊占�
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
            	// 占쎄텊筌욑옙, 占쎄텢占쎌뿺, �⑨옙-占쏙옙占쎌궔
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
		Map_panel.setBounds(150, 0, 1770, 1000);
		Map_panel.setVisible(true);
		
		panel2.add(Map_panel);
		tabbedPane.add("Map", panel2);
		tabbedPane.add("Third", panel3);
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
					
					CP.setBounds(130, 0, 1780, 980);
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
				
				CP.setBounds(130, 0, 1780, 980);
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
				

    

	public void start() throws SQLException
	{
		
	}
	
}
