package com.diderot.projetGLA.Object;

public class Mro {
	public long MRO_id;
	public String user_name;
	public String pass_word;
	public long airport_id;
	public int mechanic_numbers;
	//qualifications
	
	//Useful for stubs only
	public static Mro randomInstance(){
		Mro m = new Mro();
		m.MRO_id = 1;//(long)(Math.random()*5);
		m.user_name = "mro";
		m.pass_word = "mro";
		m.airport_id = m.MRO_id;//(long)(Math.random()*100);
		m.mechanic_numbers = (int)(Math.random()*100);
		return m;
	}
}
