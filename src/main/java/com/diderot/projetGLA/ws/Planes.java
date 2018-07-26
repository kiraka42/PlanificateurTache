package com.diderot.projetGLA.ws;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.diderot.projetGLA.Object.Plane;
import com.diderot.projetGLA.Object.Task;
import com.diderot.projetGLA.daoAdapter.DAO;
import com.diderot.projetGLA.daoAdapter.PlaneDaoAdapter;
import com.diderot.projetGLA.daoAdapter.PlanningTaskDaoAdapter;
import com.diderot.projetGLA.daoAdapter.TaskDaoAdapter;

@PermitAll
@Path("/plane")
public class Planes {
	PlaneDaoAdapter planeDao=DAO.getPlaneDao();
	TaskDaoAdapter taskDaoAdapter=DAO.getTaskDao();
	PlanningTaskDaoAdapter planningTaskDao= DAO.getPlanningTaskDao();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{date}")
	public List<Plane> maintainedSoon(@PathParam("date") String date) {
		List<Plane> planes = new ArrayList<Plane>();
		planes=planeDao.getPlanes();
		/*for(Plane plane: planes){
			List<Task> tasks=taskDaoAdapter.getTasks(plane.plane_type);
			//taskDaoAdapter
			
		}*/
		return planes;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<Plane> getplanes() {
		List<Plane> planes = new ArrayList<Plane>();
		planes=planeDao.getPlanes();
		return planes;
	}

	@GET
	@RolesAllowed({"mro","mcc"})
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id/{id_plane}")
	public Plane getPlane(@PathParam("id_plane") String plane_id) {
		Plane plane=planeDao.getPlane(plane_id);
		
		return plane;
	}

}
