import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTitleAnnotation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleAnchor;
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
	String gender;
	String birth;
	ValueMarker mark;
	//DateFormat time;	
	public jPanel01()
	{
		
		b1 = new JButton();
		b2 = new JButton();
		box1 = new JComboBox<String>();
		box2 = new JComboBox<String>();
		p1 = new JPanel(new GridBagLayout());
		CP = new ChartPanel(null);
		mark = null;
		
		//time = Date(HH:MM:SS);
		try
		{
			con = null;
			dbURL=  "jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			con = DriverManager.getConnection(dbURL,"root", "1234");
			st = con.createStatement();
			rs = null;
			st.execute("USE Thermosafer_INU;");
			//st.execute("USE testschema;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JFreeChart DrawMyChart(Float[][] floats, String User_num, String Date, Double mark_temp) throws IOException, ClassCastException, IllegalArgumentException
	{
		int SAMPLE_NUM = database_load.COUNT;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();                // bar chart 1
		try {
			rs = st.executeQuery("SELECT Distinct sex, birth from thermo_data WHERE id = "+ User_num +" AND LEFT(date,10) = '" + Date + "';" );
			rs.next();
			int num = rs.getInt(1);
			if (num == 0)
				gender = "Male";
			else
				gender = "Female";
			birth = rs.getString(2);
			System.out.println(gender + " / " + birth);

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < SAMPLE_NUM; i++){	
		    dataset.addValue(floats[0][i],"User : " + User_num + "  /  Date : " + Date+  "  /  Gender : " + gender + "  /  Birth : " + birth, i+1 + "시");
		}
		//chart2 = ChartFactory.createXYLineChart(null, "Time", "Temp", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		
		
		final LineAndShapeRenderer renderer = new LineAndShapeRenderer();
		   
		final ItemLabelPosition p_below = new ItemLabelPosition(
		             ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT
		             );
		Font f = new Font("Gulim", Font.PLAIN, 10);
		Font axisF = new Font("Gulim", Font.BOLD, 10);
		
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
		
		
		    final CategoryPlot plot = new CategoryPlot();
		   

		plot.setDataset(dataset);
		plot.setRenderer(renderer);
		
		plot.setOrientation(PlotOrientation.VERTICAL);             // �׷��� ǥ�� ����
		plot.setRangeGridlinesVisible(true);                       // X�� ���̵� ���� ǥ�ÿ���
		plot.setDomainGridlinesVisible(true);                      // Y�� ���̵� ���� ǥ�ÿ���
		 
	    plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
	
		plot.setDomainAxis(new CategoryAxis());              // X�� ���� ����
		plot.getDomainAxis().setTickLabelFont(axisF); // X�� ���ݶ� ��Ʈ ����
		
		plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);       // ī�װ� �� ��ġ ����
		plot.getDomainAxis().setTickLabelsVisible(false);
		        // Y�� ����
		plot.setRangeAxis(new NumberAxis());                 // Y�� ���� ����
		plot.getRangeAxis().setTickLabelFont(axisF);  // Y�� ���ݶ� ��Ʈ ����
		//plot.getRangeAxis().setRange(((double)database_load.Min_val)-0.1, ((double)database_load.Max_val)+0.1);
		plot.getRangeAxis().setRange(((double)33.0), ((double)42.0));
		JFreeChart chart = new JFreeChart(plot);

	
		return chart;
	}
	
	public JFreeChart DrawMyChart2(String User_num, String Date, Double mark_temp) throws IOException, SQLException
	{
		try {
			rs = st.executeQuery("SELECT Distinct sex, birth from thermo_data WHERE id = "+ User_num +" AND LEFT(date,10) = '" + Date + "';" );
			rs.next();
			int num = rs.getInt(1);
			if (num == 0)
				gender = "Male";
			else
				gender = "Female";
			birth = rs.getString(2);
			System.out.println(gender + " / " + birth);

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		Float[][] arrdata = database_load.dload(User_num, Date);
		XYSeries series = new XYSeries("User : " + User_num + "  /  Date : " + Date+  "  /  Gender : " + gender + "  /  Birth : " + birth);
		//XYSeries series2 = new XYSeries("High Temperature");
		XYSeriesCollection dataset = new XYSeriesCollection();
		//XYSeriesCollection dataset2 = new XYSeriesCollection();
		XYDataset xydataset = dataset;
		//XYDataset xydataset2 = dataset2;
		

		for(int i=0;i<288;i++){
			
			//series.add(database_load.Date_array[i], arrdata[0][i]);
			series.add(i*300000-32400000,arrdata[0][i]);
			//series2.add(i*300000-32400000,Mark_temp[i]);
			
        }

		dataset.addSeries(series);
		//dataset.addSeries(series2);
		
		 // create the chart...              
	    final JFreeChart chart = ChartFactory.createTimeSeriesChart(
	        null,      // chart title
	        "Time",                      // x axis label
	        "Temperature",                      // y axis label
	        xydataset,                  // data
	        true,                     // include legend
	        true,                     // tooltips
	        false                     // urls
	    );
	     
	    final XYPlot plot = chart.getXYPlot();
	    
	    LegendTitle legend = chart.getLegend();
	    legend.setBackgroundPaint(new Color(200, 200, 255, 100));
	    legend.setFrame(new BlockBorder(Color.white));
	    legend.setVisible(false);
	    XYTitleAnnotation ta = new XYTitleAnnotation(0.99, 0.99, legend,RectangleAnchor.TOP_RIGHT);
	    ta.setMaxWidth(0.48);
	    plot.addAnnotation(ta);
	    plot.setDataset(0,xydataset);
	    
	    plot.getRenderer().setSeriesPaint(0, Color.BLUE);	    
	    plot.getRenderer().setSeriesPaint(1, Color.RED);
	    plot.getRangeAxis().setRange(((double)33.0), ((double)42.0));
	    
	    ValueMarker marker = new ValueMarker(mark_temp);  // position is the value on the axis
	    marker.setPaint(Color.RED);
	    //marker.setLabel("here"); // see JavaDoc for labels, colors, strokes

	    plot.addRangeMarker(marker);
	    ValueAxis domain = plot.getDomainAxis();
	    domain.setVisible(false);
	    DateAxis axis = (DateAxis) plot.getDomainAxis();
	    domain.setAutoRange(true);       
	    // ValueAxis rangeAxis = plot.getRangeAxis();
	    //rangeAxis.setAutoRange(true);
	    
	    return chart;
	}
}

