package com.diderot.projetGLA.Tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class Login {
	public static Html html = new Html();

	public Login(){

	}

	public static boolean log=false;

	@POST
	@Path("post")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String postMethod(@FormParam("name") String name, @FormParam("name2") String name2) {
		html.expression = new HashMap<String ,String>() ;
		if(!html.expression.containsKey("<link rel='stylesheet' media='screen' href='css/style.css'>"))
			html.expression.put("<link rel='stylesheet' media='screen' href='css/style.css'>" ,"<style>" +html.parseFile(html.PATH+"css/style.css",-1)+"</style>");

		if (check(name, name2)) {
			log=true;
			html.expression.put("<!--login-->", html.parseFile(html.PATH+"/utils/loginMenu.t", -1));
			//html.setHtml(html.PATH+"/home.html", html.getHtml(html.PATH+"/home.html").replaceAll(html.parseFile(html.PATH+"utils/formulaire.t", -1), " "));
		/*	try {
				String tmp = html.parseFile(html.PATH+"/home.html", -1);
				new FileWriter(new File(html.PATH+"/home.html")).close();
				html.setHtml(html.PATH+"/home.html", tmp.replaceAll(html.parseFile(html.PATH+"utils/formulaire.t", -1), " "));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			return html.getHtml(html.PATH+"/home.html").replaceAll(html.parseFile(html.PATH+"utils/formulaire.t", -1), " ");

		}
		//return "False password or login <a href='http://localhost:8080'>Go Back </a>";

		html.expression.put("<!--formulaire-->", html.parseFile(html.PATH+"/utils/log.t",-1));
		return html.getHtml(html.PATH+"/home.html");

	}

	@GET
	@Path("deco")
	@Produces(MediaType.TEXT_HTML)
	public String deco(){

		html.expression = new HashMap<String ,String>() ;
		log=false;
		if(!html.expression.containsKey("<link rel='stylesheet' media='screen' href='css/style.css'>"))
			html.expression.put("<link rel='stylesheet' media='screen' href='css/style.css'>" ,"<style>" +html.parseFile(html.PATH+"css/style.css",-1)+"</style>");
		return html.getHtml(html.PATH+"/home.html");
	}




	/*Checking some tests */




	@GET
	@Path("{type}/{var}/{var2}")
	@Produces(MediaType.TEXT_PLAIN)
	public String pathMethod(@PathParam("var") String name, @PathParam("var2") String name2 , @PathParam("type") String type) {

		if (type.equals("MCC") || type.equals("MRO"))
			return checking(name, name2);
		return "ERROR Impossible  user";
	}


	public String checking(String name, String name2) {
		if (check(name, name2)) {
			return "True :  " + name + " " + name2;
		}
		return "vide list";
	}

	public boolean check(String a, String b) {
		//check if the the password and pseudo
		return ((a.equals("pseudo") && b.equals("code")));
	}


	@GET
	@Path("planned_task/{date}")
	@Produces(MediaType.TEXT_PLAIN)
	public String planned_task(@PathParam("date") String date) {
		return "ERROR Impossible  user";
	}


}
