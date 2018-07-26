package com.diderot.projetGLA.Object;

public class Airport {
	 public long airport_id;
	 public String name;
	 public String location;

	 //Useful for stubs only
	 public static Airport randomInstance(){
		 Airport a = new Airport();
		 a.airport_id = 1;//(long)(Math.random()*5);//(long)(Math.random()*100);
		 a.name = a.airport_id % 3==0 ? "Orly" : a.airport_id % 2 ==1 ? "Heathrow" : "JFK";
		 a.location = a.airport_id % 3==0 ? "Paris" : a.airport_id % 3 ==1 ? "London" : "New York City";
		 return a;
	 }
}
