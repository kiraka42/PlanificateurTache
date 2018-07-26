la package com.diderot.projetGLA.daoAdapter.fake;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.diderot.projetGLA.Object.Airport;
import com.diderot.projetGLA.Object.Flight;
import com.diderot.projetGLA.Object.Mro;
import com.diderot.projetGLA.Object.Plane;
import com.diderot.projetGLA.Object.PlanningTask;
import com.diderot.projetGLA.Object.Task;
import com.diderot.projetGLA.daoAdapter.AirportDaoAdapter;
import com.diderot.projetGLA.daoAdapter.DAO;
import com.diderot.projetGLA.daoAdapter.FlightDaoAdapter;
import com.diderot.projetGLA.daoAdapter.MroDaoAdapter;
import com.diderot.projetGLA.daoAdapter.PlaneDaoAdapter;
import com.diderot.projetGLA.daoAdapter.PlanningTaskDaoAdapter;
import com.diderot.projetGLA.daoAdapter.TaskDaoAdapter;
import com.diderot.projetGLA.daoAdapter.elasticsearch.DBConection;
import com.diderot.projetGLA.daoAdapter.elasticsearch.MroDao;

public class RemplireBDD {


	public void ajouterPlanes(){
		System.out.println("planes\t");

		PlaneDaoAdapter planeDao= DAO.getPlaneDao();
		for(int i=0;i<20; i++){
			Plane plane =Plane.randomInstance();
			planeDao.addPlane(plane);
			System.out.print(".");
		}
		System.out.println();
	}

	public void ajouterAirports(){
		System.out.println("Airports\t");

		AirportDaoAdapter airportDao= DAO.getAirportDao();
		for(int i=0;i<20; i++){
			Airport airport=Airport.randomInstance();
			airportDao.addAirport(airport);
			System.out.print(".");
		}
		System.out.println();
	}

	public void ajouterMccs(){
		System.out.println("Mccs\t");

		MccDaoAdapter mccDao = DAO.getMccDao();
		for(int i=0;i<10; i++){
			Mcc mcc = Mcc.randomInstance();
			mccDao.addMcc(mcc);
			System.out.print(".");
		}
		System.out.println();
	}

	public void ajouterMros(){
		System.out.println("Mros\t");
		MroDaoAdapter mroDao = DAO.getmroDao();
		for(int i=0;i<10; i++){
			Mro mro = Mro.randomInstance();
			mroDao.addMro(mro);
			System.out.print(".");
		}
		System.out.println();
	}

	public void ajouterFlights(){
		System.out.println("Flights\t");

		FlightDaoAdapter flightDao= DAO.getFlightDao();
		PlaneDaoAdapter planeDao = DAO.getPlaneDao();
		List<Plane> planes = planeDao.getPlanes();
		long d;
		for(Plane plane : planes){
			d = System.currentTimeMillis() + (long)(Math.random()*200000);
			for(int i=0;i<20; i++){
				Flight flight = Flight.randomInstance();
				flight.planeId = plane.plane_id;
				flightDao.addFlight(flight);
			}
			System.out.print(".");
		}
		System.out.println();

		Flight f = Flight.randomInstance();
		f.departure_airport = 1;
		f.arrival_airport = 1;
		f.planeId = 1;
		long d2 = System.currentTimeMillis() + 18000000000l;
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		Date date=new Date(d2);
		f.date_of_departure=formatDate.format(date);
		date = new Date(d2+18000000);
		System.out.println(date.toString());
		f.date_of_arrival=formatDate.format(date);
		flightDao.addFlight(f);
	}

	public void ajouterTasks(){
		System.out.println("Tasks\t");

		TaskDaoAdapter taskDao= DAO.getTaskDao();
		for(int i=0;i<20; i++){
			Task task =Task.randomInstance();
			taskDao.addTask(task);
			System.out.print(".");
		}
		System.out.println();
	}


	public void ajouterPlanningTask(){
		System.out.println("planningTasks\t");
		PlanningTaskDaoAdapter planningTaskDao= DAO.getPlanningTaskDao();
		TaskDaoAdapter taskDao = DAO.getTaskDao();
		PlaneDaoAdapter planeDao = DAO.getPlaneDao();
		List<Plane> planes = planeDao.getPlanes();
		List<Task> tasks;
		for(Plane plane : planes){
			tasks = taskDao.getTasks(plane.plane_type);
			for(Task task : tasks){
				PlanningTask planningTask = new PlanningTask();
				planningTask.taskId = task.taskId;
				planningTask.planeId = plane.plane_id;
				if (new Random().nextBoolean())
					planningTask.date = new Date(System.currentTimeMillis() + 864000000);
				else
					planningTask.date = new Date(System.currentTimeMillis() - 864000000);
				//System.out.println(planningTask.date.getTime());
				//planningTask.date = new Date(System.currentTimeMillis());
				planningTask.executed = (((int)(Math.random() * 2) % 2) == 0)? false : true;
				planningTask.Mro = (long)(Math.random() * 5);
				planningTaskDao.addPlanningTask(planningTask);
			}
			System.out.print(".");
		}
		System.out.println();
	}

	public void ajouterMro(){
		System.out.println("Mro\t");
		MroDaoAdapter mroDao = new MroDao();
		Mro mro = Mro.randomInstance();
		mroDao.addMro(mro);
		System.out.print(".");
		System.out.println();
	}

	@Test
	public void remplirBdd(){
		DBConection dbConection = DBConection.getInstance();
		dbConection.initDataBase();
		ajouterPlanes();
		ajouterAirports();
		ajouterFlights();
		ajouterTasks();
		ajouterPlanningTask();
		ajouterMro();
	}
}
