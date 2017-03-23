
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;


public class JTablePanel 
{
	//JFrame jFrame = new JFrame("JTable 예제");
	JPanel jPanel;
	String[] columnNames = { "ID", "Birth", "Temperature", "GPS"};
	Vector<String> column1 = new Vector<String>();
	
	String[][] rowData = null;
	String[] data = {"ID", "Birth", "Temperature", "GPS"};
	JTable jTable;
	JScrollPane jScrollPane;
	JPanel jPanel1;
	String id;
	String birth, temp, gps;
	JButton jb1;
	Connection con;
	java.sql.Statement st;
	ResultSet rs;
	
	String SET;
	String New;
	int cnt;
	public JTablePanel()
	{
		//jPanel = new JPanel();
		
		//jTable = new JTable(rowData, columnNames);
		//jScollPane = new JScrollPane(jTable);
		
		jPanel = new JPanel();
		jTable = new JTable();//(rowData, columnNames);
		//jScollPane = new JScrollPane(jTable);
		id = null;
		birth = null;
		temp = null;
		gps = null;
		cnt = 0;
		//String[] data = {"ID", "Birth", "Temperature", "GPS"};
		column1.add("ID");
		column1.add("Birth");
		column1.add("Temperature");
		column1.add("GPS");
		jb1 = new JButton();
		jPanel1 = new JPanel();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC","root", "1234");
			st = con.createStatement();
			rs = st.executeQuery("use Thermosafer_INU;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public JTable JTable_init(){
		
		DefaultTableModel model = new DefaultTableModel(){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] name = {"ID", "Birth", "Temperature", "GPS"}; 

            @Override 
            public int getColumnCount() { 
                return name.length; 
            } 

            @Override 
            public String getColumnName(int index) { 
                return name[index]; 
            } 
		};
		
		//model.addRow(columnNames);
		String str = null;
		try {
			cnt = 0;
			rs = st.executeQuery("SELECT date, id, birth, temp, sex, gps_lat, gps_har from thermo_data ORDER BY ID, date DESC;");
			
			while(rs.next()){
				New = rs.getString("id");
				if (New.equals(SET)){
					continue;
				}
				Vector<String> row = new Vector<String>();
				SET = rs.getString("id");
				str = rs.getString("id"); //getString(컬럼 번호) 
		          //여기서 컬럼번호는 1부터 시작
				//row.removeAllElements();
				row.addElement(str);
				str = rs.getString("birth");
				row.addElement(str);
				str = rs.getString("temp");
				row.addElement(str);
				str = rs.getString("gps_lat") +" / "+ rs.getString("gps_har");
				row.addElement(str);
				System.out.println(row);
				model.addRow(row);
		
			}
			jTable = new JTable(model){
		        /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {                
	                return false;               
		        };
			};
			JTableHeader header = jTable.getTableHeader();
			header.setBackground(Color.yellow);
			
			//jScrollPane = new JScrollPane(jTable);
			jTable.setFont(new Font( "Serif", 0 , 20 ));
			jTable.setBounds(0,0,1290,800);
			jTable.setVisible(true);
			
			//jb1.setBounds(150, 0, 150, 50);
			//jb1.setText("test");
			//jb1.setVisible(true);
			//jScrollPane.add(jb1);
			
			//jScrollPane.setVisible(true);
			
			
		
			jPanel1.setBounds(0,0,1290, 800);
			//jPanel1.add(jScrollPane);
			jPanel1.add(jTable);
			jPanel1.setVisible(true);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return jTable;
	}
	
}
