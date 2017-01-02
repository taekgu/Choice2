package practice;
import java.io.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTitleAnnotation;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleAnchor;
//1440 900
public class jPanel03 implements ActionListener {

	public jPanel03()
	{
		
	}
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
	    domain.setRange(35, 45);
	    final ValueAxis rangeAxis = plot.getRangeAxis();
	    rangeAxis.setRangeWithMargins(35, 45);
	    
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
	
	public JFreeChart DrawGenderPiChart(double fever_per,int gd) throws IOException
	{
		DefaultPieDataset data = new DefaultPieDataset();
		
		double fever = fever_per * 100;
		double not_fever_per = 100 - fever;
		
		data.setValue("Fever"+Double.toString(Double.parseDouble(String.format("%.2f",fever))), fever);
		data.setValue("Not Fever"+Double.toString(Double.parseDouble(String.format("%.2f",not_fever_per))), not_fever_per);
		
		JFreeChart chart = null;
		
		if(gd == 0)
			 chart = ChartFactory.createPieChart("Male's Fever Percentage",data,true,true,false);
		else if(gd == 1)
			chart = ChartFactory.createPieChart("Female's Fever Percentage",data,true,true,false);
		
		return chart;
	}
	
	public JFreeChart DrawAgePiChart(double fever_per,int gd) throws IOException
	{
		DefaultPieDataset data = new DefaultPieDataset();
		
		double fever = fever_per * 100;
		double not_fever_per = 100 - fever;
		
		data.setValue("Fever"+Double.toString(Double.parseDouble(String.format("%.2f",fever))), fever);
		data.setValue("Not Fever"+Double.toString(Double.parseDouble(String.format("%.2f",not_fever_per))), not_fever_per);
		
		JFreeChart chart = null;
		
		if(gd == 0)
			 chart = ChartFactory.createPieChart("0's Fever Percentage",data,true,true,false);
		else if(gd == 1)
			chart = ChartFactory.createPieChart("10's Fever Percentage",data,true,true,false);
		else if(gd == 2)
			chart = ChartFactory.createPieChart("20's Fever Percentage",data,true,true,false);
		else if(gd == 3)
			chart = ChartFactory.createPieChart("30's Fever Percentage",data,true,true,false);
		else if(gd == 4)
			chart = ChartFactory.createPieChart("40's Fever Percentage",data,true,true,false);
		else if(gd == 5)
			chart = ChartFactory.createPieChart("50's Fever Percentage",data,true,true,false);
		else if(gd == 6)
			chart = ChartFactory.createPieChart("60's Fever Percentage",data,true,true,false);
		else if(gd == 7)
			chart = ChartFactory.createPieChart("70's Fever Percentage",data,true,true,false);
		
		
		return chart;
	}
	
	public JFreeChart DrawSeasonPiChart(double fever_per,int gd) throws IOException
	{
		DefaultPieDataset data = new DefaultPieDataset();
		
		double fever = fever_per * 100;
		double not_fever_per = 100 - fever;
		
		data.setValue("Fever"+Double.toString(Double.parseDouble(String.format("%.2f",fever))), fever);
		data.setValue("Not Fever"+Double.toString(Double.parseDouble(String.format("%.2f",not_fever_per))), not_fever_per);
		
		JFreeChart chart = null;
		
		if(gd == 0)
			 chart = ChartFactory.createPieChart("Spring's Fever Percentage",data,true,true,false);
		else if(gd == 1)
			chart = ChartFactory.createPieChart("Summer's Fever Percentage",data,true,true,false);
		else if(gd == 2)
			chart = ChartFactory.createPieChart("Fall's Fever Percentage",data,true,true,false);
		else if(gd == 3)
			chart = ChartFactory.createPieChart("Winter's Fever Percentage",data,true,true,false);
		return chart;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}	
}

