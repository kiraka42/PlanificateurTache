package com.diderot.projetGLA.Object;

import java.util.Date;

public class PlanningTask {
	 public long planningTaskId;
	 public Date date;
	 public boolean executed;// [true or false]
	 public long taskId;
	 public long planeId;
	 public long Mro;

	 //Useful for stubs only
	 public static  PlanningTask randomInstance(){
		 PlanningTask pl = new PlanningTask();
		 pl.planningTaskId = (long)(Math.random()*100);
		 pl.date = new Date((long)(Math.random()*System.currentTimeMillis()));
		 pl.taskId = (long)(Math.random()*20);
		 pl.executed = false;
		 pl.planeId = (long)(Math.random()*20);
		 pl.Mro = (long)(Math.random()*20);
		 return pl;
	 }
}
