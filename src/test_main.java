import java.io.IOException;
import java.sql.SQLException;


public class test_main {
	public static final int SAMPLE_NUM  = 694;
	
	public static void main(String[] ar) throws IOException, SQLException
	{
		//double[] temp = new double[SAMPLE_NUM];
		//double[] temp2 = new double[SAMPLE_NUM];
		//double[] mean = new double[SAMPLE_NUM];
		//for (int i = 0; i< SAMPLE_NUM; i++)
		//{
		//	temp[i] = (Math.random() * 4) + 36;
		//	temp2[i] = (Math.random() * 4) + 36;
			
		//	mean[i] = (temp[i] + temp2[i]) /2;
		//}
		//MyChart MC = new MyChart();
		//MC.get_MyChart(mean);
		
		new gui();
		database_load.dload();
		new Map();
		
	}
}