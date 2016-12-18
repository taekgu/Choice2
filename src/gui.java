
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
public class gui extends JFrame {
	
	private static final long serialVersionUID = 5193460907470526697L;
	
	private JFrame jF;
	private JTabbedPane tabbedPane;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	//private JLabel label1;
	private JLabel label2;
	private JLabel label3;

	
	public gui() throws IOException, SQLException
	{
		//랜덤 데이터 생성



		tabbedPane  = new JTabbedPane();
		
		jPanel01 p1 = new jPanel01();
		
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		
		label2 = new JLabel("Second");
		
		// add chart in JPanel
		jPanel01 jP = new jPanel01();
		panel1.setLayout(new java.awt.BorderLayout());
		
	
		ChartPanel CP = new ChartPanel(jP.DrawMyChart(database_load.dload()));
		
		
		panel1.add(CP, BorderLayout.CENTER);
		panel1.add(new JLabel("Select"), BorderLayout.SOUTH);
		
		panel2.add(label2);
		
		
		tabbedPane.add("온도", panel1);
		tabbedPane.add("second", panel2);
		//tabbedPane.add("Th)
		
		//스크린 사이즈 얻기
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension di  = tk.getScreenSize();
		//프레임 생성
		jF = new JFrame();
		jF.add(tabbedPane);
		jF.setSize((int)di.getWidth(),(int)di.getWidth()-200); // Full Screen
		//jF.setSize(800, 600);
		jF.setTitle("Choice Tech"); // 창 제목
		jF.setExtendedState(JFrame.MAXIMIZED_BOTH); // 최대화
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		jF.setVisible(true);
		
	
	}
	
	



	public void start() throws SQLException
	{
		
	}
	
}
