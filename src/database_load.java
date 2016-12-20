import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class database_load {
	public static Float Min_val = (float) 0.0;
	public static Float Max_val = (float) 0.0;
	public static int COUNT = 0;
	public static Float[][] dload(String num) throws SQLException {
		
		Float[][] temp7h = new Float[7][COUNT];
		try {
			Connection con = null;
			String dbURL =  "jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			con = DriverManager.getConnection(dbURL,"root", "1234");

			//MyExcuteQuery("SHOW DATABASES;");
			java.sql.Statement st = null;
			ResultSet rs = null;
			
			
			st = con.createStatement();
			st.execute("USE newschema;");
			
			rs = st.executeQuery("SELECT COUNT(id) FROM tp WHERE LEFT(date,10)=" + "'2016-12-15'");
			COUNT = rs.getInt(1);
			
				
			rs = st.executeQuery("SELECT date, temp, gps FROM tp WHERE id = "+ num + ";");
			
			rs.next();
			int i = 0, j = 0;
			for (i = 0; i < 3; i++) // 3��
			{
				for(j = 0; j<COUNT; j++) // �Ϸ翡 694��
				{
					String str1 = rs.getNString(1);
					Float flo = rs.getFloat(2);
					temp7h[i][j] = flo;
					
					if (flo > Max_val)
						Max_val = flo;
					
					if (flo < Min_val)
					{
						if (j < 690)
							Min_val = flo;
							
					}
					if (i == 0)
					{
						System.out.println("Date : " + str1 + " //  Temperature : " +  flo);
						//if 
					}
						

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
			//������ ������ 3���� ���
			//1. execute -> ���̺� ����, ���� ���� �� �����ͺ��̽� ���� ��ɾ�
			//2. excuteUpdate -> ���ڵ� ����, ����, ������ ������ ���� ��ɾ�
			//3. excuteQuery -> ���ڵ� ��ȸ, ���̺� ��ȸ �� ��ȸ ��ɾ�
			
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
		return temp7h;
	}
	
}