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
        
    public JPanel Map_init(boolean temp_f){
    	map_panel = new JPanel() {
			public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, 1820,1000,this);
            };

    	};
    	map_panel.setLayout(null);
    	    
    	if(temp_f == true){
    		setMAap(100,100,0);
    		setMAap(200,200,0);
    		setMAap(300,300,0);
    	}else if(temp_f == false){
    		jb[0].setVisible(false);
    		
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
  
}
