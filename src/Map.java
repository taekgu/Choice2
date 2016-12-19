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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Map extends JFrame{
	

	JPanel map_panel;
	JButton[] jb = new JButton[1000];
	JButton go;
	int b_num = 0;
	ImageIcon icon;
	
    public Map(){
    	icon = new ImageIcon("Seoul.JPG");
    }
    
    public JPanel Map_init(){
    	map_panel = new JPanel() {
			public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, 1920,1000,this);
            };

    	};
    	map_panel.setLayout(null);
    	
    	//디비로 값 받아와서 

    	//setMAap(160,160);
    	//setMAap(300,300);
    	
    
    	
    	return map_panel;
    }
    
   
    public void setMAap(int a, int b){
    	
    	jb[0] = new JButton(new ImageIcon("check.png"));
    	jb[0].setBounds(a, b, 30, 30);
    	jb[0].setBorderPainted(false);
    	jb[0].setContentAreaFilled(false);
    	//jb[b_num].setSize(30,30);
        //jb[b_num].setLocation(a,b);
    	map_panel.add(jb[0]);
        b_num++;
          
    }
        
  
}
