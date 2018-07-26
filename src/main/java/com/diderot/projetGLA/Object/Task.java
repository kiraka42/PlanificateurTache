package com.diderot.projetGLA.Object;

import java.util.Arrays;
import java.util.List;

public class Task {
	public long taskId;
	public long periodicity; //nombre de d'heures
	public String airPlaneType;
	public String ATA_category;
	public boolean hangar;// [true or false]
	public String duration; 
	public List<String> action_list;
	public List<String> tools_list;
	public List<String> pieces_list; 
	public String qualifications_required;
	
	//Useful for stubs only
	public static Task randomInstance(){
		Task t = new Task();
		t.taskId = (long)(Math.random()*100);
		t.periodicity = (long)(Math.random()*8640000000l);
		int i = ((int)(Math.random()*3)*20);
		t.airPlaneType = i % 3==0 ? "A380" : i % 3==1 ? "B747" : "Concorde";
		t.ATA_category = "ATA " + (int)(Math.random()*100);
		t.hangar = false;
		t.duration = ((int)(Math.random()*28)+2) + " days";
		t.action_list = Arrays.asList(new String[]{"Action1","Action2","Action3"});
		t.tools_list = Arrays.asList(new String[]{"Tool1","Tool2","Tool3"});
		t.pieces_list = Arrays.asList(new String[]{"Piece1","Piece2","Piece3"});
		t.qualifications_required = i==0 ? "Wings" : i==20 ? "Motor" : "Software";
		return t;
	}
}
