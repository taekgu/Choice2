//package practice;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class database_load_avg{
	
	public static Float[][] dload_avg() throws SQLException {
		//�ʿ��� ���� ���� �ʱ�ȭ
		Float[][] temp7h = new Float[7][694]; // �µ����� �迭
		Connection con = null;// Dababase���� ������ ���� ����
		java.sql.Statement st = null;//mysql ���� ����� ������ ���� 
		ResultSet rs = null; // mysql ������ ����� �޾ƿ�. (���پ�?)
		int i = 0, j = 0; // for ���� ���
		try {

			//HY
			//String dbURL = "jdbc:mysql://127.0.0.1:3306?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			//JJ
			String dbURL =  "jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			//HY
			//con = DriverManager.getConnection(dbURL,"root","asdasd"); // ���ῡ ���� �ο�? 
			//JJ
			con = DriverManager.getConnection(dbURL,"root","1234"); // ���ῡ ���� �ο�? 

			st = con.createStatement();// ����
			//HY
			//st.execute("USE chois;");//������ ���̽� ���
			//JJ
			st.execute("USE hy1;");
			rs = st.executeQuery("SELECT date, temp from temp_avg;"); //select// ���� ����� ResultSet�� ��� ���� �޼ҵ�� �ַ� SELECT���� ���˴ϴ�.

			rs.next(); 
			
			for (i = 0; i < 3; i++) // 3��
			{
				for(j = 0; j<694; j++) // �Ϸ翡 694��
				{
					java.sql.Timestamp timestamp = rs.getTimestamp("date");
					java.util.Date date = timestamp;
					
					Float flo = rs.getFloat(2);//�µ�
					
					temp7h[i][j] = flo;//�µ� �迭�� ���� 
					
					//��¥ -> ��Ʈ�� 
					DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String tempDate = sdFormat.format(date);
					
					if (rs.next() == false)
						break;
				}
				if (rs.next() == false)
					break;
			}
			//���� ó��
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.close(); // ������ �����ݴϴ�. -> ���Ϸ�
		
		return temp7h; // ������ �µ� ������ ����
	}
	
	public static double dload_avg_per() throws SQLException {
		//�ʿ��� ���� ���� �ʱ�ȭ
		Connection con = null;// Dababase���� ������ ���� ����
		java.sql.Statement st = null;//mysql ���� ����� ������ ���� 
		ResultSet rs = null; // mysql ������ ����� �޾ƿ�. (���پ�?)
		double fever_per = 0;
		try {

			
			//HY--------------------------------------------------------------------------------------------------------------
			//String dbURL = "jdbc:mysql://127.0.0.1:3306?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			//Class.forName("com.mysql.jdbc.Driver");
			//con = DriverManager.getConnection(dbURL,"root","asdasd"); // ���ῡ ���� �ο�? 
			//st = con.createStatement();// ����
			//st.execute("USE chois;");//������ ���̽� ���
			//---------------------------------------------------------------------------------------------------------------
			//JJ ------------------------------------------------------------------------------------------------------------
			String dbURL =  "jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL,"root","1234"); // ���ῡ ���� �ο�? 
			st = con.createStatement();// ����
			st.execute("USE hy1;");//������ ���̽� ���
			//--------------------------------------------------------------------------------------------------------------


			
			rs = st.executeQuery("SELECT category, number from population;"); //select// ���� ����� ResultSet�� ��� ���� �޼ҵ�� �ַ� SELECT���� ���˴ϴ�.
			rs.next(); 
			int total = 0;
			int fever = 0;
			while(rs.next() != false)
			{
				String C = rs.getNString(1);
				int number = rs.getInt(2);
			
				if(C.equals("total"))
					total = number;
				if(C.equals("suspiciousTotal"))
					fever = number;
			}
			
			fever_per = (double)fever/(double)total;
			//���� ó��
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.close(); // ������ �����ݴϴ�. -> ���Ϸ�
		
		return fever_per; // ������ �µ� ������ ����
	}
	
}