import java.awt.BorderLayout;
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
    ImageIcon i = new ImageIcon("soul.png");
    Image im = i.getImage();
    Map(){
        this.setTitle("이미지 그리기 연습");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        MyPanel panel = new MyPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JButton("Hello"));
        
        this.add(panel);
        this.setSize(1800,1000);
        this.setVisible(true);
    }
    class MyPanel extends JPanel{
            
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(im,0,0,getWidth(),getHeight(),this);
        }
    }
}
