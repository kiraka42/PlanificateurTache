package com.diderot.projetGLA.daoAdapter;

import java.util.List;

import com.diderot.projetGLA.Object.Task;

public interface TaskDaoAdapter {
	
	/**
	 * 
	 * @return
	 */
	List<Task> getTasks();
	
	
	/**
	 * 
	 * @param airPlaneType
	 * @return
	 */
	List<Task> getTasks(String airPlaneType);
	
	/**
	 * 
	 * @param taskId
	 * @return
	 */
	Task getTask(long taskId);
	
	/**
	 * 
	 * @param task
	 */
	void addTask(Task task);

}
