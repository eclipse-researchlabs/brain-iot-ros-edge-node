/*******************************************************************************
 * *  Copyright (C) 2021 LINKS Foundation
 * 
 * This file is based on the ROSOSGi open-source project which is a part of DIANNE  -  Framework for distributed artificial neural networks
 * 
 * DIANNE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package eu.brain.iot.ros.edge.node;

import java.util.concurrent.atomic.AtomicReference;
import org.ros.exception.RemoteException;
import org.ros.exception.RosRuntimeException;
import org.ros.exception.ServiceNotFoundException;
import org.ros.node.ConnectedNode;
import org.ros.node.service.ServiceClient;
import org.ros.node.service.ServiceResponseListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GenericService<T1, T2>  {
	
	private ConnectedNode node;
	public ServiceClient<T1,T2> serviceClient;	
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(GenericService.class.getSimpleName());
	
	public GenericService(ConnectedNode node){
		this.node=node;
	}
	
	public void register(String topic,String type) throws Exception{
		
			serviceClient=node.newServiceClient(topic,type);

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
				responseAtom.set(response);
			}
		  });
		   while(responseAtom.get()==null)
		   {
			   long endTime = System.currentTimeMillis();
				if ((endTime-startTime)>10000)
				{
					System.out.println("Response timeout 10s");
					logger.info("Response timeout 10s");
					return null;
				}
		   }// wait the receving of the response
		   return responseAtom.get();
		  } 

}
