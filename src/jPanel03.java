package practice;
import java.io.*;
import java.text.NumberFormat;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTitleAnnotation;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleAnchor;

public class jPanel02 implements ActionListener {

	public jPanel02()
	{
		
	}
	public int f_count=0 ;
	public JFreeChart DrawMyChart(XYDataset xydtset) throws IOException
	{
		
		 // create the chart...              
	    final JFreeChart chart = ChartFactory.createTimeSeriesChart(
	        "average temperature data",      // chart title
	        "Time",                      // x axis label
	        "Temperature",                      // y axis label
	        xydtset,                  // data
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
	    
	    ValueAxis domain = plot.getDomainAxis();
	    domain.setAutoRange(true);       
	    final ValueAxis rangeAxis = plot.getRangeAxis();
	    rangeAxis.setAutoRange(true);

	    return chart;
	
	}
	
	public JFreeChart DrawAllPiChart(double fever_per) throws IOException
	{
		DefaultPieDataset data = new DefaultPieDataset();
		
		double fever = fever_per * 100;
		double not_fever_per = 100 - fever;
		
		data.setValue("Fever"+Double.toString(Double.parseDouble(String.format("%.2f",fever))), fever);
		data.setValue("Not Fever"+Double.toString(Double.parseDouble(String.format("%.2f",not_fever_per))), not_fever_per);
		
		JFreeChart chart = ChartFactory.createPieChart("Fever Percentage",data,true,true,false);
		
		
		return chart;
	}
	
	public JFreeChart DrawGenderPiChart(double fever_per) throws IOException
	{
		DefaultPieDataset data = new DefaultPieDataset();
		
		double fever = fever_per * 100;
		double not_fever_per = 100 - fever;
		
		data.setValue("Fever"+Double.toString(Double.parseDouble(String.format("%.2f",fever))), fever);
		data.setValue("Not Fever"+Double.toString(Double.parseDouble(String.format("%.2f",not_fever_per))), not_fever_per);
		
		JFreeChart chart = null;
		
		if(f_count%2 == 0)
			 chart = ChartFactory.createPieChart("Male's Fever Percentage",data,true,true,false);
		else if(f_count%2 == 1)
			chart = ChartFactory.createPieChart("Female's Fever Percentage",data,true,true,false);
		
		f_count++;
		return chart;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}	
}

