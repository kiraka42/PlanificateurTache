package com.diderot.projetGLA.daoAdapter.fake;

import java.util.List;

import org.junit.Test;

import com.diderot.projetGLA.Object.Plane;
import com.diderot.projetGLA.daoAdapter.DAO;
import com.diderot.projetGLA.daoAdapter.PlaneDaoAdapter;
import com.diderot.projetGLA.daoAdapter.elasticsearch.DBConection;

public class PlaneDaoTest {
	

	//@Test
	public void ajouter(){
		
		PlaneDaoAdapter planeDao= DAO.getPlaneDao();
		for(int i=0;i<20; i++){
			Plane plane =Plane.randomInstance();
			planeDao.addPlane(plane);
		}
	}
	
	@Test
	public void tester(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		/*PlaneDaoAdapter planeDao= DAO.getPlaneDao();
		List<Plane> listPlane=planeDao.getPlanes();
		for(Plane plane : listPlane){
			System.out.println(plane.toString());
		}*/
		DBConection dbConection = new DBConection();
		System.out.println(dbConection.getnextId("plane"));
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	//@Test
	public void tester2(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		PlaneDaoAdapter planeDao= DAO.getPlaneDao();
		Plane plane=planeDao.getPlane("963");
		if(plane != null)
			System.out.println(plane.toString());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	
}
