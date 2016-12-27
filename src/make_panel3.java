package practice;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
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

	// list에 들어갈 내용들
	static String[] condition = { "All" , "Gender", "Age" , "Season"};
	static String[] age = { "0~9", "10~19", "20~29", "30~39", "40~49", "50~59", "60~69", "70~79" };
	static String[] gender = { "Male", "Female" };
	static String[] season = { "Spring", "Summer", "Fall", "Winter" };
	// combo BOX
	static JComboBox<String> box1 = new JComboBox<String>(condition);
	static JComboBox<String> box2 = new JComboBox<String>();

	private static JFrame jfjf;
	static boolean cond1_flag = false;
	static boolean cond2_flag = false;

	public static JPanel make(JFrame JF) {
		jfjf = JF;

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
					//box1.removeAllItems();
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
				}
				else // false -> comboBox o
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
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				CP3 = new ChartPanel(null);
				PieChart = new ChartPanel(null);
				panel.removeAll();
				
				Draw_Chart(box1.getSelectedItem().toString(),box2.getSelectedItem().toString());
			}
		});
		
		set_display();

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	public static void Draw_Chart(String b1, String b2)
	{
		System.out.println(b1 + b2);
		if(b1.equals(condition[0])) // all
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
			jfjf.setVisible(true);
		}
		
		//-------------------------------Gender-----------------------------//
		else if(b1.equals(condition[1]))//gender
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
					m_PieChart = new ChartPanel(jP3.DrawGenderPiChart(gdfever_per[0][0],0));// %
					f_PieChart = new ChartPanel(jP3.DrawGenderPiChart(gdfever_per[1][0],1));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			//-----------------------male----------------------------------//
			if(b2.equals(gender[0]))//male
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
				jfjf.setVisible(true);
			}
			
			//----------------------------------female-------------------------------------//
			else if(b2.equals(gender[1]))//female
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
				jfjf.setVisible(true);
			}
			
			
		}
		else if(b1.equals(condition[2])) //age
		{
			
		}
		else if(b1.equals(condition[2])) //season
		{
			
		}
	}
	public static void set_list(String S)
	{
		box2.removeAllItems();
		if(S.equals(condition[0])) // all
		{
			box2.addItem("Click the last button");
		}
		else if(S.equals(condition[1]))//gender
		{
			 //box2 = new JComboBox<String>(gender);
			 for(int i=0; i<gender.length; i++)
			 {
				 box2.addItem(gender[i]);
			 }
		}
		else if(S.equals(condition[2])) //age
		{
			 for(int i=0; i<age.length; i++)
			 {
				 box2.addItem(age[i]);
			 }
		}
		else if(S.equals(condition[2])) //
		{
			 for(int i=0; i<season.length; i++)
			 {
				 box2.addItem(season[i]);
			 }
		}
		
	}
	public static void set_display() 
	{
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
		
		
		jfjf.setVisible(true);
	}
}
