package eu.brain.iot.robot.service;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
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

	@Activate
	public CartMapper() throws IOException
	{
		loadCart();

        System.out.println("--------------------Internal CartMapper---------------------------");
        System.out.println(Cart);
        System.out.println("-----------------------------------------------");
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
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String s = "";
		String configContentStr = "";
		try {
			while ((s = br.readLine()) != null) {
				configContentStr = configContentStr + s;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return configContentStr;
	}

	public String getCartName(int MarkerID) {
		return Cart.get(MarkerID);
	}
	

}
