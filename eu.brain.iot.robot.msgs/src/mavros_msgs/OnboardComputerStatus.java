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
package mavros_msgs;

public interface OnboardComputerStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/OnboardComputerStatus";
  static final java.lang.String _DEFINITION = "# Mavros message: ONBOARDCOMPUTERSTATUS\n\nstd_msgs/Header header\n\nuint8 component               # See enum MAV_COMPONENT\n\nuint32 uptime               # [ms] Time since system boot\nuint8 type                  # Type of the onboard computer: 0: Mission computer primary, 1: Mission computer backup 1, 2: Mission computer backup 2, 3: Compute node, 4-5: Compute spares, 6-9: Payload computers.\nuint8[8] cpu_cores          # CPU usage on the component in percent (100 - idle). A value of UINT8_MAX implies the field is unused.\nuint8[10] cpu_combined      # Combined CPU usage as the last 10 slices of 100 MS (a histogram). This allows to identify spikes in load that max out the system, but only for a short amount of time. A value of UINT8_MAX implies the field is unused\nuint8[4] gpu_cores          # GPU usage on the component in percent (100 - idle). A value of UINT8_MAX implies the field is unused\nuint8[10] gpu_combined      # Combined GPU usage as the last 10 slices of 100 MS (a histogram). This allows to identify spikes in load that max out the system, but only for a short amount of time. A value of UINT8_MAX implies the field is unused.\nint8 temperature_board      # [degC] Temperature of the board. A value of INT8_MAX implies the field is unused.\nint8[8] temperature_core    # [degC] Temperature of the CPU core. A value of INT8_MAX implies the field is unused.\nint16[4] fan_speed          # [rpm] Fan speeds. A value of INT16_MAX implies the field is unused.\nuint32 ram_usage            # [MiB] Amount of used RAM on the component system. A value of UINT32_MAX implies the field is unused.\nuint32 ram_total            # [MiB] Total amount of RAM on the component system. A value of UINT32_MAX implies the field is unused.\nuint32[4] storage_type      # Storage type: 0: HDD, 1: SSD, 2: EMMC, 3: SD card (non-removable), 4: SD card (removable). A value of UINT32_MAX implies the field is unused.\nuint32[4] storage_usage     # [MiB] Amount of used storage space on the component system. A value of UINT32_MAX implies the field is unused.\nuint32[4] storage_total     # [MiB] Total amount of storage space on the component system. A value of UINT32_MAX implies the field is unused.\nuint32[6] link_type         # Link type: 0-9: UART, 10-19: Wired network, 20-29: Wifi, 30-39: Point-to-point proprietary, 40-49: Mesh proprietary.\nuint32[6] link_tx_rate      # [KiB/s] Network traffic from the component system. A value of UINT32_MAX implies the field is unused.\nuint32[6] link_rx_rate      # [KiB/s] Network traffic to the component system. A value of UINT32_MAX implies the field is unused.\nuint32[6] link_tx_max       # [KiB/s] Network capacity from the component system. A value of UINT32_MAX implies the field is unused.\nuint32[6] link_rx_max       # [KiB/s] Network capacity to the component system. A value of UINT32_MAX implies the field is unused.";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  byte getComponent();
  void setComponent(byte value);
  int getUptime();
  void setUptime(int value);
  byte getType();
  void setType(byte value);
  org.jboss.netty.buffer.ChannelBuffer getCpuCores();
  void setCpuCores(org.jboss.netty.buffer.ChannelBuffer value);
  org.jboss.netty.buffer.ChannelBuffer getCpuCombined();
  void setCpuCombined(org.jboss.netty.buffer.ChannelBuffer value);
  org.jboss.netty.buffer.ChannelBuffer getGpuCores();
  void setGpuCores(org.jboss.netty.buffer.ChannelBuffer value);
  org.jboss.netty.buffer.ChannelBuffer getGpuCombined();
  void setGpuCombined(org.jboss.netty.buffer.ChannelBuffer value);
  byte getTemperatureBoard();
  void setTemperatureBoard(byte value);
  org.jboss.netty.buffer.ChannelBuffer getTemperatureCore();
  void setTemperatureCore(org.jboss.netty.buffer.ChannelBuffer value);
  short[] getFanSpeed();
  void setFanSpeed(short[] value);
  int getRamUsage();
  void setRamUsage(int value);
  int getRamTotal();
  void setRamTotal(int value);
  int[] getStorageType();
  void setStorageType(int[] value);
  int[] getStorageUsage();
  void setStorageUsage(int[] value);
  int[] getStorageTotal();
  void setStorageTotal(int[] value);
  int[] getLinkType();
  void setLinkType(int[] value);
  int[] getLinkTxRate();
  void setLinkTxRate(int[] value);
  int[] getLinkRxRate();
  void setLinkRxRate(int[] value);
  int[] getLinkTxMax();
  void setLinkTxMax(int[] value);
  int[] getLinkRxMax();
  void setLinkRxMax(int[] value);
}
