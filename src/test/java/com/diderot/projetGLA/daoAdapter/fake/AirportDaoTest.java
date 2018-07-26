package com.diderot.projetGLA.daoAdapter.fake;

import java.util.List;

import org.junit.Test;

import com.diderot.projetGLA.Object.Airport;
import com.diderot.projetGLA.Object.Flight;
import com.diderot.projetGLA.daoAdapter.AirportDaoAdapter;
import com.diderot.projetGLA.daoAdapter.DAO;
import com.diderot.projetGLA.daoAdapter.FlightDaoAdapter;

import junit.framework.Assert;

public class AirportDaoTest {

	
	@Test
	public void ajouter(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		AirportDaoAdapter airportDao= DAO.getAirportDao();
		
		for(int i=0;i<20; i++){
			Airport airport=Airport.randomInstance();
			airportDao.addAirport(airport);
		}
		
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		//Assert.assertEquals("ali", "ali");
	}
	
	
	//@Test
	public void tester(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		
		AirportDaoAdapter airportDao= DAO.getAirportDao();
		List<Airport> listAirport=airportDao.getAirports();
		for(Airport airport : listAirport){
			System.out.println(airport.toString());
			System.out.println();
		}
		
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		//Assert.assertEquals("ali", "ali");
	}
}
