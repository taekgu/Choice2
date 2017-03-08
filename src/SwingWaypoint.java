

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * A waypoint that is represented by a button on the map.
 *
 * @author Daniel Stahr
 */

public class SwingWaypoint extends DefaultWaypoint {
    private final JButton button;
    private final String Sel_date;
    private final String Sel_user;
    private final String id;
    private final String birth;
    private final String gender;
	private final Double Geo_lat;
	private final Double Geo_har;
	//gui gui;
	
    public SwingWaypoint(String Sel_date, String Sel_user, String id, String birth, String gender, Double Geo_lat, Double Geo_har, GeoPosition coord) {
        super(coord);
        this.Sel_date = Sel_date;
        this.Sel_user = Sel_user;
        this.id = id;
        this.birth = birth;
        this.gender = gender;
        this.Geo_lat = Geo_lat;
        this.Geo_har = Geo_har;
        GeoPosition gps = coord;
        button = new JButton( new ImageIcon("check_blue.png"));//text.substring(0, 1),
        //button.setIcon();
        button.setSize(24, 24);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setPreferredSize(new Dimension(24, 35));
        button.setContentAreaFilled(false);
        button.addMouseListener(new SwingWaypointMouseListener());
        button.setVisible(true);
    }

    JButton getButton() {
        return button;
    }

    private class SwingWaypointMouseListener implements MouseListener {



  
    	
        @Override
        public void mouseClicked(MouseEvent e) {
        	
            JButton jbt_ok = new JButton("확인");
            //JButton jbt_more = new JButton("자세히 보기");
            /*
            jbt_more.addActionListener(new ActionListener() {
                @SuppressWarnings("static-access")
				@Override
                public void actionPerformed(ActionEvent b) {  
                	
                	JButton jb = (JButton) b.getSource();
                    // set the value of the option pane
                	if (jb.getText().equals("자세히 보기")){
                		System.out.println("here");
                		
                		JOptionPane.getRootFrame().dispose();
                	}
                	
                   
                }
            });*/
            
            final JButton jbt_more = new JButton("자세히 보기");
            jbt_more.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane pane = getOptionPane((JComponent)e.getSource());
                    pane.setValue(jbt_more);
                   
					gui gui;
					try {
						gui = new gui();
						gui.re_paint(Sel_date, Sel_user);
					} catch (IOException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						
					
                    
                }
                
                protected JOptionPane getOptionPane(JComponent parent) {
                    JOptionPane pane = null;
                    if (!(parent instanceof JOptionPane)) {
                        pane = getOptionPane((JComponent)parent.getParent());
                    } else {
                        pane = (JOptionPane) parent;
                    }
                    return pane;
                }
            });
            
        	Object[] options = {"확인", jbt_more};
        	
        	int year = Calendar.getInstance().get(Calendar.YEAR);
        	int birth_int = Integer.parseInt(birth);
        	//JOP = new JOptionPane();
        	
            //@SuppressWarnings("static-access")
			int input_num = JOptionPane.showOptionDialog(
            		button,
            		"ID : " + id + "\n성별 : " + gender + "\n나이 : " + (year - birth_int + 1) + "\nGPS : " + Geo_lat + ", " + Geo_har,"Information",
                    JOptionPane.OK_CANCEL_OPTION, 
                    JOptionPane.PLAIN_MESSAGE, null, 
                    options, 
                    options[0]);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
    
    private class MyActionListener implements ActionListener{
    	  public void actionPerformed(ActionEvent e){
    		  
    	  }
    }
}
