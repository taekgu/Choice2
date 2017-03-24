
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Graphics;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class gui extends JFrame{
	
	private static final long serialVersionUID = 5193460907470526697L;
	
	public JFrame jF;
	public JTabbedPane tabbedPane;
	public static JPanel temperature_distribution;
	public JPanel panel2;
	private JPanel panel3;
	private JPanel Map_panel;
	private JPanel Graph_panel;
	private JPanel Information;
	private JPanel Information_sheet_panel;
	public static JPanel testmapPanel;
	public static JPanel testmap;
	public static JPanel chart;
	
	
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
	private JComboBox<String> Combo_Date = new JComboBox();
	private JComboBox<String> Combo_User = new JComboBox();
	private JButton Date_Button = new JButton();
	public static JButton Map_Button = new JButton();
	public static JButton Temp_Button = new JButton();
	public static JButton Back_b = new JButton();
	private JButton U_Button2 = new JButton();
	private JButton T_Button2 = new JButton();
	private JButton TT_Button2 = new JButton();
	//private JButton A_Button2 = new JButton();
	//private JButton AA_Button2 = new JButton();
	
	//private JButton do_Button2 = new JButton();
	//---------------------------------------------------
	private RangeSlider rangeSlider;
	private JLabel rangeSliderLabel0 = new JLabel();
	private JLabel rangeSliderLabel1 = new JLabel();
	private JLabel rangeSliderValue1 = new JLabel();
	private JLabel rangeSliderLabel2 = new JLabel();
	private JLabel rangeSliderValue2 = new JLabel();
	
	private RangeSlider rangeSlider2;
	private JLabel rangeSliderLabel0_2 = new JLabel();
	private JLabel rangeSliderLabel3 = new JLabel();
	private JLabel rangeSliderValue3 = new JLabel();
	private JLabel rangeSliderLabel4 = new JLabel();
	private JLabel rangeSliderValue4 = new JLabel();
	
	public static int AGE_RangeSlider_UpperValue, AGE_RangeSlider_LowerValue;
	public static int TEMP_RangeSlider_UpperValue, TEMP_RangeSlider_LowerValue;
	
	
	
	/////////////////////////////////////////////////////////
	private JButton Date_Button_tp = new JButton();
	
	private JComboBox<String> Combo_Date_tp = new JComboBox();
	private JComboBox<String> Combo_User_tp = new JComboBox();
	private JButton U_Button2_tp = new JButton();
	private JButton T_Button2_tp = new JButton();
	private JButton TT_Button2_tp = new JButton();

	private RangeSlider rangeSlider_tp;
	private JLabel rangeSliderLabel0_tp = new JLabel();
	private JLabel rangeSliderLabel1_tp = new JLabel();
	private JLabel rangeSliderValue1_tp = new JLabel();
	private JLabel rangeSliderLabel2_tp = new JLabel();
	private JLabel rangeSliderValue2_tp = new JLabel();
	
	private RangeSlider rangeSlider2_tp;
	private JLabel rangeSliderLabel0_2_tp = new JLabel();
	private JLabel rangeSliderLabel3_tp = new JLabel();
	private JLabel rangeSliderValue3_tp = new JLabel();
	private JLabel rangeSliderLabel4_tp = new JLabel();
	private JLabel rangeSliderValue4_tp = new JLabel();
	
	
	/////////////////////////////////////////////////////////
	
	private JButton Date_Button_gp = new JButton();
	private JComboBox<String> Combo_Date_gp = new JComboBox();
	private JComboBox<String> Combo_User_gp = new JComboBox();
	private JButton U_Button2_gp = new JButton();
	private JButton T_Button2_gp = new JButton();
	private JButton TT_Button2_gp = new JButton();

	private RangeSlider rangeSlider_gp;
	private JLabel rangeSliderLabel0_gp = new JLabel();
	private JLabel rangeSliderLabel1_gp = new JLabel();
	private JLabel rangeSliderValue1_gp = new JLabel();
	private JLabel rangeSliderLabel2_gp = new JLabel();
	private JLabel rangeSliderValue2_gp = new JLabel();
	
	private RangeSlider rangeSlider2_gp;
	private JLabel rangeSliderLabel0_2_gp = new JLabel();
	private JLabel rangeSliderLabel3_gp = new JLabel();
	private JLabel rangeSliderValue3_gp = new JLabel();
	private JLabel rangeSliderLabel4_gp = new JLabel();
	private JLabel rangeSliderValue4_gp = new JLabel();
	
	
	
	private XYBarChart XYBarClass;
	private JPanel XYBarChartPanel;
	private String date = "";
	private String user;
	private String temp = "";
	
	ImageIcon icon;
	public Map p2;
	public int swing_click;
	//--------------------------------
	//JJ
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	

	JComboBox<String> box1;
	JComboBox<String> box2;
	JComboBox<Double> box3;
	//JPanel p1;
	JPanel p3;
	JPanel p4;
	JPanel p5;
	JPanel p6;
	ChartPanel CP;
	
	JRadioButton rb1;
	JRadioButton rb2;
	JRadioButton rb3;
	
	
	JRadioButton rb1_tp;
	JRadioButton rb2_tp;
	JRadioButton rb3_tp;
	
	JRadioButton rb1_gp;
	JRadioButton rb2_gp;
	JRadioButton rb3_gp;
	
	ButtonGroup Bgroup;
	ButtonGroup Bgroup2;
	ButtonGroup Bgroup3;
	
	int Bgroup_gender;
	
	Font f;
	String date2;
	String user2;
	String[][] user_date;
	
	int sel_state, sel_state2 , count;
	
	interface swing_event{
		
		int clicked = 0;
		
	}
	
	public gui() throws IOException, SQLException
	{

		tabbedPane  = new JTabbedPane();	
		temperature_distribution = new JPanel();
		
		AGE_RangeSlider_UpperValue = 100;
		AGE_RangeSlider_LowerValue = 0;
		TEMP_RangeSlider_UpperValue = 42; 
		//jjjj
		TEMP_RangeSlider_LowerValue = 0;
		
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
		p4 = new JPanel();
		p5 = new JPanel();
		p6 = new JPanel();
		
		rb1 = new JRadioButton();
		rb2 = new JRadioButton();
		rb3 = new JRadioButton();
		
		rb1_tp = new JRadioButton();
		rb2_tp = new JRadioButton();
		rb3_tp = new JRadioButton();
		
		rb1_gp = new JRadioButton();
		rb2_gp = new JRadioButton();
		rb3_gp = new JRadioButton();
		
		Bgroup = new ButtonGroup();
		Bgroup2 = new ButtonGroup();
		Bgroup3 = new ButtonGroup();
		Bgroup_gender = 1;
		
		
		testmapPanel = new JPanel();
		Graph_panel = new JPanel();
		Information = new JPanel();
		Information_sheet_panel = new JPanel();
		
		rangeSlider = new RangeSlider();
		rangeSlider2 = new RangeSlider();
		
		rangeSlider_tp = new RangeSlider();
		rangeSlider2_tp = new RangeSlider();
		
		rangeSlider_gp = new RangeSlider();
		rangeSlider2_gp = new RangeSlider();
		
		testmap = new JPanel();
		chart = new JPanel();
		user2 = null;
		date2 = null;
		
		user_date = new String[2][];
		user_date[0] = new String[1000];
		user_date[1] = new String[1000];
			
	
		sel_state = 0;
		sel_state2 = 0;
		count = 0;
		swing_click = 0;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC","root", "1234");
			st = con.createStatement();
			rs = st.executeQuery("use Thermosafer_INU");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		icon = new ImageIcon("Seoul.JPG");

		tabbedPane  = new JTabbedPane();
				
		temperature_distribution = new JPanel();
		panel2 = new JPanel();
		panel2.setLayout(null);

		temperature_distribution.setLayout(null);
		//temperature_distribution_init();

		testmap = new OSM().OSM_init(null,null,0);
		testmap.setBounds(150, 0, 1290, 800);
		testmap.setVisible(true);
		
		//chart = temperature_distribution_init();
		chart.setBounds(150, 0, 1290, 800);
		chart.setVisible(false);
		
		testmapPanel.add(chart);
		
		
		
		testmapPanel.setLayout(null);
		testmapPanel_init();
		testmapPanel.add(testmap);
		testmapPanel.setVisible(true);
		//panel2.add(Graph_panel);
		panel2.setBounds(0, 0, 1280, 800);
		panel2.setVisible(true);
		
		
		InformationPanel_init();
		Information.setBounds(150, 0, 1190, 800);
		//Information.setLayout(new BorderLayout());
		Information.setVisible(true);
/////////////////////////////////////////////////////
		//Map_panel = p2.Map_init("0","0","init");



		//Map_panel.setBounds(150, 0, 1290, 820);



		//Map_panel.setVisible(true);

		

		//panel2.add(Map_panel);
////////////////////////////////////////////////////
		
				
		//make_GraphPanel();
		make_GraphPanel2();

		//----------------------------------------------------------------------------------------------
		tabbedPane.addTab("Map", testmapPanel);
		tabbedPane.addTab("GRAPH", panel2);
		tabbedPane.add("인원정보보기", Information);
		//tabbedPane.add("Data", temperature_distribution);
		//tabbedPane.add("Map", panel2);
		//tabbedPane.add("Statistics", panel3);
		
		tabbedPane.setFont( new Font( "Dialog", 0 , 20 ) );
		
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension di  = tk.getScreenSize();

		jF = new JFrame();
		jF.add(tabbedPane);
		//jF.setSize((int)di.getWidth(),(int)di.getWidth()-200); // Full Screen
		jF.setSize(1440, 850);
		jF.setTitle("Chois Tech");
		jF.setResizable(false);
		//jF.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		jF.setVisible(true);
	
	}

	public void temperature_distribution_init() throws SQLException, ClassCastException, IllegalArgumentException, IOException
	{
		st = con.createStatement();
//		b1 = new JButton();
//		b1.setText("User List");
//		b1.setBounds(0, 0,130,50);
//		b1.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
//		b1.addActionListener(new MyActionListener2()); 
//		temperature_distribution.add(b1);
		
		b2 = new JButton();
		b2.setText("Show Chart");
		b2.setBounds(0, 200,130,50);
		b2.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
		b2.addActionListener(new MyActionListener2()); 
		temperature_distribution.add(b2);
		
		b3 = new JButton();
		b3.setText("Date List");
		b3.setBounds(0, 0,130,50);
		b3.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
		b3.addActionListener(new MyActionListener2()); 
		temperature_distribution.add(b3);
	

		CP = new ChartPanel(new jPanel01().DrawMyChart2(SwingWaypoint.send_user, SwingWaypoint.send_date, 0.0));
		CP.setBounds(130, 0, 1150, 740);
		temperature_distribution.add(CP);

		box1.setBounds(0,50,130,50);
		box1.setVisible(false);

		
		temperature_distribution.add(box1);
		
    	box2.removeAllItems();
		String buf_f = null;
		String id = null;
		
		id = SwingWaypoint.send_user;
		try{
    		rs = st.executeQuery("SELECT DISTINCT date from thermo_data WHERE id="+ id +" ORDER BY date");
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
		
		box2.setBounds(0,50,130,50);
		box2.setVisible(false);
		
		temperature_distribution.add(box2);

		b2 = new JButton();
		b2.setText("Check");
		b2.setBounds(150, 50,100,20);
		b4.setBounds(0, 270, 130, 50);
		box3.setBounds(0, 320, 130, 50);
		box3.setVisible(false);
		temperature_distribution.add(box3);

		temperature_distribution.setVisible(true);
		//return temperature_distribution;
	}
	
	public void testmapPanel_init() // tab 1
	{
		try {
			st = con.createStatement();
			
			String buf_f = null;
			rs = st.executeQuery("select distinct date from thermo_data ORDER BY date");
			
			Back_b.setText("Back");
			Back_b.setBounds(0, 0, 150, 50);
			Back_b.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
			Back_b.addActionListener(new MyActionListener4());
			Back_b.setVisible(false);
			
			Date_Button.setText("Date");
	    	Date_Button.setBounds(0, 50, 150, 50);
	    	Date_Button.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
	    	Date_Button.addActionListener(new MyActionListener4());
	    	
	    	
	    	Map_Button.setText("Map");
	    	Map_Button.setBounds(150, 0, 150, 50);
	    	Map_Button.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
	    	Map_Button.addActionListener(new MyActionListener4());
	    	Map_Button.setVisible(false);
	    	
	    	Temp_Button.setText("Temp");
	    	Temp_Button.setBounds(300, 0, 150, 50);
	    	Temp_Button.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
	    	Temp_Button.addActionListener(new MyActionListener4());
	    	Temp_Button.setVisible(false);
	    	
	    	
	    	Combo_Date.setBounds(0, 100, 150, 50);
	    	
	    	
	        //rangeSliderValue1.setHorizontalAlignment(JLabel.LEFT);
	       // rangeSliderValue2.setHorizontalAlignment(JLabel.LEFT);
	    	rangeSlider.setPreferredSize(new Dimension(100, 100));
	        rangeSlider.setMinimum(0);
	        rangeSlider.setMaximum(10);
	        rangeSliderLabel0.setText("Age Range");
	        rangeSliderLabel1.setText("Lower value:");
	        rangeSliderLabel2.setText("Upper value:");
	        

	        rangeSlider.setValue(0);
	        rangeSlider.setUpperValue(10);
	        rangeSliderLabel0.setBounds(15, 400, 100, 20);
	        rangeSliderLabel0.setVisible(true);
	        rangeSliderLabel1.setBounds(15,420,100,20);
	        rangeSliderLabel1.setVisible(true);
	        rangeSliderValue1.setBounds(100,420,100,20);
	        rangeSliderValue1.setVisible(true);
	        
	        rangeSliderLabel2.setBounds(15,440,100,20);
	        rangeSliderLabel2.setVisible(true);
	        rangeSliderValue2.setBounds(100,440,100,20);
	        rangeSliderValue2.setVisible(true);
	        
	        rangeSliderValue1.setText(String.valueOf(rangeSlider.getValue()));
	        rangeSliderValue2.setText(String.valueOf(rangeSlider.getUpperValue()));
	        rangeSlider.addChangeListener(new ChangeListener() {
	            public void stateChanged(ChangeEvent e) {
	                RangeSlider slider = (RangeSlider) e.getSource();
	                rangeSliderValue1.setText(String.valueOf((slider.getValue())*10));
	                rangeSliderValue2.setText(String.valueOf((slider.getUpperValue())*10));
	                
	                rangeSlider_tp.setValue(rangeSlider.getValue());
	    	        rangeSlider_tp.setUpperValue(rangeSlider.getUpperValue());
	                rangeSliderValue1_tp.setText(String.valueOf((slider.getValue())*10));
	                rangeSliderValue2_tp.setText(String.valueOf((slider.getUpperValue())*10));
	                
	                rangeSlider_gp.setValue(rangeSlider.getValue());
	    	        rangeSlider_gp.setUpperValue(rangeSlider.getUpperValue());
	                rangeSliderValue1_gp.setText(String.valueOf((slider.getValue())*10));
	                rangeSliderValue2_gp.setText(String.valueOf((slider.getUpperValue())*10));
	                
	                AGE_RangeSlider_UpperValue = Integer.valueOf(slider.getUpperValue()*10);
	                AGE_RangeSlider_LowerValue = Integer.valueOf(slider.getValue()*10);
	                
	                //panel2.remove(Graph_panel);
		               //make_XYBarChartPanel(AGE_RangeSlider_UpperValue,AGE_RangeSlider_LowerValue);
	        		//panel2.add(Graph_panel);
	            }
	        });
	        
	        rangeSlider.setBounds(13, 460, 130,20);
	        rangeSlider.setVisible(true);
	        //testmapPanel.add(rangeSlider);
	        rangeSliderValue1.setText("0");
	        rangeSliderValue2.setText("100");

	        ////////
	    	rangeSlider2.setPreferredSize(new Dimension(100, 100));
	        rangeSlider2.setMinimum(0);
	        rangeSlider2.setMaximum(42);
	        rangeSliderLabel0_2.setText("Temp Range");
	        rangeSliderLabel3.setText("Lower value:");
	        rangeSliderLabel4.setText("Upper value:");
	        

	        rangeSlider2.setValue(0);
	        rangeSlider2.setUpperValue(42);
	        rangeSliderLabel0_2.setBounds(15,500, 100, 20);
	        rangeSliderLabel0_2.setVisible(true);
	        rangeSliderLabel3.setBounds(15,520,100,20);
	        rangeSliderLabel3.setVisible(true);
	        rangeSliderValue3.setBounds(100,520,100,20);
	        rangeSliderValue4.setVisible(true);
	        
	        rangeSliderLabel4.setBounds(15,540,100,20);
	        rangeSliderLabel4.setVisible(true);
	        rangeSliderValue4.setBounds(100,540,100,20);
	        rangeSliderValue4.setVisible(true);
	        
	        rangeSliderValue3.setText(String.valueOf(rangeSlider.getValue()));
	        rangeSliderValue4.setText(String.valueOf(rangeSlider.getUpperValue()));
	        rangeSlider2.addChangeListener(new ChangeListener() {
	            public void stateChanged(ChangeEvent e) {
	                RangeSlider slider2 = (RangeSlider) e.getSource();
	                rangeSliderValue3.setText(String.valueOf(slider2.getValue()));
	                rangeSliderValue4.setText(String.valueOf(slider2.getUpperValue()));
	                
	                rangeSlider2_tp.setValue(rangeSlider2.getValue());
	    	        rangeSlider2_tp.setUpperValue(rangeSlider2.getUpperValue());
	                rangeSliderValue3_tp.setText(String.valueOf(slider2.getValue()));
	                rangeSliderValue4_tp.setText(String.valueOf(slider2.getUpperValue()));
	                
	                rangeSlider2_gp.setValue(rangeSlider2.getValue());
	    	        rangeSlider2_gp.setUpperValue(rangeSlider2.getUpperValue());
	                rangeSliderValue3_gp.setText(String.valueOf(slider2.getValue()));
	                rangeSliderValue4_gp.setText(String.valueOf(slider2.getUpperValue()));
	                
	                TEMP_RangeSlider_UpperValue = Integer.valueOf(slider2.getUpperValue());
	                TEMP_RangeSlider_LowerValue = Integer.valueOf(slider2.getValue());
	            }
	        });
	        
	        rangeSlider2.setBounds(13, 560, 130,20);
	        rangeSlider2.setVisible(true);
	        //testmapPanel.add(rangeSlider);
	        rangeSliderValue3.setText("0");
	        rangeSliderValue4.setText("42");
	        
			while(rs.next()){
				if(rs.getString("date").substring(0, 10).equals(buf_f))
				{
					continue;
				}else{
					buf_f = rs.getString("date").substring(0, 10);
					Combo_Date.addItem(buf_f);
					//System.out.println("test : "+buf_f);
					
				}
			}
			
			//rangeSlider.setBounds(x, y, width, height);
			
			Combo_Date.setVisible(false);
			Combo_User.setBounds(0, 200, 150, 50);
			Combo_User.setVisible(false);
			
			//U_Button2.setText("User");
			U_Button2.setText("Show");
			U_Button2.setBounds(0, 150, 150, 50);
			U_Button2.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
			U_Button2.setVisible(true);
			U_Button2.addActionListener(new MyActionListener4());
			
			//Radio Button--------------------------------------
			
			p4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Option", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0,0,0)));
			p4.setBounds(25, 250, 100, 115);
			p4.setLayout(new FlowLayout(FlowLayout.LEADING));
			
			rb1.setText("All");
			//rb1.setBounds(0, 400, 70, 20);
			rb1.setVisible(true);
			rb1.setSelected(true);
			//rb1.addActionListener(new MyActionListener3());
			rb1.addItemListener(new SelectItemListener());
			p4.add(rb1);
			
			rb2.setText("Male");
			//rb2.setBounds(0, 450, 70, 20);
			rb2.setVisible(true);
			rb2.addItemListener(new SelectItemListener());
			p4.add(rb2);
			
			rb3.setText("Female");
			//rb3.setBounds(0, 500, 70, 20);
			rb3.setVisible(true);
			rb3.addItemListener(new SelectItemListener());
			p4.add(rb3);
			p4.setVisible(true);
			Bgroup.add(rb1);
			Bgroup.add(rb2);
			Bgroup.add(rb3);
			testmapPanel.add(p4);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
				
		testmapPanel.add(Date_Button);
		testmapPanel.add(Map_Button);
		testmapPanel.add(Temp_Button);
		testmapPanel.add(Back_b);
		testmapPanel.add(U_Button2);
		testmapPanel.add(Combo_Date);
		testmapPanel.add(Combo_User);
		testmapPanel.add(rangeSliderLabel0);
		testmapPanel.add(rangeSliderLabel1);
		testmapPanel.add(rangeSliderLabel2);
		testmapPanel.add(rangeSliderValue1);
		testmapPanel.add(rangeSliderValue2);
		testmapPanel.add(rangeSlider);
		testmapPanel.add(rangeSliderLabel0_2);
		testmapPanel.add(rangeSliderLabel3);
		testmapPanel.add(rangeSliderLabel4);
		testmapPanel.add(rangeSliderValue3);
		testmapPanel.add(rangeSliderValue4);
		testmapPanel.add(rangeSlider2);
		
		
		
	}
	
	
	public void InformationPanel_init() // return information
	{
		try {
			st = con.createStatement();
			
			String buf_f_tp = null;
			rs = st.executeQuery("select distinct date from thermo_data ORDER BY date");
			
			Date_Button_tp.setText("Date");
	    	Date_Button_tp.setBounds(0, 50, 150, 50);
	    	Date_Button_tp.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
	    	Date_Button_tp.addActionListener(new MyActionListener4());
	    	Combo_Date_tp.setBounds(0, 100, 150, 50);
	    	
	    	
	        //rangeSliderValue1.setHorizontalAlignment(JLabel.LEFT);
	       // rangeSliderValue2.setHorizontalAlignment(JLabel.LEFT);
	    	rangeSlider_tp.setPreferredSize(new Dimension(100,100));
	    	
	        rangeSlider_tp.setMinimum(0);
	        rangeSlider_tp.setMaximum(10);
	        rangeSliderLabel0_tp.setText("Age Range");
	        rangeSliderLabel1_tp.setText("Lower value:");
	        rangeSliderLabel2_tp.setText("Upper value:");
	        

	        rangeSlider_tp.setValue(0);
	        rangeSlider_tp.setUpperValue(10);
	        rangeSliderLabel0_tp.setBounds(15, 400, 100, 20);
	        rangeSliderLabel0_tp.setVisible(true);
	        rangeSliderLabel1_tp.setBounds(15,420,100,20);
	        rangeSliderLabel1_tp.setVisible(true);
	        rangeSliderValue1_tp.setBounds(100,420,100,20);
	        rangeSliderValue1_tp.setVisible(true);
	        
	        rangeSliderLabel2_tp.setBounds(15,440,100,20);
	        rangeSliderLabel2_tp.setVisible(true);
	        rangeSliderValue2_tp.setBounds(100,440,100,20);
	        rangeSliderValue2_tp.setVisible(true);
	        
	        rangeSliderValue1_tp.setText(String.valueOf(rangeSlider_tp.getValue()));
	        rangeSliderValue2_tp.setText(String.valueOf(rangeSlider_tp.getUpperValue()));
	        rangeSlider_tp.addChangeListener(new ChangeListener() {
	            public void stateChanged(ChangeEvent e) {
	                RangeSlider slider_tp = (RangeSlider) e.getSource();
	                rangeSliderValue1_tp.setText(String.valueOf((slider_tp.getValue())*10));
	                rangeSliderValue2_tp.setText(String.valueOf((slider_tp.getUpperValue())*10));
	                
	                rangeSlider.setValue(rangeSlider_tp.getValue());
	    	        rangeSlider.setUpperValue(rangeSlider_tp.getUpperValue());
	                rangeSliderValue1.setText(String.valueOf((slider_tp.getValue())*10));
	                rangeSliderValue2.setText(String.valueOf((slider_tp.getUpperValue())*10));
	                
	                rangeSlider_gp.setValue(rangeSlider_tp.getValue());
	    	        rangeSlider_gp.setUpperValue(rangeSlider_tp.getUpperValue());
	                rangeSliderValue1_gp.setText(String.valueOf((slider_tp.getValue())*10));
	                rangeSliderValue2_gp.setText(String.valueOf((slider_tp.getUpperValue())*10));
	                
	                AGE_RangeSlider_UpperValue = Integer.valueOf(slider_tp.getUpperValue()*10);
	                AGE_RangeSlider_LowerValue = Integer.valueOf(slider_tp.getValue()*10);
	            }
	        });
	        
	        rangeSlider_tp.setBounds(13, 460, 130,20);
	        rangeSlider_tp.setVisible(true);
	        //testmapPanel.add(rangeSlider);
	        rangeSliderValue1_tp.setText("0");
	        rangeSliderValue2_tp.setText("100");

	        ////////
	    	rangeSlider2_tp.setPreferredSize(new Dimension(100, 100));
	        rangeSlider2_tp.setMinimum(0);
	        rangeSlider2_tp.setMaximum(42);
	        rangeSliderLabel0_2_tp.setText("Temp Range");
	        rangeSliderLabel3_tp.setText("Lower value:");
	        rangeSliderLabel4_tp.setText("Upper value:");
	        

	        rangeSlider2_tp.setValue(0);
	        rangeSlider2_tp.setUpperValue(42);
	        rangeSliderLabel0_2_tp.setBounds(15,500, 100, 20);
	        rangeSliderLabel0_2_tp.setVisible(true);
	        rangeSliderLabel3_tp.setBounds(15,520,100,20);
	        rangeSliderLabel3_tp.setVisible(true);
	        rangeSliderValue3_tp.setBounds(100,520,100,20);
	        rangeSliderValue4_tp.setVisible(true);
	        
	        rangeSliderLabel4_tp.setBounds(15,540,100,20);
	        rangeSliderLabel4_tp.setVisible(true);
	        rangeSliderValue4_tp.setBounds(100,540,100,20);
	        rangeSliderValue4_tp.setVisible(true);
	        
	        rangeSliderValue3_tp.setText(String.valueOf(rangeSlider_tp.getValue()));
	        rangeSliderValue4_tp.setText(String.valueOf(rangeSlider_tp.getUpperValue()));
	        rangeSlider2_tp.addChangeListener(new ChangeListener() {
	            public void stateChanged(ChangeEvent e) {
	                RangeSlider slider2_tp = (RangeSlider) e.getSource();
	                rangeSliderValue3_tp.setText(String.valueOf(slider2_tp.getValue()));
	                rangeSliderValue4_tp.setText(String.valueOf(slider2_tp.getUpperValue()));
	                
	                rangeSlider2.setValue(rangeSlider2_tp.getValue());
	    	        rangeSlider2.setUpperValue(rangeSlider2_tp.getUpperValue());
	                rangeSliderValue3.setText(String.valueOf(slider2_tp.getValue()));
	                rangeSliderValue4.setText(String.valueOf(slider2_tp.getUpperValue()));
	                
	                rangeSlider2_gp.setValue(rangeSlider2_tp.getValue());
	    	        rangeSlider2_gp.setUpperValue(rangeSlider2_tp.getUpperValue());
	                rangeSliderValue3_gp.setText(String.valueOf(slider2_tp.getValue()));
	                rangeSliderValue4_gp.setText(String.valueOf(slider2_tp.getUpperValue()));
	                
	                TEMP_RangeSlider_UpperValue = Integer.valueOf(slider2_tp.getUpperValue());
	                TEMP_RangeSlider_LowerValue = Integer.valueOf(slider2_tp.getValue());
	            }
	        });
	        
	        rangeSlider2_tp.setBounds(13, 560, 130,20);
	        rangeSlider2_tp.setVisible(true);
	        //testmapPanel.add(rangeSlider);
	        rangeSliderValue3_tp.setText("0");
	        rangeSliderValue4_tp.setText("42");
	        
			while(rs.next()){
				if(rs.getString("date").substring(0, 10).equals(buf_f_tp))
				{
					continue;
				}else{
					buf_f_tp = rs.getString("date").substring(0, 10);
					Combo_Date_tp.addItem(buf_f_tp);
					//System.out.println("test : "+buf_f);
				}
			}
			
			//rangeSlider.setBounds(x, y, width, height);
			
			Combo_Date_tp.setVisible(false);
			Combo_User_tp.setBounds(0, 200, 150, 50);
			Combo_User_tp.setVisible(false);
			
			U_Button2_tp.setText("User");
			U_Button2_tp.setBounds(0, 150, 150, 50);
			U_Button2_tp.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
			U_Button2_tp.setVisible(true);
			U_Button2_tp.addActionListener(new MyActionListener4());
			
			//Radio Button--------------------------------------
			
			p5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Option", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0,0,0)));
			p5.setBounds(25, 250, 100, 115);
			p5.setLayout(new FlowLayout(FlowLayout.LEADING));
			
			rb1_tp.setText("All");
			//rb1.setBounds(0, 400, 70, 20);
			rb1_tp.setVisible(true);
			rb1_tp.setSelected(true);
			rb1_tp.addItemListener(new SelectItemListener());
			p5.add(rb1_tp);
			
			rb2_tp.setText("Male");
			//rb2.setBounds(0, 450, 70, 20);
			rb2_tp.setVisible(true);
			rb2_tp.addItemListener(new SelectItemListener());
			p5.add(rb2_tp);
			
			rb3_tp.setText("Female");
			//rb3.setBounds(0, 500, 70, 20);
			rb3_tp.setVisible(true);
			rb3_tp.addItemListener(new SelectItemListener());
			p5.add(rb3_tp);
			p5.setVisible(true);
			Bgroup2.add(rb1_tp);
			Bgroup2.add(rb2_tp);
			Bgroup2.add(rb3_tp);
			Information.add(p5);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JPanel table_Panel = new JPanel();
		JTable table = new JTablePanel().JTable_init();
		JScrollPane pane2 = new JTablePanel2().JTable_init();
		pane2.setVisible(true);
		//pane2.setBounds(150,0,1180,700);
		JScrollPane pane = new JScrollPane(table);
		pane.setVisible(true);
		table_Panel.add(table);
		table_Panel.add(pane);
		table_Panel.setBounds(150,0,1190,800);
		table_Panel.setLayout(null);
		table_Panel.setVisible(true);
	
		Information.setLayout(null);
		pane2.setLayout(null);
		pane2.setBounds(150,0,1180,700);
		//pane2.setViewportBorder(null);
		pane2.setVisible(true);

		Information.add(pane2);
		
		//Information.add
		//pane2.setViewportView();
		//Information.setBounds(150, 0, 1190, 800);
		//Information.setVisible(true);
		
				
		Information.add(Date_Button_tp);
		Information.add(U_Button2_tp);
		Information.add(Combo_Date_tp);
		Information.add(Combo_User_tp);
		Information.add(rangeSliderLabel0_tp);
		Information.add(rangeSliderLabel1_tp);
		Information.add(rangeSliderLabel2_tp);
		Information.add(rangeSliderValue1_tp);
		Information.add(rangeSliderValue2_tp);
		Information.add(rangeSlider_tp);
		Information.add(rangeSliderLabel0_2_tp);
		Information.add(rangeSliderLabel3_tp);
		Information.add(rangeSliderLabel4_tp);
		Information.add(rangeSliderValue3_tp);
		Information.add(rangeSliderValue4_tp);
		Information.add(rangeSlider2_tp);

		
	}
	public JPanel make_XYBarChartPanel(){
		XYBarClass =  new XYBarChart("test");
		
		Graph_panel = XYBarClass.createXYBarChartPanel();
		Graph_panel.setLayout(null);
		Graph_panel.setBounds(150,0,1280,810);
		Graph_panel.setVisible(true);
		return Graph_panel;
	}
	
	public JPanel make_XYBarChartPanel(int Upper_age, int Lower_age, int gender, String Sel_date){
		XYBarClass =  new XYBarChart("test", Upper_age, Lower_age, gender, Sel_date);
		
		Graph_panel = XYBarClass.createXYBarChartPanel(Upper_age, Lower_age, gender, Sel_date);
		Graph_panel.setLayout(null);
		Graph_panel.setBounds(150,0,1280,810);
		Graph_panel.setVisible(true);
		return Graph_panel;
	}
	/*
	public JPanel make_JTablePanel(){
		JTablePanel Table_Panel_class = new JTablePanel();
		JPanel Table_Panel = Table_Panel_class.JTable_init();
		Table_Panel.setLayout(null);
		Table_Panel.setBounds(0,0,1290,800);
		Table_Panel.setVisible(true);
		return Table_Panel;
	}
	*/
	
	public void make_GraphPanel2()
	{
		try {
			st = con.createStatement();
			
			String buf_f_gp = null;
			rs = st.executeQuery("select distinct date from thermo_data ORDER BY date");
			
			Date_Button_gp.setText("Date");
	    	Date_Button_gp.setBounds(0, 50, 150, 50);
	    	Date_Button_gp.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
	    	Date_Button_gp.addActionListener(new MyActionListener3());
	    	Combo_Date_gp.setBounds(0, 100, 150, 50);
	    	
	    	
	        //rangeSliderValue1.setHorizontalAlignment(JLabel.LEFT);
	       // rangeSliderValue2.setHorizontalAlignment(JLabel.LEFT);
	    	rangeSlider_gp.setPreferredSize(new Dimension(100,100));
	    	
	        rangeSlider_gp.setMinimum(0);
	        rangeSlider_gp.setMaximum(10);
	        rangeSliderLabel0_gp.setText("Age Range");
	        rangeSliderLabel1_gp.setText("Lower value:");
	        rangeSliderLabel2_gp.setText("Upper value:");
	        

	        rangeSlider_gp.setValue(0);
	        rangeSlider_gp.setUpperValue(10);
	        rangeSliderLabel0_gp.setBounds(15, 400, 100, 20);
	        rangeSliderLabel0_gp.setVisible(true);
	        rangeSliderLabel1_gp.setBounds(15,420,100,20);
	        rangeSliderLabel1_gp.setVisible(true);
	        rangeSliderValue1_gp.setBounds(100,420,100,20);
	        rangeSliderValue1_gp.setVisible(true);
	        
	        rangeSliderLabel2_gp.setBounds(15,440,100,20);
	        rangeSliderLabel2_gp.setVisible(true);
	        rangeSliderValue2_gp.setBounds(100,440,100,20);
	        rangeSliderValue2_gp.setVisible(true);
	        
	        rangeSliderValue1_gp.setText(String.valueOf(rangeSlider_tp.getValue()));
	        rangeSliderValue2_gp.setText(String.valueOf(rangeSlider_tp.getUpperValue()));
	        rangeSlider_gp.addChangeListener(new ChangeListener() {
	            public void stateChanged(ChangeEvent e) {
	                RangeSlider slider_gp = (RangeSlider) e.getSource();
	                rangeSliderValue1.setText(String.valueOf((slider_gp.getValue())*10));
	                rangeSliderValue2.setText(String.valueOf((slider_gp.getUpperValue())*10));
	                
	                rangeSlider.setValue(rangeSlider_gp.getValue());
	    	        rangeSlider.setUpperValue(rangeSlider_gp.getUpperValue());
	                rangeSliderValue1.setText(String.valueOf((slider_gp.getValue())*10));
	                rangeSliderValue2.setText(String.valueOf((slider_gp.getUpperValue())*10));
	                
	                rangeSlider_tp.setValue(rangeSlider_gp.getValue());
	    	        rangeSlider_tp.setUpperValue(rangeSlider_gp.getUpperValue());
	                rangeSliderValue1_tp.setText(String.valueOf((slider_gp.getValue())*10));
	                rangeSliderValue2_tp.setText(String.valueOf((slider_gp.getUpperValue())*10));
	                
	                AGE_RangeSlider_UpperValue = Integer.valueOf(slider_gp.getUpperValue()*10);
	                AGE_RangeSlider_LowerValue = Integer.valueOf(slider_gp.getValue()*10);
	            }
	        });
	        
	        rangeSlider_gp.setBounds(13, 460, 130,20);
	        rangeSlider_gp.setVisible(true);
	        //testmapPanel.add(rangeSlider);
	        rangeSliderValue1_gp.setText("0");
	        rangeSliderValue2_gp.setText("100");

	        ////////
	    	rangeSlider2_gp.setPreferredSize(new Dimension(100, 100));
	        rangeSlider2_gp.setMinimum(0);
	        rangeSlider2_gp.setMaximum(42);
	        rangeSliderLabel0_2_gp.setText("Temp Range");
	        rangeSliderLabel3_gp.setText("Lower value:");
	        rangeSliderLabel4_gp.setText("Upper value:");
	        

	        rangeSlider2_gp.setValue(0);
	        rangeSlider2_gp.setUpperValue(42);
	        rangeSliderLabel0_2_gp.setBounds(15,500, 100, 20);
	        rangeSliderLabel0_2_gp.setVisible(true);
	        rangeSliderLabel3_gp.setBounds(15,520,100,20);
	        rangeSliderLabel3_gp.setVisible(true);
	        rangeSliderValue3_gp.setBounds(100,520,100,20);
	        rangeSliderValue4_gp.setVisible(true);
	        
	        rangeSliderLabel4_gp.setBounds(15,540,100,20);
	        rangeSliderLabel4_gp.setVisible(true);
	        rangeSliderValue4_gp.setBounds(100,540,100,20);
	        rangeSliderValue4_gp.setVisible(true);
	        
	        rangeSliderValue3_gp.setText(String.valueOf(rangeSlider_tp.getValue()));
	        rangeSliderValue4_gp.setText(String.valueOf(rangeSlider_tp.getUpperValue()));
	        rangeSlider2_gp.addChangeListener(new ChangeListener() {
	            public void stateChanged(ChangeEvent e) {
	                RangeSlider slider2_gp = (RangeSlider) e.getSource();
	                rangeSliderValue3_gp.setText(String.valueOf(slider2_gp.getValue()));
	                rangeSliderValue4_gp.setText(String.valueOf(slider2_gp.getUpperValue()));
	                
	                rangeSlider2.setValue(rangeSlider2_gp.getValue());
	    	        rangeSlider2.setUpperValue(rangeSlider2_gp.getUpperValue());
	                rangeSliderValue3.setText(String.valueOf(slider2_gp.getValue()));
	                rangeSliderValue4.setText(String.valueOf(slider2_gp.getUpperValue()));
	                
	                rangeSlider2_tp.setValue(rangeSlider2_gp.getValue());
	    	        rangeSlider2_tp.setUpperValue(rangeSlider2_gp.getUpperValue());
	                rangeSliderValue3_tp.setText(String.valueOf(slider2_gp.getValue()));
	                rangeSliderValue4_tp.setText(String.valueOf(slider2_gp.getUpperValue()));
	                
	                AGE_RangeSlider_UpperValue = Integer.valueOf(slider2_gp.getUpperValue());
	                AGE_RangeSlider_LowerValue = Integer.valueOf(slider2_gp.getValue());
	            }
	        });
	        
	        rangeSlider2_gp.setBounds(13, 560, 130,20);
	        rangeSlider2_gp.setVisible(true);
	        //testmapPanel.add(rangeSlider);
	        rangeSliderValue3_gp.setText("0");
	        rangeSliderValue4_gp.setText("42");
	        
			while(rs.next()){
				if(rs.getString("date").substring(0, 10).equals(buf_f_gp))
				{
					continue;
				}else{
					buf_f_gp = rs.getString("date").substring(0, 10);
					Combo_Date_gp.addItem(buf_f_gp);
					//System.out.println("test : "+buf_f);
				}
			}
			

			
			//rangeSlider.setBounds(x, y, width, height);
			
			Combo_Date_gp.setVisible(false);
			Combo_User_gp.setBounds(0, 200, 150, 50);
			Combo_User_gp.setVisible(false);
			
			U_Button2_gp.setText("Show");
			U_Button2_gp.setBounds(0, 150, 150, 50);
			U_Button2_gp.setFont( new Font( "Dialog", Font.BOLD , 15 ) );
			U_Button2_gp.setVisible(true);
			U_Button2_gp.addActionListener(new MyActionListener3());
			
			//Radio Button--------------------------------------
			
			p6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Option", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0,0,0)));
			p6.setBounds(25, 250, 100, 115);
			p6.setLayout(new FlowLayout(FlowLayout.LEADING));
			
			rb1_gp.setText("All");
			//rb1.setBounds(0, 400, 70, 20);
			rb1_gp.setVisible(true);
			rb1_gp.setSelected(true);
			rb1_gp.addItemListener(new SelectItemListener());
			p6.add(rb1_gp);
			
			rb2_gp.setText("Male");
			//rb2.setBounds(0, 450, 70, 20);
			rb2_gp.setVisible(true);
			rb2_gp.addItemListener(new SelectItemListener());
			p6.add(rb2_gp);
			
			rb3_gp.setText("Female");
			//rb3.setBounds(0, 500, 70, 20);
			rb3_gp.setVisible(true);
			rb3_gp.addItemListener(new SelectItemListener());
			p6.add(rb3_gp);
			p6.setVisible(true);
			Bgroup3.add(rb1_gp);
			Bgroup3.add(rb2_gp);
			Bgroup3.add(rb3_gp);
			panel2.add(p6);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
				
		panel2.add(Date_Button_gp);
		panel2.add(U_Button2_gp);
		panel2.add(Combo_Date_gp);
		panel2.add(Combo_User_gp);
		panel2.add(rangeSliderLabel0_gp);
		panel2.add(rangeSliderLabel1_gp);
		panel2.add(rangeSliderLabel2_gp);
		panel2.add(rangeSliderValue1_gp);
		panel2.add(rangeSliderValue2_gp);
		panel2.add(rangeSlider_gp);
		panel2.add(rangeSliderLabel0_2_gp);
		panel2.add(rangeSliderLabel3_gp);
		panel2.add(rangeSliderLabel4_gp);
		panel2.add(rangeSliderValue3_gp);
		panel2.add(rangeSliderValue4_gp);
		panel2.add(rangeSlider2_gp);
		//	public static int AGE_RangeSlider_UpperValue, AGE_RangeSlider_LowerValue;
		//public static int TEMP_RangeSlider_UpperValue, TEMP_RangeSlider_LowerValue;
		make_XYBarChartPanel();
		panel2.add(Graph_panel);
		
	}
	/*
	public void make_GraphPanel(){
		
		try {
			st = con.createStatement();
			
			String buf_f = null;
			rs = st.executeQuery("select distinct date from thermo_data");
			
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
			
			rs = st.executeQuery("select distinct id from thermo_data");
			
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
		
		panel2.add(make_XYBarChartPanel());
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
	
	
	*/
	public static void re_paint(String Sel_date, String Sel_user){
		testmapPanel.remove(testmap);
		testmapPanel.remove(temperature_distribution);
		testmap.setVisible(false);
		testmap.removeAll();
		testmap = new OSM().OSM_init(Sel_date, Sel_user, 1);
		testmap.setBounds(150, 50, 1290, 750);
		testmap.setVisible(true);
		testmapPanel.add(testmap);
		testmapPanel.repaint();
		testmapPanel.setVisible(true);
	}
	
	public void re_paint_chart(String Sel_date, String Sel_user){
		testmapPanel.remove(testmap);
		temperature_distribution.setVisible(false);
		temperature_distribution.removeAll();
		try {
			temperature_distribution_init();
		} catch (ClassCastException | IllegalArgumentException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		temperature_distribution.setBounds(150, 50, 1290, 820);
		temperature_distribution.setVisible(true);
		testmapPanel.add(temperature_distribution);
		testmapPanel.repaint();
		testmapPanel.setVisible(true);
	}
	
	
	
		
    private class SelectItemListener  implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
        	//------------------date-----------------
        	AbstractButton sel = (AbstractButton)e.getItemSelectable();
                       
            if(e.getStateChange() == ItemEvent.SELECTED)
            {
                   if ( sel.getText().equals("All") )
                   {
                       rb1.setSelected(true);
                       rb1_tp.setSelected(true);
                       rb1_gp.setSelected(true);
                       Bgroup_gender = 1;
           			                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
           				
                   }
                   else if ( sel.getText().equals( "Male" ) )
                   {
                       rb2.setSelected(true);
                       rb2_tp.setSelected(true);
                       rb2_gp.setSelected(true);
                       Bgroup_gender = 2;
                	  // box2.setSelectedIndex(1);;
                   }
                   else if ( sel.getText().equals( "Female" ) )
                   {
                       rb3.setSelected(true);
                       rb3_tp.setSelected(true);
                       rb3_gp.setSelected(true);
                       Bgroup_gender = 3;
                	  // box2.setSelectedIndex(2);;
                   }
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
		
	//	panel2.add(Map_panel);
		//tabbedPane.add("Map", panel2);
		//tabbedPane.add("Statistics", panel3);
		jF.add(tabbedPane);
    	
    }
    
    private void set_User(String d){
    	
    	String id_buf = "";
    	
    	try {
			st = con.createStatement();			
			rs = st.executeQuery("select id,date from thermo_data");
			
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
	        		rs = st.executeQuery("SELECT DISTINCT date from thermo_data WHERE id="+ id +" ORDER BY date");
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

            	//System.out.println("Redraw");
            
            	try {
					//new_data = database_load.dload(box1.getSelectedItem().toString(), box2.getSelectedItem().toString());
					//System.out.println(box1.getSelectedItem().toString() + " / " + box2.getSelectedItem().toString());
					//ChartPanel NCP = new ChartPanel(new jPanel01().DrawMyChart(new_data));
					//NCP.setBounds(300, 10, 1610, 980);
					temperature_distribution.remove(CP);
					
					CP = new ChartPanel(new jPanel01().DrawMyChart2(SwingWaypoint.send_user, box2.getSelectedItem().toString(), 37.0));
					
					CP.setBounds(130, 0, 1150, 740);
					temperature_distribution.add(CP);
					temperature_distribution.repaint();
					temperature_distribution.setVisible(true);


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
            	
				temperature_distribution.remove(CP);
				
				try {
					CP = new ChartPanel(new jPanel01().DrawMyChart2(box1.getSelectedItem().toString(), box2.getSelectedItem().toString(), (Double)box3.getSelectedItem()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				CP.setBounds(130, 0, 1280, 700);
				temperature_distribution.add(CP);
				temperature_distribution.repaint();
				temperature_distribution.setVisible(true);
            	
            }
        }
    }
	
	private class MyActionListener3 implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        	//------------------date-----------------
            JButton b = (JButton) e.getSource();
            if (b.getText().equals("Date")){
            	b.setText("Date select");
            	Combo_Date_gp.setVisible(true);
            }      	
            else if(b.getText().equals("Date select")){
            	b.setText("Date");
            	
            	date2 = Combo_Date_gp.getSelectedItem().toString();            
            	Combo_Date_gp.setVisible(false);
            	Combo_User_gp.removeAllItems();

        		try {
        			st.executeQuery("use Thermosafer_INU;");
					rs = st.executeQuery("SELECT DISTINCT id from thermo_data WHERE LEFT(date,10) = '" + date2 + "'");
					while(rs.next()){
						Combo_User_gp.addItem(rs.getString("id"));
	        		}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		sel_state = 0;
            }
            
            if (b.getText().equals("Show")){
//            	testmapPanel.remove(testmap);
//            	testmap.setVisible(false);
//            	testmap.removeAll();
//            	
//            	testmap = new OSM().OSM_init(date2, Bgroup_gender, 1 );
                panel2.remove(Graph_panel);
                
	            make_XYBarChartPanel(AGE_RangeSlider_UpperValue, AGE_RangeSlider_LowerValue, Bgroup_gender , date2);
        		panel2.add(Graph_panel);
        		panel2.repaint();
        		panel2.setVisible(true);
            	/*
        		testmap.repaint();
        		testmap.setBounds(150, 0, 1290, 800);
        		testmap.setVisible(true);
        		
        		testmapPanel.add(testmap);
        		testmapPanel.repaint();
        		testmapPanel.setVisible(true);*/
        		
            }
            /*
            //---------------user---------------------
            if (b.getText().equals("User")){
            		b.setText("User select");
                	Combo_User_gp.setVisible(true);
                	sel_state2 = 1;
                	testmap = new OSM().OSM_init(date2, 1, 1 );
                	
            }      	
            else if(b.getText().equals("User select")){
            	b.setText("User");
            	user2 = Combo_User_gp.getSelectedItem().toString();
            	setTitle(b.getText());
            	
          		//combo_u.removeAllItems();
          		Combo_User_gp.setVisible(false);
          		
          		
            	if (sel_state2 == 1)
            	{
	            	testmapPanel.remove(testmap);
	            	testmap.setVisible(false);
	            	testmap.removeAll();
	            	if (sel_state == 0)
	            	{

	            		testmap = new OSM().OSM_init(date2, 1, 1 );
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
            	}*/
            
            
            //--------------temp-----------------
            
            
             
            

            //System.out.println("state1 : " + sel_state + " / state2 : " + sel_state2);
        }
	}
	
	
	
	
    public class MyActionListener4 implements ActionListener { // Map Panel Action Listener
        public void actionPerformed(ActionEvent e) {

        	//------------------date-----------------
            JButton b = (JButton) e.getSource();
            if (b.getText().equals("Date")){
            	b.setText("Date select");
            	Combo_Date.setVisible(true);
            }      	
            else if(b.getText().equals("Date select")){
            	b.setText("Date");
            	
            	date2 = Combo_Date.getSelectedItem().toString();            
            	Combo_Date.setVisible(false);
            	Combo_User.removeAllItems();

        		try {
        			st.executeQuery("use Thermosafer_INU;");
					rs = st.executeQuery("SELECT DISTINCT id from thermo_data WHERE LEFT(date,10) = '" + date2 + "'");
					while(rs.next()){
						Combo_User.addItem(rs.getString("id"));
	        		}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		sel_state = 0;
            }
            
            if (b.getText().equals("Show")){
            	testmapPanel.remove(testmap);
            	testmap.setVisible(false);
            	testmap.removeAll();
            	
            	testmap = new OSM().OSM_init(date2, Bgroup_gender,AGE_RangeSlider_UpperValue,AGE_RangeSlider_LowerValue,  1 );
            	
        		testmap.repaint();
        		testmap.setBounds(150, 0, 1290, 800);
        		testmap.setVisible(true);
        		
        		testmapPanel.add(testmap);
        		testmapPanel.repaint();
        		testmapPanel.setVisible(true);
        		
        		Map_Button.setVisible(false);
        		Temp_Button.setVisible(false);
        		temperature_distribution.setVisible(false);
        		Back_b.setVisible(false);
        		
        		b2.setVisible(false);
        		b3.setVisible(false);
        		
        		
            }
            if (b.getText().equals("Back")){
            	testmapPanel.remove(testmap);
            	testmap.setVisible(false);
            	testmap.removeAll();
        		/*
        		 * (String Sel_date, int gender, int Upper_age, int Lower_age,int state)
        		 * AGE_RangeSlider_UpperValue, AGE_RangeSlider_LowerValue;
        		*/
            	testmap = new OSM().OSM_init(date2, Bgroup_gender,AGE_RangeSlider_UpperValue,AGE_RangeSlider_LowerValue,  1 );
            	
        		testmap.repaint();
        		testmap.setBounds(150, 0, 1290, 800);
        		testmap.setVisible(true);
        		
        		testmapPanel.add(testmap);
        		testmapPanel.repaint();
        		testmapPanel.setVisible(true);
        		
        		Map_Button.setVisible(false);
        		Temp_Button.setVisible(false);
        		temperature_distribution.setVisible(false);
        		Back_b.setVisible(false);
        		
        		b2.setVisible(false);
        		b3.setVisible(false);
        		
        		
            }
            
            
            
            if (b.getText().equals("Temp")){

            	re_paint_chart(SwingWaypoint.send_date, SwingWaypoint.send_user);
        		
            }
            
            if (b.getText().equals("Map")){
            	re_paint(SwingWaypoint.send_date, SwingWaypoint.send_user);
            	
        		
            }
            /*
            //---------------user---------------------
            if (b.getText().equals("User")){
            		b.setText("User select");
                	Combo_User.setVisible(true);
                	sel_state2 = 1;
                	testmap = new OSM().OSM_init(date2, 1, 1 );
                	
            }      	
            else if(b.getText().equals("User select")){
            	b.setText("User");
            	user2 = Combo_User.getSelectedItem().toString();
            	setTitle(b.getText());
            	
          		//combo_u.removeAllItems();
          		Combo_User.setVisible(false);
          		
          		
            	if (sel_state2 == 1)
            	{
	            	testmapPanel.remove(testmap);
	            	testmap.setVisible(false);
	            	testmap.removeAll();
	            	if (sel_state == 0)
	            	{
	            		//testmap = new OSM().OSM_init(date2,user2,1);
	            		
	            		testmap = new OSM().OSM_init(date2, 1, 1 );
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
            	}*/
            	
            	
            	
            
            
            //--------------temp-----------------
            
            
             
            

            //System.out.println("state1 : " + sel_state + " / state2 : " + sel_state2);
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
