package eu.brain.iot.robot.service;

import java.util.HashMap;
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
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(RobotService.class.getSimpleName());

	@Activate
	public CartMapper() throws IOException
	{
		loadCart();

		logger.info("--------------------Internal CartMapper---------------------------");
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
			logger.error("\n Exception:", e);
		} finally {
		    if (is != null) {
		        try {
		            is.close();
		        } catch (IOException e) {
		        	logger.error("\n Exception:", e);
		        }
		    }
		}
		return configContentStr;
	}

	public String getCartName(int MarkerID) {
		return Cart.get(MarkerID);
	}
	

}
