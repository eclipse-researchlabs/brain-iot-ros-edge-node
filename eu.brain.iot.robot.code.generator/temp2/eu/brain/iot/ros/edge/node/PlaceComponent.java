package eu.brain.iot.ros.edge.node;

import eu.brain.iot.robot.service.GenericService;
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
    public GenericService<ProcedureQueryRequest, ProcedureQueryResponse> placeCancel;
    private String robotName;
    public GenericService<ProcedureQueryRequest, ProcedureQueryResponse> placeQuery;
    public GenericService<PlacePetitionRequest, PlacePetitionResponse> placeRun;

    public PlaceComponent(ConnectedNode node, MessageFactory Factory, String robotName) {
        this.node = node;
        this.Factory = Factory;
        this.robotName = robotName;
    }

    public void register() {
        placeCancel = new GenericService<ProcedureQueryRequest, ProcedureQueryResponse>(node);
        placeCancel.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/PlaceComponent/cancel"), "procedures_msgs/ProcedureQuery");
        placeQuery = new GenericService<ProcedureQueryRequest, ProcedureQueryResponse>(node);
        placeQuery.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/PlaceComponent/query_state"), "procedures_msgs/ProcedureQuery");
        placeRun = new GenericService<PlacePetitionRequest, PlacePetitionResponse>(node);
        placeRun.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/PlaceComponent/add"), "robot_local_control_msgs/PlacePetition");
    }

    /**
     * @return
     *     check if the request is successfully sent to the ROS service, the possible result are: ok/error
     */
    public String call_placeCancel(ProcedureQueryRequest request) {
        String result = null;
        ProcedureQueryResponse responseVal = placeCancel.call(request);
        if (responseVal!= null) {
            result = responseVal.getResult().getResult();
        } else {
            System.out.println(robotName+" PlaceComponent placeCancel Response timeout! return null");
        }
        return result;
    }

    /**
     * Default message constructor, need override according to usage
     */
    public ProcedureQueryRequest constructMsg_placeCancel() {
        ProcedureQueryRequest request;
        request = placeCancel.serviceClient.newMessage();
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
     *     check if the request is successfully sent to the ROS service, the possible result are: ok/error
     */
    public CallResponse call_placeQuery(ProcedureQueryRequest request) {
        CallResponse callResp = null;
        ProcedureQueryResponse responseVal = placeQuery.call(request);
        if (responseVal!= null) {
            ProcedureState pState = responseVal.getState();
            ProcedureResult pResult = responseVal.getResult();
            callResp = new CallResponse();
            callResp.result = pResult.getResult();
            callResp.current_state = pState.getCurrentState();
            callResp.last_event = pResult.getLastEvent();
            callResp.message = pState.getMessage();
        } else {
            System.out.println(robotName+" PlaceComponent placeQuery Response timeout! return null");
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
            System.out.println(robotName+" PlaceComponent placeRun Response timeout! return null");
        }
        return result;
    }

    /**
     * This abstract method need to be overrided when instantiate the class
     */
    public abstract PlacePetitionRequest constructMsg_placeRun();
}
