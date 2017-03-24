import java.awt.ScrollPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTablePanel2 {
	
	Connection con;
	java.sql.Statement st;
	ResultSet rs;
	
	JTable jTable;
	
	String id;
	String birth, temp, gps;
	int cnt;
	JPanel jPanel;
	String[] columnNames = { "ID", "Birth", "Temperature", "GPS Latitude", "GPS Longtitude"};
	
	String SET;
	String New;
	
	JScrollPane pane;
	
	public JTablePanel2(){
		id = null;
		birth = null;
		temp = null;
		gps = null;
		jPanel = new JPanel();
		pane = new JScrollPane();
		cnt = 0;
		
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC","root", "1234");
			st = con.createStatement();
			rs = st.executeQuery("use Thermosafer_INU;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public JScrollPane JTable_init(){
		
		//Vector<String> columnName= new Vector<String>();
		Vector<String> row = new Vector<String>();
		Vector<Vector<String>> data= new Vector<Vector<String>>();
		//ScrollPane pane = new ScrollPane();
		
		DefaultTableModel model = new DefaultTableModel(){

			/**
			 * 
			 */
			private static final long serialVersionUID = -2170712284827305637L;
			String[] name = {"ID", "Birth", "Temperature", "GPS Latitude", "GPS Longtitude"};

            @Override 
            public int getColumnCount() { 
                return name.length; 
            } 

            @Override 
            public String getColumnName(int index) { 
                return name[index]; 
            } 
		};
		
		String str = null;
		try {
			cnt = 0;
			rs = st.executeQuery("SELECT date, id, birth, temp, sex, gps_lat, gps_har from thermo_data ORDER BY ID, date DESC;");
			//model.addRow(column1);
				while(rs.next()){
					New = rs.getString("id");
					if (New.equals(SET)){
						continue;
					}
					row.removeAllElements();
					//ArrayList<String> row2 = new ArrayList<String>();
					SET = rs.getString("id");
					str = rs.getString("id"); //getString(컬럼 번호) 
			          //여기서 컬럼번호는 1부터 시작
					//row.removeAllElements();
					
					row.addElement(str);
					str = rs.getString("birth");
					row.addElement(str);
					str = rs.getString("temp");
					row.addElement(str);
					str = rs.getString("gps_lat");
					row.addElement(str);
					str =  rs.getString("gps_har");
					row.addElement(str);
					System.out.println(row);
					model.addRow(row);
			
				}
				jTable = new JTable(model)
				{
			        /**
					 * 
					 */
					private static final long serialVersionUID = 7706704291500662144L;
					public boolean isCellEditable(int row, int column) {                
						return false;               
					};
				};
				//jTable.setFillsViewportHeight(true);
				
				//JPContainer.add(scrollPane);
				jTable.setBounds(150,0,1180,700);
				pane.setViewportView(jTable);
				pane.add(jTable);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JScrollPane scrollPane = new JScrollPane(jTable);
		return scrollPane;
	}
}
