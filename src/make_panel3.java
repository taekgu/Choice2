

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
	
	static final JPanel allchoice = new JPanel(new GridLayout(1, 2, -150, 0)); // ��ư + ����Ʈ 
	static final JPanel List = new JPanel(new FlowLayout(FlowLayout.LEFT, 200, 25)); // ����Ʈ
	static final JPanel allstat = new JPanel(new GridLayout(2, 1)); // ��ư + ����Ʈ + ������Ʈ

	// ����ϴ� ���� ���� & �ʱ�ȭ
	static jPanel03 jP3 = new jPanel03(); //�ĳ� ��ü
	static ChartPanel CP3; // ��Ʈ �׸�
	static ChartPanel PieChart; // ������Ʈ 
	static JList list = new JList();// ����Ʈ

	// list�� �� �����
	static String[] season = { "Spring", "Summer", "Fall", "Winter" };
	static String[] age = { "0~9", "10~19", "20~29", "30~39", "40~49", "50~59", "60~69", "70~79" };
	static String[] gender = {"Female","Male"};
	
	static Dimension ld = new Dimension(130, 190); // list ũ�� ���� 
	
	private static JFrame jfjf ;
	
	public static JPanel make(JFrame JF)
	{
		jfjf = JF;
		// -----------��ư�����---------------------//
				final JButton button1 = new JButton("All");
				
				button1.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						PieChart = new ChartPanel(null);

						// ---------------�׷��� �׸��� --------------------------//
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

						// --------------------���̱׷��� �׸��� ----------------------//
						// ����ɸ� ��� �ۼ������� ���//
						try {
							double fever_per = database_load_avg.dload_avg_per(); // ����ɸ����
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
						
						//����Ʈ ��� �����//
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
						
						//�ٲ���� �̸� �����
						panel.removeAll();
						List.removeAll();
						panel.removeAll();
						allstat.removeAll();
						
						// ����Ʈ�� ��ϵ� �߰��ϱ�
						list = new JList();
						list.setPreferredSize(ld); // ũ�� ���� 
						list.setListData(season); // ���� ��� �θ���
						list.setSelectedIndex(0);// ó�� ������ 0��°
						List.add(list); // �ĳڿ� �߰�
						
						list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						list.addListSelectionListener(new ListSelectionListener(){
							@Override
							public void valueChanged(ListSelectionEvent e) {
								// TODO Auto-generated method stub
								//if(list.getSelectedIndex() == 0)
									System.out.println(list.getSelectedValue());
									
									if(list.getSelectedValue().equals(season[0])); // ��
									else if(list.getSelectedValue().equals(season[1]));//����
									else if(list.getSelectedValue().equals(season[2]));//����
									else if(list.getSelectedValue().equals(season[3]));//�ܿ�
							}
						});
						
						//�ٲ� ����� �߰��Ͽ� ��Ÿ����
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
						//���� �ִ����� ����
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
						
						// ----- ���̱׷��� �׸��� ------ //
						try {
							double[][] fever_per = database_load_gender.dload_avg_per(); // ����ɸ����
																							// %
							try {//확인!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
								m_PieChart = new ChartPanel(jP3.DrawGenderPiChart(fever_per[0][0],0));
								f_PieChart = new ChartPanel(jP3.DrawGenderPiChart(fever_per[1][0],1));
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
						
						//list ����
						List.removeAll();
						list = new JList();
						list.setPreferredSize(ld);
						list.setListData(gender); // ���� ��� �θ���
						list.setSelectedIndex(0);// ó�� ������ 0��°
						List.add(list);
						
						//����Ʈ �̺�Ʈ �߻�!
						list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						list.addListSelectionListener(new ListSelectionListener(){
							@Override
							public void valueChanged(ListSelectionEvent e) {
								// TODO Auto-generated method stub
								//if(list.getSelectedIndex() == 0)
									System.out.println(list.getSelectedValue());
									
									if(list.getSelectedValue().equals(gender[0])); // ����
									else if(list.getSelectedValue().equals(gender[1]));//����
							}
						});
						
						
						//����ϴ� �׷��� �߰� 
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
						
						//�ٲܰ� �����
						panel.removeAll();
						allstat.removeAll();
						panel.removeAll();
						List.removeAll();
						
						//����Ʈ ����� -> ���� 
						list = new JList();
						list.setPreferredSize(ld); // ũ�� ���� 
						list.setListData(age); // ���� ��� �θ���
						list.setSelectedIndex(0);// ó�� ������ 0��°
						List.add(list); // �ĳڿ� �߰�
						
						//����Ʈ �̺�Ʈ �߻�!
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
						
						//�ٲ� ���� ǥ��
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
