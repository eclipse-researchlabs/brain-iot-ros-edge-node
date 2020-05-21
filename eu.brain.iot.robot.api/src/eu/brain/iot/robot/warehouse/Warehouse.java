package eu.brain.iot.robot.warehouse;

import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Warehouse {
	
	private HashMap<Integer,Robot_Pose> Storage=new HashMap<Integer,Robot_Pose>();
	private HashMap<Integer,Robot_Pose> Unload=new HashMap<Integer,Robot_Pose>();
	private HashMap<Integer,Robot_Pose> Place_C=new HashMap<Integer,Robot_Pose>();	
	private HashMap<Integer,Robot_Pose> Place_L=new HashMap<Integer,Robot_Pose>();
	private HashMap<Integer,Robot_Pose> Place_R=new HashMap<Integer,Robot_Pose>();
	private HashMap<Integer,String> Cart=new HashMap<Integer,String>();

	public Warehouse() throws IOException
	{
		loadPose();
		loadCart();

   /*     System.out.println("-----------------------------------------------");
        System.out.println(Cart);
        System.out.println(Storage);
        System.out.println(Unload);
        System.out.println(Place_C);
        System.out.println(Place_L);
        System.out.println(Place_R);
        
        System.out.println("-----------------------------------------------");*/
	}
	
	public void loadPose()
	{
		String content=get_jar_file("/pose.json");
        JSONObject jsonObject=new JSONObject(content);

        
        JSONArray json_data = jsonObject.getJSONArray("Robots");
        for (int i=0;i<json_data.length();i++)
        {
        	Robot_Pose json_pose;
        	int id;
        	JSONObject robot_obj;
        	JSONObject pose_obj;
        	robot_obj=json_data.getJSONObject(i);
        	id=robot_obj.getInt("id");

        	pose_obj=robot_obj.getJSONObject("STORAGE");
        	json_pose=json_array2pose(pose_obj);
        	Storage.put(id, json_pose);	
        	
        	pose_obj=robot_obj.getJSONObject("UNLOAD");
        	json_pose=json_array2pose(pose_obj);
        	Unload.put(id, json_pose);
        	
        	pose_obj=robot_obj.getJSONObject("PLACEC");
        	json_pose=json_array2pose(pose_obj);
        	Place_C.put(id, json_pose);
        	
        	pose_obj=robot_obj.getJSONObject("PLACEL");
        	json_pose=json_array2pose(pose_obj);
        	Place_L.put(id, json_pose);
        	
        	pose_obj=robot_obj.getJSONObject("PLACER");
        	json_pose=json_array2pose(pose_obj);
        	Place_R.put(id, json_pose);
        }
	}
	
	public void loadCart()
	{
		String content=get_jar_file("/cart.json");
        JSONObject jsonObject=new JSONObject(content);
        
        JSONArray json_data = jsonObject.getJSONArray("Carts");
        
        for (int i=0;i<json_data.length();i++)
        {
        	JSONObject cart_obj=json_data.getJSONObject(i);
        	
        	Cart.put(cart_obj.getInt("ID"), cart_obj.getString("NAME"));
        	
        }
        
	}
	
	
	
	
	public String get_jar_file(String path)
	{
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s="";
        String configContentStr = "";
        try {
            while((s=br.readLine())!=null) {
                configContentStr = configContentStr+s;
            }
        } catch (IOException e) {

                    e.printStackTrace();
        }
        return configContentStr;
	}
	
	public Robot_Pose json_array2pose(JSONObject data)
	{
		Robot_Pose pose=new Robot_Pose();
		pose.x=data.getDouble("x");
		pose.y=data.getDouble("y");
		pose.theta=data.getDouble("theta");
	
		return pose;
	}
	
	
	public Request Get_Request(int id,String command)
	{
		Request Req=new Request();
		Req.id=id;

		if (command.equalsIgnoreCase("STORAGE"))
		{
			Req.command="STORAGE";
			Req.pose=Storage.get(id);			
		}
		else if (command.equalsIgnoreCase("UNLOAD"))
		{
			Req.command="UNLOAD";
			Req.pose=Unload.get(id);
		}
		else if(command.equalsIgnoreCase("PLACE_C"))
		{
			Req.command="PLACE_C";
			Req.pose=Place_C.get(id);
		}
		else if(command.equalsIgnoreCase("PLACE_L"))
		{
			Req.command="PLACE_L";
			Req.pose=Place_L.get(id);
		}
		else if(command.equalsIgnoreCase("PLACE_R"))
		{
			Req.command="PLACE_R";
			Req.pose=Place_R.get(id);
		}
		
		
		return Req;
	}
	
	public Request Get_Request(int id, int mission)
	{
		Request Req=new Request();
		Req.id=id;
		
		switch(mission)
		{
			case 1:	
				Req.command="PLACE_CENTER";
				Req.pose=Place_C.get(id);
				break;
			case 2:	
				Req.command="PLACE_LEFT";
				Req.pose=Place_L.get(id);
				break;
			case 3:
				Req.command="PLACE_RIGHT";
				Req.pose=Place_R.get(id);
				break;
			case 4:
				Req.command="STORAGE";
				Req.pose=Storage.get(id);
				break;
			case 5:
				Req.command="UNLOAD";
				Req.pose=Unload.get(id);
				break;
			default:
				break;
		}
		
		
		return Req;
	}
	
	public String getCart(int id)
	{
		return Cart.get(id);
	}
	

}
