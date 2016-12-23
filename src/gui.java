
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
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

import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;


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
	JComboBox<String> box1;
	JComboBox<String> box2;
	JPanel p1 ;
	ChartPanel CP;
	
	
	public gui() throws IOException, SQLException
	{

		tabbedPane  = new JTabbedPane();	
		panel1 = new JPanel();
		
		b1 = new JButton();
		b2 = new JButton();
		box1 = new JComboBox<String>();
		box2 = new JComboBox<String>();
		p1 = new JPanel(new GridBagLayout());
		CP = new ChartPanel(null);
		
		try {
			//sb
			//con = DriverManager.getConnection("jdbc:mysql://localhost","root", "1234"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC","root", "1234");
			st = con.createStatement();
			//sb
			rs = st.executeQuery("use newschema4");
			//rs = st.executeQuery("use newschema");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		icon = new ImageIcon("Seoul.JPG");

		tabbedPane  = new JTabbedPane();
				
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel3 = new JPanel();
		
		label3 = new JLabel("Third");
				
		// add chart in JPanel
		jPanel01 jP = new jPanel01();
		p2 = new Map();
		//panel2 = p2.Map_init();

		//panel1.setLayout(null);
		//ChartPanel CP = new ChartPanel(jP.DrawMyChart(database_load.dload()));
		panel1.setLayout(new java.awt.BorderLayout());
		panel1.add(jP.JP1());

		//panel1.add(new JLabel("Select"), BorderLayout.SOUTH);
		
		
		//--------MAP--------------------------------------------------------------------------
						
		
		//p2 = new Map();
		Map_panel = p2.Map_init("0","0","초기");
		Map_panel.setBounds(100, 0, 1820, 1000);
		Map_panel.setVisible(true);
		
		panel2.add(Map_panel);
				
		setUser();

		//----------------------------------------------------------------------------------------------

		panel3.add(label3);
		
		tabbedPane.add("Data", panel1);
		tabbedPane.add("Map", panel2);
		tabbedPane.add("Third", panel3);
		//tabbedPane.add("Th)
		
		//��ũ�� ������ ���
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension di  = tk.getScreenSize();
		//������ ����
		jF = new JFrame();
		jF.add(tabbedPane);
		jF.setSize((int)di.getWidth(),(int)di.getWidth()-200); // Full Screen
		//jF.setSize(800, 600);
		jF.setTitle("Choice Tech"); // â ����
		jF.setExtendedState(JFrame.MAXIMIZED_BOTH); // �ִ�ȭ
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		jF.setVisible(true);
	
	}
	
	public void setUser(){
		
		try {
			st = con.createStatement();
			
			String buf_f = null;
			rs = st.executeQuery("select distinct date from tp");
			
			D_Button.setText("전체 날짜");
	    	D_Button.setBounds(0, 0, 100, 50);
	    	D_Button.addActionListener(new MyActionListener());
	    	combo_d.setBounds(0, 50, 100, 50);
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
			U_Button.setBounds(0, 500, 100, 50);
			U_Button.addActionListener(new MyActionListener());
			combo_u.setBounds(0, 550, 100, 50);
			combo_u.setVisible(false);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    	
		T_Button.setText("개인온도");
		T_Button.setBounds(0, 900, 100, 50);
		T_Button.addActionListener(new MyActionListener());
		
		TT_Button.setText("개인고온");
		TT_Button.setBounds(0, 950, 100, 50);
		TT_Button.addActionListener(new MyActionListener());
		
		do_Button.setText("실행");
		do_Button.setBounds(0,800,100,50);
		do_Button.addActionListener(new MyActionListener());
		
		A_Button.setText("전체온도");
		A_Button.setBounds(0,650,100,50);
		A_Button.addActionListener(new MyActionListener());
		
		AA_Button.setText("전체고온");
		AA_Button.setBounds(0,700,100,50);
		AA_Button.addActionListener(new MyActionListener());
		
		
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
            if (b.getText().equals("전체 날짜")){
            	b.setText("날짜 선택");
            	combo_d.setVisible(true);
            }      	
            else if(b.getText().equals("날짜 선택")){
            	b.setText("전체 날짜");
            	date = combo_d.getSelectedItem().toString();            
            	setTitle(b.getText());
            	            	
            	combo_d.setVisible(false);
            	// InnerClassListener의 멤버나 JFrame의 멤버 호출
            }
            //---------------user---------------------
            if (b.getText().equals("User")){
            	if(date.equals("")){
            		b.setText("User");
            	}else{
            		b.setText("User 선택");
            		set_User(date);
                	combo_u.setVisible(true);
            	}
            }      	
            else if(b.getText().equals("User 선택")){
            	b.setText("User");
            	user = combo_u.getSelectedItem().toString();
            	setTitle(b.getText());
            	
          		combo_u.removeAllItems();
          		combo_u.setVisible(false);
            }
            
            //--------------temp-----------------
            if (b.getText().equals("개인온도")){
            	temp = b.getText();
            }      	
            
            if(b.getText().equals("개인고온")){
            	temp = b.getText();
            }
            
            if (b.getText().equals("실행")){
            	// 날짜, 사람, 고-저온
            	// date, user, temp
            	showMap(date, user, temp);
            } 
            
            if(b.getText().equals("전체온도")){
            	temp = b.getText();
            }   
            
            if(b.getText().equals("전체고온")){
            	temp = b.getText();
            }   

        }
    }
    
    private void showMap(String d, String u, String t){
    	
    	Map_panel.setVisible(false);
    	jF.getContentPane().remove(Map_panel);
    	
    	p2 = new Map();
		Map_panel = p2.Map_init(d, u, t);
		Map_panel.setBounds(100, 0, 1820, 1000);
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
    
	public void start() throws SQLException
	{
		
	}
	
}
