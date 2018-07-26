package com.diderot.projetGLA.ws;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@PermitAll
@Path("/home")
public class Home {
	
	@GET
	@RolesAllowed({"mro","mcc"})
	@Produces(MediaType.TEXT_HTML)
	public String testAuth(@Context SecurityContext sc) {
		if (sc.isUserInRole("mcc")) {
			return "mcc";
		}
		if (sc.isUserInRole("mro")) {
			return "mro";
		}
		return "Impossible";
	}

}
