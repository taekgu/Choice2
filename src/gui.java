
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
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
	private JPanel panel1;
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
	
	ImageIcon icon;
	public Map p2;
	//--------------------------------

	
	public gui() throws IOException, SQLException
	{
		//���� ������ ����
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost","root", "1234");
			st = con.createStatement();
			rs = st.executeQuery("use testschema");
			
			
			
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
		panel1.setLayout(new java.awt.BorderLayout());
		
	
		ChartPanel CP = new ChartPanel(jP.DrawMyChart(database_load.dload()));
		
		
		panel1.add(CP, BorderLayout.CENTER);
		panel1.add(new JLabel("Select"), BorderLayout.SOUTH);
		
		
		//--------MAP--------------------------------------------------------------------------
						
		
		p2 = new Map();
		Map_panel = p2.Map_init(0);
		Map_panel.setBounds(100, 0, 1820, 1000);
		Map_panel.setVisible(true);
		
		panel2.add(Map_panel);
		
		
		setUser();
		
		
		//----------------------------------------------------------------------------------------------

		panel3.add(label3);
		
		
		tabbedPane.add("�µ�", panel1);
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
					System.out.println("test : "+buf_f);
				}
				
			}
			combo_d.setVisible(false);
			
			
			rs = st.executeQuery("select distinct id from tp");
			
			U_Button.setText("전체User");
			U_Button.setBounds(0, 500, 100, 50);
			U_Button.addActionListener(new MyActionListener());
			combo_u.setBounds(0, 550, 100, 50);
			while(rs.next()){
				combo_u.addItem(rs.getString("id"));
				System.out.println("test : "+rs.getString("id"));
				
			}
			combo_u.setVisible(false);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    	
		T_Button.setText("전체 온도");
		T_Button.setBounds(0, 950, 100, 50);
		T_Button.addActionListener(new MyActionListener());
		
		panel2.add(D_Button);
		panel2.add(U_Button);
		panel2.add(T_Button);
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
            	setTitle(b.getText());
            	combo_d.setVisible(false);
            	// InnerClassListener의 멤버나 JFrame의 멤버 호출
            }
            //---------------user---------------------
            if (b.getText().equals("전체User")){
            	b.setText("User 선택");
            	combo_u.setVisible(true);
            }      	
            else if(b.getText().equals("User 선택")){
            	b.setText("전체User");
            	setTitle(b.getText());
            	combo_u.setVisible(false);
            	// InnerClassListener의 멤버나 JFrame의 멤버 호출
            }
            
            //--------------temp-----------------
            if (b.getText().equals("전체 온도")){
            	b.setText("고온");
            	//combo.setVisible(true);
            	showMap(1);
      
            }      	
            else if(b.getText().equals("고온")){
            	b.setText("전체 온도");
            	setTitle(b.getText());
            	//combo.setVisible(false);
            	// InnerClassListener의 멤버나 JFrame의 멤버 호출
            	showMap(2);
            }

        }
    }
    
    private void showMap(int temp_f){
    	
    	Map_panel.setVisible(false);
    	jF.getContentPane().remove(Map_panel);
    	
    	p2 = new Map();
		Map_panel = p2.Map_init(temp_f);
		Map_panel.setBounds(100, 0, 1820, 1000);
		Map_panel.setVisible(true);
		
		panel2.add(Map_panel);
		tabbedPane.add("Map", panel2);
		tabbedPane.add("Third", panel3);
		jF.add(tabbedPane);
    	
    }
    
	public void start() throws SQLException
	{
		
	}
	
}
