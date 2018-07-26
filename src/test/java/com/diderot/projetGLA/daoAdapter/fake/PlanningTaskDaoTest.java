package com.diderot.projetGLA.daoAdapter.fake;

import java.util.List;

import org.junit.Test;


import com.diderot.projetGLA.Object.PlanningTask;
import com.diderot.projetGLA.daoAdapter.DAO;

import com.diderot.projetGLA.daoAdapter.PlanningTaskDaoAdapter;

public class PlanningTaskDaoTest {
	
	//@Test
	public void ajouter(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		PlanningTaskDaoAdapter planningTaskDao= DAO.getPlanningTaskDao();
		for(int i=0;i<100; i++){
			PlanningTask planningTask=PlanningTask.randomInstance();
			planningTaskDao.addPlanningTask(planningTask);
			System.out.println("ajouté");
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	@Test
	public void tester(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		PlanningTaskDaoAdapter planningTaskDao= DAO.getPlanningTaskDao();
		/*List<PlanningTask> planningTasks =planningTaskDao.getUrgentPlanningTasks();
		for(PlanningTask planningTask : planningTasks){
			System.out.println(planningTask.toString());
			System.out.println(planningTask.executed);
			System.out.println(planningTask.planeId);
			System.out.println(planningTask.date.toString());
			System.out.println();
		}*/
		planningTaskDao.setExecuted(14);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
}
