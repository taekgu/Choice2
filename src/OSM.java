

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.LocalResponseCache;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

//import sample7_swingwaypoints.SwingWaypoint;

import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;



public class OSM extends Thread{
	JPanel MapPanel;
	Connection con;
	java.sql.Statement st;
	java.sql.Statement wt;
	ResultSet rs;
	static JXMapViewer mapViewer;
	List<Painter<JXMapViewer>> painters;
	int index;
	private static int COUNT = 50;
	static Thread test_thread1;
	Thread test_thread2;
	int temp;
	static int temp2;
	geo_list_class glc_temp;
	swing_waypoints sw_temp;
	
	public OSM()
	{
		MapPanel = new JPanel();
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC","root", "1234");
			st = con.createStatement();
			rs = st.executeQuery("use newschema5");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mapViewer = new JXMapViewer();
		painters = new ArrayList<Painter<JXMapViewer>>();
		index = 0;
		test_thread1 = null;
		test_thread2 = null;
		temp2 = 0;
		glc_temp = new geo_list_class();
		sw_temp = new swing_waypoints();
	}
	/*
	public JXMapViewer OSM_init(String Sel_date, String Sel_user, int state)
	{
		TileFactoryInfo info = new OSMTileFactoryInfo();
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		mapViewer.setTileFactory(tileFactory);
		

		tileFactory.setThreadPoolSize(8);

		// Setup local file cache
		File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
		LocalResponseCache.installResponseCache(info.getBaseURL(), cacheDir, false);

		GeoPosition seoul = new GeoPosition(37.5653,126.9745);

		// Set the focus
		mapViewer.setZoom(7);
		mapViewer.setAddressLocation(seoul);                                                 
	
		// Add interactions
		MouseInputListener mia = new PanMouseInputListener(mapViewer);
		mapViewer.addMouseListener(mia);
		mapViewer.addMouseMotionListener(mia);

		mapViewer.addMouseListener(new CenterMapListener(mapViewer));
		
		mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
		
		mapViewer.addKeyListener(new PanKeyListener(mapViewer));
		
		// Add a selection painter
		SelectionAdapter sa = new SelectionAdapter(mapViewer); 
		SelectionPainter sp = new SelectionPainter(sa); 
		mapViewer.addMouseListener(sa); 
		mapViewer.addMouseMotionListener(sa); 
		mapViewer.setOverlayPainter(sp);
		
		//Multi_user("2016-01-14", "89", 1);
		//Multi_user("2016-12-13", "51", 1);
		
		List<GeoPosition> Geolist = get_geolist(Sel_date, Sel_user);
		//Set<SwingWaypoint> waypoints = new HashSet<SwingWaypoint>(Arrays.asList()
		RoutePainter routePainter = new RoutePainter(Geolist);
		//mapViewer.zoomToBestFit(new HashSet<GeoPosition>(Geolist), 0.0);
		if (state == 1)
			mapViewer.zoomToBestFit(Geolist_zoom(Sel_date, Sel_user), 0.5);
		
		Set<Waypoint> waypoints = get_waypoint(Sel_date, Sel_user);
		
		WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<Waypoint>();
		waypointPainter.setWaypoints(waypoints);
		
		// Create a compound painter that uses both the route-painter and the waypoint-painter

		painters.add(routePainter); // Line
		painters.add(waypointPainter); // Marker

		CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
		mapViewer.setOverlayPainter(painter);

		return mapViewer;
	}
	
	public JXMapViewer OSM_init()
	{
		
		return mapViewer;
	}
	
	public JXMapViewer OSM_init(String[][] user_date, int state)
	{
		geo_list_class glc = new geo_list_class(); 
		//geo_list_class glc_temp = new geo_list_class();
		glc_temp = glc.get_list(user_date[1][index], user_date[0][index]);
		
		test_thread1 = new Thread(){
			@Override
			public void run()
			{
				
				painters.removeAll(painters);
				while(user_date[0][index] != null) //index
				{
					if (temp == 500)
						break;
					//List<GeoPosition> Geolist = get_geolist(user_date[1][index], user_date[0][index]);
					List<GeoPosition> Geolist = glc_temp.Geolist;

					RoutePainter routePainter = new RoutePainter(Geolist);
		
					
					//Set<Waypoint> waypoints = get_waypoint(user_date[1][index], user_date[0][index]);
					Set<Waypoint> waypoints = glc_temp.waypoints;
					WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<Waypoint>();
					waypointPainter.setWaypoints(waypoints);
					painters.add(routePainter);
					painters.add(waypointPainter);
					System.out.println("date : " + user_date[1][index] + " // id : " + user_date[0][index]);
					index++;
					temp++;
				}
				CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
				mapViewer.setOverlayPainter(painter);
				OSM.temp2 = 1;
			}
		};

		TileFactoryInfo info = new OSMTileFactoryInfo();
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		mapViewer.setTileFactory(tileFactory);
		

		tileFactory.setThreadPoolSize(8);

		// Setup local file cache
		File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
		LocalResponseCache.installResponseCache(info.getBaseURL(), cacheDir, false);

		GeoPosition seoul = new GeoPosition(37.5653,126.9745);

		// Set the focus
		mapViewer.setZoom(7);
		mapViewer.setAddressLocation(seoul);                                                 
	
		// Add interactions
		MouseInputListener mia = new PanMouseInputListener(mapViewer);
		mapViewer.addMouseListener(mia);
		mapViewer.addMouseMotionListener(mia);

		mapViewer.addMouseListener(new CenterMapListener(mapViewer));
		
		mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
		
		mapViewer.addKeyListener(new PanKeyListener(mapViewer));
		
		// Add a selection painter
		SelectionAdapter sa = new SelectionAdapter(mapViewer); 
		SelectionPainter sp = new SelectionPainter(sa); 
		mapViewer.addMouseListener(sa); 
		mapViewer.addMouseMotionListener(sa); 
		mapViewer.setOverlayPainter(sp);
		
		//ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		
		
		//one_paint("2016-01-10", "97");
		multi_paint(user_date,state);
		CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
		mapViewer.setOverlayPainter(painter);
		
		
		return mapViewer;
	}*/
	public JXMapViewer OSM_init(String Sel_date, String Sel_user, int state)
	{
		swing_waypoints sw = new swing_waypoints(); 
		sw_temp = sw.get_Swing_Waypoints(Sel_date, Sel_user);
		TileFactoryInfo info = new OSMTileFactoryInfo();
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		mapViewer.setTileFactory(tileFactory);
		

		tileFactory.setThreadPoolSize(8);

		// Setup local file cache
		File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
		LocalResponseCache.installResponseCache(info.getBaseURL(), cacheDir, false);

		GeoPosition seoul = new GeoPosition(37.5653,126.9745);

		// Set the focus
		mapViewer.setZoom(7);
		mapViewer.setAddressLocation(seoul);                                                 
	
		// Add interactions
		MouseInputListener mia = new PanMouseInputListener(mapViewer);
		mapViewer.addMouseListener(mia);
		mapViewer.addMouseMotionListener(mia);

		mapViewer.addMouseListener(new CenterMapListener(mapViewer));
		
		mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
		
		mapViewer.addKeyListener(new PanKeyListener(mapViewer));
		
		// Add a selection painter
		SelectionAdapter sa = new SelectionAdapter(mapViewer); 
		SelectionPainter sp = new SelectionPainter(sa); 
		mapViewer.addMouseListener(sa); 
		mapViewer.addMouseMotionListener(sa); 
		mapViewer.setOverlayPainter(sp);
		
		//Multi_user("2016-01-14", "89", 1);
		//Multi_user("2016-12-13", "51", 1);
		
		//List<GeoPosition> Geolist = get_geolist(Sel_date, Sel_user);
		List<GeoPosition> Geolist = sw_temp.Geolist;
		//Set<SwingWaypoint> waypoints = new HashSet<SwingWaypoint>(Arrays.asList()
		RoutePainter routePainter = new RoutePainter(Geolist);
		//mapViewer.zoomToBestFit(new HashSet<GeoPosition>(Geolist), 0.0);
		if (state == 1)
			mapViewer.zoomToBestFit(Geolist_zoom(Sel_date, Sel_user), 0.5);
		
		//Set<Waypoint> waypoints = get_waypoint(Sel_date, Sel_user);
		Set<SwingWaypoint> waypoints = sw_temp.waypoints;
		
		//WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<Waypoint>();
		WaypointPainter<SwingWaypoint> swingWaypointPainter = new SwingWaypointOverlayPainter();
		//waypointPainter.setWaypoints(waypoints);
		swingWaypointPainter.setWaypoints(waypoints);
		// Create a compound painter that uses both the route-painter and the waypoint-painter

		painters.add(routePainter); // Line
		//painters.add(waypointPainter); // Marker
		painters.add(swingWaypointPainter);//Button
	    for (SwingWaypoint w : waypoints) {
            mapViewer.add(w.getButton());
        }
		CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
		mapViewer.setOverlayPainter(painter);

		return mapViewer;
	}
	
