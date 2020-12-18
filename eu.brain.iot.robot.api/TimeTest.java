package eu.brain.iot.robot.events;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// https://howtodoinjava.com/java/date-time/java-date-examples/

public class Test {

	public static void main(String[] args) {
		
		Test test = new Test();
		System.out.println(test.gettime());
		
		test.data();
	}
	


	public String gettime() {
		String date = null;
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
	    try {
	        
	    	date = simpleDateFormat.parse("13:10").toString();
	    } catch (ParseException ingnored) { }
	    
	    return date;
	}
	
	void data() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		String date = sdf.format(new Date()); 
		System.out.println(date);
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1 = sdf.format(new Time()); 
		System.out.println(date);
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String date2 = sdf.format(new Date()); 
		System.out.println(date);
	}
}
