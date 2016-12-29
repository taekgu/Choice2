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
			st.execute("USE newschema5;");
			//st.execute("USE testschema;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JFreeChart DrawMyChart(Float[][] floats, String User_num, String Date) throws IOException, ClassCastException, IllegalArgumentException
	{
		int SAMPLE_NUM = database_load.COUNT;

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();                // bar chart 1

		
		for (int i = 0; i < SAMPLE_NUM; i++){	
		    dataset.addValue(floats[0][i],"User " + User_num + " / " + Date, i+1 + "시");

		}
		
		final LineAndShapeRenderer renderer = new LineAndShapeRenderer();
		   
		final ItemLabelPosition p_below = new ItemLabelPosition(
		             ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT
		             );
		Font f = new Font("Gulim", Font.PLAIN, 10);
		Font axisF = new Font("Gulim", Font.PLAIN, 10);
		
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
		plot.getRangeAxis().setRange(((double)database_load.Min_val)-0.1, ((double)database_load.Max_val)+0.1);
		
		JFreeChart chart = new JFreeChart(plot);

	
		return chart;
	}
}