	public JXMapViewer OSM_init()
	{
		
		return mapViewer;
	}
	public JXMapViewer OSM_init(String[][] user_date, int state)
	{
		System.out.println("OSM_init_i_wanted");
		swing_waypoints sw = new swing_waypoints(); 
		//geo_list_class glc_temp = new geo_list_class();
		sw_temp = sw.get_Swing_Waypoints(user_date[1][index], user_date[0][index]);
		
		test_thread1 = new Thread(){
			@Override
			public void run()
			{
				
				painters.removeAll(painters);
				while(user_date[0][index] != null) //index
				{
					if (temp == 500)
						break;
					//List<GeoPosition> Geolist = get_geolist(user_date[1][index], user_date[0][index]);
					List<GeoPosition> Geolist = sw_temp.Geolist;

					RoutePainter routePainter = new RoutePainter(Geolist);
		
					
					//Set<Waypoint> waypoints = get_waypoint(user_date[1][index], user_date[0][index]);
					Set<SwingWaypoint> waypoints = sw_temp.waypoints;
					WaypointPainter<SwingWaypoint> swingWaypointPainter = new SwingWaypointOverlayPainter();
			        swingWaypointPainter.setWaypoints(waypoints);
			        mapViewer.setOverlayPainter(swingWaypointPainter);
					painters.add(routePainter);
					painters.add(swingWaypointPainter);
				    for (SwingWaypoint w : waypoints) {
			            mapViewer.add(w.getButton());
			        }
					System.out.println("date : " + user_date[1][index] + " // id : " + user_date[0][index]);
					index++;
					temp++;
				}

				CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);

				mapViewer.setOverlayPainter(painter);
				
				OSM.temp2 = 1;
			}
		};

