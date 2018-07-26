package com.diderot.projetGLA.Object;

public class Mcc {
	public long MCC_id;
	public String last_name;
	public String first_name;
	public String e_mail;
	public String pass_word;
	
	//Useful for stubs only
	public static Mcc randomInstance(){
		Mcc m = new Mcc();
		m.MCC_id = (long)(Math.random()*100);
		m.first_name = m.MCC_id % 3==0 ? "Yann" : m.MCC_id % 3==1 ? "Pierre" : "Jean-Baptiste";
		m.last_name = m.MCC_id % 3==0 ? "Régis-Gianas" : m.MCC_id % 3==1 ? "Letouzey" : "Yunès";
		m.e_mail = (m.MCC_id % 3==0 ? "yrg" : m.MCC_id % 3==1 ? "pl" : "jby") + "@gmail.com";
		m.pass_word = "mcc";
		return m;
	}
}
