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
import robot_local_control_msgs.GoToPetitionRequest;
import robot_local_control_msgs.GoToPetitionResponse;
import robot_local_control_msgs.PickPetitionResponse;


/**
 * When there has no abstract methods in the class, this abstract class can be instantiated by:
 * GoToComponent object = new GoToComponent (){};
 * Otherwise, the abstract method need to be overrided when instantiation
 */
public abstract class GoToComponent {
    private ConnectedNode node;
    private MessageFactory Factory;
    public GenericService<GoToPetitionRequest, GoToPetitionResponse> gotoRun;
    private String robotName;
    public GenericService<ProcedureQueryRequest, ProcedureQueryResponse> gotoCancle;
    public GenericService<ProcedureQueryRequest, ProcedureQueryResponse> gotoQuery;
    
    private static final Logger logger = (Logger) LoggerFactory.getLogger(GenericService.class.getSimpleName());

    public GoToComponent(ConnectedNode node, MessageFactory Factory, String robotName) {
        this.node = node;
        this.Factory = Factory;
        this.robotName = robotName;
    }

    public void register() throws Exception {
        gotoRun = new GenericService<GoToPetitionRequest, GoToPetitionResponse>(node);
        gotoRun.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/GoToComponent/add"), "robot_local_control_msgs/GoToPetition");
        gotoCancle = new GenericService<ProcedureQueryRequest, ProcedureQueryResponse>(node);
        gotoCancle.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/GoToComponent/cancel"), "procedures_msgs/ProcedureQuery");
        gotoQuery = new GenericService<ProcedureQueryRequest, ProcedureQueryResponse>(node);
        gotoQuery.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/GoToComponent/query_state"), "procedures_msgs/ProcedureQuery");
    }

    /**
     * @return
     *     check if the request is successfully sent to the ROS service, the possible result are: ok/error
     */
    public String call_gotoRun(GoToPetitionRequest request) {
        
        String result = null;

        GoToPetitionResponse responseVal = gotoRun.call(request);
        if (responseVal!= null) {
            result = responseVal.getResult().getResult();
        } else {
        	System.out.println(robotName+" GoToComponent send GoTo Response timeout! return null");
        	logger.info(robotName+" GoToComponent send GoTo Response timeout! return null");
        }
        return result;
    }

    /**
     * This abstract method need to be override when instantiate the class
     */
    public abstract GoToPetitionRequest constructMsg_gotoRun();

    /**
     * @return
     *     check if the request is successfully sent to the ROS service, the possible result are: ok/error
     */
    public String call_gotoCancle(ProcedureQueryRequest request) {
        
        String result = null;

        ProcedureQueryResponse responseVal = gotoCancle.call(request);
        if (responseVal!= null) {
            result = responseVal.getResult().getResult();
        } else {
        	System.out.println(robotName+" GoToComponent Cancel Response timeout! return null");
        	logger.info(robotName+" GoToComponent Cancel Response timeout! return null");
        }
        return result;
    }

    /**
     * Default message constructor, need override according to usage
     */
    public ProcedureQueryRequest constructMsg_gotoCancle() {
        ProcedureQueryRequest request;
        request = gotoCancle.serviceClient.newMessage();
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

    /**
     * @return
     *     returnVal[0] is the check result of response result,returnVal[1] is the check result of response state
     */
    public CallResponse call_gotoQuery(ProcedureQueryRequest request) {       
        CallResponse callResp = null;
        ProcedureQueryResponse pqr;
        pqr = gotoQuery.call(request);
        
        if (pqr!= null) {
        	ProcedureState pState = pqr.getState();
        	ProcedureResult pResult = pqr.getResult();
        	
        	callResp = new CallResponse();
        	callResp.result = pResult.getResult();
        	callResp.current_state = pState.getCurrentState();
        	callResp.last_event = pState.getLastEvent();
        	callResp.message = pResult.getMessage();
            
        } else {
        	System.out.println(robotName+" GoToComponent Query Response timeout! return null");
        	logger.info(robotName+" GoToComponent Query Response timeout! return null");
        }
        return callResp;
    }
    
  /*  example response.  for "queued", 
    {
        "_format": "ros",
        "state": {
            "header": {
                "priority": 0,
                "stamp": {
                    "secs": 0,
                    "nsecs": 0
                },
                "id": 4,
                "name": ""
            },
            "current_state": "queued",
            "last_event": "added"
        },
        "result": {
            "message": "",
            "result": "ok"
        }
    }
    
    */

  /*  for "unknown", possible response is (maybe the same command sent before previous one isn't done):
   *   {
        "_format": "ros",
        "state": {
            "header": {
                "priority": 0,
                "stamp": {
                    "secs": 417,
                    "nsecs": 500000000
                },
                "id": -1,
                "name": ""
            },
            "current_state": "unknown",
            "last_event": "abort"
        },
        "result": {
            "message": "Could not add procedure to \"GoToComponent\" because component: \"GoToComponent\" is already running a procedure",
            "result": "error"
        }
    }  */
    
    /**
     * Default message constructor, need override according to usage
     */
    public ProcedureQueryRequest constructMsg_gotoQuery() {
        ProcedureQueryRequest request;
        request = gotoQuery.serviceClient.newMessage();
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
