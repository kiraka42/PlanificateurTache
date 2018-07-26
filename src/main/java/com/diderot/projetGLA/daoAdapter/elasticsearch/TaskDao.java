package com.diderot.projetGLA.daoAdapter.elasticsearch;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import com.diderot.projetGLA.Object.Task;
import com.diderot.projetGLA.daoAdapter.TaskDaoAdapter;

public class TaskDao implements TaskDaoAdapter{	
	public final String TABLE="task"; 
	//private DBConection dbConection=new DBConection();
	private DBConection dbConection = DBConection.getInstance();
	public List<Task> getTasks()
	{
		List<Task> listTask=dbConection.getAll(TABLE,"taskId", Task.class);
		return listTask;
	}

	public List<Task> getTasks(String airPlaneType)
	{
		QueryBuilder query = QueryBuilders.matchQuery("airPlaneType", airPlaneType);
		List<Task> listTask = new ArrayList<Task>();
		listTask = dbConection.searchByQuery(TABLE, query,"taskId", Task.class);
		return listTask;
	}
	
	public Task getTask(long taskId)
	{
		Task task=dbConection.getById(TABLE, String.valueOf(taskId), Task.class);
		return task;
	}
	
	
	public void addTask(Task task)
	{	
		long id =  dbConection.getnextId(TABLE);
		task.taskId = id;
		dbConection.addObject(TABLE, task, String.valueOf(task.taskId));
		dbConection.setnextId(TABLE, id + 1); 
	}
}
