package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;  
import java.sql.SQLException;

public class database_load_avg{
	
	public static Float[][] dload_avg() throws SQLException {
		//필요한 변수 선언 초기화
		Float[][] temp7h = new Float[7][694]; // 온도저장 배열
		Connection con = null;// Database와의 연결을 위한 변수
		java.sql.Statement st = null;//mysql 에게 명령을 내리기 위해 
		ResultSet rs = null; // mysql 에서의 결과를 받아옴. (한줄씩?)
		int i = 0, j = 0; // for 문에 사용
		try {
			String dbURL = "jdbc:mysql://127.0.0.1:3306?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL,"root","asdasd"); // 연결에 권한 부여? 
			
			st = con.createStatement();// 연결
			st.execute("USE chois;");//데이터 베이스 사용
			
			rs = st.executeQuery("SELECT date, temp from temp_avg;"); //select// 쿼리 결과를 ResultSet을 얻기 위한 메소드로 주로 SELECT문에 사용됩니다.
			rs.next(); 
			
			for (i = 0; i < 3; i++) // 3일
			{
				for(j = 0; j<694; j++) // 하루에 694번
				{
					Float flo = rs.getFloat(2);//온도
					
					temp7h[i][j] = flo;//온도 배열에 저장 
					
					if (rs.next() == false)
						break;
				}
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
	
	public static double dload_avg_per() throws SQLException {
		//필요한 변수 선언 초기화
		Connection con = null;// Database와의 연결을 위한 변수
		java.sql.Statement st = null;//mysql 에게 명령을 내리기 위해 
		ResultSet rs = null; // mysql 에서의 결과를 받아옴. (한줄씩?)
		double fever_per = 0;
		try {
			
			String dbURL = "jdbc:mysql://127.0.0.1:3306?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL,"root","asdasd"); // 연결에 권한 부여? 
			
			st = con.createStatement();// 연결
			st.execute("USE chois;");//데이터 베이스 사용
			
			rs = st.executeQuery("SELECT category, number from population;"); //select// 쿼리 결과를 ResultSet을 얻기 위한 메소드로 주로 SELECT문에 사용됩니다.
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