		TileFactoryInfo info = new OSMTileFactoryInfo();
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		mapViewer.setTileFactory(tileFactory);
		

		tileFactory.setThreadPoolSize(8);

		// Setup local file cache
		File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
		LocalResponseCache.installResponseCache(info.getBaseURL(), cacheDir, false);

		GeoPosition seoul = new GeoPosition(37.5653,126.9745);

		// Set the focus
		mapViewer.setZoom(7);
		mapViewer.setAddressLocation(seoul);                                                 
	
		// Add interactions
		MouseInputListener mia = new PanMouseInputListener(mapViewer);
		mapViewer.addMouseListener(mia);
		mapViewer.addMouseMotionListener(mia);

		mapViewer.addMouseListener(new CenterMapListener(mapViewer));
		
		mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
		
		mapViewer.addKeyListener(new PanKeyListener(mapViewer));
		
		// Add a selection painter
		SelectionAdapter sa = new SelectionAdapter(mapViewer); 
		SelectionPainter sp = new SelectionPainter(sa); 
		mapViewer.addMouseListener(sa); 
		mapViewer.addMouseMotionListener(sa); 
		mapViewer.setOverlayPainter(sp);
		
		//ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		
		
		//one_paint("2016-01-10", "97");
		multi_paint(user_date,state);
		CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
		

