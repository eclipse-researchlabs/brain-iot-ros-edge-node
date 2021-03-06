package eu.brain.iot.robot.service;

import org.ros.message.MessageFactory;
import org.ros.message.Time;
import org.ros.node.ConnectedNode;

import eu.brain.iot.robot.api.CallResponse;
import procedures_msgs.ProcedureHeader;
import procedures_msgs.ProcedureQueryRequest;
import procedures_msgs.ProcedureQueryResponse;
import procedures_msgs.ProcedureResult;
import procedures_msgs.ProcedureState;
import robot_local_control_msgs.GoToPetitionRequest;
import robot_local_control_msgs.GoToPetitionResponse;


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

    public GoToComponent(ConnectedNode node, MessageFactory Factory, String robotName) {
        this.node = node;
        this.Factory = Factory;
        this.robotName = robotName;
    }

    public void register() {
        gotoRun = new GenericService<GoToPetitionRequest, GoToPetitionResponse>(node);
        gotoRun.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/GoToComponent/add"), "robot_local_control_msgs/GoToPetition");
        gotoCancle = new GenericService<ProcedureQueryRequest, ProcedureQueryResponse>(node);
        gotoCancle.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/GoToComponent/cancel"), "procedures_msgs/ProcedureQuery");
        gotoQuery = new GenericService<ProcedureQueryRequest, ProcedureQueryResponse>(node);
        gotoQuery.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/GoToComponent/query_state"), "procedures_msgs/ProcedureQuery");
    }

    /**
     * @return
     *     returnVal[0] is the check result of response result,returnVal[1] is the check result of response state
     */
    public Integer[] call_gotoRun(GoToPetitionRequest request) {
        String result;
        String state;
        GoToPetitionResponse responseVal;
        ProcedureResult pr;
        Integer[] returnVal = new Integer[] { 0, 0 };
        responseVal = gotoRun.call(request);
        if (responseVal!= null) {
            result = responseVal.getResult().getResult();
            state = responseVal.getState().getCurrentState();
        } else {
        	System.out.println(robotName+" GoToComponent GoTo Response timeout! return null");
            return returnVal;
        }
        CallResponse cr = new CallResponse();
        cr.result = responseVal.getResult().getResult();
        cr.current_state = responseVal.getState().getCurrentState();
        
        if (result.compareTo("ok") == 0) {
            returnVal[ 0 ] = 1;
        }
        if (state.compareTo("finished") == 0) {
            returnVal[ 1 ] = 1;
        }
        if (state.compareTo("queued") == 0) {
            returnVal[ 1 ] = 2;
        }
        if (state.compareTo("running") == 0) {
            returnVal[ 1 ] = 3;
        }
        if (state.compareTo("paused") == 0) {
            returnVal[ 1 ] = 4;
        }
        if (state.compareTo("unknown") == 0) {
            returnVal[ 1 ] = 5;
        }
        return returnVal;
    }

    /**
     * This abstract method need to be overrided when instantiate the class
     */
    public abstract GoToPetitionRequest constructMsg_gotoRun();

    /**
     * @return
     *     returnVal[0] is the check result of response result,returnVal[1] is the check result of response state
     */
    public Integer[] call_gotoCancle(ProcedureQueryRequest request) {
        String result;
        String state;
        ProcedureQueryResponse responseVal;
        Integer[] returnVal = new Integer[] { 0, 0 };
        responseVal = gotoCancle.call(request);
        if (responseVal!= null) {
            result = responseVal.getResult().getResult();
            state = responseVal.getState().getCurrentState();
        } else {
        	System.out.println(robotName+" GoToComponent Cancel Response timeout! return null");
            return returnVal;
        }
        if (result.compareTo("ok") == 0) {
            returnVal[ 0 ] = 1;
        }
        if (state.compareTo("finished") == 0) {
            returnVal[ 1 ] = 1;
        }
        if (state.compareTo("queued") == 0) {
            returnVal[ 1 ] = 2;
        }
        if (state.compareTo("running") == 0) {
            returnVal[ 1 ] = 3;
        }
        if (state.compareTo("paused") == 0) {
            returnVal[ 1 ] = 4;
        }
        if (state.compareTo("unknown") == 0) {
            returnVal[ 1 ] = 5;
        }
        return returnVal;
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
