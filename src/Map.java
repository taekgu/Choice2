import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
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

public class Map extends JFrame{
	
	Container ctp;
	JButton[] jb = new JButton[1000];
	
	MyPanel panel;
	int b_num = 0;
    ImageIcon i = new ImageIcon("Seoul.JPG");
    Image im = i.getImage();
    public Map(){
        this.setTitle("이미지 그리기 연습");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //((JFrame) ctp).getContentPane();
        panel = new MyPanel();
        panel.setLayout(new FlowLayout());
        
        
        this.add(panel);
        this.setSize(1800,1000);
        this.setVisible(true);
    }
    class MyPanel extends JPanel{
            
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(im,0,0,getWidth(),getHeight(),this);
            setMAap(150,150);

        }
    }
    
    private void setMAap(int a, int b){
    	jb[b_num] = new JButton(new ImageIcon("check.png"));
    	jb[b_num].setBorderPainted(false);
    	jb[b_num].setContentAreaFilled(false);
    	jb[b_num].setSize(30,30);
        jb[b_num].setLocation(a,b);
        panel.add(jb[b_num]);
        b_num++;
    }
}
