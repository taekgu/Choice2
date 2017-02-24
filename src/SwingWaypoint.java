import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A waypoint that is represented by a button on the map.
 *
 * @author Daniel Stahr
 */
public class SwingWaypoint extends DefaultWaypoint {
    private final JButton button;
    private final String id;

    public SwingWaypoint(String id, String birth, String gender, GeoPosition coord) {
        super(coord);
        this.id = id;
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
            JOptionPane.showMessageDialog(button, "You clicked on\n hello" + id);
           
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
}
