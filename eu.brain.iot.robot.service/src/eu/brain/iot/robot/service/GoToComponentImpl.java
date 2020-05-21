package eu.brain.iot.robot.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.osgi.framework.BundleContext;
import org.ros.exception.RemoteException;
import org.ros.exception.RosRuntimeException;
import org.ros.exception.ServiceNotFoundException;
import org.ros.message.MessageFactory;
import org.ros.message.Time;
import org.ros.node.ConnectedNode;
import org.ros.node.service.ServiceClient;
import org.ros.node.service.ServiceResponseListener;

import eu.brain.iot.robot.api.GoToComponent;
import eu.brain.iot.robot.warehouse.Request;
import geometry_msgs.Pose2D;
import geometry_msgs.Vector3;
import procedures_msgs.ProcedureHeader;
import procedures_msgs.ProcedureQuery;
import procedures_msgs.ProcedureQueryRequest;
import procedures_msgs.ProcedureQueryResponse;
import robot_local_control_msgs.GoTo;
import robot_local_control_msgs.GoToPetitionResponse;
import robot_local_control_msgs.MovePetitionResponse;
import robot_local_control_msgs.Pose2DStamped;
import robot_local_control_msgs.Twist2D;
import std_msgs.Header;

public class GoToComponentImpl implements GoToComponent {
	private String name;
	private ConnectedNode node;
	private MessageFactory Factory;
	
	ServiceClient<robot_local_control_msgs.GoToPetitionRequest,robot_local_control_msgs.GoToPetitionResponse> serviceClient_GoTo;
	ServiceClient<ProcedureQueryRequest,ProcedureQueryResponse> serviceClient_GoTo_cancel;
	ServiceClient<ProcedureQueryRequest,ProcedureQueryResponse>  serviceClient_GoTo_Query_State;

	
	public GoToComponentImpl(String name,ConnectedNode node,MessageFactory Factory){
		this.name = name;
		this.node=node;
		this.Factory=Factory;
	}
	
	public void register(){
	  try {
		serviceClient_GoTo=node.newServiceClient("/"+name+"/robot_local_control/NavigationComponent/GoToComponent/add",robot_local_control_msgs.GoToPetition._TYPE);
	    serviceClient_GoTo_cancel= node.newServiceClient("/"+name+"/robot_local_control/NavigationComponent/GoToComponent/cancel", procedures_msgs.ProcedureQuery._TYPE);
	    serviceClient_GoTo_Query_State= node.newServiceClient("/"+name+ "/robot_local_control/NavigationComponent/GoToComponent/query_state", procedures_msgs.ProcedureQuery._TYPE);
	  } catch (ServiceNotFoundException e) {
		throw new RosRuntimeException(e);
	  }  	
	} 

