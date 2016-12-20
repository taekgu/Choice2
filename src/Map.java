import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Map extends JFrame{
	
	Connection con;
	java.sql.Statement st;
	java.sql.Statement wt;
	ResultSet rs;
	ResultSet ws;
			
	//------------------MAP-----------------------
	
	private JComboBox combo_d = new JComboBox();
	private JComboBox combo_u = new JComboBox();
	private JButton D_Button = new JButton();
	private JButton U_Button = new JButton();
	private JButton T_Button = new JButton();
	
	
	public gui g;
	
	//--------------------------------

	JPanel map_panel;
	JButton[] jb = new JButton[1000];
	JButton go;
	int b_num = 0;
	ImageIcon icon;
	
    public Map(){
    	icon = new ImageIcon("Seoul.JPG");
    }
        
    public JPanel Map_init(int temp_f){
    	
    	try {
    		//sb
			//con = DriverManager.getConnection("jdbc:mysql://localhost","root", "1234"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC","root", "1234"); // jj
			st = con.createStatement();
			//sb
			//rs = st.executeQuery("use testschema");
			rs = st.executeQuery("use newschema"); // jj
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	map_panel = new JPanel() {
			public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, 1820,1000,this);
            };

    	};
    	map_panel.setLayout(null);
    	    
    	if(temp_f == 0){

    		//전체
    	}else if(temp_f == 1){
    		
			try {
				st = con.createStatement();
				//wt = con.createStatement();
				rs = st.executeQuery("select * from tp");
				//ws = wt.executeQuery("select gps_har from tp");
				while(rs.next()){
					System.out.println("gps_lat : "+(int)((rs.getFloat("gps_lat") - 37.4531)/0.0002639));
					System.out.println("gps_har : "+(int)((rs.getFloat("gps_har") - 126.6566)/0.0003632));
					setMAap((int)((rs.getFloat("gps_har") - 126.6566)/0.0003632),(int)((rs.getFloat("gps_lat") - 37.4531)/0.0002639),0);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    		
    		//고온
    	}else if(temp_f == 2){
    		
    		try {
				st = con.createStatement();
				//wt = con.createStatement();
				rs = st.executeQuery("select * from tp where temp >37.3 ");
				//ws = wt.executeQuery("select gps_har from tp");
				while(rs.next()){
					System.out.println("gps_lat : "+(int)((rs.getFloat("gps_lat") - 37.4531)/0.0002639));
					System.out.println("gps_har : "+(int)((rs.getFloat("gps_har") - 126.6566)/0.0003632));
					setMAap_up((int)((rs.getFloat("gps_har") - 126.6566)/0.0003632),(int)((rs.getFloat("gps_lat") - 37.4531)/0.0002639),0);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		setMAap(100,500,0);
    		setMAap(200,600,0);
    		setMAap(300,700,0);  
    	}
    	
    	return map_panel;
    }
          
    public void setMAap(int a, int b, int cnt){
    	
    	jb[cnt] = new JButton(new ImageIcon("check.png"));
    	jb[cnt].setBounds(a+100, b, 30, 30);
    	jb[cnt].setBorderPainted(false);
    	jb[cnt].setContentAreaFilled(false);
    	map_panel.add(jb[cnt]);
    }
    
    public void setMAap_up(int a, int b, int cnt){
    	
    	jb[cnt] = new JButton(new ImageIcon("check.png"));
    	jb[cnt].setBounds(a+100, b, 30, 30);
    	jb[cnt].setBorderPainted(false);
    	jb[cnt].setContentAreaFilled(false);
    	map_panel.add(jb[cnt]);
    }
  
}
