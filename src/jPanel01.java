import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.ui.TextAnchor;

public class jPanel01 {
	
	Connection con;
	private String dbURL = "";
	java.sql.Statement st;
	ResultSet rs;
	
	JButton b1;
	JButton b2;
	JComboBox<String> box1;
	JComboBox<String> box2;
	JPanel p1 ;
	ChartPanel CP;
		
	public jPanel01()
	{
		
		b1 = new JButton();
		b2 = new JButton();
		box1 = new JComboBox<String>();
		box2 = new JComboBox<String>();
		p1 = new JPanel(new GridBagLayout());
		CP = new ChartPanel(null);
		try
		{
			con = null;
			dbURL=  "jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			con = DriverManager.getConnection(dbURL,"root", "1234");
			st = con.createStatement();
			ResultSet rs = null;
			st.execute("USE newschema4;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JFreeChart DrawMyChart(Float[][] floats) throws IOException, ClassCastException, IllegalArgumentException
	{
		int SAMPLE_NUM = database_load.COUNT;
		    // ������ ����
		
		/////////////////////////////////////////////////////////////////

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();                // bar chart 1
		
		// ������ �Է� ( ��, ����, ī�װ� )
		// �׷��� 1
		
		for (int i = 0; i < SAMPLE_NUM; i++){	
		    dataset.addValue(floats[0][i], "S1", i+1 + "시");
			//System.out.println(floats[0][i]);
		}
		//dataset = null;
		
		// ������ ���� �� ����
		// ������ ����
		final LineAndShapeRenderer renderer = new LineAndShapeRenderer();
		     
		// ���� �ɼ� ����
		//final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator(); // ���ڻ���
		
		final ItemLabelPosition p_below = new ItemLabelPosition(
		             ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT
		             );
		Font f = new Font("Gulim", Font.PLAIN, 10);
		Font axisF = new Font("Gulim", Font.PLAIN, 10);
		   
		    // ������ ����
		// �׷��� 1
		   // renderer.setBaseItemLabelGenerator(generator); // ���ڻ���
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseShapesVisible(true);
		renderer.setDrawOutlines(true);
		renderer.setUseFillPaint(true);
		renderer.setBaseFillPaint(Color.WHITE);
		renderer.setBaseItemLabelFont(f);
		renderer.setBasePositiveItemLabelPosition(p_below);
		renderer.setSeriesPaint(0,new Color(219,121,22));
		renderer.setSeriesStroke(0,new BasicStroke(
		                                       2.0f,
		                                       BasicStroke.CAP_ROUND,
		                                       BasicStroke.JOIN_ROUND,
		                                       3.0f)
		);
		
		// plot ����
		    final CategoryPlot plot = new CategoryPlot();
		   
		    // plot �� ������ ����
		plot.setDataset(dataset);
		plot.setRenderer(renderer);
		//////////////////////////////////////////////////////////////////////////////////
		 
		        // plot �⺻ ����
		plot.setOrientation(PlotOrientation.VERTICAL);             // �׷��� ǥ�� ����
		plot.setRangeGridlinesVisible(true);                       // X�� ���̵� ���� ǥ�ÿ���
		plot.setDomainGridlinesVisible(true);                      // Y�� ���̵� ���� ǥ�ÿ���
		 
		        // ������ ���� ���� : dataset ��� ������� ������ ( ��, ���� ����Ѱ� �Ʒ��� �� )
	    plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
		   
		    // X�� ����
		plot.setDomainAxis(new CategoryAxis());              // X�� ���� ����
		plot.getDomainAxis().setTickLabelFont(axisF); // X�� ���ݶ� ��Ʈ ����
		plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);       // ī�װ� �� ��ġ ����
		 
		        // Y�� ����
		plot.setRangeAxis(new NumberAxis());                 // Y�� ���� ����
		plot.getRangeAxis().setTickLabelFont(axisF);  // Y�� ���ݶ� ��Ʈ ����
		plot.getRangeAxis().setRange(((double)database_load.Min_val)-0.1, ((double)database_load.Max_val)+0.1);
		//plot.getRangeAxis().setRange(34.0, 39.0);
		    // ���õ� plot�� �������� chart ����
		JFreeChart chart = new JFreeChart(plot);
		//System.out.println(database_load.Min_val + ", " + database_load.Max_val);
		//chart.setTitle("Test MyChart"); // ��Ʈ Ÿ��Ʋ

		//Double min = (double)database_load.Min_val;
		//Double max = (double)database_load.Max_val;
		//chart.getXYPlot().getRangeAxis().setRange((double)34.0, (double)39.0);
		//	        TextTitle copyright = new TextTitle("JFreeChart WaferMapPlot", new Font("SansSerif", Font.PLAIN, 9));
		//	        copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		//	        chart.addSubtitle(copyright);  // ��Ʈ ���� Ÿ��Ʋ\
		//return chart;
	
		return chart;
	}
	

	@SuppressWarnings("null")
	public JPanel JP1()  throws IOException, SQLException, NullPointerException
	{
		/*
		Connection con = null;
		String dbURL =  "jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
		con = DriverManager.getConnection(dbURL,"root", "1234");

		//MyExcuteQuery("SHOW DATABASES;");
		java.sql.Statement st = null;
		ResultSet rs = null;
		st = con.createStatement();
		st.execute("USE newschema3;");
	*/
		
		//JPanel p1 = new JPanel(new GridBagLayout());
	//	GridBagLayout gridbag = new GridBagLayout();
		//GridBagConstraints c =new GridBagConstraints();
		//c.fill = GridBagConstraints.HORIZONTAL;
		
		p1.setLayout(null);
		jPanel01 jP01 = new jPanel01();

	
		// ComboBox 1
		//JComboBox<String> box1 = new JComboBox<String>();
		rs = st.executeQuery("SELECT DISTINCT id FROM tp");
		while(rs.next()){
			box1.addItem(rs.getString("id"));
		}
		
		//box1.addActionListener(box1);
		JLabel label1 = new JLabel("User Select : ");
		label1.setBounds(10, 0, 100, 20);
		p1.add(label1);
		box1.setBounds(90,0,50,20);
		p1.add(box1);
		
		//JButton b1 = new JButton();
		b1 = new JButton();
		b1.setText("Select");
		b1.setBounds(150, 0,70,20);
		b1.addActionListener(new MyActionListener()); 

		p1.add(b1);
		
		
		
		//ComboBox 2
		/*
		String buf_f = null;
		int num = 0;
		String temp_date[] = null;
		//JComboBox<String> box2 = new JComboBox<String>();
		rs = st.executeQuery("SELECT DISTINCT date FROM tp ORDER BY date");
		while(rs.next()){
			if(rs.getString("date").substring(0, 10).equals(buf_f))
			{
				continue;
			}else{
				buf_f = rs.getString("date").substring(0, 10);
				box2.addItem(buf_f);
			}
		}*/
		
		//box2.addActionListener(box2);
		JLabel label2 = new JLabel("Date : ");
		label2.setBounds(10, 50, 50, 20);
		p1.add(label2);
		box2.setBounds(45,50,100,20);
		p1.add(box2);
		

		
		//JButton b2 = new JButton();
		b2 = new JButton();
		b2.setText("Check");
		b2.setBounds(150, 50,70,20);
		b2.addActionListener(new MyActionListener()); 
		p1.add(b2);
		
		//Chart Visible
		ChartPanel CP = new ChartPanel(jP01.DrawMyChart(database_load.dload("1", "2016-10-03")));
		CP.setBounds(300, 10, 1610, 980);
		
		p1.add(CP);
		p1.setVisible(true);
		return p1; 
	}
	
    
	
		
	private class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            if (b.getText().equals("Select")){
            	box2.removeAllItems();
        		String buf_f = null;
        		String id = null;
        		
        		id = box1.getSelectedItem().toString();
        		try{
	        		rs = st.executeQuery("SELECT DISTINCT date FROM tp WHERE id="+ id +" ORDER BY date");
	        		while(rs.next()){
	        			if(rs.getString("date").substring(0, 10).equals(buf_f))
	        			{
	        				continue;
	        			}else{
	        				buf_f = rs.getString("date").substring(0, 10);
	        				box2.addItem(buf_f);
	        			}
	        		}
        		} catch (SQLException se) {
        			// TODO Auto-generated catch block
        			se.printStackTrace();
        		}
            }      	
            
            else if(b.getText().equals("Check")){
            	
            	Float[][] new_data = new Float[1][1000];
            	System.out.println("Redraw");
            
            	try {
            		
					new_data = database_load.dload(box1.getSelectedItem().toString(), box2.getSelectedItem().toString());
					System.out.println(box1.getSelectedItem().toString() + " / " + box2.getSelectedItem().toString());
			
					jPanel01 jP01 = new jPanel01();
					ChartPanel NCP  = new ChartPanel(jP01.DrawMyChart(new_data)); 
					JPanel panel11 = new gui().panel1;
					
							
					//p1.remove(CP);
					/*
					panel11.remove(CP);
					NCP.setBounds(300, 10, 1610, 980);
					panel11.add(NCP);
					panel11.invalidate();
					panel11.validate();
					panel11.repaint();*/
					
					
					
					
				} catch (SQLException e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassCastException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} /*catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/ catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        }
    }
}