		mapViewer.setOverlayPainter(painter);

		
		
		return mapViewer;
	}
	
	public void one_paint(String Sel_date, String Sel_user)
	{
		List<GeoPosition> Geolist = get_geolist(Sel_date, Sel_user);
		RoutePainter routePainter = new RoutePainter(Geolist);

		
		Set<Waypoint> waypoints = get_waypoint(Sel_date, Sel_user);
		
		WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<Waypoint>();
		waypointPainter.setWaypoints(waypoints);
		
		// Create a compound painter that uses both the route-painter and the waypoint-painter


		painters.add(routePainter); // Line
		painters.add(waypointPainter); // Marker
		//return painters;
	}

	
	
	public void multi_paint(String[][] user_date, int state)
	{

		index = 0;
		temp=0;
		test_thread1.start();
		
	}
	

	
	public List<GeoPosition> get_geolist(String Sel_date, String Sel_user)
	{
		
		List<GeoPosition> Geolist = new ArrayList<GeoPosition>();
		try {
			rs = st.executeQuery("SELECT gps_lat, gps_har FROM tp WHERE id = " + Sel_user + " AND LEFT(date,10) = '" + Sel_date + "'");
			Double Geo_lat = null;
			Double Geo_har = null;
			while(rs.next()){
				Geo_lat = rs.getDouble("gps_lat");
				Geo_har = rs.getDouble("gps_har");
				Geolist.add(new GeoPosition(Geo_lat, Geo_har));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return Geolist;
	}
	
	public Set<GeoPosition> Geolist_zoom(String Sel_date, String Sel_user)
	{
		Set<GeoPosition> Geolist = new HashSet<GeoPosition>();
		try {
			rs = st.executeQuery("SELECT gps_lat, gps_har FROM tp WHERE id = " + Sel_user + " AND LEFT(date,10) = '" + Sel_date + "'");
			Double Geo_lat = null;
			Double Geo_har = null;
			Double Sum1 = 0.0, Sum2 = 0.0;
			Double count = 0.0;
			while(rs.next()){
				Geo_lat = rs.getDouble("gps_lat");
				Geo_har = rs.getDouble("gps_har");
				//Geolist.add(new DefaultWaypoint(Geo_lat, Geo_har));
				Sum1 += Geo_lat;
				Sum2 += Geo_har;
				count++;
			}
			Geolist.add(new GeoPosition((Sum1 / count), (Sum2 / count)));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Geolist;
	}
	

	public Set<Waypoint> get_waypoint(String Sel_date, String Sel_user)
	{
		Set<Waypoint> waypoints = new HashSet<Waypoint>();
		try {
			rs = st.executeQuery("SELECT gps_lat, gps_har FROM tp WHERE id = " + Sel_user + " AND LEFT(date,10) = '" + Sel_date + "'");
			Double Geo_lat = null;
			Double Geo_har = null;
			while(rs.next()){
				Geo_lat = rs.getDouble("gps_lat");
				Geo_har = rs.getDouble("gps_har");
				waypoints.add(new DefaultWaypoint(Geo_lat, Geo_har));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return waypoints;
	}
	
	public geo_list_class get_list(String Sel_date, String Sel_user)
	{
		geo_list_class load_glc = new geo_list_class();
		
		//List<GeoPosition> Geolist = new ArrayList<GeoPosition>();
		try {
			rs = st.executeQuery("SELECT gps_lat, gps_har FROM tp WHERE id = " + Sel_user + " AND LEFT(date,10) = '" + Sel_date + "'");
			Double Geo_lat = null;
			Double Geo_har = null;
			Double Sum1 = 0.0, Sum2 = 0.0;
			Double count = 0.0;
			while(rs.next()){
				Geo_lat = rs.getDouble("gps_lat");
				Geo_har = rs.getDouble("gps_har");
				load_glc.Geolist.add(new GeoPosition(Geo_lat, Geo_har));
				load_glc.waypoints.add(new DefaultWaypoint(Geo_lat, Geo_har));
				Sum1 += Geo_lat;
				Sum2 += Geo_har;
				count++;
			}
			load_glc.Geolist2.add(new GeoPosition((Sum1 / count), (Sum2 / count)));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return load_glc;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public List<GeoPosition> get_geolist(String[][] user_date)
	{
		
		List<GeoPosition> Geolist = new ArrayList<GeoPosition>();
		try {
			rs = st.executeQuery("SELECT gps_lat, gps_har FROM tp WHERE id = " + user_date[0] + " AND LEFT(date,10) = '" + user_date[1] + "'");
			Double Geo_lat = null;
			Double Geo_har = null;
			while(rs.next()){
				Geo_lat = rs.getDouble("gps_lat");
				Geo_har = rs.getDouble("gps_har");
				Geolist.add(new GeoPosition(Geo_lat, Geo_har));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return Geolist;
	}
	
	public Set<GeoPosition> Geolist_zoom(String[][] user_date)
	{
		Set<GeoPosition> Geolist = new HashSet<GeoPosition>();
		try {
			rs = st.executeQuery("SELECT gps_lat, gps_har FROM tp WHERE id = " + user_date[0] + " AND LEFT(date,10) = '" + user_date[1] + "'");
			Double Geo_lat = null;
			Double Geo_har = null;
			Double Sum1 = 0.0, Sum2 = 0.0;
			Double count = 0.0;
			while(rs.next()){
				Geo_lat = rs.getDouble("gps_lat");
				Geo_har = rs.getDouble("gps_har");
				//Geolist.add(new DefaultWaypoint(Geo_lat, Geo_har));
				Sum1 += Geo_lat;
				Sum2 += Geo_har;
				count++;
			}
			Geolist.add(new GeoPosition((Sum1 / count), (Sum2 / count)));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Geolist;
	}
	
	public Set<Waypoint> get_waypoint(String[][] user_date)
	{
		Set<Waypoint> waypoints = new HashSet<Waypoint>();
		try {
			rs = st.executeQuery("SELECT gps_lat, gps_har FROM tp WHERE id = " + user_date[0] + " AND LEFT(date,10) = '" + user_date[1] + "'");
			Double Geo_lat = null;
			Double Geo_har = null;
			while(rs.next()){
				Geo_lat = rs.getDouble("gps_lat");
				Geo_har = rs.getDouble("gps_har");
				waypoints.add(new DefaultWaypoint(Geo_lat, Geo_har));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return waypoints;
	}
	
	public geo_list_class get_list2(String[][] user_date)
	{
		
		//List<GeoPosition> Geolist = new ArrayList<GeoPosition>();
		//Set<GeoPosition> Geolist2 = new HashSet<GeoPosition>();
		//Set<Waypoint> waypoints = new HashSet<Waypoint>();
		
		geo_list_class load_glc = new geo_list_class();
		
		
		try {
			rs = st.executeQuery("SELECT gps_lat, gps_har FROM tp WHERE id = " + user_date[0] + " AND LEFT(date,10) = '" + user_date[1] + "'");
			Double Geo_lat = null;
			Double Geo_har = null;
			Double Sum1 = 0.0, Sum2 = 0.0;
			Double count = 0.0;
			while(rs.next()){
				Geo_lat = rs.getDouble("gps_lat");
				Geo_har = rs.getDouble("gps_har");
				load_glc.Geolist.add(new GeoPosition(Geo_lat, Geo_har));
				load_glc.waypoints.add(new DefaultWaypoint(Geo_lat, Geo_har));
				Sum1 += Geo_lat;
				Sum2 += Geo_har;
				count++;
			}
			load_glc.Geolist2.add(new GeoPosition((Sum1 / count), (Sum2 / count)));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return load_glc;
	}
	
	class geo_list_class 
	{
		List<GeoPosition> Geolist = new ArrayList<GeoPosition>();
		Set<GeoPosition> Geolist2 = new HashSet<GeoPosition>();
		Set<Waypoint> waypoints = new HashSet<Waypoint>();
		
		
		public geo_list_class get_list(String Sel_date, String Sel_user)
		{
			geo_list_class load_glc = new geo_list_class();
			
			//List<GeoPosition> Geolist = new ArrayList<GeoPosition>();
			try {
				rs = st.executeQuery("SELECT gps_lat, gps_har FROM tp WHERE id = " + Sel_user + " AND LEFT(date,10) = '" + Sel_date + "'");
				Double Geo_lat = null;
				Double Geo_har = null;
				Double Sum1 = 0.0, Sum2 = 0.0;
				Double count = 0.0;
				while(rs.next()){
					Geo_lat = rs.getDouble("gps_lat");
					Geo_har = rs.getDouble("gps_har");
					load_glc.Geolist.add(new GeoPosition(Geo_lat, Geo_har));
					load_glc.waypoints.add(new DefaultWaypoint(Geo_lat, Geo_har));
					Sum1 += Geo_lat;
					Sum2 += Geo_har;
					count++;
				}
				load_glc.Geolist2.add(new GeoPosition((Sum1 / count), (Sum2 / count)));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return load_glc;
		}
	}
	
	class swing_waypoints{
		List<GeoPosition> Geolist = new ArrayList<GeoPosition>();
		Set<GeoPosition> Geolist2 = new HashSet<GeoPosition>();
		Set<SwingWaypoint> waypoints = new HashSet<SwingWaypoint>();
		WaypointPainter<SwingWaypoint> swingWaypointPainter = new SwingWaypointOverlayPainter();
		public swing_waypoints get_Swing_Waypoints(String Sel_date, String Sel_user)
		{
			swing_waypoints load_sw = new swing_waypoints();
			try{
				rs = st.executeQuery("SELECT id, sex, LEFT(birth,4) , gps_lat, gps_har FROM tp WHERE id = " + Sel_user + " AND LEFT(date,10) = '" + Sel_date + "'");
				String id = null;
				String birth = null;
				String gender = null;
				Double Geo_lat = null;
				Double Geo_har = null;
				Double Sum1 = 0.0, Sum2 = 0.0;
				Double count = 0.0;
				while(rs.next()){
					id = rs.getString("id");
					birth = rs.getString(3);
					if (rs.getInt("sex") == 0)
						gender = "Male";
					else 
						gender = "Female";
					
					Geo_lat = rs.getDouble("gps_lat");
					Geo_har = rs.getDouble("gps_har");
					load_sw.Geolist.add(new GeoPosition(Geo_lat, Geo_har));
					load_sw.waypoints.add(new SwingWaypoint(Sel_date,Sel_user, id, birth, gender, Geo_lat, Geo_har, new GeoPosition(Geo_lat, Geo_har)));
					
					//load_glc.Geolist.add(new GeoPosition(Geo_lat, Geo_har));
					//load_glc.waypoints.add(new DefaultWaypoint(Geo_lat, Geo_har));
					Sum1 += Geo_lat;
					Sum2 += Geo_har;
					count++;
				}
				//System.out.println(birth);
				load_sw.Geolist2.add(new GeoPosition((Sum1 / count), (Sum2 / count)));

			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return load_sw;
		}
			 
	}
}



