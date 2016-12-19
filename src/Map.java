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
	//--------------------------------

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
                g.drawImage(icon.getImage(), 100, 0, 1820,1000,this);
            };

    	};
    	map_panel.setLayout(null);
    	    
    	setUser();
    	
    	return map_panel;
    }
    
    public void setUser(){
    	
    	D_Button.setText("전체 날짜");
    	D_Button.setBounds(0, 0, 100, 50);
    	D_Button.addActionListener(new MyActionListener());
		
    	combo_d.addItem("date1");
    	combo_d.setBounds(0, 50, 100, 50);
		combo_d.addItem("date2");
		combo_d.addItem("date3");
		combo_d.setVisible(false);
		
		U_Button.setText("전체User");
		U_Button.setBounds(0, 500, 100, 50);
		U_Button.addActionListener(new MyActionListener());
    	
		combo_u.addItem("user1");
		combo_u.setBounds(0, 550, 100, 50);
		combo_u.addItem("user2");
		combo_u.addItem("user3");
		combo_u.setVisible(false);
    	
		T_Button.setText("전체 온도");
		T_Button.setBounds(0, 950, 100, 50);
		T_Button.addActionListener(new MyActionListener());
		
		map_panel.add(D_Button);
		map_panel.add(U_Button);
		map_panel.add(T_Button);
		map_panel.add(combo_d);
		map_panel.add(combo_u);
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
            }      	
            else if(b.getText().equals("고온")){
            	b.setText("전체 온도");
            	setTitle(b.getText());
            	//combo.setVisible(false);
            	// InnerClassListener의 멤버나 JFrame의 멤버 호출
            }

        }
    }
    
   
    public void setMAap(int a, int b){
    	
    	jb[0] = new JButton(new ImageIcon("check.png"));
    	jb[0].setBounds(a+100, b, 30, 30);
    	jb[0].setBorderPainted(false);
    	jb[0].setContentAreaFilled(false);
    	//jb[b_num].setSize(30,30);
        //jb[b_num].setLocation(a,b);
    	map_panel.add(jb[0]);
        b_num++;
          
    }
        
  
}
