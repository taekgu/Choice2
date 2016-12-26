package practice;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class make_panel3 implements ActionListener 
{
	static private JPanel panel = new JPanel(new GridLayout(1, 2));;
	
	static final JPanel allchoice = new JPanel(new GridLayout(1, 2, -150, 0)); // 버튼 + 리스트 
	static final JPanel List = new JPanel(new FlowLayout(FlowLayout.LEFT, 200, 25)); // 리스트
	static final JPanel allstat = new JPanel(new GridLayout(2, 1)); // 버튼 + 리스트 + 파이차트

	// 사용하는 변수 선언 & 초기화
	static jPanel03 jP3 = new jPanel03(); //파넬 전체
	static ChartPanel CP3; // 차트 그림
	static ChartPanel PieChart; // 파이차트 
	static JList list = new JList();// 리스트

	// list에 들어갈 내용들
	static String[] season = { "Spring", "Summer", "Fall", "Winter" };
	static String[] age = { "0~9", "10~19", "20~29", "30~39", "40~49", "50~59", "60~69", "70~79" };
	static String[] gender = {"Female","Male"};
	
	static Dimension ld = new Dimension(130, 190); // list 크기 조절 
	
	private static JFrame jfjf ;
	
	public static JPanel make(JFrame JF)
	{
		jfjf = JF;
		// -----------버튼만들기---------------------//
				final JButton button1 = new JButton("All");
				
				button1.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						PieChart = new ChartPanel(null);

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
						panel.removeAll();
						allstat.removeAll();
						
						//리스트 목록 지우기//
						List.removeAll();
						list = new JList();
						list.setPreferredSize(ld);
						List.add(list);
						
						allstat.add(allchoice);
						allstat.add(PieChart);

						panel.add(CP3);
						panel.add(allstat);
						jfjf.setVisible(true);
					}
				});

				final JButton button2 = new JButton("Season");
				button2.setActionCommand("Season");
				button2.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						CP3 = new ChartPanel(null);
						PieChart = new ChartPanel(null);
						
						//바뀔것은 미리 지우기
						panel.removeAll();
						List.removeAll();
						panel.removeAll();
						allstat.removeAll();
						
						// 리스트에 목록들 추가하기
						list = new JList();
						list.setPreferredSize(ld); // 크기 조절 
						list.setListData(season); // 나이 목록 부르기
						list.setSelectedIndex(0);// 처음 선택은 0번째
						List.add(list); // 파넬에 추가
						
						list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						list.addListSelectionListener(new ListSelectionListener(){
							@Override
							public void valueChanged(ListSelectionEvent e) {
								// TODO Auto-generated method stub
								//if(list.getSelectedIndex() == 0)
									System.out.println(list.getSelectedValue());
									
									if(list.getSelectedValue().equals(season[0])); // 봄
									else if(list.getSelectedValue().equals(season[1]));//여름
									else if(list.getSelectedValue().equals(season[2]));//가을
									else if(list.getSelectedValue().equals(season[3]));//겨울
							}
						});
						
						//바뀐 내용들 추가하여 나타내기
						allstat.add(allchoice);
						allstat.add(PieChart);

						panel.add(CP3);
						panel.add(allstat);
						jfjf.setVisible(true);
					}
				});

				
				final JButton button3 = new JButton("Gender");
				button3.setActionCommand("Gender");
				button3.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						// TODO Auto-generated method stub
						//원래 있던내용 비우기
						panel.removeAll();
						allstat.removeAll();
						PieChart = new ChartPanel(null);
						CP3 = new ChartPanel(null);
						
						try {
							Float[][] arrdata = database_load_gender.dload_avg();
							XYSeries series1 = new XYSeries("male");
							XYSeries series2 = new XYSeries("female");
							XYSeriesCollection dataset = new XYSeriesCollection();
							XYDataset xydataset = dataset;

							for (int i = 0; i < 35; i++) {
								series1.add(i * 600000 - 26640000, arrdata[1][i]);
								series2.add(i * 600000 - 26640000, arrdata[0][i]);
							}
							dataset.addSeries(series1);
							dataset.addSeries(series2);

							CP3 = new ChartPanel(jP3.DrawMyChart(xydataset));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						ChartPanel m_PieChart = null;
						ChartPanel f_PieChart = null;
						
						// ----- 파이그래프 그리기 ------ //
						try {
							double[][] fever_per = database_load_gender.dload_avg_per(); // 감기걸린사람
																							// %
							try {
								m_PieChart = new ChartPanel(jP3.DrawGenderPiChart(fever_per[0][0]));
								f_PieChart = new ChartPanel(jP3.DrawGenderPiChart(fever_per[1][0]));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						final JPanel gender_pie = new JPanel(new GridLayout(1, 2));

						gender_pie.add(m_PieChart);
						gender_pie.add(f_PieChart);
						
						//list 비우기
						List.removeAll();
						list = new JList();
						list.setPreferredSize(ld);
						list.setListData(gender); // 나이 목록 부르기
						list.setSelectedIndex(0);// 처음 선택은 0번째
						List.add(list);
						
						//리스트 이벤트 발생!
						list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						list.addListSelectionListener(new ListSelectionListener(){
							@Override
							public void valueChanged(ListSelectionEvent e) {
								// TODO Auto-generated method stub
								//if(list.getSelectedIndex() == 0)
									System.out.println(list.getSelectedValue());
									
									if(list.getSelectedValue().equals(gender[0])); // 남자
									else if(list.getSelectedValue().equals(gender[1]));//여자
							}
						});
						
						
						//사용하는 그래프 추가 
						allstat.add(allchoice);
						allstat.add(gender_pie);

						panel.add(CP3);
						panel.add(allstat);
						jfjf.setVisible(true);
					}
				});

				
				final JButton button4 = new JButton("Age");
				button4.setActionCommand("Age");
				button4.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						CP3 = new ChartPanel(null);
						PieChart = new ChartPanel(null);
						
						//바꿀거 지우기
						panel.removeAll();
						allstat.removeAll();
						panel.removeAll();
						List.removeAll();
						
						//리스트 만들기 -> 나이 
						list = new JList();
						list.setPreferredSize(ld); // 크기 조절 
						list.setListData(age); // 나이 목록 부르기
						list.setSelectedIndex(0);// 처음 선택은 0번째
						List.add(list); // 파넬에 추가
						
						//리스트 이벤트 발생!
						list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						list.addListSelectionListener(new ListSelectionListener(){
							@Override
							public void valueChanged(ListSelectionEvent e) {
								// TODO Auto-generated method stub
								//if(list.getSelectedIndex() == 0)
									System.out.println(list.getSelectedValue());
									
									if(list.getSelectedValue().equals(age[0])); // 0-9
									else if(list.getSelectedValue().equals(age[1]));// 10-19
									else if(list.getSelectedValue().equals(age[2]));// 20-29
									else if(list.getSelectedValue().equals(age[3]));// 30-39
									else if(list.getSelectedValue().equals(age[4]));// 40-49
									else if(list.getSelectedValue().equals(age[5]));// 50-59
									else if(list.getSelectedValue().equals(age[6]));// 60-69
									else if(list.getSelectedValue().equals(age[7]));// 70-79
							}
						});
						
						//바뀐 내용 표시
						allstat.add(allchoice);
						allstat.add(PieChart);

						panel.add(CP3);
						panel.add(allstat);
						jfjf.setVisible(true);
					}
				});

				Dimension d = new Dimension(150, 80);
				button1.setPreferredSize(d);
				button2.setPreferredSize(d);
				button3.setPreferredSize(d);
				button4.setPreferredSize(d);

				final JPanel buttonPanel1 = new JPanel((new FlowLayout(FlowLayout.LEFT, 50, 5)));
				buttonPanel1.add(button1);
				buttonPanel1.add(button2);

				final JPanel buttonPanel2 = new JPanel((new FlowLayout(FlowLayout.LEFT, 50, 5)));
				buttonPanel2.add(button3);
				buttonPanel2.add(button4);

				final JPanel Button = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 20));
				Button.add(buttonPanel1);
				Button.add(buttonPanel2);

				list.setPreferredSize(ld);
				List.add(list);

				allchoice.add(Button);
				allchoice.add(List);

				CP3 = new ChartPanel(null);
				PieChart = new ChartPanel(null);

				allstat.add(allchoice);
				allstat.add(PieChart);

				panel.add(CP3);
				panel.add(allstat);
		
				
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
