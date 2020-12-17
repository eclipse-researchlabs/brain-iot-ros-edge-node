package eu.brain.iot.robot.tables.queryer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import eu.brain.iot.eventing.annotation.SmartBehaviourDefinition;
import eu.brain.iot.eventing.api.EventBus;
import eu.brain.iot.eventing.api.SmartBehaviour;
import eu.brain.iot.warehouse.events.CartMovedNotice;
import eu.brain.iot.warehouse.events.CartNoticeResponse;
import eu.brain.iot.warehouse.events.DockingRequest;
import eu.brain.iot.warehouse.events.DockingResponse;
import eu.brain.iot.warehouse.events.NewPickPointRequest;
import eu.brain.iot.warehouse.events.NewPickPointResponse;
import eu.brain.iot.warehouse.events.NewStoragePointRequest;
import eu.brain.iot.warehouse.events.NewStoragePointResponse;
import eu.brain.iot.warehouse.events.NoCartNotice;
import eu.brain.iot.robot.api.Coordinate;
import eu.brain.iot.robot.api.RobotCommand;
import eu.brain.iot.robot.events.WriteGoTo;

@Component(service = { TableQueryer.class },
					   immediate = true)
@SmartBehaviourDefinition(
		consumed = { NewPickPointRequest.class, NewStoragePointRequest.class, NoCartNotice.class,
		CartMovedNotice.class, DockingRequest.class }, 
		author = "LINKS", name = "Warehouse Backend", 
		description = "Implements the Tables Queryer in Warehouse Backend.")

public class TableQueryer implements SmartBehaviour<RobotCommand> { // TODO must able to cache multiple requests

	private static String base = "/home/rui/git/ros-edge-node/eu.brain.iot.robot.tables.creator";

	private static final String JDBC_URL = "jdbc:h2:" + base + "/tables;DB_CLOSE_DELAY=-1";

	private static final String USER = "RosEdgeNode";

	private static final String PASSWORD = "123";

	private static final String DRIVER_CLASS = "org.h2.Driver";

	private Connection conn;
	private Statement stmt;
	private ExecutorService worker;
	private ServiceRegistration<?> reg;
	
	@Reference
	private EventBus eventBus;
	

