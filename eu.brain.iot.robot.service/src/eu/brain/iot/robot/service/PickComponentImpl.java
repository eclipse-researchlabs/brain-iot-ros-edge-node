package eu.brain.iot.robot.service;

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

import eu.brain.iot.robot.api.PickComponent;
import procedures_msgs.ProcedureHeader;
import procedures_msgs.ProcedureQuery;
import procedures_msgs.ProcedureQueryRequest;
import procedures_msgs.ProcedureQueryResponse;
import robot_local_control_msgs.GoToPetitionResponse;
import robot_local_control_msgs.Pick;
import robot_local_control_msgs.PickPetitionResponse;

public class PickComponentImpl implements PickComponent{
	private String name;
	private ConnectedNode node;
	private MessageFactory Factory;

	ServiceClient<robot_local_control_msgs.PickPetitionRequest,robot_local_control_msgs.PickPetitionResponse> serviceClient_Pick;
	ServiceClient<ProcedureQueryRequest,ProcedureQueryResponse> serviceClient_Pick_Cancel;
	ServiceClient<ProcedureQueryRequest,ProcedureQueryResponse>  serviceClient_Pick_Query_State;
	
	public PickComponentImpl(String name, ConnectedNode node,MessageFactory Factory){
		this.name = name;
		this.node=node;
		this.Factory=Factory;
	}
	
	public void register(){
		try {
			serviceClient_Pick=node.newServiceClient("/"+name+"/robot_local_control/NavigationComponent/PickComponent/add",robot_local_control_msgs.PickPetition._TYPE);
		    serviceClient_Pick_Cancel= node.newServiceClient("/"+name+"/robot_local_control/NavigationComponent/PickComponent/cancel", procedures_msgs.ProcedureQuery._TYPE);
		    serviceClient_Pick_Query_State= node.newServiceClient("/"+name+ "/robot_local_control/NavigationComponent/PickComponent/query_state", procedures_msgs.ProcedureQuery._TYPE);
		} catch (ServiceNotFoundException e) {
			throw new RosRuntimeException(e);
		}  	
	} 
	
	public int call_Pick(robot_local_control_msgs.PickPetitionRequest PickRequest) {
		long startTime = System.currentTimeMillis();
		AtomicReference<String> responseAtom=new  AtomicReference<String>();
		serviceClient_Pick.call(PickRequest,
				new ServiceResponseListener<robot_local_control_msgs.PickPetitionResponse>() {

					@Override
					public void onFailure(RemoteException e) {
						throw new RosRuntimeException(e);
					}

					@Override
					public void onSuccess(PickPetitionResponse PickResponse) {
						System.out.println("\n PickComponent: GET Pick Response: result = " + PickResponse.getResult().getResult()
								+ "  state = " + PickResponse.getState().getCurrentState());
						responseAtom.set(PickResponse.getResult().getResult());
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
		serviceClient_Pick_Cancel.call(cancelRequest,
				new ServiceResponseListener<procedures_msgs.ProcedureQueryResponse>() {

					@Override
					public void onFailure(RemoteException e) {
						throw new RosRuntimeException(e);
					}

					@Override
					public void onSuccess(ProcedureQueryResponse cancelResponse) {
						System.out.println("\n PickComponent: GET Pick_Cancel Response: result = " + cancelResponse.getResult().getResult()
								+ "  state = " + cancelResponse.getState().getCurrentState());
						
						response.set(cancelResponse.getState().getCurrentState());
					}

				});
		 
		 while(response.get()==null)
		 {
			long endTime = System.currentTimeMillis();
			if ((endTime-startTime)>10000)
			{
				System.out.println("Response timeout");
				return 0;
			}
		 }
		 
		 if (response.get().compareTo("finished")==0)
			 return 1;
		 else
			 return 0;
	 }
	
	 public int call_Query_State(procedures_msgs.ProcedureQueryRequest queryPickStateRequest) {
		 AtomicReference<String> response=new  AtomicReference<String>();
		 long startTime = System.currentTimeMillis();
		 serviceClient_Pick_Query_State.call(queryPickStateRequest, new ServiceResponseListener<procedures_msgs.ProcedureQueryResponse>() {

			@Override
			public void onFailure(RemoteException e) {
				throw new RosRuntimeException(e);
			}

			@Override
			public void onSuccess(ProcedureQueryResponse queryStateResponse) {
				if(queryStateResponse.getState().getCurrentState().equals("finished"))
				System.out.println("\n PickComponent: Pick_Query_State Response: result= "+ queryStateResponse.getResult().getResult()
						+ "  state = " + queryStateResponse.getState().getCurrentState());				
				response.set(queryStateResponse.getState().getCurrentState());
			} 
		 });
		 
		 while(response.get()==null)
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
	public robot_local_control_msgs.PickPetitionRequest constructPickMessage(String PickFrameId){
		robot_local_control_msgs.PickPetitionRequest pickRequest=serviceClient_Pick.newMessage();
		Pick procedure= Factory.newFromType(Pick._TYPE);
		procedure.setPickFrameId(PickFrameId);
		pickRequest.setProcedure(procedure);
		return pickRequest;
		
	}
	public procedures_msgs.ProcedureQueryRequest constructPickCancleMessage(){
		   procedures_msgs.ProcedureQueryRequest pickCancelRequest=serviceClient_Pick_Cancel.newMessage();
		   ProcedureHeader header=Factory.newFromType(ProcedureHeader._TYPE);
		   header.setId(-1);
		   header.setPriority(0);
		   Time time=new Time();
		   Time.fromMillis(0);
		   Time.fromNano(0);
		   header.setStamp(time);
		   header.setName("");
		   pickCancelRequest.setHeader(header);
		   return pickCancelRequest; 
	   }
	public procedures_msgs.ProcedureQueryRequest constructPickQuerySateMessage(){
		   procedures_msgs.ProcedureQueryRequest pickQueryRequest=serviceClient_Pick_Query_State.newMessage();
		   ProcedureHeader header=Factory.newFromType(ProcedureHeader._TYPE);
		   header.setId(-1);
		   header.setPriority(0);
		   Time time=new Time();
		   Time.fromMillis(0);
		   Time.fromNano(0);
		   header.setStamp(time);
		   header.setName("");
		   pickQueryRequest.setHeader(header);
		   return pickQueryRequest; 
	   }
}
