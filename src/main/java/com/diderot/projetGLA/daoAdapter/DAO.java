package com.diderot.projetGLA.daoAdapter;

import com.diderot.projetGLA.daoAdapter.elasticsearch.*;

public class DAO {

	public static AirportDaoAdapter getAirportDao(){
		return new AirportDao();
	}
	
	public static FlightDaoAdapter getFlightDao(){
		return new FlightDao();
	}
	
	public static MccDaoAdapter getMccDao(){
		return new MccDao();
	}
	
	public static PlaneDaoAdapter getPlaneDao(){
		return new PlaneDao();
	}
	
	public static PlanningTaskDaoAdapter getPlanningTaskDao(){
		return new PlanningTaskDao();
	}
	
	public static TaskDaoAdapter getTaskDao(){
		return new TaskDao();
	}
	public static MroDaoAdapter getmroDao(){
		return new MroDao();
	}
	
}
