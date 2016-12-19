
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
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
		//���� ������ ����

		tabbedPane  = new JTabbedPane();
				
		panel1 = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		panel2 = new JPanel();
		panel3 = new JPanel();
		
		label3 = new JLabel("Third");
				
		// add chart in JPanel
		jPanel01 jP = new jPanel01();
		Map p2 = new Map();
		panel2 = p2.Map_init();
		panel1.setLayout(new java.awt.BorderLayout());
		
	
		ChartPanel CP = new ChartPanel(jP.DrawMyChart(database_load.dload()));
		
		
		panel1.add(CP, BorderLayout.CENTER);
		panel1.add(new JLabel("Select"), BorderLayout.SOUTH);
		
		p2.setMAap(100, 100);
		p2.setMAap(200, 200);
		p2.setMAap(300, 300);

		panel3.add(label3);
		
		
		tabbedPane.add("�µ�", panel1);
		tabbedPane.add("Map", panel2);
		tabbedPane.add("Third", panel3);
		//tabbedPane.add("Th)
		
		//��ũ�� ������ ���
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension di  = tk.getScreenSize();
		//������ ����
		jF = new JFrame();
		jF.add(tabbedPane);
		jF.setSize((int)di.getWidth(),(int)di.getWidth()-200); // Full Screen
		//jF.setSize(800, 600);
		jF.setTitle("Choice Tech"); // â ����
		jF.setExtendedState(JFrame.MAXIMIZED_BOTH); // �ִ�ȭ
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		jF.setVisible(true);
		
	
	}
	



	public void start() throws SQLException
	{
		
	}
	
}
