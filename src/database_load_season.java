//package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;  

public class database_load_season {
	public static Float[][] dload_avg() throws SQLException {

		// �ʿ��� ���� ���� �ʱ�ȭ
		Float[][] temp7h = new Float[4][694]; // �µ����� �迭
		Connection con = null;// Database���� ������ ���� ����
		java.sql.Statement st = null;// mysql ���� ����� ������ ����
		ResultSet rs = null; // mysql ������ ����� �޾ƿ�. (���پ�?)
		int i0 = 0,i1 = 0, i2 =0 , i3 = 0; // for ���� ���
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
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbURL,"root","1234"); // ���ῡ ���� �ο�? 
			st = con.createStatement();// ����
			st.execute("USE hy2;");//������ ���̽� ���
			//--------------------------------------------------------------------------------------------------------------

			rs = st.executeQuery("SELECT season, date, temp from temp_season;"); 

			System.out.println("���Դ�");
			while (rs.next())
			{
				int season = 5;
				
				Float flo = rs.getFloat(3);// �µ�
				
				if(rs.getString(1).equals("SP")) // ��
				{
					season = 0;
					temp7h[season][i0] = flo;// �µ� �迭�� ����
					i0++;
				}
				else if(rs.getString(1).equals("SU")) // ����
				{
					season = 1;
					temp7h[season][i1] = flo;// �µ� �迭�� ����
					i1++;
				}
				else if(rs.getString(1).equals("AT")) // ����
				{
					season = 2;
					temp7h[season][i2] = flo;// �µ� �迭�� ����
					i2++;
				}
				else if(rs.getString(1).equals("WT"))//�ܿ�
				{
					season = 3;
					temp7h[season][i3] = flo;// �µ� �迭�� ����
					i3++;
				}
			}

			// ���� ó��
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
		// �ʿ��� ���� ���� �ʱ�ȭ
		Connection con = null;// Database���� ������ ���� ����
		java.sql.Statement st = null;// mysql ���� ����� ������ ����
		ResultSet rs = null; // mysql ������ ����� �޾ƿ�. (���پ�?)
		double[][] fever_per = new double[4][1]; // 0 -> ���� / 1 -> ����
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
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbURL,"root","1234"); // ���ῡ ���� �ο�? 
			st = con.createStatement();// ����
			st.execute("USE hy2;");//������ ���̽� ���
			//--------------------------------------------------------------------------------------------------------------

			rs = st.executeQuery("SELECT category, number from population;"); 

			int total0 = 0;
			int total1 = 0;
			int total2 = 0;
			int total3 = 0;
			
			int fever0 = 0;
			int fever1 = 0;
			int fever2 = 0;
			int fever3 = 0;

			while (rs.next() != false) {
				String C = rs.getNString(1);
				int number = rs.getInt(2);

				if (C.equals("numSP"))
					total0 = number;
				else if (C.equals("numSU"))
					total1 = number;
				else if (C.equals("numAT"))
					total2 = number;
				else if (C.equals("numWT"))
					total3 = number;
				
				if (C.equals("suspiciousSP"))
					fever0 = number;
				else if (C.equals("suspiciousSU"))
					fever1 = number;
				else if (C.equals("suspiciousAT"))
					fever2 = number;
				else if (C.equals("suspiciousWT"))
					fever3 = number;
			}

			fever_per[0][0] = (double)fever0/(double)total0;
			fever_per[1][0] = (double)fever1/(double)total1;
			fever_per[2][0] = (double)fever2/(double)total2;
			fever_per[3][0] = (double)fever3/(double)total3;
			// ���� ó��
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