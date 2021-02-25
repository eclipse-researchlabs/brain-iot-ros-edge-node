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
package robotnik_msgs;

public interface QueryAlarmsRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/QueryAlarmsRequest";
  static final java.lang.String _DEFINITION = "string TYPE_QUERY_ALL=ALL\nstring TYPE_QUERY_TOP=TOP\nstring TYPE_BETWEEN_DATE=BETWEEN_DATE\nstring TYPE_TOP_BETWEEN_DATE=TOP_BETWEEN_DATE\nstring FILTER_TYPE_NOTHING=NOTHING\nstring FILTER_TYPE_EVENT=EVENT\nstring FILTER_TYPE_ALARM=ALARM\nstring FILTER_TYPE_ERROR=ERROR\nstring type_query\nstring start_date\nstring end_date\nint32 top\nstring filter_type\n";
  static final java.lang.String TYPE_QUERY_ALL = "ALL";
  static final java.lang.String TYPE_QUERY_TOP = "TOP";
  static final java.lang.String TYPE_BETWEEN_DATE = "BETWEEN_DATE";
  static final java.lang.String TYPE_TOP_BETWEEN_DATE = "TOP_BETWEEN_DATE";
  static final java.lang.String FILTER_TYPE_NOTHING = "NOTHING";
  static final java.lang.String FILTER_TYPE_EVENT = "EVENT";
  static final java.lang.String FILTER_TYPE_ALARM = "ALARM";
  static final java.lang.String FILTER_TYPE_ERROR = "ERROR";
  java.lang.String getTypeQuery();
  void setTypeQuery(java.lang.String value);
  java.lang.String getStartDate();
  void setStartDate(java.lang.String value);
  java.lang.String getEndDate();
  void setEndDate(java.lang.String value);
  int getTop();
  void setTop(int value);
  java.lang.String getFilterType();
  void setFilterType(java.lang.String value);
}
