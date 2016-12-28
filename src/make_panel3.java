package practice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class make_panel3 implements ActionListener {
	static private JPanel panel = new JPanel(null);

	static private int Temp_G_X_Size = 870, Temp_G_Y_Size = 700;

	// 사용하는 변수 선언 & 초기화
	static jPanel03 jP3 = new jPanel03(); // 파넬 전체
	static ChartPanel CP3; // 차트 그림
	static ChartPanel PieChart; // 파이차트

	final static JButton button1 = new JButton("Condition1");
	final static JButton button2 = new JButton("Condition2");
	final static JButton button3 = new JButton("Show Chart");
	// gender에서 사용하는 변수
	static Float[][] gdarrdata;
	static XYSeries gdseries1;
	static XYSeries gdseries2;
	static XYSeriesCollection gddataset;
	static XYDataset gdxydataset;
	static ChartPanel m_PieChart;
	static ChartPanel f_PieChart;
	static double[][] gdfever_per;

	// age
	static Float[][] agearrdata;
	static XYSeries ageseries0;
	static XYSeries ageseries1;
	static XYSeries ageseries2;
	static XYSeries ageseries3;
	static XYSeries ageseries4;
	static XYSeries ageseries5;
	static XYSeries ageseries6;
	static XYSeries ageseries7;
	static XYSeriesCollection agedataset;
	static XYDataset agexydataset;
	static ChartPanel agePieChart0;
	static ChartPanel agePieChart1;
	static ChartPanel agePieChart2;
	static ChartPanel agePieChart3;
	static ChartPanel agePieChart4;
	static ChartPanel agePieChart5;
	static ChartPanel agePieChart6;
	static ChartPanel agePieChart7;
	static double[][] agefever_per;

	//season
	static Float[][] ssarrdata;
	static double[][] ssfever_per;
	static XYSeries ssseries0;
	static XYSeries ssseries1;
	static XYSeries ssseries2;
	static XYSeries ssseries3;
	static XYSeriesCollection ssdataset;
	static XYDataset ssxydataset;
	static ChartPanel ssPieChart0;
	static ChartPanel ssPieChart1;
	static ChartPanel ssPieChart2;
	static ChartPanel ssPieChart3;
	
	// list에 들어갈 내용들
	static String[] condition = { "All", "Gender", "Age", "Season" };
	static String[] age = { "0~9", "10~19", "20~29", "30~39", "40~49", "50~59", "60~69", "70~79" };
	static String[] gender = { "Male", "Female" };
	static String[] season = { "Spring", "Summer", "Fall", "Winter" };
	// combo BOX
	static JComboBox<String> box1 = new JComboBox<String>(condition);
	static JComboBox<String> box2 = new JComboBox<String>();

	static boolean cond1_flag = false;
	static boolean cond2_flag = false;

	public static JPanel make(JFrame JF) {

		// ---------------------------전체 ------------------------------------//

		button1.addActionListener(new ActionListener() // Condition1
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (cond1_flag) // true -> comboBox x
				{
					cond1_flag = false;
					box1.setVisible(cond1_flag); // 안보임
					// box1.removeAllItems();
				} else // false -> comboBox o
				{
					cond1_flag = true;
					box1.setVisible(cond1_flag); // 보임
				}
			}
		});

		button2.addActionListener(new ActionListener() // Condition1
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (cond2_flag) // true -> comboBox x
				{
					cond2_flag = false;
					box2.setVisible(cond2_flag); // 안보임
				} else // false -> comboBox o
				{
					set_list(box1.getSelectedItem().toString());
					cond2_flag = true;
					box2.setVisible(cond2_flag); // 보임
				}
			}
		});

		button3.addActionListener(new ActionListener() // Condition1
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CP3 = new ChartPanel(null);
				PieChart = new ChartPanel(null);
				panel.removeAll();

				Draw_Chart(box1.getSelectedItem().toString(), box2.getSelectedItem().toString());
			}
		});

		button1.setBounds(0, 0, 100, 50);
		box1.setBounds(0, 50, 100, 50);
		box1.setVisible(cond1_flag);

		button2.setBounds(0, 100, 100, 50);
		box2.setBounds(0, 150, 100, 50);
		box2.setVisible(cond2_flag);

		button3.setBounds(0, 200, 100, 50);

		panel.add(button1);
		panel.add(button2);
		panel.add(button3);

		panel.add(box1);
		panel.add(box2);

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	public static void Draw_Chart(String b1, String b2) {
		if (b1.equals(condition[0])) // all
		{
			// ---------------그래프 그리기 --------------------------//
			try {
				Float[][] arrdata = database_load_avg.dload_avg();
				XYSeries series = new XYSeries("total");
				XYSeriesCollection dataset = new XYSeriesCollection();
				XYDataset xydataset = dataset;

				for (int i = 0; i < 35; i++) {
					series.add(i * 600000 - 26640000, arrdata[0][i]);
				}

				dataset.addSeries(series);

				CP3 = new ChartPanel(jP3.DrawMyChart(xydataset));

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// --------------------파이그래프 그리기 ----------------------//
			// 감기걸린 사람 퍼센테이지 계산//
			try {
				double fever_per = database_load_avg.dload_avg_per(); // 감기걸린사람
																		// %
				try {
					PieChart = new ChartPanel(jP3.DrawAllPiChart(fever_per));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			PieChart.setBounds(970, 350, 400, 350);
			CP3.setBounds(100, 0, Temp_G_X_Size, Temp_G_Y_Size);

			panel.add(button1);
			panel.add(button2);
			panel.add(button3);

			panel.add(box1);
			panel.add(box2);

			panel.add(PieChart);
			panel.add(CP3);

			panel.repaint();
		}

		// -------------------------------Gender-----------------------------//
		else if (b1.equals(condition[1]))// gender
		{
			try {
				gdarrdata = database_load_gender.dload_avg();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			// ---- Pie Chart ----- //
			try {
				gdfever_per = database_load_gender.dload_avg_per(); // fever

				try {
					m_PieChart = new ChartPanel(jP3.DrawGenderPiChart(gdfever_per[0][0], 0));// %
					f_PieChart = new ChartPanel(jP3.DrawGenderPiChart(gdfever_per[1][0], 1));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// -----------------------male----------------------------------//
			if (b2.equals(gender[0]))// male
			{
				gdseries1 = new XYSeries("male");
				gddataset = new XYSeriesCollection();
				gdxydataset = gddataset;

				for (int i = 0; i < 35; i++) {
					gdseries1.add(i * 600000 - 26640000, gdarrdata[0][i]);
				}

				gddataset.addSeries(gdseries1);

				try {
					CP3 = new ChartPanel(jP3.DrawMyChart(gdxydataset));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				m_PieChart.setBounds(970, 350, 400, 350);
				CP3.setBounds(100, 0, Temp_G_X_Size, Temp_G_Y_Size);

				panel.add(button1);
				panel.add(button2);
				panel.add(button3);

				panel.add(box1);
				panel.add(box2);

				panel.add(m_PieChart);
				panel.add(CP3);
				panel.repaint();
			}

			// ----------------------------------female-------------------------------------//
			else if (b2.equals(gender[1]))// female
			{
				gdseries2 = new XYSeries("female");
				gddataset = new XYSeriesCollection();
				gdxydataset = gddataset;

				for (int i = 0; i < 35; i++) {
					gdseries2.add(i * 600000 - 26640000, gdarrdata[1][i]);
				}

				gddataset.addSeries(gdseries2);

				try {
					CP3 = new ChartPanel(jP3.DrawMyChart(gdxydataset));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				f_PieChart.setBounds(970, 350, 400, 350);
				CP3.setBounds(100, 0, Temp_G_X_Size, Temp_G_Y_Size);

				panel.add(button1);
				panel.add(button2);
				panel.add(button3);

				panel.add(box1);
				panel.add(box2);

				panel.add(f_PieChart);
				panel.add(CP3);
				panel.repaint();
			}
		}

		
		// ----------------------------------------------------------------------------//
		// |                                age                                       |//
		// ----------------------------------------------------------------------------//
		
		else if (b1.equals(condition[2])) // age
		{
			try {
				agearrdata = database_load_age.dload_avg();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			// ---- Pie Chart ----- //
			try {
				agefever_per = database_load_age.dload_avg_per(); // fever

				try {
					agePieChart0 = new ChartPanel(jP3.DrawAgePiChart(agefever_per[0][0], 0));// %
					agePieChart1 = new ChartPanel(jP3.DrawAgePiChart(agefever_per[1][0], 1));
					agePieChart2 = new ChartPanel(jP3.DrawAgePiChart(agefever_per[2][0], 2));
					agePieChart3 = new ChartPanel(jP3.DrawAgePiChart(agefever_per[3][0], 3));
					agePieChart4 = new ChartPanel(jP3.DrawAgePiChart(agefever_per[4][0], 4));
					agePieChart5 = new ChartPanel(jP3.DrawAgePiChart(agefever_per[5][0], 5));
					agePieChart6 = new ChartPanel(jP3.DrawAgePiChart(agefever_per[6][0], 6));
					agePieChart7 = new ChartPanel(jP3.DrawAgePiChart(agefever_per[7][0], 7));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// { "0~9", "10~19", "20~29", "30~39", "40~49", "50~59", "60~69","70~79" };
			if (b2.equals(age[0])) 
			{
				ageseries0 = new XYSeries("0-9");
				agedataset = new XYSeriesCollection();
				agexydataset = agedataset;

				for (int i = 0; i < 35; i++) {
					ageseries0.add(i * 600000 - 26640000, agearrdata[0][i]);
				}

				agedataset.addSeries(ageseries0);

				try {
					CP3 = new ChartPanel(jP3.DrawMyChart(agedataset));
				} catch (IOException e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}

				agePieChart0.setBounds(970, 350, 400, 350);
				CP3.setBounds(100, 0, Temp_G_X_Size, Temp_G_Y_Size);

				panel.add(button1);
				panel.add(button2);
				panel.add(button3);

				panel.add(box1);
				panel.add(box2);

				panel.add(agePieChart0);
				panel.add(CP3);
				panel.repaint();
			} 
			
			else if (b2.equals(age[1])) 
			{
				ageseries1 = new XYSeries("10-19");
				agedataset = new XYSeriesCollection();
				agexydataset = agedataset;

				for (int i = 0; i < 35; i++) {
					ageseries1.add(i * 600000 - 26640000, agearrdata[1][i]);
				}

				agedataset.addSeries(ageseries1);

				try {
					CP3 = new ChartPanel(jP3.DrawMyChart(agedataset));
				} catch (IOException e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}

				agePieChart1.setBounds(970, 350, 400, 350);
				CP3.setBounds(100, 0, Temp_G_X_Size, Temp_G_Y_Size);

				panel.add(button1);
				panel.add(button2);
				panel.add(button3);

				panel.add(box1);
				panel.add(box2);

				panel.add(agePieChart1);
				panel.add(CP3);
				panel.repaint();
					
			} 
			else if (b2.equals(age[2]))
			{
				ageseries2 = new XYSeries("20-29");
				agedataset = new XYSeriesCollection();
				agexydataset = agedataset;

				for (int i = 0; i < 35; i++) {
					ageseries2.add(i * 600000 - 26640000, agearrdata[2][i]);
				}

				agedataset.addSeries(ageseries2);

				try {
					CP3 = new ChartPanel(jP3.DrawMyChart(agedataset));
				} catch (IOException e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}

				agePieChart2.setBounds(970, 350, 400, 350);
				CP3.setBounds(100, 0, Temp_G_X_Size, Temp_G_Y_Size);

				panel.add(button1);
				panel.add(button2);
				panel.add(button3);

				panel.add(box1);
				panel.add(box2);

				panel.add(agePieChart2);
				panel.add(CP3);
				panel.repaint();
			} 
			
			else if (b2.equals(age[3])) 
			{
				ageseries3 = new XYSeries("30-39");
				agedataset = new XYSeriesCollection();
				agexydataset = agedataset;

				for (int i = 0; i < 35; i++) {
					ageseries3.add(i * 600000 - 26640000, agearrdata[3][i]);
				}

				agedataset.addSeries(ageseries3);

				try {
					CP3 = new ChartPanel(jP3.DrawMyChart(agedataset));
				} catch (IOException e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}

				agePieChart3.setBounds(970, 350, 400, 350);
				CP3.setBounds(100, 0, Temp_G_X_Size, Temp_G_Y_Size);

				panel.add(button1);
				panel.add(button2);
				panel.add(button3);

				panel.add(box1);
				panel.add(box2);

				panel.add(agePieChart3);
				panel.add(CP3);
				panel.repaint();
			} 
			else if (b2.equals(age[4])) 
			{
				ageseries4 = new XYSeries("40-49");
				agedataset = new XYSeriesCollection();
				agexydataset = agedataset;

				for (int i = 0; i < 35; i++) {
					ageseries4.add(i * 600000 - 26640000, agearrdata[4][i]);
				}

				agedataset.addSeries(ageseries4);

				try {
					CP3 = new ChartPanel(jP3.DrawMyChart(agedataset));
				} catch (IOException e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}

				agePieChart4.setBounds(970, 350, 400, 350);
				CP3.setBounds(100, 0, Temp_G_X_Size, Temp_G_Y_Size);

				panel.add(button1);
				panel.add(button2);
				panel.add(button3);

				panel.add(box1);
				panel.add(box2);

				panel.add(agePieChart4);
				panel.add(CP3);
				panel.repaint();
				
			} 
			else if (b2.equals(age[5])) 
			{
				ageseries5 = new XYSeries("50-59");
				agedataset = new XYSeriesCollection();
				agexydataset = agedataset;

				for (int i = 0; i < 35; i++) {
					ageseries5.add(i * 600000 - 26640000, agearrdata[5][i]);
				}

				agedataset.addSeries(ageseries5);

				try {
					CP3 = new ChartPanel(jP3.DrawMyChart(agedataset));
				} catch (IOException e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}

				agePieChart5.setBounds(970, 350, 400, 350);
				CP3.setBounds(100, 0, Temp_G_X_Size, Temp_G_Y_Size);

				panel.add(button1);
				panel.add(button2);
				panel.add(button3);

				panel.add(box1);
				panel.add(box2);

				panel.add(agePieChart5);
				panel.add(CP3);
				panel.repaint();
				
			} 
			else if (b2.equals(age[6]))
			{
				ageseries6 = new XYSeries("60-69");
				agedataset = new XYSeriesCollection();
				agexydataset = agedataset;

				for (int i = 0; i < 35; i++) {
					ageseries6.add(i * 600000 - 26640000, agearrdata[6][i]);
				}

				agedataset.addSeries(ageseries6);

				try {
					CP3 = new ChartPanel(jP3.DrawMyChart(agedataset));
				} catch (IOException e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}

				agePieChart6.setBounds(970, 350, 400, 350);
				CP3.setBounds(100, 0, Temp_G_X_Size, Temp_G_Y_Size);

				panel.add(button1);
				panel.add(button2);
				panel.add(button3);

				panel.add(box1);
				panel.add(box2);

				panel.add(agePieChart6);
				panel.add(CP3);
				panel.repaint();
				
			} 
			else if (b2.equals(age[7])) 
			{
				ageseries7 = new XYSeries("70-79");
				agedataset = new XYSeriesCollection();
				agexydataset = agedataset;

				for (int i = 0; i < 35; i++) {
					ageseries7.add(i * 600000 - 26640000, agearrdata[7][i]);
				}

				agedataset.addSeries(ageseries7);

				try {
					CP3 = new ChartPanel(jP3.DrawMyChart(agedataset));
				} catch (IOException e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}

				agePieChart7.setBounds(970, 350, 400, 350);
				CP3.setBounds(100, 0, Temp_G_X_Size, Temp_G_Y_Size);

				panel.add(button1);
				panel.add(button2);
				panel.add(button3);

				panel.add(box1);
				panel.add(box2);

				panel.add(agePieChart7);
				panel.add(CP3);
				panel.repaint();
			}
		}

		// ----------------------------------------------------------------------------//
		// |                           Season                                         |//
		// ----------------------------------------------------------------------------//
		
		else if(b1.equals(condition[3])) // season
		{
			try {
				ssarrdata = database_load_season.dload_avg();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			// ---- Pie Chart ----- //
			try {
				ssfever_per = database_load_season.dload_avg_per(); // fever

				try {
					ssPieChart0 = new ChartPanel(jP3.DrawSeasonPiChart(ssfever_per[0][0], 0));// %
					ssPieChart1 = new ChartPanel(jP3.DrawSeasonPiChart(ssfever_per[1][0], 1));
					ssPieChart2 = new ChartPanel(jP3.DrawSeasonPiChart(ssfever_per[2][0], 2));
					ssPieChart3 = new ChartPanel(jP3.DrawSeasonPiChart(ssfever_per[3][0], 3));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(b2.equals(season[0]))
			{
				ssseries0 = new XYSeries("Spring");
				ssdataset = new XYSeriesCollection();
				ssxydataset = ssdataset;

				for (int i = 0; i < 35; i++) {
					ssseries0.add(i * 600000 - 26640000, ssarrdata[0][i]);
				}

				ssdataset.addSeries(ssseries0);

				try {
					CP3 = new ChartPanel(jP3.DrawMyChart(ssdataset));
				} catch (IOException e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}

				ssPieChart0.setBounds(970, 350, 400, 350);
				CP3.setBounds(100, 0, Temp_G_X_Size, Temp_G_Y_Size);

				panel.add(button1);
				panel.add(button2);
				panel.add(button3);

				panel.add(box1);
				panel.add(box2);

				panel.add(ssPieChart0);
				panel.add(CP3);
				panel.repaint();
			}
			else if(b2.equals(season[1]))
			{
				ssseries1 = new XYSeries("Summer");
				ssdataset = new XYSeriesCollection();
				ssxydataset = ssdataset;

				for (int i = 0; i < 35; i++) {
					ssseries1.add(i * 600000 - 26640000, ssarrdata[1][i]);
				}

				ssdataset.addSeries(ssseries1);

				try {
					CP3 = new ChartPanel(jP3.DrawMyChart(ssdataset));
				} catch (IOException e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}

				ssPieChart1.setBounds(970, 350, 400, 350);
				CP3.setBounds(100, 0, Temp_G_X_Size, Temp_G_Y_Size);

				panel.add(button1);
				panel.add(button2);
				panel.add(button3);

				panel.add(box1);
				panel.add(box2);

				panel.add(ssPieChart1);
				panel.add(CP3);
				panel.repaint();
			}
			else if(b2.equals(season[2]))
			{
				ssseries2 = new XYSeries("Fall");
				ssdataset = new XYSeriesCollection();
				ssxydataset = ssdataset;

				for (int i = 0; i < 35; i++) {
					ssseries2.add(i * 600000 - 26640000, ssarrdata[1][i]);
				}

				ssdataset.addSeries(ssseries2);

				try {
					CP3 = new ChartPanel(jP3.DrawMyChart(ssdataset));
				} catch (IOException e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}

				ssPieChart2.setBounds(970, 350, 400, 350);
				CP3.setBounds(100, 0, Temp_G_X_Size, Temp_G_Y_Size);

				panel.add(button1);
				panel.add(button2);
				panel.add(button3);

				panel.add(box1);
				panel.add(box2);

				panel.add(ssPieChart2);
				panel.add(CP3);
				panel.repaint();
			}
			else if(b2.equals(season[3]))
			{
				ssseries3 = new XYSeries("Winter");
				ssdataset = new XYSeriesCollection();
				ssxydataset = ssdataset;

				for (int i = 0; i < 35; i++) {
					ssseries3.add(i * 600000 - 26640000, ssarrdata[1][i]);
				}

				ssdataset.addSeries(ssseries3);

				try {
					CP3 = new ChartPanel(jP3.DrawMyChart(ssdataset));
				} catch (IOException e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}

				ssPieChart3.setBounds(970, 350, 400, 350);
				CP3.setBounds(100, 0, Temp_G_X_Size, Temp_G_Y_Size);

				panel.add(button1);
				panel.add(button2);
				panel.add(button3);

				panel.add(box1);
				panel.add(box2);

				panel.add(ssPieChart3);
				panel.add(CP3);
				panel.repaint();
			}
			
		}
	}
	public static void set_list(String S) {
		box2.removeAllItems();
		if (S.equals(condition[0])) // all
		{
			box2.addItem("Click the last button");
		} else if (S.equals(condition[1]))// gender
		{
			// box2 = new JComboBox<String>(gender);
			for (int i = 0; i < gender.length; i++) {
				box2.addItem(gender[i]);
			}
		} else if (S.equals(condition[2])) // age
		{
			for (int i = 0; i < age.length; i++) {
				box2.addItem(age[i]);
			}
		} else if (S.equals(condition[3])) //
		{
			for (int i = 0; i < season.length; i++) {
				box2.addItem(season[i]);
			}
		}

	}

}
