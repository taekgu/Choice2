import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;



public class database_load {
	public static Float Min_val;
	public static Float Max_val; 
	public static int COUNT = 0;
	public static Date[] Date_array = new Date[20000];
	public database_load()
	{
		Min_val = (float)100.0;
		Max_val = (float)0.0;
	}
	
		
	
	
	public static Float[][] dload(String sel_user, String sel_date) throws SQLException {

	
		Float [][] temp7h = new Float[2][20000];
		//Date[] Date_array = new Date[20000];
		
		try {
			Connection con = null;
			String dbURL =  "jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";
			con = DriverManager.getConnection(dbURL,"root", "1234");

			//MyExcuteQuery("SHOW DATABASES;");
			java.sql.Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
			st.execute("USE Thermosafer_INU;");
			//rs = st.executeQuery("SELECT COUNT(temp) from thermo_data WHERE id = 1 AND LEFT(date,10) = " + "'2016-03-11'" + ";");
			
			//COUNT = 276;
			//rs.next();
			//System.out.println("count : " +  COUNT);
			rs = st.executeQuery("SELECT temp,date from thermo_data WHERE id = "+ sel_user +" AND LEFT(date,10) = '" + sel_date + "';" );
			//rs = st.executeQuery("SELECT temp from thermo_data WHERE id = 1 AND LEFT(date,10) = '2016-03-11';");
			
			rs.next();
			Min_val = (float)100.0;
			Max_val = (float)0.0;
			int i = 0, j = 0;
		
			for(j = 0; j<20000; j++)
			{
				//String str1 = rs.getNString(1);
				Float flo = rs.getFloat("temp");
				//DateTime dt = DateTime.ParseExact(value, "yyyy-MM-dd", null);

				//long result = dt.ToFileTime();
				Date date = rs.getDate("date");
				temp7h[0][j] = flo;
				Date_array[j] = date;
				if (flo > Max_val)
					Max_val = flo;
				
				if (flo < Min_val)
					Min_val = flo;

				//System.out.println("Temperature : " +  flo);

				if (rs.next() == false)
					break;
			}
			
			//System.out.println("Max : " + Max_val + "  Min : " + Min_val);

			//������ ������ 3���� ���
			//1. execute -> ���̺� ����, ���� ���� �� �����ͺ��̽� ���� ��ɾ�
			//2. excuteUpdate -> ���ڵ� ����, ����, ������ ������ ���� ��ɾ�
			//3. excuteQuery -> ���ڵ� ��ȸ, ���̺� ��ȸ �� ��ȸ ��ɾ�
			
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
		System.out.println("Data Load");
		return temp7h;
	}
	
}