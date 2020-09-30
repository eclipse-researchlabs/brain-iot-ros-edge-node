package eu.brain.iot.robot.service;

import org.ros.message.MessageFactory;
import org.ros.message.Time;
import org.ros.node.ConnectedNode;
import procedures_msgs.ProcedureHeader;
import procedures_msgs.ProcedureQueryRequest;
import procedures_msgs.ProcedureQueryResponse;
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

    public PickComponent(ConnectedNode node, MessageFactory Factory, String robotName) {
        this.node = node;
        this.Factory = Factory;
        this.robotName = robotName;
    }

    public void register() {
        pickRun = new GenericService<PickPetitionRequest, PickPetitionResponse>(node);
        pickRun.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/PickComponent/add"), "robot_local_control_msgs/PickPetition");
        pickCancle = new GenericService<ProcedureQueryRequest, ProcedureQueryResponse>(node);
        pickCancle.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/PickComponent/cancel"), "procedures_msgs/ProcedureQuery");
        pickQuery = new GenericService<ProcedureQueryRequest, ProcedureQueryResponse>(node);
        pickQuery.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/PickComponent/query_state"), "procedures_msgs/ProcedureQuery");
    }

    /**
     * @return
     *     returnVal[0] is the check result of response result,returnVal[1] is the check result of response state
     */
    public Integer[] call_pickRun(PickPetitionRequest request) {
        String result;
        String state;
        PickPetitionResponse responseVal;
        Integer[] returnVal = new Integer[] { 0, 0 };
        responseVal = pickRun.call(request);
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
    public abstract PickPetitionRequest constructMsg_pickRun();

    /**
     * @return
     *     returnVal[0] is the check result of response result,returnVal[1] is the check result of response state
     */
    public Integer[] call_pickCancle(ProcedureQueryRequest request) {
        String result;
        String state;
        ProcedureQueryResponse responseVal;
        Integer[] returnVal = new Integer[] { 0, 0 };
        responseVal = pickCancle.call(request);
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

    /**
     * @return
     *     returnVal[0] is the check result of response result,returnVal[1] is the check result of response state
     */
    public Integer[] call_pickQuery(ProcedureQueryRequest request) {
        String result;
        String state;
        ProcedureQueryResponse responseVal;
        Integer[] returnVal = new Integer[] { 0, 0 };
        responseVal = pickQuery.call(request);
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
