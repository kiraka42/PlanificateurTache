package com.diderot.projetGLA.daoAdapter.elasticsearch;

import java.util.Date;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;

import com.diderot.projetGLA.Object.PlanningTask;
import com.diderot.projetGLA.daoAdapter.PlanningTaskDaoAdapter;
import com.diderot.projetGLA.daoAdapter.TaskDaoAdapter;

public class PlanningTaskDao implements PlanningTaskDaoAdapter{
	public static final String TABLE="planning_task"; 
	//private DBConection dbConection=new DBConection();
	private DBConection dbConection = DBConection.getInstance();
	/**
	 * 
	 */
	public List<PlanningTask> getPlanningTasks(){
		List<PlanningTask> listPlanningTask=dbConection.getAll(TABLE,"date", PlanningTask.class);
		return listPlanningTask;
	}
	/**
	 * 
	 */
	public List<PlanningTask> getPlanningTasks(long planeId){
		TermQueryBuilder query=QueryBuilders.termQuery("planeId", planeId);
		List<PlanningTask> listPlanningTask=dbConection.searchByQuery(TABLE, query,"date", PlanningTask.class);
		return listPlanningTask;
	}
	/**
	 * 
	 */
	public List<PlanningTask> getPlanningTasks(Date date)
	{
		TermQueryBuilder query=QueryBuilders.termQuery("date", date);		
		List<PlanningTask> listPlanningTask=dbConection.searchByQuery(TABLE, query,"date", PlanningTask.class);
		
		return listPlanningTask;
	}
	/**
	 * 
	 */
	public PlanningTask getPlanningTask(long planningTaskId)
	{
		PlanningTask planningTask=dbConection.getById(TABLE, String.valueOf(planningTaskId), PlanningTask.class);
		
		return planningTask;
	}
	
	/**
	 * 
	 */
	public void addPlanningTask(PlanningTask planningTask)
	{
		long id=dbConection.getnextId(TABLE);
		planningTask.planningTaskId=id;
		dbConection.addObject(TABLE, planningTask, String.valueOf(planningTask.planningTaskId ));	
		dbConection.setnextId(TABLE, id + 1);
	}
	
	@Override
	public List<PlanningTask> getMroPlanningTasks(long mro) {
		// TODO Auto-generated method stub
		
		QueryBuilder query= QueryBuilders.boolQuery()
							.must(QueryBuilders.matchQuery("Mro", mro))
							.must(QueryBuilders.matchQuery("executed", false));
		List<PlanningTask> listPlanningTask=dbConection.searchByQuery(TABLE, query,"date", PlanningTask.class);
		//System.out.println(listPlanningTask.size()+"\n"+mro);
		return listPlanningTask;
	}
	
	@Override
	public PlanningTask getLastTask(long planeId, long taskId){
		//PlanningTask planningTask=new PlanningTask(); 
	
		QueryBuilder query=QueryBuilders
				.boolQuery().must(QueryBuilders.matchQuery("planeId", planeId))
				.must(QueryBuilders.matchQuery("taskId", taskId));
		List<PlanningTask> PlanningTasks=dbConection.searchByQuery(TABLE, query,"date", PlanningTask.class, 1);
		if(PlanningTasks.size() > 0)
			return PlanningTasks.get(0);
		return null;
	}
	@Override
	public List<PlanningTask> getUrgentPlanningTasks() {
		// TODO Auto-generated method stub
		QueryBuilder query= QueryBuilders.boolQuery()
				.must(QueryBuilders.matchQuery("executed", false))
				.must(QueryBuilders.rangeQuery("date").to(System.currentTimeMillis()));
		List<PlanningTask> PlanningTasks=dbConection.searchByQuery(TABLE, query,"date", PlanningTask.class);
		return PlanningTasks;
	}
	@Override
	public void setExecuted(long planningTaskId) {
		// TODO Auto-generated method stub
		PlanningTask planningTask = this.getPlanningTask(planningTaskId);
		if(planningTask != null){
			//DBConection dbConection = new DBConection();
			planningTask.executed = true;
			dbConection.updateObject(TABLE, planningTask, planningTask.planningTaskId);
			PlanningTask pt = PlanningTask.randomInstance();
			pt.planeId = planningTask.planeId;
			pt.taskId = planningTask.taskId;
			pt.planeId= planningTask.planeId;
			TaskDaoAdapter taskdao = new TaskDao();
			pt.date = new Date(taskdao.getTask(pt.taskId).periodicity + System.currentTimeMillis());
			addPlanningTask(pt);
		}
	}
	
	public List<PlanningTask> getTasksMCC(){
		QueryBuilder query= QueryBuilders.boolQuery()
				.must(QueryBuilders.matchQuery("executed", false));
		List<PlanningTask> planningTasks=dbConection.searchByQuery(TABLE, query,"date", PlanningTask.class);
		return planningTasks;
	}
	
}