   public int call_GoTo(robot_local_control_msgs.GoToPetitionRequest GoTorequest){
	   long startTime = System.currentTimeMillis();
	   AtomicReference<String> responseAtom=new  AtomicReference<String>();
	   serviceClient_GoTo.call(GoTorequest, new ServiceResponseListener<robot_local_control_msgs.GoToPetitionResponse>(){

		@Override
		public void onFailure(RemoteException e) {
			throw new RosRuntimeException(e);
		}

		@Override
		public void onSuccess(GoToPetitionResponse response) {

			// TODO Auto-generated method stub
			System.out.println("\n GoToComponent: GET GoTo Response: result = "+response.getResult().getResult()+"  state = "+ response.getState().getCurrentState());
			responseAtom.set(response.getResult().getResult());
		}
		
	  });
	   while(responseAtom.get()==null)
	   {
		   long endTime = System.currentTimeMillis();
			if ((endTime-startTime)>10000)
			{
				System.out.println("Response timeout");
				return 0;
			}
	   }// wait the receving of the response
	   if (responseAtom.get().compareTo("ok")==0)
			 return 1;
		 else
			 return 0;
	  } 

 
	public int call_Cancel(procedures_msgs.ProcedureQueryRequest cancelRequest) {
		long startTime = System.currentTimeMillis();
		AtomicReference<String> response = new AtomicReference<String>();
		serviceClient_GoTo_cancel.call(cancelRequest, new ServiceResponseListener<procedures_msgs.ProcedureQueryResponse>() {

			@Override
			public void onFailure(RemoteException e) {
				throw new RosRuntimeException(e);
			}
			@Override
			public void onSuccess(ProcedureQueryResponse cancelResponse) {
<<<<<<< HEAD
=======
				
>>>>>>> 580dd4b3ab85979c286751f9745f5b844b58fa0a
				System.out.println("\n GoToComponent: GET GoTo_Cancel Response: result = "+cancelResponse.getResult().getResult()+"  state = "+ cancelResponse.getState().getCurrentState());
				response.set(cancelResponse.getState().getCurrentState());
			}
		});
		while (response.get() == null)
		{
			long endTime = System.currentTimeMillis();
			if ((endTime-startTime)>10000)
			{
				System.out.println("Response timeout");
				return 0;
			}
		}// wait the receiving of the response

		if (response.get().compareTo("finished") == 0)
			return 1;
		else
			return 0;
	}
	
   public int  call_Query_State(procedures_msgs.ProcedureQueryRequest queryStateRequest) {
	   AtomicReference<String> response=new  AtomicReference<String>();  
	   long startTime = System.currentTimeMillis();
	   serviceClient_GoTo_Query_State.call(queryStateRequest, new ServiceResponseListener<procedures_msgs.ProcedureQueryResponse>(){
	       
			@Override
			public void onFailure(RemoteException e) {
				throw new RosRuntimeException(e);
			}
			@Override
			public void onSuccess(ProcedureQueryResponse queryStateResponse) {
				if(queryStateResponse.getState().getCurrentState().equals("finished"))
<<<<<<< HEAD
				System.out.println("\n GoToComponent: GET GoTo_Query_State Response: result = "+queryStateResponse.getResult().getResult()+"  state = "+ queryStateResponse.getState().getCurrentState());
=======
				System.out.println(" GoToComponent: GET GoTo_Query_State Response: result = "+queryStateResponse.getResult().getResult()+"  state = "+ queryStateResponse.getState().getCurrentState());
>>>>>>> 580dd4b3ab85979c286751f9745f5b844b58fa0a
				response.set(queryStateResponse.getState().getCurrentState());		
			}
		});

		while (response.get() == null)
		{
			long endTime = System.currentTimeMillis();
			if ((endTime-startTime)>10000)
			{
				System.out.println("Response timeout");
				return 0;
			}
		}// wait the receiving of the response

		String state = response.get();
		int query_state;
		if (state.compareTo("finished") == 0)
			query_state = 1;
		else if (state.compareTo("queued") == 0)
			query_state = 2;
		else if (state.compareTo("running") == 0)
			query_state = 3;
		else if (state.compareTo("paused") == 0)
			query_state = 4;
		else if (state.compareTo("unknown") == 0)
			query_state = 5;
		else
			query_state = 0;
		return query_state;
	}

