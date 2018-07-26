package com.diderot.projetGLA.Object;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Flight {
	public long flight_id;
	public String commercial_number;
	public long planeId;
	public long departure_airport;
	public long arrival_airport;
	public String date_of_departure;
	public String date_of_arrival;
	
	//Useful for stubs only
	public static Flight randomInstance(){
		Flight f = new Flight();
		f.flight_id=(long)(Math.random()*20);
		f.planeId = (long)(Math.random()*20);
		switch((int)f.planeId%10){
			case 0:f.commercial_number="AB"+f.planeId;break;
			case 1:f.commercial_number="AC"+f.planeId;break;
			case 2:f.commercial_number="AF"+f.planeId;break;
			case 3:f.commercial_number="BC"+f.planeId;break;
			case 4:f.commercial_number="BF"+f.planeId;break;
			case 5:f.commercial_number="AD"+f.planeId;break;
			case 6:f.commercial_number="BC"+f.planeId;break;
			case 7:f.commercial_number="CF"+f.planeId;break;
			case 8:f.commercial_number="CD"+f.planeId;break;
			case 9:f.commercial_number="CF"+f.planeId;break;
			
		}
		f.departure_airport = (long)(Math.random()*20);
		f.arrival_airport = (long)(Math.random()*20);
		long d = System.currentTimeMillis() + ((long)(Math.random() * 900000000));
		//TimeZone tz = TimeZone.getTimeZone("UTC");
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		Date date=new Date(d); 
		//f.date_of_departure = new Date(d);
		date = new Date(d+18000000);
		f.date_of_arrival=formatDate.format(date);
		f.date_of_departure=formatDate.format(date);
		//f.date_of_departure=date;
		return f;
	}
}
