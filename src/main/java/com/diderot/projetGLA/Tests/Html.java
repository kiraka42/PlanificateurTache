package com.diderot.projetGLA.Tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;

public class Html {


	//Insert CSS file into html.
	public String PATH = "src/main/webapp/";
	int lineCss=-1; // 4 by default
	String css ="style.css";
	//Key = regexp value = insertion code
	public HashMap<String , String> expression = new HashMap<String ,String>() ;


	public boolean check(String a , String  b){
		 a = a.replaceAll("\\s+", "");
		 b = b.replaceAll("\\s+", "");
		 return  (a.equalsIgnoreCase(b));
	}



	public String getHtml(String ss) {
		String chaine = "";
		String fichier = ss;
		// lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				chaine += ligne + "\n";
				for(Entry<String, String> entry : expression.entrySet()) {
				    String cle = entry.getKey();
				    //String valeur = entry.getValue();
				    if(check(cle,ligne)) {
				    	chaine+= ""+ entry.getValue()+"\n";
				    }
				}

			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return "";
		}
		System.out.println(chaine);
		return chaine;
	}

	public void setHtml(String nomFic, String texte) {
		String adressedufichier = System.getProperty("user.dir") + "/" + nomFic;

		try {
			FileWriter fw = new FileWriter(adressedufichier, true);
			BufferedWriter output = new BufferedWriter(fw);
			output.write(texte);
			output.flush();
			System.out.println("fichier cr");
		} catch (IOException ioe) {
			System.out.print("Erreur : ");
			ioe.printStackTrace();
		}
	}

	public String parseFile(String name,int line){
		String chaine = "";
		String fichier = name;
		int nl=0;
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				chaine += ligne + "\n";
				nl++;
				if(nl == line) break;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return "";
		}
		System.out.println(chaine);
		return chaine;

	}
}
