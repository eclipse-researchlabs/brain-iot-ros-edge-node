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

import org.ros.message.MessageFactory;
import org.ros.message.Time;
import org.ros.node.ConnectedNode;
import procedures_msgs.ProcedureHeader;
import procedures_msgs.ProcedureQueryRequest;
import procedures_msgs.ProcedureQueryResponse;
import procedures_msgs.ProcedureResult;
import procedures_msgs.ProcedureState;
import robot_local_control_msgs.PlacePetitionRequest;
import robot_local_control_msgs.PlacePetitionResponse;


/**
 * When there has no abstract methods in the class, this abstract class can be instantiated by:
 * PlaceComponent object = new PlaceComponent (){};
 * Otherwise, the abstract method need to be overrided when instantiation
 */
public abstract class PlaceComponent {
    private ConnectedNode node;
    private MessageFactory Factory;
    public GenericService<PlacePetitionRequest, PlacePetitionResponse> placeRun;
    private String robotName;
    public GenericService<ProcedureQueryRequest, ProcedureQueryResponse> placeCancle;
    public GenericService<ProcedureQueryRequest, ProcedureQueryResponse> placeQuery;

    public PlaceComponent(ConnectedNode node, MessageFactory Factory, String robotName) {
        this.node = node;
        this.Factory = Factory;
        this.robotName = robotName;
    }

    public void register() throws Exception {
        placeRun = new GenericService<PlacePetitionRequest, PlacePetitionResponse>(node);
        placeRun.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/PlaceComponent/add"), "robot_local_control_msgs/PlacePetition");
        placeCancle = new GenericService<ProcedureQueryRequest, ProcedureQueryResponse>(node);
        placeCancle.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/PlaceComponent/cancel"), "procedures_msgs/ProcedureQuery");
        placeQuery = new GenericService<ProcedureQueryRequest, ProcedureQueryResponse>(node);
        placeQuery.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/PlaceComponent/query_state"), "procedures_msgs/ProcedureQuery");
    }

    /**
     * @return
     *     check if the request is successfully sent to the ROS service, the possible result are: ok/error
     */
    public String call_placeRun(PlacePetitionRequest request) {
    
        String result = null;

        PlacePetitionResponse responseVal = placeRun.call(request);
        if (responseVal!= null) {
            result = responseVal.getResult().getResult();
        } else {
        	System.out.println(robotName+" PlaceComponent Place Response timeout! return null");
        }
        return result;
    }

    /**
     * This abstract method need to be overrided when instantiate the class
     */
    public abstract PlacePetitionRequest constructMsg_placeRun();

    /**
     * @return
     *    check if the request is successfully sent to the ROS service, the possible result are: ok/error
     */
    public String call_placeCancle(ProcedureQueryRequest request) {

    	String result = null;
    	
        ProcedureQueryResponse responseVal = placeCancle.call(request);
        if (responseVal!= null) {
            result = responseVal.getResult().getResult();
        } else {
        	System.out.println(robotName+" PlaceComponent Cancel Response timeout! return null");
        }
        return result;
    }

    /**
     * Default message constructor, need override according to usage
     */
    public ProcedureQueryRequest constructMsg_placeCancle() {
        ProcedureQueryRequest request;
        request = placeCancle.serviceClient.newMessage();
        ProcedureHeader header = Factory.newFromType(ProcedureHeader._TYPE);
        header.setId(-1);
        header.setPriority(0);
        Time time = new Time();
        Time.fromMillis(0);
        Time.fromNano(0);
        header.setStamp(time);
        header.setName("");
        request.setHeader(header);
        return request;
    }

   
    public CallResponse call_placeQuery(ProcedureQueryRequest request) {
        CallResponse callResp = null;
        ProcedureQueryResponse pqr;
        pqr = placeQuery.call(request);
        
        if (pqr!= null) {
        	ProcedureState pState = pqr.getState();
        	ProcedureResult pResult = pqr.getResult();
        	
        	callResp = new CallResponse();
        	callResp.result = pResult.getResult();
        	callResp.current_state = pState.getCurrentState();
        	callResp.last_event = pState.getLastEvent();
        	callResp.message = pResult.getMessage();
            
        } else {
        	System.out.println(robotName+" PlaceComponent Query Response timeout! return null");
        }
        return callResp;
    }

    /**
     * Default message constructor, need override according to usage
     */
    public ProcedureQueryRequest constructMsg_placeQuery() {
        ProcedureQueryRequest request;
        request = placeQuery.serviceClient.newMessage();
        ProcedureHeader header = Factory.newFromType(ProcedureHeader._TYPE);
        header.setId(-1);
        header.setPriority(0);
        Time time = new Time();
        Time.fromMillis(0);
        Time.fromNano(0);
        header.setStamp(time);
        header.setName("");
        request.setHeader(header);
        return request;
    }
}
