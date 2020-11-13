package eu.brain.iot.robot.database.creator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(
		service = {DBCreater.class},
		immediate=true
	)
public class DBCreater {
	//Define the connection of database 
	  private static final String JDBC_URL = "jdbc:h2:./tasks;DB_CLOSE_DELAY=-1";//"./test":DB locaiton;"DB_CLOSE_DELAY=-1":allow single connection 

	  private static final String USER = "RosEdgeNode";

	  private static final String PASSWORD = "123";

	  private static final String DRIVER_CLASS="org.h2.Driver";
	  @Activate
	  public void init()
	  {
		  try {
			  Class.forName(DRIVER_CLASS);

			  Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			  Statement stmt = conn.createStatement();

			  stmt.execute("DROP TABLE IF EXISTS TASK_INFO");

			  stmt.execute("CREATE TABLE TASK_INFO(taskId INT PRIMARY KEY,pickedStatus BOOLEAN, pickPoseID INT, placePose VARCHAR(20))");

			  stmt.executeUpdate("INSERT INTO TASK_INFO VALUES('1','false','1','PLACE_C')");
			  stmt.executeUpdate("INSERT INTO TASK_INFO VALUES('2','false','2','PLACE_L')");
			  stmt.executeUpdate("INSERT INTO TASK_INFO VALUES('3','false','3','PLACE_R')");

			  ResultSet rs = stmt.executeQuery("SELECT * FROM TASK_INFO");

			  while (rs.next()) {
			       System.out.println(rs.getString("taskId") + ", " + rs.getString("pickedStatus")+ ", " + rs.getString("pickPoseID")+", " +rs.getString("placePose"));
			  }

			   stmt.close();
			 conn.close();
			 }catch (Exception e)
		  {
				System.out.println("Multiple access to DB");
				e.printStackTrace();
		  }
	  }
	  
	
}
