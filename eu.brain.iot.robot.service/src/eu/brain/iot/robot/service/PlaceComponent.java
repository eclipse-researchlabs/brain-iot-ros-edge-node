package eu.brain.iot.robot.service;

import org.ros.message.MessageFactory;
import org.ros.message.Time;
import org.ros.node.ConnectedNode;
import procedures_msgs.ProcedureHeader;
import procedures_msgs.ProcedureQueryRequest;
import procedures_msgs.ProcedureQueryResponse;
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

    public void register() {
        placeRun = new GenericService<PlacePetitionRequest, PlacePetitionResponse>(node);
        placeRun.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/PlaceComponent/add"), "robot_local_control_msgs/PlacePetition");
        placeCancle = new GenericService<ProcedureQueryRequest, ProcedureQueryResponse>(node);
        placeCancle.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/PlaceComponent/cancel"), "procedures_msgs/ProcedureQuery");
        placeQuery = new GenericService<ProcedureQueryRequest, ProcedureQueryResponse>(node);
        placeQuery.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/PlaceComponent/query_state"), "procedures_msgs/ProcedureQuery");
    }

    /**
     * @return
     *     returnVal[0] is the check result of response result,returnVal[1] is the check result of response state
     */
    public Integer[] call_placeRun(PlacePetitionRequest request) {
        String result;
        String state;
        PlacePetitionResponse responseVal;
        Integer[] returnVal = new Integer[] { 0, 0 };
        responseVal = placeRun.call(request);
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
    public abstract PlacePetitionRequest constructMsg_placeRun();

    /**
     * @return
     *     returnVal[0] is the check result of response result,returnVal[1] is the check result of response state
     */
    public Integer[] call_placeCancle(ProcedureQueryRequest request) {
        String result;
        String state;
        ProcedureQueryResponse responseVal;
        Integer[] returnVal = new Integer[] { 0, 0 };
        responseVal = placeCancle.call(request);
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

    /**
     * @return
     *     returnVal[0] is the check result of response result,returnVal[1] is the check result of response state
     */
    public Integer[] call_placeQuery(ProcedureQueryRequest request) {
        String result;
        String state;
        ProcedureQueryResponse responseVal;
        Integer[] returnVal = new Integer[] { 0, 0 };
        responseVal = placeQuery.call(request);
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
