package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;  
import java.sql.SQLException;

public class database_load_gender{
	public static Float[][] dload_avg() throws SQLException {
		
		//필요한 변수 선언 초기화
		Float[][] temp7h = new Float[7][694]; // 온도저장 배열
		Connection con = null;// Database와의 연결을 위한 변수
		java.sql.Statement st = null;//mysql 에게 명령을 내리기 위해 
		ResultSet rs = null; // mysql 에서의 결과를 받아옴. (한줄씩?)
		int i = 0; // for 문에 사용
		try {
			
			String dbURL = "jdbc:mysql://127.0.0.1:3306?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL,"root","asdasd"); // 연결에 권한 부여? 
			
			st = con.createStatement();// 연결
			st.execute("USE chois;");//데이터 베이스 사용
			
			rs = st.executeQuery("SELECT date, temp from temp_female;"); //select// 쿼리 결과를 ResultSet을 얻기 위한 메소드로 주로 SELECT문에 사용됩니다.
			rs.next(); 
				for(i = 0; i<144; i++) // 하루에 144번
				{
					Float flo = rs.getFloat(2);//온도
					temp7h[0][i] = flo;//온도 배열에 저장 
					if (rs.next() == false)
						break;
			}
				
				rs = st.executeQuery("SELECT date, temp from temp_male;"); //select// 쿼리 결과를 ResultSet을 얻기 위한 메소드로 주로 SELECT문에 사용됩니다.
				rs.next(); 
					for(i = 0; i<144; i++) // 하루에 144번
					{
						Float flo = rs.getFloat(2);//온도
						temp7h[1][i] = flo;//온도 배열에 저장 
						if (rs.next() == false)
							break;
				}
			//에러 처리
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
		//필요한 변수 선언 초기화
		Connection con = null;// Dababase와의 연결을 위한 변수
		java.sql.Statement st = null;//mysql 에게 명령을 내리기 위해 
		ResultSet rs = null; // mysql 에서의 결과를 받아옴. (한줄씩?)
		
		//Float[][] temp7h = new Float[7][694]; // 온도저장 배열
		double[][] fever_per = new double[2][1]; // 0 -> 남자 / 1 -> 여자
		try {
			
			String dbURL = "jdbc:mysql://127.0.0.1:3306?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL,"root","asdasd"); // 연결에 권한 부여? 
			
			st = con.createStatement();// 연결
			st.execute("USE chois;");//데이터 베이스 사용
			
			rs = st.executeQuery("SELECT category, number from population;"); //select// 쿼리 결과를 ResultSet을 얻기 위한 메소드로 주로 SELECT문에 사용됩니다.
			
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
			//에러 처리
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