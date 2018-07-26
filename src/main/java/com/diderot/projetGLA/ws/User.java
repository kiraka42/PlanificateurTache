package com.diderot.projetGLA.ws;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.diderot.projetGLA.Object.Mcc;
import com.diderot.projetGLA.Object.Mro;
import com.diderot.projetGLA.daoAdapter.DAO;
import com.diderot.projetGLA.daoAdapter.MroDaoAdapter;

@PermitAll
@Path("/user")
public class User {

	@GET
	@RolesAllowed("mro")
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/mro/{username_mro}")
	public Mro getMro(@PathParam("username_mro") String name) {
		// TODO Return mro from database associated with username
		//MroDaoAdapter mroDao = DAO.getmroDao();
		//return mroDao.getMro(name);
		return Mro.randomInstance();
	}

	@GET
	@RolesAllowed("mcc")
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/mcc/{username_mcc}")
	public Mcc getMcc(@PathParam("username_mcc") String name) {
		// TODO Return mcc from database associated with username
		return Mcc.randomInstance();
	}

	@GET
	@RolesAllowed("mcc")
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id_plane}/{date}")
	public Mro availableMro(@PathParam("id_plane") long id, @PathParam("date") String date) {
		return Mro.randomInstance();
	}




}
