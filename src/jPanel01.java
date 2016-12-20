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
import javax.swing.JComboBox;
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
	

	
	public jPanel01()
	{
		
	}
	
	public JFreeChart DrawMyChart(Float[][] floats) throws IOException
	{
		int SAMPLE_NUM = test_main.SAMPLE_NUM;
		    // ������ ����
		
		/////////////////////////////////////////////////////////////////
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();                // bar chart 1
		
		// ������ �Է� ( ��, ����, ī�װ� )
		// �׷��� 1       
		for (int i = 0; i < SAMPLE_NUM; i++){	
		    dataset.addValue(floats[0][i], "S1", i+1 + "��");
			//System.out.println(floats[0][i]);

		}
		
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
		   
		    // ���õ� plot�� �������� chart ����
		JFreeChart chart = new JFreeChart(plot);
		chart.setTitle("Test MyChart"); // ��Ʈ Ÿ��Ʋ
		//	        TextTitle copyright = new TextTitle("JFreeChart WaferMapPlot", new Font("SansSerif", Font.PLAIN, 9));
		//	        copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		//	        chart.addSubtitle(copyright);  // ��Ʈ ���� Ÿ��Ʋ\
		//return chart;
	
		return chart;
	}
	

	@SuppressWarnings("null")
	public JPanel JP1()  throws IOException, SQLException, NullPointerException
	{
		Connection con = null;
		String dbURL =  "jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
		con = DriverManager.getConnection(dbURL,"root", "1234");

		//MyExcuteQuery("SHOW DATABASES;");
		java.sql.Statement st = null;
		ResultSet rs = null;
		st = con.createStatement();
		st.execute("USE choice;");
	
		
		JPanel p1 = new JPanel(new GridBagLayout());
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c =new GridBagConstraints();
		//c.fill = GridBagConstraints.HORIZONTAL;
		
		p1.setLayout(gridbag);
		jPanel01 jP01 = new jPanel01();
		
	
		
		JComboBox<String> box1 = new JComboBox<String>();
		rs = st.executeQuery("select distinct id from temperature");
		while(rs.next()){
			box1.addItem(rs.getString("id"));
			//System.out.println("test : "+rs.getString("id"));
		}
		box1.addActionListener(box1);
		//box.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2"}));
		c.gridx = 0;
		c.gridy = 0;
		p1.add(box1,c);
		
		String buf_f = null;
		int num = 0;
		String temp_date[] = null;
		JComboBox<String> box2 = new JComboBox<String>();
		rs = st.executeQuery("use newschema");
		rs = st.executeQuery("select distinct date from tp");
		while(rs.next()){
			if(rs.getString("date").substring(0, 10).equals(buf_f))
			{
				continue;
			}else{
				buf_f = rs.getString("date").substring(0, 10);
				box2.addItem(buf_f);
				//System.out.println("test : "+buf_f);
			}
		}

		
		//Collections.sort((List<T>) box2);
		box2.addActionListener(box2);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		p1.add(box2,c);
		
		
		
		ChartPanel CP = new ChartPanel(jP01.DrawMyChart(database_load.dload("3")));
		//JFreeChart jfc = jP01.DrawMyChart(database_load.dload());
		
	
		
		c.gridwidth=3;
		c.gridheight=2;
		c.gridx=0;
		c.gridy=1;
		c.fill = GridBagConstraints.HORIZONTAL;
		p1.add(CP,c);
		
		return p1; 
	}
}

