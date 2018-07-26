package com.diderot.projetGLA.ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.tasks.TaskCancelledException;

import com.diderot.projetGLA.Object.Flight;
import com.diderot.projetGLA.Object.Mro;
import com.diderot.projetGLA.Object.PlanningTask;
import com.diderot.projetGLA.Object.Task;
import com.diderot.projetGLA.daoAdapter.DAO;
import com.diderot.projetGLA.daoAdapter.FlightDaoAdapter;
import com.diderot.projetGLA.daoAdapter.MroDaoAdapter;
import com.diderot.projetGLA.daoAdapter.PlaneDaoAdapter;
import com.diderot.projetGLA.daoAdapter.PlanningTaskDaoAdapter;
import com.diderot.projetGLA.daoAdapter.TaskDaoAdapter;
import com.diderot.projetGLA.daoAdapter.elasticsearch.DBConection;
import com.diderot.projetGLA.daoAdapter.elasticsearch.FlightDao;

@PermitAll
@Path("/planned_task")
public class Planned_Task {
	TaskDaoAdapter taskDao=DAO.getTaskDao();
	PlanningTaskDaoAdapter planningTaskDao=DAO.getPlanningTaskDao();
	MroDaoAdapter mroDao = DAO.getmroDao();
	/**
	 * Example
	 * @param date
	 * @return
	 */


	@GET
	@RolesAllowed("mcc")
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{date}")
	public List<PlanningTask> maintainedSoon(@PathParam("date") String date) {
		/*List<PlanningTask> planningTasks;//= new ArrayList<PlanningTask>()
		List<PlanningTask> planTasks= new ArrayList<PlanningTask>();
		planningTasks=planningTaskDao.getPlanningTasks();
		for(PlanningTask planningTask : planningTasks ){
			planningTask.date.setTime(planningTask.date.getTime()+taskDao.getTask(planningTask.taskId).periodicity);
			//planTasks.add(planningTask);
		}
		List<Task> tasks;//= new ArrayList<Task>();
		tasks=taskDao.getTasks();
		return planTasks;*/
		List<PlanningTask> planningTasks = planningTaskDao.getTasksMCC();
		return planningTasks;//.subList(0, Math.min(20,planningTasks.size()));
	}
	
	@GET
	@RolesAllowed({"mro","mcc"})
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{mroId}/{date}")
	public List<PlanningTask> mroTasks(@PathParam("mroId") long MroId,
			@PathParam("date") String date) {
		List<PlanningTask> planningTasks;// = new ArrayList<PlanningTask>();
		planningTasks = planningTaskDao.getMroPlanningTasks(MroId);
		
		/*List<PlanningTask> list2 = new ArrayList<PlanningTask>();
		List<Task> list2 = new ArrayList<Task>();
		list2=taskDao.getTasks();*/
		return planningTasks;//.subList(0, Math.min(20,planningTasks.size()));
	}
	
	@GET
	@RolesAllowed({"mro","mcc"})
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/tasks/{plane_id}/{plane_type}")
	public List<PlanningTask> planeTasks(@PathParam("plane_id") long planeId,
			@PathParam("plane_type") String planetype) {
		List<Task> tasks = taskDao.getTasks(planetype);
		List<PlanningTask> planningTasks = new ArrayList<PlanningTask>();
		for(Task task : tasks){
			 PlanningTask planningTask = planningTaskDao.getLastTask(planeId,task.taskId);
			 if(planningTask != null){
				 long date = planningTask.date.getTime();
				 date += task.periodicity;
				 planningTask.date.setTime(date);
				 planningTasks.add(planningTask);
			 }
		}
		//List<Task> list2 = new ArrayList<Task>();
		//list2=taskDao.getTasks();
		return planningTasks;
	}
	
	@GET
	@RolesAllowed({"mro","mcc"})
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/nextTask/{plane_id}/{plane_type}")
	public PlanningTask nextplaneTasks(@PathParam("plane_id") long planeId,
			@PathParam("plane_type") String planetype) {
		List<Task> tasks = taskDao.getTasks(planetype);
		List<PlanningTask> planningTasks = new ArrayList<PlanningTask>();
		for(Task task : tasks){
			 PlanningTask planningTask = planningTaskDao.getLastTask(planeId,task.taskId);
			 if(planningTask != null){
				 long date = planningTask.date.getTime();
				 date += task.periodicity;
				 planningTask.date.setTime(date);
				 planningTasks.add(planningTask);
			 }
		}
		//List<Task> list2 = new ArrayList<Task>();
		//list2=taskDao.getTasks();
		Comparator<PlanningTask> comparator=new Comparator<PlanningTask>() {
			
			@Override
			public int compare(PlanningTask planningTask1, PlanningTask planningTask2) {
				// TODO Auto-generated method stub
				if(planningTask1.date.getTime() > planningTask2.date.getTime())
					return 1;
				return 0;
			}
		};
		//planningTasks.sort(comparator);
		return planningTasks.get(0);
	}
	
	@GET
	@RolesAllowed({"mro","mcc"})
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id/{id}")
	public PlanningTask getTask(@PathParam("id") long id) {
		PlanningTask tmp;// = PlanningTask.randomInstance();
		tmp=planningTaskDao.getPlanningTask(id);
		return tmp;
	}
	
	@GET
	@RolesAllowed("mcc")
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/urgent")
	public List<PlanningTask> getUrgentTasks() {
		List<PlanningTask> list = planningTaskDao.getUrgentPlanningTasks();
		return list;
	}
	
	@GET
	@RolesAllowed("mcc")
	@Produces(MediaType.TEXT_HTML)
	@Path("/urgentNumber")
	public String getUrgentTasksNumber() {
		List<PlanningTask> list = planningTaskDao.getUrgentPlanningTasks();
		return "" + list.size();
	}

	@POST
	@RolesAllowed("mcc")
	@Produces(MediaType.TEXT_HTML)
	@Path("/{mro}/{date}/{id}")
	public String linksTask(@PathParam("mro") String mro,
			@PathParam("date") String date, @PathParam("id") long id) {
		return "Success";
	}

	@POST
	@RolesAllowed("mro")
	@Produces(MediaType.TEXT_HTML)
	@Path("/{id_pt}")
	public String marksTask(@PathParam("id_pt") long id) {
		planningTaskDao.setExecuted(id);
		return "Success";
	}

	@POST
	@RolesAllowed({"mro","mcc"})
	@Produces(MediaType.TEXT_HTML)
	@Path("/{id_task}/{id_plane}/{id_airport}/{date}")
	public String linksTaskPlane(	@PathParam("id_task") long task_id,
									@PathParam("id_plane") long plane_id,
									@PathParam("id_airport") long airport_id, 
									@PathParam("date") String date) {
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
			Date d;
			d = formatDate.parse(date);
			PlanningTask planningTask= new PlanningTask();
			Mro mro = mroDao.getMroByairport(airport_id);
			planningTask.date=d;
			planningTask.planeId=plane_id;
			planningTask.taskId=task_id;
			planningTask.Mro=mro.MRO_id;
			planningTask.executed=false;
			//System.out.println(planningTask.planningTaskId);
			planningTaskDao.addPlanningTask(planningTask);
			return "Success";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return "erreur date";
		}
		
	}
	
	

}
