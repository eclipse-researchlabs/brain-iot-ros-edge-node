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
package rosgraph_msgs;

public interface Log extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "rosgraph_msgs/Log";
  static final java.lang.String _DEFINITION = "##\n## Severity level constants\n##\nbyte DEBUG=1 #debug level\nbyte INFO=2  #general level\nbyte WARN=4  #warning level\nbyte ERROR=8 #error level\nbyte FATAL=16 #fatal/critical level\n##\n## Fields\n##\nHeader header\nbyte level\nstring name # name of the node\nstring msg # message \nstring file # file the message came from\nstring function # function the message came from\nuint32 line # line the message came from\nstring[] topics # topic names that the node publishes\n";
  static final byte DEBUG = 1;
  static final byte INFO = 2;
  static final byte WARN = 4;
  static final byte ERROR = 8;
  static final byte FATAL = 16;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  byte getLevel();
  void setLevel(byte value);
  java.lang.String getName();
  void setName(java.lang.String value);
  java.lang.String getMsg();
  void setMsg(java.lang.String value);
  java.lang.String getFile();
  void setFile(java.lang.String value);
  java.lang.String getFunction();
  void setFunction(java.lang.String value);
  int getLine();
  void setLine(int value);
  java.util.List<java.lang.String> getTopics();
  void setTopics(java.util.List<java.lang.String> value);
}
