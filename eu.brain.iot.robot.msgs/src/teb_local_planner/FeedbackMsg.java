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
package teb_local_planner;

public interface FeedbackMsg extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "teb_local_planner/FeedbackMsg";
  static final java.lang.String _DEFINITION = "# Message that contains intermediate results \n# and diagnostics of the (predictive) planner.\n\nstd_msgs/Header header\n\n# The planned trajectory (or if multiple plans exist, all of them)\nteb_local_planner/TrajectoryMsg[] trajectories\n\n# Index of the trajectory in \'trajectories\' that is selected currently\nuint16 selected_trajectory_idx\n\n# List of active obstacles\ncostmap_converter/ObstacleArrayMsg obstacles_msg\n\n\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  java.util.List<teb_local_planner.TrajectoryMsg> getTrajectories();
  void setTrajectories(java.util.List<teb_local_planner.TrajectoryMsg> value);
  short getSelectedTrajectoryIdx();
  void setSelectedTrajectoryIdx(short value);
  costmap_converter.ObstacleArrayMsg getObstaclesMsg();
  void setObstaclesMsg(costmap_converter.ObstacleArrayMsg value);
}
