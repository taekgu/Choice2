import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class database_load {
	public static Float[][] dload() throws SQLException {
		Float[][] temp7h = new Float[7][694];
		
		try {
			Connection con = null;
			String dbURL =  "jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			con = DriverManager.getConnection(dbURL,"root", "jx2doublej");

			//MyExcuteQuery("SHOW DATABASES;");
			java.sql.Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
			st.execute("USE choice;");
			rs = st.executeQuery("SELECT date, temp, gps FROM temperature WHERE id = 0;");
			
			rs.next();
			int i = 0, j = 0;
			for (i = 0; i < 3; i++) // 3일
			{
				for(j = 0; j<694; j++) // 하루에 694번
				{
					String str1 = rs.getNString(1);
					String str2 = rs.getNString(3);
					Float flo = rs.getFloat(2);
					temp7h[i][j] = flo;
					if (i == 0)
						System.out.println("Date : " + str1 + " //  Temperature : " +  flo + " //  GPS : " + str2);

						//System.out.println(flo + " // " + temp7h[0][j]);

					//System.out.println(str1 + " // " +  flo + " // " + str2 +  " // " + i + " // " + j);
					if (rs.next() == false)
						break;
				}
				if (rs.next() == false)
					break;
			}
			/*while (rs.next()) {
				
				
				String str1 = rs.getNString(1);
				String str2 = rs.getNString(3);
				Float flo = rs.getFloat(2);
				temp7h[i][j%694] = flo;
				System.out.println(str1 + " // " +  flo + " // " + str2 +  " // " + i + " // " + j);
				j++;
				i = (int)(j / 694);
			}*/
			//쿼리를 던지는 3가지 방법
			//1. execute -> 테이블 생성, 수정 삭제 등 데이터베이스 관리 명령어
			//2. excuteUpdate -> 레코드 삽입, 수정, 삭제등 데이터 조작 명령어
			//3. excuteQuery -> 레코드 조회, 테이블 조회 등 조회 명령어
			
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
		return temp7h;
	}
	
}