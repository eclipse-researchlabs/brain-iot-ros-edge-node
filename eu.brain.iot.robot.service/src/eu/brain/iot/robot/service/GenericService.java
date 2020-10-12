package eu.brain.iot.robot.service;

import java.util.concurrent.atomic.AtomicReference;
import org.ros.exception.RemoteException;
import org.ros.exception.RosRuntimeException;
import org.ros.exception.ServiceNotFoundException;
import org.ros.node.ConnectedNode;
import org.ros.node.service.ServiceClient;
import org.ros.node.service.ServiceResponseListener;


public class GenericService<T1, T2>  {
	
	private ConnectedNode node;
	public ServiceClient<T1,T2> serviceClient;	
	
	public GenericService(ConnectedNode node){
		this.node=node;
	}
	
	public void register(String topic,String type){
		  try {
			serviceClient=node.newServiceClient(topic,type);
		  } catch (ServiceNotFoundException e) {
			throw new RosRuntimeException(e);
		  }  	
		} 
	
	 public T2 call(T1 request){
		   long startTime = System.currentTimeMillis();
		   AtomicReference<T2> responseAtom=new  AtomicReference<T2>();
		   serviceClient.call(request,  new ServiceResponseListener<T2>(){
			@Override
			public void onFailure(RemoteException e) {
				throw new RosRuntimeException(e);
			}
			@Override
			public void onSuccess(T2 response) {

				// TODO Auto-generated method stub
				responseAtom.set(response);
			}
		  });
		   while(responseAtom.get()==null)
		   {
			   long endTime = System.currentTimeMillis();
				if ((endTime-startTime)>10000)
				{
					System.out.println("Response timeout 10s");
					return null;
				}
		   }// wait the receving of the response
		   return responseAtom.get();
		  } 

}
