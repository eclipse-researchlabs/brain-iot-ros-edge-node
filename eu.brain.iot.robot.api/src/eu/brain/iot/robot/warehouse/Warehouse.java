package eu.brain.iot.robot.warehouse;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component(
		service = {Warehouse.class},
		immediate=true
	)
public class Warehouse {
	
	private HashMap<Integer,Cooridinate> unload=new HashMap<Integer,Cooridinate>();

	private HashMap<Integer,String> Cart=new HashMap<Integer,String>();
	
	private Cooridinate placeCenter = null;
	private Cooridinate placeLeft = null;
	private Cooridinate placeRight = null;
	

	public Warehouse() throws IOException
	{
		loadPose();
		loadCart();

        System.out.println("--------------------Warehouse---------------------------");
        System.out.println(Cart);
        System.out.println(unload);
        System.out.println(placeCenter);
        System.out.println(placeLeft);
        System.out.println(placeRight);
        System.out.println("-----------------------------------------------");
	}
	
	public void loadPose()
	{
		String content=read_Json_file("/map.json");
        JSONObject jsonObject=new JSONObject(content);

        JSONObject coordinate_data = null;
        
        
        JSONObject map = jsonObject.getJSONObject("Map");
        
        coordinate_data=map.getJSONObject("PlaceCenter");
        placeCenter=json2Cooridinate(coordinate_data);
        
        coordinate_data=map.getJSONObject("PlaceLeft");
        placeLeft=json2Cooridinate(coordinate_data);
        
        coordinate_data=map.getJSONObject("PlaceRight");
        placeRight=json2Cooridinate(coordinate_data);
        
        JSONArray unloadArray = map.getJSONArray("UNLOAD");
        
    	JSONObject initPose = null;
    	Cooridinate coordinate = null;
    	int pickPoseID;
    	
        for (int i=0;i<unloadArray.length();i++)
        {
        	initPose=unloadArray.getJSONObject(i);
        	
        	pickPoseID=initPose.getInt("pickPoseID");
        	coordinate_data=initPose.getJSONObject("pose");
        	coordinate=json2Cooridinate(coordinate_data);
        	unload.put(pickPoseID, coordinate);
        }
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
	
	public Cooridinate json2Cooridinate(JSONObject data) {
		Cooridinate cooridinate = new Cooridinate();
		cooridinate.x = data.getDouble("x");
		cooridinate.y = data.getDouble("y");
		cooridinate.z = data.getDouble("z");

		return cooridinate;
	}
	
	
	public Cooridinate GetCooridinate(int pickPoseID, String mission) {
		if (mission.equalsIgnoreCase("UNLOAD")) {
			return unload.get(pickPoseID);
			
		} else if (mission.equalsIgnoreCase("PLACE_C")) {
			return placeCenter;
			
		} else if (mission.equalsIgnoreCase("PLACE_L")) {
			return placeLeft;
			
		} else if (mission.equalsIgnoreCase("PLACE_R")) {
			return placeRight;
		}
		return null;
	}

	public String getCartName(int MarkerID) {
		return Cart.get(MarkerID);
	}
	

}
