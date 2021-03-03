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
package eu.brain.iot.ros.edge.node;

import java.util.HashMap;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component(
		service = {CartMapper.class},
		immediate=true
	)
public class CartMapper {

	private HashMap<Integer,String> Cart=new HashMap<Integer,String>();
//	private  Logger logger;
	private static final Logger logger = (Logger) LoggerFactory.getLogger(CartMapper.class.getSimpleName());


	@Activate
	public CartMapper() throws IOException
	{
		logger.info("--------------------Internal CartMapper 2---------------------------");
    	
		loadCart();
		
		logger.info(Cart.toString());
		logger.info("-----------------------------------------------");
	}
	
	public void loadCart() {
		String content = read_Json_file("/carts.json");
		JSONObject jsonObject = new JSONObject(content);

		JSONArray json_data = jsonObject.getJSONArray("Carts");

		for (int i = 0; i < json_data.length(); i++) {
			JSONObject cart_obj = json_data.getJSONObject(i);

			Cart.put(cart_obj.getInt("MarkerID"), cart_obj.getString("NAME"));

		}

	}

	public String read_Json_file(String path) {
		String configContentStr = "";
		BufferedReader br = null;
		InputStream is = null;
		try {
		is = this.getClass().getClassLoader().getResourceAsStream(path);
		br = new BufferedReader(new InputStreamReader(is));
		String s = "";
		
		
			while ((s = br.readLine()) != null) {
				configContentStr = configContentStr + s;
			}
			br.close();
		} catch (IOException e) {
			logger.error("CartMapper Exception: {}", ExceptionUtils.getStackTrace(e));
		} finally {
		    if (is != null) {
		        try {
		            is.close();
		        } catch (IOException e) {
		        	logger.error("CartMapper Exception: {}", ExceptionUtils.getStackTrace(e));
		        }
		    }
		}
		return configContentStr;
	}

	public String getCartName(int MarkerID) {
		return Cart.get(MarkerID);
	}
	

}
