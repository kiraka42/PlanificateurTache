package com.diderot.projetGLA.daoAdapter.fake;

import java.util.List;

import org.junit.Test;

import com.diderot.projetGLA.Object.Mcc;
import com.diderot.projetGLA.daoAdapter.DAO;
import com.diderot.projetGLA.daoAdapter.MccDaoAdapter;

public class MccDaoTest {
	@Test
	public void ajouter(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		MccDaoAdapter mccDao= DAO.getMccDao();
		
		for(int i=0;i<10; i++){
			Mcc mcc =Mcc.randomInstance();
			mccDao.addMcc(mcc);
		}
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		//Assert.assertEquals("ali", "ali");
	}
	
	//@Test
	public void tester(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		MccDaoAdapter mccDao= DAO.getMccDao();
		List<Mcc> listMcc=mccDao.getAllMcc();
		
		for(Mcc mcc : listMcc){
			System.out.println(mcc.toString());
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		//Assert.assertEquals("ali", "ali");
	}
}
