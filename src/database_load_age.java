package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class database_load_age {
	public static Float[][] dload_avg() throws SQLException {

		// 필요한 변수 선언 초기화
		Float[][] temp7h = new Float[8][694]; // 온도저장 배열
		Connection con = null;// Database와의 연결을 위한 변수
		java.sql.Statement st = null;// mysql 에게 명령을 내리기 위해
		ResultSet rs = null; // mysql 에서의 결과를 받아옴. (한줄씩?)
		int i = 0; // for 문에 사용
		try {

			String dbURL = "jdbc:mysql://127.0.0.1:3306?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, "root", "asdasd"); // 연결에
																		// 권한
																		// 부여?

			st = con.createStatement();// 연결
			st.execute("USE chois;");// 데이터 베이스 사용

			rs = st.executeQuery("SELECT age, date, temp from temp_age where age=0 ;"); // select//
																						// 쿼리
																						// 결과를
																						// ResultSet을
																						// 얻기
																						// 위한
																						// 메소드로
																						// 주로
																						// SELECT문에
																						// 사용됩니다.

			while (rs.next()) {
				Float flo = rs.getFloat(3);// 온도
				temp7h[0][i] = flo;// 온도 배열에 저장
				i++;
			}

			i = 0;
			rs = st.executeQuery("SELECT age, date, temp from temp_age where age=10 ;"); // select//
																							// 쿼리
																							// 결과를
																							// ResultSet을
																							// 얻기
																							// 위한
																							// 메소드로
																							// 주로
																							// SELECT문에
																							// 사용됩니다.

			while (rs.next()) {
				Float flo = rs.getFloat(3);// 온도
				temp7h[1][i] = flo;// 온도 배열에 저장
				i++;
			}

			i = 0;
			rs = st.executeQuery("SELECT age, date, temp from temp_age where age=20 ;"); // select//
																							// 쿼리
																							// 결과를
																							// ResultSet을
																							// 얻기
																							// 위한
																							// 메소드로
																							// 주로
																							// SELECT문에
																							// 사용됩니다.

			while (rs.next()) {
				Float flo = rs.getFloat(3);// 온도
				temp7h[2][i] = flo;// 온도 배열에 저장
				i++;
			}

			i = 0;
			rs = st.executeQuery("SELECT age, date, temp from temp_age where age=30 ;"); // select//
																							// 쿼리
																							// 결과를
																							// ResultSet을
																							// 얻기
																							// 위한
																							// 메소드로
																							// 주로
																							// SELECT문에
																							// 사용됩니다.

			while (rs.next()) {
				Float flo = rs.getFloat(3);// 온도
				temp7h[3][i] = flo;// 온도 배열에 저장
				i++;
			}

			i = 0;
			rs = st.executeQuery("SELECT age, date, temp from temp_age where age=40 ;"); // select//
																							// 쿼리
																							// 결과를
																							// ResultSet을
																							// 얻기
																							// 위한
																							// 메소드로
																							// 주로
																							// SELECT문에
																							// 사용됩니다.

			while (rs.next()) {
				Float flo = rs.getFloat(3);// 온도
				temp7h[4][i] = flo;// 온도 배열에 저장
				i++;
			}

			i = 0;
			rs = st.executeQuery("SELECT age, date, temp from temp_age where age=50 ;"); // select//
																							// 쿼리
																							// 결과를
																							// ResultSet을
																							// 얻기
																							// 위한
																							// 메소드로
																							// 주로
																							// SELECT문에
																							// 사용됩니다.

			while (rs.next()) {
				Float flo = rs.getFloat(3);// 온도
				temp7h[5][i] = flo;// 온도 배열에 저장
				i++;
			}

			i = 0;
			rs = st.executeQuery("SELECT age, date, temp from temp_age where age=60 ;"); // select//
																							// 쿼리
																							// 결과를
																							// ResultSet을
																							// 얻기
																							// 위한
																							// 메소드로
																							// 주로
																							// SELECT문에
																							// 사용됩니다.

			while (rs.next()) {
				Float flo = rs.getFloat(3);// 온도
				temp7h[6][i] = flo;// 온도 배열에 저장
				i++;
			}

			i = 0;
			rs = st.executeQuery("SELECT age, date, temp from temp_age where age=70 ;"); // select//
																							// 쿼리
																							// 결과를
																							// ResultSet을
																							// 얻기
																							// 위한
																							// 메소드로
																							// 주로
																							// SELECT문에
																							// 사용됩니다.

			while (rs.next()) {
				Float flo = rs.getFloat(3);// 온도
				temp7h[7][i] = flo;// 온도 배열에 저장
				i++;
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
		double[][] fever_per = new double[8][1]; // 0 -> 남자 / 1 -> 여자
		try {

			String dbURL = "jdbc:mysql://127.0.0.1:3306?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, "root", "asdasd"); // 연결에
																		// 권한
																		// 부여?

			st = con.createStatement();// 연결
			st.execute("USE chois;");// 데이터 베이스 사용

			rs = st.executeQuery("SELECT category, number from population;"); // select//
																				// 쿼리
																				// 결과를
																				// ResultSet을
																				// 얻기
																				// 위한
																				// 메소드로
																				// 주로
																				// SELECT문에
																				// 사용됩니다.

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