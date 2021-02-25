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
    public GenericService<ProcedureQueryRequest, ProcedureQueryResponse> gotoQuery;
    private String robotName;
    public GenericService<GoToPetitionRequest, GoToPetitionResponse> gotoRun;
    public GenericService<ProcedureQueryRequest, ProcedureQueryResponse> gotoCancel;

    public GoToComponent(ConnectedNode node, MessageFactory Factory, String robotName) {
        this.node = node;
        this.Factory = Factory;
        this.robotName = robotName;
    }

    public void register() {
        gotoQuery = new GenericService<ProcedureQueryRequest, ProcedureQueryResponse>(node);
        gotoQuery.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/GoToComponent/query_state"), "procedures_msgs/ProcedureQuery");
        gotoRun = new GenericService<GoToPetitionRequest, GoToPetitionResponse>(node);
        gotoRun.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/GoToComponent/add"), "robot_local_control_msgs/GoToPetition");
        gotoCancel = new GenericService<ProcedureQueryRequest, ProcedureQueryResponse>(node);
        gotoCancel.register((("/"+ robotName)+"/robot_local_control/NavigationComponent/GoToComponent/cancel"), "procedures_msgs/ProcedureQuery");
    }

    /**
     * @return
     *     check if the request is successfully sent to the ROS service, the possible result are: ok/error
     */
    public CallResponse call_gotoQuery(ProcedureQueryRequest request) {
        CallResponse callResp = null;
        ProcedureQueryResponse responseVal = gotoQuery.call(request);
        if (responseVal!= null) {
            ProcedureState pState = responseVal.getState();
            ProcedureResult pResult = responseVal.getResult();
            callResp = new CallResponse();
            callResp.result = pResult.getResult();
            callResp.current_state = pState.getCurrentState();
            callResp.last_event = pResult.getLastEvent();
            callResp.message = pState.getMessage();
        } else {
            System.out.println(robotName+" GoToComponent gotoQuery Response timeout! return null");
        }
        return callResp;
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
            System.out.println(robotName+" GoToComponent gotoRun Response timeout! return null");
        }
        return result;
    }

    /**
     * This abstract method need to be overrided when instantiate the class
     */
    public abstract GoToPetitionRequest constructMsg_gotoRun();

    /**
     * @return
     *     check if the request is successfully sent to the ROS service, the possible result are: ok/error
     */
    public String call_gotoCancel(ProcedureQueryRequest request) {
        String result = null;
        ProcedureQueryResponse responseVal = gotoCancel.call(request);
        if (responseVal!= null) {
            result = responseVal.getResult().getResult();
        } else {
            System.out.println(robotName+" GoToComponent gotoCancel Response timeout! return null");
        }
        return result;
    }

    /**
     * Default message constructor, need override according to usage
     */
    public ProcedureQueryRequest constructMsg_gotoCancel() {
        ProcedureQueryRequest request;
        request = gotoCancel.serviceClient.newMessage();
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
