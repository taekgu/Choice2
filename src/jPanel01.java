import java.io.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
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
		final JFreeChart chart = new JFreeChart(plot);
		chart.setTitle("Test MyChart"); // ��Ʈ Ÿ��Ʋ
		//	        TextTitle copyright = new TextTitle("JFreeChart WaferMapPlot", new Font("SansSerif", Font.PLAIN, 9));
		//	        copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		//	        chart.addSubtitle(copyright);  // ��Ʈ ���� Ÿ��Ʋ\
		//return chart;
	
		return chart;
	
	}	
}