	public robot_local_control_msgs.GoToPetitionRequest constructGoToMessage() {
		robot_local_control_msgs.GoToPetitionRequest GoToRequest = serviceClient_GoTo.newMessage();
		GoTo procedure = Factory.newFromType(GoTo._TYPE);
		// --- construct goals-----
		List<Pose2DStamped> goals = new LinkedList<Pose2DStamped>();
		Pose2DStamped goal = Factory.newFromType(Pose2DStamped._TYPE);
		Header header = Factory.newFromType(Header._TYPE);
		header.setFrameId("map");
		header.setSeq(0);
		Time time = new Time();
		Time.fromMillis(0);
		Time.fromNano(0);
		header.setStamp(time);
		;
		goal.setHeader(header);
		Pose2D pose = Factory.newFromType(Pose2D._TYPE);
		/*
		 * pose.setTheta(-3.14); pose.setX(8); pose.setY(-3.6); goal.setPose(pose);
		 */
		pose.setTheta(-3.14);
		pose.setX(0);
		pose.setY(0);
		goal.setPose(pose);
		goals.add(goal);
		// ------construct velocities-------
		List<Twist2D> velocities = new LinkedList<Twist2D>();
		Twist2D twist2D = Factory.newFromType(Twist2D._TYPE);
		twist2D.setAngularZ(0.0);
		twist2D.setLinearX(0.0);
		twist2D.setLinearY(0.0);
		velocities.add(twist2D);
		procedure.setGoals(goals);
		procedure.setMaxVelocities(velocities);
		GoToRequest.setProcedure(procedure);
		return GoToRequest;
	}
   
   
   public robot_local_control_msgs.GoToPetitionRequest constructGoToMessage(Request req) {
	   	robot_local_control_msgs.GoToPetitionRequest GoToRequest = serviceClient_GoTo.newMessage();
	   	GoTo procedure=Factory.newFromType(GoTo._TYPE);
	   	// --- construct goals-----
	   	List<Pose2DStamped> goals= new LinkedList<Pose2DStamped>(); 
	   	Pose2DStamped goal=Factory.newFromType(Pose2DStamped._TYPE);
	   	Header header=Factory.newFromType(Header._TYPE);
	   	header.setFrameId("map");
	   	header.setSeq(0);
	   	Time time= new Time();
	   	Time.fromMillis(0);
	   	Time.fromNano(0);
	   	header.setStamp(time);;
		goal.setHeader(header);
	   	Pose2D pose=Factory.newFromType(Pose2D._TYPE);
	   /*pose.setTheta(-3.14);
	   	pose.setX(8);
	   	pose.setY(-3.6);
		goal.setPose(pose);*/
	   	pose.setTheta(req.pose.getTheta());
	   	pose.setX(req.pose.getX());
	   	pose.setY(req.pose.getY());
		goal.setPose(pose);
		goals.add(goal);
		//------construct velocities-------
		List<Twist2D> velocities= new LinkedList<Twist2D>();
		Twist2D twist2D= Factory.newFromType(Twist2D._TYPE);
		twist2D.setAngularZ(0.0);
		twist2D.setLinearX(1.0);
		twist2D.setLinearY(1.0);
		velocities.add(twist2D);
		procedure.setGoals(goals);
	   	procedure.setMaxVelocities(velocities);
		GoToRequest.setProcedure(procedure);
		return GoToRequest;
   }
   
   
   
   
   
   
   
   
   public procedures_msgs.ProcedureQueryRequest constructCancleMessage(){
	   procedures_msgs.ProcedureQueryRequest cancelRequest=serviceClient_GoTo_cancel.newMessage();
	   ProcedureHeader header=Factory.newFromType(ProcedureHeader._TYPE);
	   header.setId(-1);
	   header.setPriority(0);
	   Time time=new Time();
	   Time.fromMillis(0);
	   Time.fromNano(0);
	   header.setStamp(time);
	   header.setName("");
	   cancelRequest.setHeader(header);
	   return cancelRequest;
	   
   }
   public procedures_msgs.ProcedureQueryRequest constructQueryStateMessage(){
	   procedures_msgs.ProcedureQueryRequest cancelRequest=serviceClient_GoTo_Query_State.newMessage();
	   ProcedureHeader header=Factory.newFromType(ProcedureHeader._TYPE);
	   header.setId(-1);
	   header.setPriority(0);
	   Time time=new Time();
	   Time.fromMillis(0);
	   Time.fromNano(0);
	   header.setStamp(time);
	   header.setName("");
	   cancelRequest.setHeader(header);
	   return cancelRequest;   
   }
   }

