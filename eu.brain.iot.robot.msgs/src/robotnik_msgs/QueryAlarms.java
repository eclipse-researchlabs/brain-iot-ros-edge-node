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

public interface QueryAlarms extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/QueryAlarms";
  static final java.lang.String _DEFINITION = "string TYPE_QUERY_ALL=ALL\nstring TYPE_QUERY_TOP=TOP\nstring TYPE_BETWEEN_DATE=BETWEEN_DATE\nstring TYPE_TOP_BETWEEN_DATE=TOP_BETWEEN_DATE\nstring FILTER_TYPE_NOTHING=NOTHING\nstring FILTER_TYPE_EVENT=EVENT\nstring FILTER_TYPE_ALARM=ALARM\nstring FILTER_TYPE_ERROR=ERROR\nstring type_query\nstring start_date\nstring end_date\nint32 top\nstring filter_type\n---\nQueryAlarm[] alarms\nbool success\nstring msg\n";
}
