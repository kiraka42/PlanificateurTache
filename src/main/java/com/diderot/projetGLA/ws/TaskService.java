package com.diderot.projetGLA.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;

import com.diderot.projetGLA.Object.Task;
import com.diderot.projetGLA.daoAdapter.DAO;
import com.diderot.projetGLA.daoAdapter.TaskDaoAdapter;
import com.diderot.projetGLA.daoAdapter.elasticsearch.TaskDao;

@PermitAll
@Path("/task")
public class TaskService {
	TaskDaoAdapter taskDao=DAO.getTaskDao();
	@GET
	@RolesAllowed({"mro","mcc"})
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id/{id}")
	public Task getTask(@PathParam("id") long id) {		
		
		Task task=new Task();
		task=taskDao.getTask(id);
		return task;
	}
	
	@GET
	@RolesAllowed("mcc")
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/plane_type/{plane_type}")
	public List<Task> getPlaneTasks(@PathParam("plane_type") String airPlaneType) {		
		List<Task> tasks;
		tasks=taskDao.getTasks(airPlaneType);
		System.out.println("taille="+tasks.size()+"  "+airPlaneType );
		return tasks;
	}
	
	@PUT
	@RolesAllowed("mcc")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	@Path("/add")
	public String add(com.diderot.projetGLA.Object.Task task) {
		TaskDao taskDaoFake=new TaskDao();
		taskDaoFake.addTask(task);
		return "Success";
	}
}
