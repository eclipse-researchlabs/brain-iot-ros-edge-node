package eu.brain.iot.ros.edge.node;

import ar_track_alvar_msgs.AlvarMarkers;
import eu.brain.iot.robot.service.GenericSubscriber;
import org.ros.node.ConnectedNode;

public abstract class PoseMarkerComponent {
    private ConnectedNode node;
    private GenericSubscriber<AlvarMarkers> poseMarker;
    private AlvarMarkers alvarmarkers = null;
    private String robotName;

    public PoseMarkerComponent(ConnectedNode node, String robotName) {
        this.node = node;
        this.robotName = robotName;
    }

    public void register() {
        poseMarker = new GenericSubscriber<AlvarMarkers>(node);
        poseMarker.register((("/"+ robotName)+"/ar_pose_marker"), "ar_track_alvar_msgs/AlvarMarkers");
    }

    public AlvarMarkers get_poseMarker_value() {
        alvarmarkers = poseMarker.getCurrentValue();
        while (alvarmarkers == null) {
            System.out.println(" poseMarker get empty value, read again.......");
            try {
                sleep(2);
            } catch (final InterruptedException e) {
                throw e;
            }
            alvarmarkers = poseMarker.getCurrentValue();
        }
        return alvarmarkers;
    }
}
