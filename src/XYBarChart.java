/* --------------------
 * XYBarChartDemo4.java
 * --------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 */



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demonstration of the {@link XYBarDataset} wrapper class.
 */
public class XYBarChart extends ApplicationFrame {
	Connection con;
	java.sql.Statement st;
	ResultSet rs;
	String SET;
	String New;
	Double SET_temp;

	Double[] count;
	int year = Calendar.getInstance().get(Calendar.YEAR);
	int temp_year = 0;
    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYBarChart(String title) {
        super(title);
        count = new Double[10];
        Arrays.fill(count, 0.0);
        
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC","root", "1234");
			st = con.createStatement();
			rs = st.executeQuery("use Thermosafer_INU");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        JPanel chartPanel = createXYBarChartPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(1280, 830));
        chartPanel.setBounds(0, 0, 1280, 830);
        setContentPane(chartPanel);
    }
    public XYBarChart(String title, int Upper_age, int Lower_age, int gender, String Sel_date) {
        super(title);
        count = new Double[10];
        Arrays.fill(count, 0.0);
        
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC","root", "1234");
			st = con.createStatement();
			rs = st.executeQuery("use Thermosafer_INU");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        JPanel chartPanel = createXYBarChartPanel(Upper_age, Lower_age, gender, Sel_date);
        chartPanel.setPreferredSize(new java.awt.Dimension(1280, 830));
        chartPanel.setBounds(0, 0, 1280, 830);
        setContentPane(chartPanel);
    }

    private JFreeChart createChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYBarChart(
            null,
            "Temperature(˚C)",
            false,
            "The Number of People(명)",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            false,
            false
        );

        // then customise it a little...
        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        domainAxis.setTickUnit(new NumberTickUnit(0.5));
        
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        return chart;
    }

    /**
     * Creates a sample dataset.  We create the dataset using a standard
     * {@link XYSeriesCollection}, then wrap it in an {@link XYBarDataset}.
     *
     * @return A sample dataset.
     */
    private IntervalXYDataset createDataset() {
    	Arrays.fill(count, 0.0);
    	try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT date, id, birth, temp, sex, gps_lat, gps_har from thermo_data ORDER BY id, date DESC;");
			//rs.next();
			SET = null;
			while(rs.next()){
				New = rs.getString(2);
				if (New.equals(SET)){
					continue;
				}
				SET = rs.getString(2);
				SET_temp = rs.getDouble("temp");
				if (SET_temp < 36.5)
					count[0]+=1.0;
				else if((SET_temp>=36.5) && (SET_temp <37.0))
					count[1]+=1.0;
				else if((SET_temp>=37.0) && (SET_temp <37.5))
					count[2]+=1.0;
				else if((SET_temp>=37.5) && (SET_temp <38.0))
					count[3]+=1.0;
				else if((SET_temp>=38.0) && (SET_temp <38.5))
					count[4]+=1.0;
				else if((SET_temp>=38.5) && (SET_temp <39.0))
					count[5]+=1.0;
				else if((SET_temp>=39.0) && (SET_temp <39.5))
					count[6]+=1.0;
				else if((SET_temp>=39.5) && (SET_temp <40.0))
					count[7]+=1.0;
				else if(SET_temp>=40)
					count[8]+=1.0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    	
    	
        XYSeries series = new XYSeries("Series 1");
        series.add(36.0, count[0]);
        series.add(36.5, count[1]);
        series.add(37.0, count[2]);
        series.add(37.5, count[3]);
        series.add(38.0, count[4]);
        series.add(38.5, count[5]);
        series.add(39.0, count[6]);
        series.add(39.5, count[7]);
        series.add(40.0, count[8]);
        
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series);
        return new XYBarDataset(collection, 0.4);
    }
    private IntervalXYDataset createDataset(int Upper_age, int Lower_age, int gender, String Sel_date) {
    	Arrays.fill(count, 0.0);
    	try {
			st = con.createStatement();
			if (gender  == 1){
				rs = st.executeQuery("SELECT date, id, birth, temp, sex, gps_lat, gps_har from thermo_data WHERE( "+year+" - (LEFT(birth,4)) >= "+Lower_age+" AND "+year+" - LEFT(birth,4) < "+Upper_age+" AND LEFT(date,10) = '" + Sel_date + "') ORDER BY id, date DESC;");
			}
			else if (gender == 2){ // male
				rs = st.executeQuery("SELECT date, id, birth, temp, sex, gps_lat, gps_har from thermo_data WHERE( sex = 0 AND "+year+" - (LEFT(birth,4)) >= "+Lower_age+" AND "+year+" - LEFT(birth,4) < "+Upper_age+" AND LEFT(date,10) = '" + Sel_date + "') ORDER BY id, date DESC;");
			}
			else if (gender == 3){ //female
				rs = st.executeQuery("SELECT date, id, birth, temp, sex, gps_lat, gps_har from thermo_data WHERE( sex = 1 AND "+year+" - (LEFT(birth,4)) >= "+Lower_age+" AND "+year+" - LEFT(birth,4) < "+Upper_age+" AND LEFT(date,10) = '" + Sel_date + "') ORDER BY id, date DESC;");
			}
			
			
			
			
			
			//rs.next();
			SET = null;
			while(rs.next()){
				New = rs.getString(2);
				if (New.equals(SET)){
					continue;
				}
				SET = rs.getString(2);
				SET_temp = rs.getDouble("temp");
				if (SET_temp < 36.5)
					count[0]+=1.0;
				else if((SET_temp>=36.5) && (SET_temp <37.0))
					count[1]+=1.0;
				else if((SET_temp>=37.0) && (SET_temp <37.5))
					count[2]+=1.0;
				else if((SET_temp>=37.5) && (SET_temp <38.0))
					count[3]+=1.0;
				else if((SET_temp>=38.0) && (SET_temp <38.5))
					count[4]+=1.0;
				else if((SET_temp>=38.5) && (SET_temp <39.0))
					count[5]+=1.0;
				else if((SET_temp>=39.0) && (SET_temp <39.5))
					count[6]+=1.0;
				else if((SET_temp>=39.5) && (SET_temp <40.0))
					count[7]+=1.0;
				else if(SET_temp>=40)
					count[8]+=1.0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        XYSeries series = new XYSeries("Series 1");
        series.add(36.0, count[0]);
        series.add(36.5, count[1]);
        series.add(37.0, count[2]);
        series.add(37.5, count[3]);
        series.add(38.0, count[4]);
        series.add(38.5, count[5]);
        series.add(39.0, count[6]);
        series.add(39.5, count[7]);
        series.add(40.0, count[8]);
        
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series);
        return new XYBarDataset(collection, 0.4);
    }

    /**
     * Creates a panel for the demo.
     *
     * @return A panel.
     */
    public JPanel createXYBarChartPanel() {
    	JPanel CP = new ChartPanel(createChart(createDataset()));
    	CP.setBounds(0, 0, 1280, 830);
    	return CP;
        
    }
    public JPanel createXYBarChartPanel(int Upper_age, int Lower_age, int gender, String Sel_date) {
    	JPanel CP = new ChartPanel(createChart(createDataset(Upper_age, Lower_age, gender, Sel_date)));
    	CP.setBounds(0, 0, 1280, 830);
    	return CP;
        
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    /*
    public static void nomain(String[] args) {
        XYBarChart demo = new XYBarChart("XY Bar Chart Demo 4");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }*/

}