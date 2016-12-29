//package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class database_load_age {
	public static Float[][] dload_avg() throws SQLException {

		// �ʿ��� ���� ���� �ʱ�ȭ
		Float[][] temp7h = new Float[8][694]; // �µ����� �迭
		Connection con = null;// Database���� ������ ���� ����
		java.sql.Statement st = null;// mysql ���� ����� ������ ����
		ResultSet rs = null; // mysql ������ ����� �޾ƿ�. (���پ�?)
		int i = 0; // for ���� ���
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

			rs = st.executeQuery("SELECT age, date, temp from temp_age where age=0 ;"); // select//
																						// ����
																						// �����
																						// ResultSet��
																						// ���
																						// ����
																						// �޼ҵ��
																						// �ַ�
																						// SELECT����
																						// ���˴ϴ�.

			while (rs.next()) {
				Float flo = rs.getFloat(3);// �µ�
				temp7h[0][i] = flo;// �µ� �迭�� ����
				i++;
			}

			i = 0;
			rs = st.executeQuery("SELECT age, date, temp from temp_age where age=10 ;"); // select//
																							// ����
																							// �����
																							// ResultSet��
																							// ���
																							// ����
																							// �޼ҵ��
																							// �ַ�
																							// SELECT����
																							// ���˴ϴ�.

			while (rs.next()) {
				Float flo = rs.getFloat(3);// �µ�
				temp7h[1][i] = flo;// �µ� �迭�� ����
				i++;
			}

			i = 0;
			rs = st.executeQuery("SELECT age, date, temp from temp_age where age=20 ;"); // select//
																							// ����
																							// �����
																							// ResultSet��
																							// ���
																							// ����
																							// �޼ҵ��
																							// �ַ�
																							// SELECT����
																							// ���˴ϴ�.

			while (rs.next()) {
				Float flo = rs.getFloat(3);// �µ�
				temp7h[2][i] = flo;// �µ� �迭�� ����
				i++;
			}

			i = 0;
			rs = st.executeQuery("SELECT age, date, temp from temp_age where age=30 ;"); // select//
																							// ����
																							// �����
																							// ResultSet��
																							// ���
																							// ����
																							// �޼ҵ��
																							// �ַ�
																							// SELECT����
																							// ���˴ϴ�.

			while (rs.next()) {
				Float flo = rs.getFloat(3);// �µ�
				temp7h[3][i] = flo;// �µ� �迭�� ����
				i++;
			}

			i = 0;
			rs = st.executeQuery("SELECT age, date, temp from temp_age where age=40 ;"); // select//
																							// ����
																							// �����
																							// ResultSet��
																							// ���
																							// ����
																							// �޼ҵ��
																							// �ַ�
																							// SELECT����
																							// ���˴ϴ�.

			while (rs.next()) {
				Float flo = rs.getFloat(3);// �µ�
				temp7h[4][i] = flo;// �µ� �迭�� ����
				i++;
			}

			i = 0;
			rs = st.executeQuery("SELECT age, date, temp from temp_age where age=50 ;"); // select//
																							// ����
																							// �����
																							// ResultSet��
																							// ���
																							// ����
																							// �޼ҵ��
																							// �ַ�
																							// SELECT����
																							// ���˴ϴ�.

			while (rs.next()) {
				Float flo = rs.getFloat(3);// �µ�
				temp7h[5][i] = flo;// �µ� �迭�� ����
				i++;
			}

			i = 0;
			rs = st.executeQuery("SELECT age, date, temp from temp_age where age=60 ;"); // select//
																							// ����
																							// �����
																							// ResultSet��
																							// ���
																							// ����
																							// �޼ҵ��
																							// �ַ�
																							// SELECT����
																							// ���˴ϴ�.

			while (rs.next()) {
				Float flo = rs.getFloat(3);// �µ�
				temp7h[6][i] = flo;// �µ� �迭�� ����
				i++;
			}

			i = 0;
			rs = st.executeQuery("SELECT age, date, temp from temp_age where age=70 ;"); // select//
																							// ����
																							// �����
																							// ResultSet��
																							// ���
																							// ����
																							// �޼ҵ��
																							// �ַ�
																							// SELECT����
																							// ���˴ϴ�.

			while (rs.next()) {
				Float flo = rs.getFloat(3);// �µ�
				temp7h[7][i] = flo;// �µ� �迭�� ����
				i++;
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
		double[][] fever_per = new double[8][1]; // 0 -> ���� / 1 -> ����
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

			rs = st.executeQuery("SELECT category, number from population;"); // select//
																				// ����
																				// �����
																				// ResultSet��
																				// ���
																				// ����
																				// �޼ҵ��
																				// �ַ�
																				// SELECT����
																				// ���˴ϴ�.

			int total0 = 0;
			int total1 = 0;
			int total2 = 0;
			int total3 = 0;
			int total4 = 0;
			int total5 = 0;
			int total6 = 0;
			int total7 = 0;

			int fever0 = 0;
			int fever1 = 0;
			int fever2 = 0;
			int fever3 = 0;
			int fever4 = 0;
			int fever5 = 0;
			int fever6 = 0;
			int fever7 = 0;

			while (rs.next() != false) {
				String C = rs.getNString(1);
				int number = rs.getInt(2);

				if (C.equals("num00s"))
					total0 = number;
				else if (C.equals("num10s"))
					total1 = number;
				else if (C.equals("num20s"))
					total2 = number;
				else if (C.equals("num30s"))
					total3 = number;
				else if (C.equals("num40s"))
					total4 = number;
				else if (C.equals("num50s"))
					total5 = number;
				else if (C.equals("num60s"))
					total6 = number;
				else if (C.equals("num70s"))
					total7 = number;
				
				if (C.equals("suspicious00s"))
					fever0 = number;
				else if (C.equals("suspicious10s"))
					fever1 = number;
				else if (C.equals("suspicious20s"))
					fever2 = number;
				else if (C.equals("suspicious30s"))
					fever3 = number;
				else if (C.equals("suspicious40s"))
					fever4 = number;
				else if (C.equals("suspicious50s"))
					fever5 = number;
				else if (C.equals("suspicious60s"))
					fever6 = number;
				else if (C.equals("suspicious70s"))
					fever7 = number;
			}

			fever_per[0][0] = (double)fever0/(double)total0;
			fever_per[1][0] = (double)fever1/(double)total1;
			fever_per[2][0] = (double)fever2/(double)total2;
			fever_per[3][0] = (double)fever3/(double)total3;
			fever_per[4][0] = (double)fever4/(double)total4;
			fever_per[5][0] = (double)fever5/(double)total5;
			fever_per[6][0] = (double)fever6/(double)total6;
			fever_per[7][0] = (double)fever7/(double)total7;
			
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