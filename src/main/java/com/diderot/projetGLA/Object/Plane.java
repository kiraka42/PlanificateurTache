package com.diderot.projetGLA.Object;


public class Plane {
	 public long plane_id;
	 public String plane_type;
	 
	 static int cpt = 0;
	 
	 //Useful for stubs only
	 public static Plane randomInstance(){
		 Plane p = new Plane();
		 p.plane_id = cpt++;
		 //p.plane_id = (long)(Math.random()*100);
		 p.plane_type = p.plane_id % 3==0 ? "A380" : p.plane_id % 3==1 ? "B747" : "Concorde";
		 
		 return p;
	 }
}
