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
package eu.brain.iot.ros.edge.node.common;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.ros.message.MessageFactory;
import org.ros.message.Time;
import org.ros.node.ConnectedNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import procedures_msgs.ProcedureHeader;
import procedures_msgs.ProcedureQueryRequest;
import procedures_msgs.ProcedureQueryResponse;
import procedures_msgs.ProcedureResult;
import procedures_msgs.ProcedureState;
import robot_local_control_msgs.PickPetitionRequest;
import robot_local_control_msgs.PickPetitionResponse;


/**
 * When there has no abstract methods in the class, this abstract class can be instantiated by:
 * PickComponent object = new PickComponent (){};
 * Otherwise, the abstract method need to be overrided when instantiation
 */
public abstract class PickComponent {
    private ConnectedNode node;
    private MessageFactory Factory;
    public GenericService<PickPetitionRequest, PickPetitionResponse> pickRun;
    private String robotName;
    public GenericService<ProcedureQueryRequest, ProcedureQueryResponse> pickCancle;
    public GenericService<ProcedureQueryRequest, ProcedureQueryResponse> pickQuery;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(PickComponent.class.getSimpleName());

    public PickComponent(ConnectedNode node, MessageFactory Factory, String robotName) {
        this.node = node;
        this.Factory = Factory;
        this.robotName = robotName;
    }

    public void register() {
    	try {
    		pickRun = new GenericService<PickPetitionRequest, PickPetitionResponse>(node);
    		pickRun.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/PickComponent/add"), "robot_local_control_msgs/PickPetition");
    		pickCancle = new GenericService<ProcedureQueryRequest, ProcedureQueryResponse>(node);
    		pickCancle.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/PickComponent/cancel"), "procedures_msgs/ProcedureQuery");
    		pickQuery = new GenericService<ProcedureQueryRequest, ProcedureQueryResponse>(node);
    		pickQuery.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/PickComponent/query_state"), "procedures_msgs/ProcedureQuery");
    	} catch (Exception e) {
			logger.error("\n PickComponent REGISTER Exception: {}", ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
		}
    	}

    /**
     * @return
     *     check if the request is successfully sent to the ROS service, the possible result are: ok/error
     */
    public String call_pickRun(PickPetitionRequest request) {
        String result = null;

        PickPetitionResponse responseVal = pickRun.call(request);
        if (responseVal!= null) {
            result = responseVal.getResult().getResult();
        } else {
        	System.out.println(robotName+" PickComponent Pick Response timeout! return null");
        }
        return result;
    }

    /**
     * This abstract method need to be override when instantiate the class
     */
    public abstract PickPetitionRequest constructMsg_pickRun();

    /**
     * @return
     *     check if the request is successfully sent to the ROS service, the possible result are: ok/error
     */
    public String call_pickCancle(ProcedureQueryRequest request) {
    	String result = null;

    	ProcedureQueryResponse responseVal = pickCancle.call(request);
        if (responseVal!= null) {
            result = responseVal.getResult().getResult();
        } else {
        	System.out.println(robotName+" PickComponent Cancel Response timeout! return null");
        }
        return result;
    }

    /**
     * Default message constructor, need override according to usage
     */
    public ProcedureQueryRequest constructMsg_pickCancle() {
        ProcedureQueryRequest request;
        request = pickCancle.serviceClient.newMessage();
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

    public CallResponse call_pickQuery(ProcedureQueryRequest request) {
        CallResponse callResp = null;
        ProcedureQueryResponse pqr;
        pqr = pickQuery.call(request);
        
        if (pqr!= null) {
        	ProcedureState pState = pqr.getState();
        	ProcedureResult pResult = pqr.getResult();
        	
        	callResp = new CallResponse();
        	callResp.result = pResult.getResult();
        	callResp.current_state = pState.getCurrentState();
        	callResp.last_event = pState.getLastEvent();
        	callResp.message = pResult.getMessage();
            
        } else {
        	System.out.println(robotName+" PickComponent Query Response timeout! return null");
        }
        return callResp;
    }

    
    /*  
    pick state availability  result:
    {
        "operation_state": "moving",
        "robot_state": "standby",
        "navigation_status": {
            "state": "",
            "type": "PickComponent,"
   */
    
    /**
     * Default message constructor, need override according to usage
     */
    public ProcedureQueryRequest constructMsg_pickQuery() {
        ProcedureQueryRequest request;
        request = pickQuery.serviceClient.newMessage();
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