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
package kobuki_msgs;

public interface SensorState extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "kobuki_msgs/SensorState";
  static final java.lang.String _DEFINITION = "# Kobuki Sensor Data Messages\n#\n# For more direct simple interactions (buttons, leds, gyro, motor velocity\n# etc) use the other topics. This provides detailed information about the\n# entire state package that is transmitted at 50Hz from the robot.\n#\n\n\n###### CONSTANTS ######\n# Bumper states (states are combined, when multiple bumpers are pressed)\nuint8 BUMPER_RIGHT  = 1\nuint8 BUMPER_CENTRE = 2\nuint8 BUMPER_LEFT   = 4\n\n# Wheel drop sensor states (states are combined, when both wheel drop sensors are triggered)\nuint8 WHEEL_DROP_RIGHT = 1\nuint8 WHEEL_DROP_LEFT  = 2\n\n# Cliff sensor states (states are combined, when multiple cliff sensors are triggered)\nuint8 CLIFF_RIGHT  = 1\nuint8 CLIFF_CENTRE = 2\nuint8 CLIFF_LEFT   = 4\n\n# Button states (only one button can be triggered at a time)\nuint8 BUTTON0 = 1\nuint8 BUTTON1 = 2\nuint8 BUTTON2 = 4\n\n# Charger state is a combination of charging device (adapter, docking station)\n# and state (charging, charged, discharging):\nuint8 DISCHARGING      = 0\nuint8 DOCKING_CHARGED  = 2\nuint8 DOCKING_CHARGING = 6\nuint8 ADAPTER_CHARGED  = 18\nuint8 ADAPTER_CHARGING = 22\n\n# Over current states\nuint8 OVER_CURRENT_LEFT_WHEEL  = 1\nuint8 OVER_CURRENT_RIGHT_WHEEL = 2\nuint8 OVER_CURRENT_BOTH_WHEELS = 3\n\n# Digital input states (states are combined, when multiple inputs are set at the same time)\n# When connecting Yujin\'s test board, it acts as pull-up what inverts the behaviour:\n# No input: 79, all inputs set (e.g. buttons pressed): 64\nuint8 DIGITAL_INPUT0 = 1\nuint8 DIGITAL_INPUT1 = 2\nuint8 DIGITAL_INPUT2 = 4\nuint8 DIGITAL_INPUT3 = 8\nuint8 DB25_TEST_BOARD_CONNECTED = 64\n\n###### MESSAGE ######\n\nHeader header\n\n###################\n# Core Packet\n###################\nuint16 time_stamp      # milliseconds starting when turning on Kobuki (max. 65536, then starts from 0 again)\nuint8  bumper          # see bumper states\nuint8  wheel_drop      # see wheel drop sensor states\nuint8  cliff           # see cliff sensor states\nuint16 left_encoder    # accumulated ticks left wheel starting with turning on Kobuki (max. 65535)\nuint16 right_encoder   # accumulated ticks right wheel starting with turning on Kobuki (max. 65535)\nint8   left_pwm        # % of applied maximum voltage left wheel: -100 (max. voltage backward) to +100 (max. voltage forward)\nint8   right_pwm       # % of applied maximum voltage right wheel: -100 (max. voltage backward) to +100 (max. voltage forward)\nuint8  buttons         # see button states\nuint8  charger         # see charger states\nuint8  battery         # battery voltage in 0.1V (ex. 16.1V -> 161)\n\n###################\n# Cliff Packet\n###################\nuint16[] bottom        # ADC output of the right, centre, left cliff PSD sensor (0 - 4095, distance measure is non-linear)\n\n###################\n# Current Packet\n###################\nuint8[] current        # motor current for the left and right motor in 10mA (ex. 12 -> 120mA)\nuint8   over_current   # see over current states\n\n###################\n# Input Packet\n###################\nuint16   digital_input # see digital input states; will show garbage when nothing is connected\nuint16[] analog_input  # ADC values for the 4 analog inputs; 0 - 4095: 0.0 - 3.3V; will show garbage when nothing is connected\n";
  static final byte BUMPER_RIGHT = 1;
  static final byte BUMPER_CENTRE = 2;
  static final byte BUMPER_LEFT = 4;
  static final byte WHEEL_DROP_RIGHT = 1;
  static final byte WHEEL_DROP_LEFT = 2;
  static final byte CLIFF_RIGHT = 1;
  static final byte CLIFF_CENTRE = 2;
  static final byte CLIFF_LEFT = 4;
  static final byte BUTTON0 = 1;
  static final byte BUTTON1 = 2;
  static final byte BUTTON2 = 4;
  static final byte DISCHARGING = 0;
  static final byte DOCKING_CHARGED = 2;
  static final byte DOCKING_CHARGING = 6;
  static final byte ADAPTER_CHARGED = 18;
  static final byte ADAPTER_CHARGING = 22;
  static final byte OVER_CURRENT_LEFT_WHEEL = 1;
  static final byte OVER_CURRENT_RIGHT_WHEEL = 2;
  static final byte OVER_CURRENT_BOTH_WHEELS = 3;
  static final byte DIGITAL_INPUT0 = 1;
  static final byte DIGITAL_INPUT1 = 2;
  static final byte DIGITAL_INPUT2 = 4;
  static final byte DIGITAL_INPUT3 = 8;
  static final byte DB25_TEST_BOARD_CONNECTED = 64;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  short getTimeStamp();
  void setTimeStamp(short value);
  byte getBumper();
  void setBumper(byte value);
  byte getWheelDrop();
  void setWheelDrop(byte value);
  byte getCliff();
  void setCliff(byte value);
  short getLeftEncoder();
  void setLeftEncoder(short value);
  short getRightEncoder();
  void setRightEncoder(short value);
  byte getLeftPwm();
  void setLeftPwm(byte value);
  byte getRightPwm();
  void setRightPwm(byte value);
  byte getButtons();
  void setButtons(byte value);
  byte getCharger();
  void setCharger(byte value);
  byte getBattery();
  void setBattery(byte value);
  short[] getBottom();
  void setBottom(short[] value);
  org.jboss.netty.buffer.ChannelBuffer getCurrent();
  void setCurrent(org.jboss.netty.buffer.ChannelBuffer value);
  byte getOverCurrent();
  void setOverCurrent(byte value);
  short getDigitalInput();
  void setDigitalInput(short value);
  short[] getAnalogInput();
  void setAnalogInput(short[] value);
}