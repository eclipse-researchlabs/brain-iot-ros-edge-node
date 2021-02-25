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
