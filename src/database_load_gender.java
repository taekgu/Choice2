

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class database_load_gender{
	public static Float[][] dload_avg() throws SQLException {
		
		//�ʿ��� ���� ���� �ʱ�ȭ
		Float[][] temp7h = new Float[7][694]; // �µ����� �迭
		Connection con = null;// Database���� ������ ���� ����
		java.sql.Statement st = null;//mysql ���� ����� ������ ���� 
		ResultSet rs = null; // mysql ������ ����� �޾ƿ�. (���پ�?)
		int i = 0; // for ���� ���
		try {
			//HY
			//String dbURL = "jdbc:mysql://127.0.0.1:3306?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			//JJ
			String dbURL =  "jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			Class.forName("com.mysql.jdbc.Driver");
			//HY
			//con = DriverManager.getConnection(dbURL,"root","asdasd"); // ���ῡ ���� �ο�? 
			//JJ
			con = DriverManager.getConnection(dbURL,"root","1234"); // ���ῡ ���� �ο�? 
			
			st = con.createStatement();// ����
			//HY
			//st.execute("USE chois;");//������ ���̽� ���
			//JJ
			st.execute("USE hy1;");
			rs = st.executeQuery("SELECT date, temp from temp_female;"); //select// ���� ����� ResultSet�� ��� ���� �޼ҵ�� �ַ� SELECT���� ���˴ϴ�.
			rs.next(); 
				for(i = 0; i<35; i++) // �Ϸ翡 144��
				{
					Float flo = rs.getFloat(2);//�µ�
					temp7h[0][i] = flo;//�µ� �迭�� ���� 
					if (rs.next() == false)
						break;
			}
				
				rs = st.executeQuery("SELECT date, temp from temp_male;"); //select// ���� ����� ResultSet�� ��� ���� �޼ҵ�� �ַ� SELECT���� ���˴ϴ�.
				rs.next(); 
					for(i = 0; i<35; i++) // �Ϸ翡 144��
					{
						Float flo = rs.getFloat(2);//�µ�
						temp7h[1][i] = flo;//�µ� �迭�� ���� 
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
	
	public static double[][] dload_avg_per() throws SQLException {
		//�ʿ��� ���� ���� �ʱ�ȭ
		Connection con = null;// Dababase���� ������ ���� ����
		java.sql.Statement st = null;//mysql ���� ����� ������ ���� 
		ResultSet rs = null; // mysql ������ ����� �޾ƿ�. (���پ�?)
		
		//Float[][] temp7h = new Float[7][694]; // �µ����� �迭
		double[][] fever_per = new double[2][1]; // 0 -> ���� / 1 -> ����
		try {
			
			String dbURL = "jdbc:mysql://127.0.0.1:3306?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbURL,"root","1234"); // ���ῡ ���� �ο�? 
			
			st = con.createStatement();// ����
			st.execute("USE hy1;");//������ ���̽� ���
			
			rs = st.executeQuery("SELECT category, number from population;"); //select// ���� ����� ResultSet�� ��� ���� �޼ҵ�� �ַ� SELECT���� ���˴ϴ�.
			
			int f_total = 0;
			int m_total = 0;
			int f_fever = 0;
			int m_fever = 0;
			
			while(rs.next() != false)
			{
				String C = rs.getNString(1);
				int number = rs.getInt(2);
			
				if(C.equals("female"))
					f_total = number;
				if(C.equals("suspiciousFemale"))
					f_fever = number;
				if(C.equals("male"))
					m_total = number;
				if(C.equals("suspiciousMale"))
					m_fever = number;
			}
			
			fever_per[0][0] = (double)m_fever/(double)m_total;
			fever_per[1][0] = (double)f_fever/(double)f_total;
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