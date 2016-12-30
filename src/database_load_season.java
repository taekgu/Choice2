package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;  

public class database_load_season {
	public static Float[][] dload_avg() throws SQLException {

		// 필요한 변수 선언 초기화
		Float[][] temp7h = new Float[4][694]; // 온도저장 배열
		Connection con = null;// Database와의 연결을 위한 변수
		java.sql.Statement st = null;// mysql 에게 명령을 내리기 위해
		ResultSet rs = null; // mysql 에서의 결과를 받아옴. (한줄씩?)
		int i0 = 0,i1 = 0, i2 =0 , i3 = 0; // for 문에 사용
		try {

			String dbURL = "jdbc:mysql://127.0.0.1:3306?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(dbURL, "root", "asdasd"); // 연결에
							
			st = con.createStatement();// 연결
			st.execute("USE chois;");// 데이터 베이스 사용

			rs = st.executeQuery("SELECT season, date, temp from temp_season;"); 

			System.out.println("들어왔다");
			while (rs.next())
			{
				int season = 5;
				
				Float flo = rs.getFloat(3);// 온도
				
				if(rs.getString(1).equals("SP")) // 봄
				{
					season = 0;
					temp7h[season][i0] = flo;// 온도 배열에 저장
					i0++;
				}
				else if(rs.getString(1).equals("SU")) // 여름
				{
					season = 1;
					temp7h[season][i1] = flo;// 온도 배열에 저장
					i1++;
				}
				else if(rs.getString(1).equals("AT")) // 가을
				{
					season = 2;
					temp7h[season][i2] = flo;// 온도 배열에 저장
					i2++;
				}
				else if(rs.getString(1).equals("WT"))//겨울
				{
					season = 3;
					temp7h[season][i3] = flo;// 온도 배열에 저장
					i3++;
				}
			}

			// 에러 처리
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.close(); // 연결을 끝내줍니다. -> 사용완료

		return temp7h; // 저장한 온도 데이터 리턴
	}

	public static double[][] dload_avg_per() throws SQLException {
		// 필요한 변수 선언 초기화
		Connection con = null;// Database와의 연결을 위한 변수
		java.sql.Statement st = null;// mysql 에게 명령을 내리기 위해
		ResultSet rs = null; // mysql 에서의 결과를 받아옴. (한줄씩?)
		double[][] fever_per = new double[4][1]; // 0 -> 남자 / 1 -> 여자
		try {

			String dbURL = "jdbc:mysql://127.0.0.1:3306?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, "root", "asdasd"); // 연결에
																		// 권한
																		// 부여?

			st = con.createStatement();// 연결
			st.execute("USE chois;");// 데이터 베이스 사용

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
			// 에러 처리
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.close(); // 연결을 끝내줍니다. -> 사용완료

		return fever_per; // 저장한 온도 데이터 리턴
	}
}