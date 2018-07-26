package com.diderot.projetGLA.daoAdapter.fake;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.diderot.projetGLA.Object.Airport;
import com.diderot.projetGLA.Object.Task;
import com.diderot.projetGLA.daoAdapter.AirportDaoAdapter;
import com.diderot.projetGLA.daoAdapter.DAO;
import com.diderot.projetGLA.daoAdapter.TaskDaoAdapter;

import junit.framework.Assert;

public class TaskDaoTest {
	
	
	@Test
	public void ajouter(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		TaskDaoAdapter taskDao= DAO.getTaskDao();
		
		for(int i=0;i<20; i++){
			Task task =Task.randomInstance();
			taskDao.addTask(task);
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	//@Test
	public void tester(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		TaskDaoAdapter taskDao= DAO.getTaskDao();
		List<Task> listTask=taskDao.getTasks();
		
		for(Task task : listTask){
			System.out.println(task.toString());
			System.out.println();
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		//Assert.assertEquals("ali", "ali");
	}
	
	@After
	public void afterTest(){
		
	}
}
