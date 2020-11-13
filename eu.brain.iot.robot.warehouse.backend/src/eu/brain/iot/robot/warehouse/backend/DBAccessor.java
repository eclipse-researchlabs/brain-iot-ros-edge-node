package eu.brain.iot.robot.warehouse.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.osgi.service.component.annotations.Component;


@Component(
		service = {DBAccessor.class},
		immediate=true)
public class DBAccessor {
	
	  private static final String JDBC_URL = "jdbc:h2:./tasks;DB_CLOSE_DELAY=-1";

	  private static final String USER = "RosEdgeNode";

	  private static final String PASSWORD = "123";

	  private static final String DRIVER_CLASS="org.h2.Driver";
	  
	  
	  
	public synchronized TaskInfo getTask() {
		try {
			Class.forName(DRIVER_CLASS);
			Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM TASK_INFO WHERE pickedStatus=false");

			TaskInfo info = new TaskInfo();
			while (rs.next()) {
				info.taskId = rs.getInt("taskId");
				info.isPicked = rs.getBoolean("pickedStatus");
				info.pickPoseID = rs.getInt("pickPoseID");
				info.placePose = rs.getString("placePose");
				stmt.executeUpdate("UPDATE TASK_INFO SET pickedStatus='" + true + "' WHERE taskId='" + info.taskId + "'");
				stmt.close();
				conn.close();
				return info;
			}
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	  
	  public void changeStatus(int taskId, Boolean status)  // syncronize of table to be considered 
	  {
		  try {
			  Class.forName(DRIVER_CLASS);
			  Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			  Statement stmt = conn.createStatement();
			  stmt.executeUpdate("UPDATE TASK_INFO SET pickedStatus='"+status +"' WHERE taskId='"+taskId+"'");
			  
			  stmt.close();
			  conn.close();	  
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		 
	  }
	  
	  public void printDB()
	  {
		  try {
			  Class.forName(DRIVER_CLASS);
			  Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			  Statement stmt = conn.createStatement();
			  ResultSet rs = stmt.executeQuery("SELECT * FROM TASK_INFO");
			  System.out.println("taskId, pickedStatus, pickPoseID, placePose");
			  while (rs.next()) {
			       System.out.println(rs.getString("taskId") + "," + rs.getString("pickedStatus")+ "," + rs.getString("pickPoseID")+"," +rs.getString("placePose"));
			       }
			  
			  stmt.close();
			  conn.close();
			  
			  
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		  }
}
