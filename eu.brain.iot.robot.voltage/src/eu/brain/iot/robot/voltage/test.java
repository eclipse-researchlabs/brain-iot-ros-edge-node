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
package eu.brain.iot.robot.voltage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {

	public static void main(String[] args) {
		
		byte b = -110;
		
		Byte byteObject = new Byte(b);
	      //Converting the byte object into a double
	      double d = byteObject;
	      //Printing the result
	      System.out.println("double value: "+d);
		
		System.out.println((long)b);
		
		
		String dateAsText = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(1614192666 * 1000L));
		
		System.out.println(dateAsText);
		
	}

}
