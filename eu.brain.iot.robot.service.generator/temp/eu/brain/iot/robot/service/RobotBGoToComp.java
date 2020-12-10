package eu.brain.iot.robot.service;

import org.ros.message.MessageFactory;
import org.ros.message.Time;
import org.ros.node.ConnectedNode;
import procedures_msgs.ProcedureHeader;
import procedures_msgs.ProcedureQueryRequest;
import procedures_msgs.ProcedureQueryResponse;
import robot_local_control_msgs.GoToPetitionRequest;
import robot_local_control_msgs.GoToPetitionResponse;


/**
 * When there has no abstract methods in the class, this abstract class can be instantiated by:
 * RobotBGoToComp object = new RobotBGoToComp (){};
 * Otherwise, the abstract method need to be overrided when instantiation
 */
public abstract class RobotBGoToComp {
    private String robotName;
    private ConnectedNode node;
    private MessageFactory Factory;
    public GenericComponentImpl<GoToPetitionRequest, GoToPetitionResponse> gotoRun;
    public GenericComponentImpl<ProcedureQueryRequest, ProcedureQueryResponse> gotoCancle;
    public GenericComponentImpl<ProcedureQueryRequest, ProcedureQueryResponse> gotoQuery;

    public RobotBGoToComp(String robotName, ConnectedNode node, MessageFactory Factory) {
        this.robotName = robotName;
        this.node = node;
        this.Factory = Factory;
    }

    public void register() {
        gotoRun = new GenericComponentImpl<GoToPetitionRequest, GoToPetitionResponse>(node);
        gotoRun.register("/rb1_base_b/robot_local_control/NavigationComponent/GoToComponent/add", "robot_local_control_msgs/GoToPetition");
        gotoCancle = new GenericComponentImpl<ProcedureQueryRequest, ProcedureQueryResponse>(node);
        gotoCancle.register("/rb1_base_b/robot_local_control/NavigationComponent/GoToComponent/cancel", "procedures_msgs/ProcedureQuery");
        gotoQuery = new GenericComponentImpl<ProcedureQueryRequest, ProcedureQueryResponse>(node);
        gotoQuery.register("/rb1_base_b/robot_local_control/NavigationComponent/GoToComponent/query_state", "procedures_msgs/ProcedureQuery");
    }

    /**
     * @return
     *     returnVal[0] is the check result of response result,returnVal[1] is the check result of response state
     */
    public Integer[] call_gotoRun(GoToPetitionRequest request) {
        String result;
        String state;
        GoToPetitionResponse responseVal;
        Integer[] returnVal = new Integer[] { 0, 0 };
        responseVal = gotoRun.call(request);
        if (responseVal!= null) {
            result = responseVal.getResult().getResult();
            state = responseVal.getState().getCurrentState();
        } else {
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
    public Integer[] call_gotoQuery(ProcedureQueryRequest request) {
        String result;
        String state;
        ProcedureQueryResponse responseVal;
        Integer[] returnVal = new Integer[] { 0, 0 };
        responseVal = gotoQuery.call(request);
        if (responseVal!= null) {
            result = responseVal.getResult().getResult();
            state = responseVal.getState().getCurrentState();
        } else {
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
