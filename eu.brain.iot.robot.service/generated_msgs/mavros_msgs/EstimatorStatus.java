package mavros_msgs;

public interface EstimatorStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/EstimatorStatus";
  static final java.lang.String _DEFINITION = "# Current autopilot estimator state\n#\n# https://mavlink.io/en/messages/common.html#ESTIMATOR_STATUS_FLAGS\n\nstd_msgs/Header header\nbool attitude_status_flag\n\nbool velocity_horiz_status_flag\nbool velocity_vert_status_flag\n\nbool pos_horiz_rel_status_flag\nbool pos_horiz_abs_status_flag\n\nbool pos_vert_abs_status_flag\nbool pos_vert_agl_status_flag\n\nbool const_pos_mode_status_flag\n\nbool pred_pos_horiz_rel_status_flag\nbool pred_pos_horiz_abs_status_flag\n\nbool gps_glitch_status_flag\nbool accel_error_status_flag";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  boolean getAttitudeStatusFlag();
  void setAttitudeStatusFlag(boolean value);
  boolean getVelocityHorizStatusFlag();
  void setVelocityHorizStatusFlag(boolean value);
  boolean getVelocityVertStatusFlag();
  void setVelocityVertStatusFlag(boolean value);
  boolean getPosHorizRelStatusFlag();
  void setPosHorizRelStatusFlag(boolean value);
  boolean getPosHorizAbsStatusFlag();
  void setPosHorizAbsStatusFlag(boolean value);
  boolean getPosVertAbsStatusFlag();
  void setPosVertAbsStatusFlag(boolean value);
  boolean getPosVertAglStatusFlag();
  void setPosVertAglStatusFlag(boolean value);
  boolean getConstPosModeStatusFlag();
  void setConstPosModeStatusFlag(boolean value);
  boolean getPredPosHorizRelStatusFlag();
  void setPredPosHorizRelStatusFlag(boolean value);
  boolean getPredPosHorizAbsStatusFlag();
  void setPredPosHorizAbsStatusFlag(boolean value);
  boolean getGpsGlitchStatusFlag();
  void setGpsGlitchStatusFlag(boolean value);
  boolean getAccelErrorStatusFlag();
  void setAccelErrorStatusFlag(boolean value);
}
