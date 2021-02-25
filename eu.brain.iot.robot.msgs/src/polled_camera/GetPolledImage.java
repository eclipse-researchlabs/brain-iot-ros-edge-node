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
package polled_camera;

public interface GetPolledImage extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "polled_camera/GetPolledImage";
  static final java.lang.String _DEFINITION = "# Namespace to publish response topics in. A polled camera driver node\n# should publish:\n#   <response_namespace>/image_raw\n#   <response_namespace>/camera_info\nstring response_namespace\n\n# Timeout for attempting to capture data from the device. This does not\n# include latency from ROS communication, post-processing of raw camera\n# data, etc. A zero duration indicates no time limit.\nduration timeout\n\n# Binning settings, if supported by the camera.\nuint32 binning_x\nuint32 binning_y\n\n# Region of interest, if supported by the camera.\nsensor_msgs/RegionOfInterest roi\n---\nbool success          # Could the image be captured?\nstring status_message # Error message in case of failure\ntime stamp            # Timestamp of the captured image. Can be matched\n                      # against incoming sensor_msgs/Image header.\n";
}