	@Activate
	public void activate(BundleContext context, Map<String, Object> props) throws SQLException {
		try {
			System.out.println("\nHello, this is Table Queryer !");
			
			Class.forName(DRIVER_CLASS);

			conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			stmt = conn.createStatement();

			worker = Executors.newFixedThreadPool(10);
			
			Dictionary<String, Object> serviceProps = new Hashtable<>(props.entrySet().stream()
					.filter(e -> !e.getKey().startsWith(".")).collect(Collectors.toMap(Entry::getKey, Entry::getValue)));

			serviceProps.put(SmartBehaviourDefinition.PREFIX_ + "filter",  // -1, get all events
					String.format("(|(robotID=%s)(robotID=%s))", 2, RobotCommand.ALL_ROBOTS));

		//	serviceProps.put(SmartBehaviourDefinition.PREFIX_ + "filter",  // -1, get all events
		//			String.format("((robotID=%s))", RobotCommand.ALL_ROBOTS));
			
			System.out.println("+++++++++ Table Queryer filter = " + serviceProps.get(SmartBehaviourDefinition.PREFIX_ + "filter"));
			reg = context.registerService(SmartBehaviour.class, this, serviceProps);
			
			System.out.println("------------  PickingTable ----------------");

			  ResultSet rs = stmt.executeQuery("SELECT * FROM PickingTable");

			  while (rs.next()) {
			       System.out.println(rs.getString("PPid") + ", " + rs.getString("pose")+ ", " + rs.getString("isAssigned"));
			  }
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void notify(RobotCommand event) {

		System.out.println("--> Table Queryer received a "+event.getClass().getSimpleName()+" event");
		
		if (event instanceof NewPickPointRequest) {
			NewPickPointRequest pickRequest = (NewPickPointRequest) event;
			NewPickPointResponse rs = getPickResponse(pickRequest);
			System.out.println("Queryer  sent NewPickPointResponse "+ rs);
			eventBus.deliver(rs);
		/*	worker.execute(() -> {
			//	System.out.println("--> Table Queryer received NewPickPointRequest event");
				eventBus.deliver(getPickResponse(pickRequest));
			});*/
			
		} else if (event instanceof NewStoragePointRequest) {
			NewStoragePointRequest storageRequest = (NewStoragePointRequest) event;
			worker.execute(() -> {
		//		System.out.println("--> Table Queryer received NewStoragePointRequest event");
				eventBus.deliver(getStorageResponse(storageRequest));
			});
			
		} else if (event instanceof DockingRequest) {
			DockingRequest dockRequest = (DockingRequest) event;
			worker.execute(() -> {
				System.out.println("--> Table Queryer received DockingRequest event");
				eventBus.deliver(getDockResponse(dockRequest));
			});
			
		} else if (event instanceof CartMovedNotice) {
			CartMovedNotice cartMovedNotice = (CartMovedNotice) event;
			worker.execute(() -> {
				System.out.println("--> Table Queryer received CartMovedNotice event");
				eventBus.deliver(getCartMovedNotice(cartMovedNotice));
			});
			
		} else if (event instanceof NoCartNotice) {
			NoCartNotice noCartNotice = (NoCartNotice) event;
			worker.execute(() -> {
				System.out.println("--> Table Queryer received NoCartNotice event");
				eventBus.deliver(getNoCartNotice(noCartNotice));
			});
		}
	}
	

	private NewPickPointResponse getPickResponse(NewPickPointRequest pickRequest) {

		NewPickPointResponse pickReponse = new NewPickPointResponse();
		pickReponse.robotID = pickRequest.robotID;

		ResultSet rs;
		try {
			rs = stmt.executeQuery("SELECT * FROM PickingTable WHERE isAssigned=false");

			while (rs.next()) {

				pickReponse.hasNewPoint = true;

				pickReponse.pickPoint = "8.0,-5.5,-3.14";
		//		pickReponse.pickPoint = getCoordinate(rs.getString("pose"));
				System.out.println("--> Table Queryer got a pickPoint "+pickReponse.pickPoint);
				stmt.executeUpdate(
						"UPDATE PickingTable SET isAssigned='" + true + "' WHERE PPid='" + rs.getString("PPid") + "'");
				break;
			}
			
			System.out.println("------------  PickingTable ----------------");
			  rs = stmt.executeQuery("SELECT * FROM PickingTable");
			  while (rs.next()) {
			       System.out.println(rs.getString("PPid") + ", " + rs.getString("pose")+ ", " + rs.getString("isAssigned"));
			  }
			  
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pickReponse;
	}

	private NewStoragePointResponse getStorageResponse(NewStoragePointRequest storageRequest) {

		int markerID = storageRequest.markerID;
		String storageID = null;

		NewStoragePointResponse storageReponse = new NewStoragePointResponse();
		storageReponse.robotID = storageRequest.robotID;

		ResultSet rs;
		try {
			rs = stmt.executeQuery("SELECT * FROM CartTable WHERE cartID=" + markerID);

			while (rs.next()) {
				storageID = rs.getString("storageID");
			}
			if (storageID != null) {

				rs = stmt.executeQuery("SELECT * FROM StorageTable WHERE STid = " +storageID);
				
				while (rs.next()) {

					storageReponse.hasNewPoint = true;
					storageReponse.storageAuxliaryPoint = getCoordinate(rs.getString("storageAUX"));
					storageReponse.storagePoint = getCoordinate(rs.getString("storagePose"));
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return storageReponse;
	}
	
	private DockingResponse getDockResponse(DockingRequest dockingRequest) {

		DockingResponse dockReponse = new DockingResponse();
		dockReponse.robotID = dockingRequest.robotID;

		ResultSet rs;
		try {
			rs = stmt.executeQuery("SELECT * FROM DockTable WHERE IPid = "+ dockingRequest.robotIP);

			while (rs.next()) {

				dockReponse.hasNewPoint = true;
				dockReponse.dockAuxliaryPoint = getCoordinate(rs.getString("dockAUX"));
				dockReponse.dockingPoint = getCoordinate(rs.getString("dockPose"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dockReponse;
	}
	

	private Coordinate getCoordinate(String crd) {

		Coordinate cord = new Coordinate();

		String[] strs = crd.split(",");
		cord.setX(new Double(strs[0]).doubleValue());
		cord.setY(new Double(strs[1]).doubleValue());
		cord.setZ(new Double(strs[2]).doubleValue());

		return cord;
	}
	
	private CartNoticeResponse getCartMovedNotice(CartMovedNotice cartMovedNotice) {

		CartNoticeResponse cartNoticeResponse = new CartNoticeResponse();
		cartNoticeResponse.robotID = cartMovedNotice.robotID;

		Coordinate targetPoint = cartMovedNotice.pickPoint;
		Coordinate pickPose = null;
		ResultSet rs;
		try {
			rs = stmt.executeQuery("SELECT * FROM PickingTable WHERE isAssigned=true");

			while (rs.next()) {
				pickPose = getCoordinate(rs.getString("pose"));
				
				if(pickPose.getX() == targetPoint.getX() && pickPose.getY() == targetPoint.getY() && pickPose.getZ() == targetPoint.getZ()) {
					stmt.executeUpdate(
							"UPDATE PickingTable SET isAssigned='" + false + "' WHERE PPid='" + rs.getString("PPid") + "'");
					pickPose = null;
					break;
				}
				pickPose = null;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cartNoticeResponse;
	}
	
	private CartNoticeResponse getNoCartNotice(NoCartNotice noCartNotice) {

		CartNoticeResponse cartNoticeResponse = new CartNoticeResponse();
		cartNoticeResponse.robotID = noCartNotice.robotID;

		Coordinate targetPoint = noCartNotice.pickPoint;
		Coordinate pickPose = null;
		ResultSet rs;
		try {
			rs = stmt.executeQuery("SELECT * FROM PickingTable WHERE isAssigned=true");

			while (rs.next()) {
				pickPose = getCoordinate(rs.getString("pose"));
				
				if(pickPose.getX() == targetPoint.getX() && pickPose.getY() == targetPoint.getY() && pickPose.getZ() == targetPoint.getZ()) {
					stmt.executeUpdate(
							"UPDATE PickingTable SET isAssigned='" + false + "' WHERE PPid='" + rs.getString("PPid") + "'");
					pickPose = null;
					break;
				}
				pickPose = null;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cartNoticeResponse;
	}
	
	
	@Deactivate
	void stop() {
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		worker.shutdown();
		try {
			worker.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
	}


}
