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

import eu.brain.iot.robot.api.PlaceComponent;
import procedures_msgs.ProcedureHeader;
import procedures_msgs.ProcedureQueryRequest;
import procedures_msgs.ProcedureQueryResponse;
import robot_local_control_msgs.GoToPetitionResponse;
import robot_local_control_msgs.Place;
import robot_local_control_msgs.PlacePetitionResponse;

public class PlaceComponentImpl implements PlaceComponent {
	private String name;
	private ConnectedNode node;
	private MessageFactory Factory;
	ServiceClient<robot_local_control_msgs.PlacePetitionRequest,robot_local_control_msgs.PlacePetitionResponse> serviceClient_Place;
	ServiceClient<ProcedureQueryRequest,ProcedureQueryResponse> serviceClient_Place_Cancel;
	ServiceClient<ProcedureQueryRequest,ProcedureQueryResponse>  serviceClient_Place_Query_State;
	
	public PlaceComponentImpl(String name, ConnectedNode node,MessageFactory Factory){
		this.name = name;
		this.node=node;
		this.Factory=Factory;
	}
	public void register() {
		try {
			serviceClient_Place=node.newServiceClient("/"+name+"/robot_local_control/NavigationComponent/PlaceComponent/add",robot_local_control_msgs.PlacePetition._TYPE);
		    serviceClient_Place_Cancel= node.newServiceClient("/"+name+"/robot_local_control/NavigationComponent/PlaceComponent/cancel", procedures_msgs.ProcedureQuery._TYPE);
		    serviceClient_Place_Query_State= node.newServiceClient("/"+name+ "/robot_local_control/NavigationComponent/PlaceComponent/query_state", procedures_msgs.ProcedureQuery._TYPE);
		  } catch (ServiceNotFoundException e) {
			throw new RosRuntimeException(e);
		}  	
	}
	
	 public int call_Place(robot_local_control_msgs.PlacePetitionRequest Placerequest){
		  long startTime = System.currentTimeMillis();
		  AtomicReference<String> responseAtom=new  AtomicReference<String>();
		  serviceClient_Place.call(Placerequest, new ServiceResponseListener<robot_local_control_msgs.PlacePetitionResponse>(){
	       
			@Override
			public void onFailure(RemoteException e) {
				throw new RosRuntimeException(e);
			}

			@Override
			public void onSuccess(PlacePetitionResponse placeResponse) {
				System.out.print("\n PlaceComponent: GET Place Response:");
				System.out.print("result:"+placeResponse.getResult().getResult());
				System.out.print("    ");
				System.out.println("state:"+placeResponse.getState().getCurrentState());
				responseAtom.set(placeResponse.getResult().getResult());
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
	 public int call_Cancel(procedures_msgs.ProcedureQueryRequest cancelPlaceRequest) {
		 long startTime = System.currentTimeMillis();
		 AtomicReference<String> response=new  AtomicReference<String>();
		 serviceClient_Place_Cancel.call(cancelPlaceRequest, new ServiceResponseListener<procedures_msgs.ProcedureQueryResponse>() {
         
			@Override
			public void onFailure(RemoteException e) {
				throw new RosRuntimeException(e);
			}

			@Override
			public void onSuccess(ProcedureQueryResponse cancelPlaceResponse) {
				System.out.print("\n PlaceComponent: GET Place_Cancel Response:");
				System.out.print("result:"+cancelPlaceResponse.getResult().getResult());
				System.out.print("    ");
				System.out.println("state:"+cancelPlaceResponse.getState().getCurrentState());
				response.set(cancelPlaceResponse.getState().getCurrentState());
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
		 
		 if (response.get().compareTo("finished")==0)
			 return 1;
		 else
			 return 0;
	 }
	 public int call_Query_State(procedures_msgs.ProcedureQueryRequest queryPlaceStateRequest) {
		 AtomicReference<String> response=new  AtomicReference<String>();
		 long startTime = System.currentTimeMillis();
		 serviceClient_Place_Query_State.call(queryPlaceStateRequest, new ServiceResponseListener<procedures_msgs.ProcedureQueryResponse>() {

			@Override
			public void onFailure(RemoteException e) {
				throw new RosRuntimeException(e);
			}

			@Override
			public void onSuccess(ProcedureQueryResponse queryStateResponse) {
				if(queryStateResponse.getState().getCurrentState().equals("finished"))
				System.out.println("\n PlaceComponent: Place_Query_State Response: result= "+
						queryStateResponse.getResult().getResult()+"  state = "+
						queryStateResponse.getState().getCurrentState());
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
		 } // wait the receiving of the response		 
		 
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
    public robot_local_control_msgs.PlacePetitionRequest constructPlaceRequest(){
    	robot_local_control_msgs.PlacePetitionRequest placeRequest=serviceClient_Place.newMessage();
    	Place place = Factory.newFromType(Place._TYPE);
    	place.setPickFrameId("");
		placeRequest.setProcedure(place);
    	return placeRequest;
    	
    }
	public procedures_msgs.ProcedureQueryRequest constructPlaceCancleMessage(){
		   procedures_msgs.ProcedureQueryRequest placeCancelRequest=serviceClient_Place_Cancel.newMessage();
		   ProcedureHeader header=Factory.newFromType(ProcedureHeader._TYPE);
		   header.setId(-1);
		   header.setPriority(0);
		   Time time=new Time();
		   Time.fromMillis(0);
		   Time.fromNano(0);
		   header.setStamp(time);
		   header.setName("");
		   placeCancelRequest.setHeader(header);
		   return placeCancelRequest; 
	   }
	public procedures_msgs.ProcedureQueryRequest constructPlaceQuerySateMessage(){
		   procedures_msgs.ProcedureQueryRequest placeQueryRequest=serviceClient_Place_Query_State.newMessage();
		   ProcedureHeader header=Factory.newFromType(ProcedureHeader._TYPE);
		   header.setId(-1);
		   header.setPriority(0);
		   Time time=new Time();
		   Time.fromMillis(0);
		   Time.fromNano(0);
		   header.setStamp(time);
		   header.setName("");
		   placeQueryRequest.setHeader(header);
		   return placeQueryRequest; 
	   }
}
