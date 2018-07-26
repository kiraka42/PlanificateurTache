package com.diderot.projetGLA.daoAdapter;

import java.util.Date;
import java.util.List;

import com.diderot.projetGLA.Object.PlanningTask;

public interface PlanningTaskDaoAdapter {

	/**
	 *
	 * @return
	 */
	List<PlanningTask> getPlanningTasks();

	/**
	 *
	 * @param plane
	 * @return
	 */
	List<PlanningTask> getPlanningTasks(long planeId);

	/**
	 *
	 * @param date
	 * @return
	 */
	List<PlanningTask> getPlanningTasks(Date date);

	/**
	 *
	 * @param Mro
	 * @return
	 */
	List<PlanningTask> getMroPlanningTasks(long Mro);

	/**
	 *
	 * @param id_planning_task
	 * @return
	 */
	PlanningTask getPlanningTask(long planningTaskId);

	/**
	 *
	 * @param planningTask
	 */
	void addPlanningTask(PlanningTask planningTask);
	/**
	 *
	 * @param planeId
	 * @param taskId
	 * @return
	 */
	PlanningTask getLastTask(long planeId, long taskId);
	/**
	 *
	 * @return
	 */
	List<PlanningTask> getUrgentPlanningTasks();

	/**
	 *
	 * @param id
	 */
	public void setExecuted(long planningTaskId);
	
	List<PlanningTask> getTasksMCC();
}
