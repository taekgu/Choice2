

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
	private final Double temp;
	private int state;
	
	public static String send_user;
	public static String send_date;
	public static int waypoint_status;
	
	//gui gui;
	
    public SwingWaypoint(String Sel_date, String Sel_user, String id, String birth, String gender, Double Geo_lat, Double Geo_har, GeoPosition coord, Double temp) {
        super(coord);
        this.Sel_date = Sel_date;
        this.Sel_user = Sel_user;
        this.id = id;
        this.birth = birth;
        this.gender = gender;
        this.Geo_lat = Geo_lat;
        this.Geo_har = Geo_har;
        this.temp = temp;
        //GeoPosition gps = coord;
      //  String Path = File.class.getResource("").getPath();
        //URL imageURL = getClass().getClassLoader().getResource("/resources/check_blue.png");
        
        //URL url = SwingWaypoint.class.getResource("/resources/check_blue.png");
       // button = new JButton(new ImageIcon(getClass().getClassLoader().getResource("/images/standard_waypoint.png")));//text.substring(0, 1),
        //button = new JButton( new ImageIcon("check_blue.png"));

       // ImageIcon SettingsIc = new ImageIcon("/resources/check_blue.png");
        button = new JButton(new ImageIcon(getClass().getResource("/resources/images/check_blue.png")));//text.substring(0, 1),
        
        //button = new JButton(SettingsIc);
        //button = new JButton(new ImageIcon("check_blue.png"));//text.substring(0, 1),

        	
        //getClass().getResource("/images/yourImageName.extension");
        //button = new JButton(SettingsIc);
        //button.setIcon();
        button.setSize(24, 24);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setPreferredSize(new Dimension(24, 35));
        button.setContentAreaFilled(false);
        button.addMouseListener(new SwingWaypointMouseListener());
        button.setVisible(true);
        state = 1; // with routepainter
    }
    
    public SwingWaypoint(String Sel_date, String id, String birth, String gender, Double Geo_lat, Double Geo_har, GeoPosition coord, Double temp) {
        super(coord);
        this.Sel_date = Sel_date;
        this.Sel_user = null;
        this.id = id;
        this.birth = birth;
        this.gender = gender;
        this.Geo_lat = Geo_lat;
        this.Geo_har = Geo_har;
        this.temp = temp;
        GeoPosition gps = coord;
        //button = new JButton( new ImageIcon("check_blue.png"));//text.substring(0, 1),
        //button = new JButton(new ImageIcon(getClass().getClassLoader().getResource("/images/standard_waypoint.png")));//text.substring(0, 1),
        button = new JButton(new ImageIcon(getClass().getResource("/resources/images/check_blue.png")));
        //button.setIcon();
        button.setSize(24, 24);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setPreferredSize(new Dimension(24, 35));
        button.setContentAreaFilled(false);
        button.addMouseListener(new SwingWaypointMouseListener());
        button.setVisible(true);
        state = 2; // without routepainter
    }
    
    JButton getButton() {
        return button;
    }

    private class SwingWaypointMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        	
            JButton jbt_ok = new JButton("확인");
            
            
	            final JButton jbt_more = new JButton("자세히 보기");
	            jbt_more.addActionListener(new ActionListener() {
	                @SuppressWarnings("static-access")
					@Override
	                public void actionPerformed(ActionEvent e) {
	                    JOptionPane pane = getOptionPane((JComponent)e.getSource());
	                    pane.setValue(jbt_more);
	                    gui.re_paint(Sel_date, id);
	                    send_date = Sel_date;
						send_user = id;
						gui.Map_Button.setVisible(true);
						gui.Temp_Button.setVisible(true);
						gui.Back_b.setVisible(true);
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
        	//String birth_int = birth;
        	
        	
        	send_user = id;
        	send_date = Sel_date;
        	
        	//JOP = new JOptionPane();
        	
            //@SuppressWarnings("static-access")
			int input_num = JOptionPane.showOptionDialog(
            		button,
            		"ID : " + id + "\n성별 : " + gender + "\n생년월일 : " + birth + "\nGPS : " + Geo_lat + ", " + Geo_har + "\n현재온도 : " + temp,"Information",
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
//	public static void re_paint(String Sel_date, String Sel_user){
//		gui.testmapPanel.remove(gui.testmap);
//		gui.testmap.setVisible(false);
//		gui.testmap.removeAll();
//		gui.testmap = new OSM().OSM_init(Sel_date, Sel_user, 1);
//		gui.testmap.setBounds(150, 50, 1290, 750);
//		gui.testmap.setVisible(true);
//		gui.testmapPanel.add(gui.testmap);
//		gui.testmapPanel.repaint();
//		gui.testmapPanel.setVisible(true);
//	}
    
    private class MyActionListener implements ActionListener{
    	  public void actionPerformed(ActionEvent e){
    		  
    	  }
    }
}
