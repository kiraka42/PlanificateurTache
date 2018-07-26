package com.diderot.projetGLA.daoAdapter.fake;

import java.util.List;

import org.junit.Test;

import com.diderot.projetGLA.Object.Airport;
import com.diderot.projetGLA.Object.Flight;
import com.diderot.projetGLA.daoAdapter.AirportDaoAdapter;
import com.diderot.projetGLA.daoAdapter.DAO;
import com.diderot.projetGLA.daoAdapter.FlightDaoAdapter;

public class FlightDaoTest {

	
	@Test
	public void ajouter(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		
		FlightDaoAdapter flightDao= DAO.getFlightDao();
		
		for(int i=0;i<20; i++){
			Flight flight=Flight.randomInstance();
			flightDao.addFlight(flight);
		}
		
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		//Assert.assertEquals("ali", "ali");
	}
	
	
	
	//@Test
	public void tester(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		
		FlightDaoAdapter flightDao= DAO.getFlightDao();
		List<Flight> listFlights=flightDao.getFlights();
		for(Flight flight : listFlights){
			System.out.println(flight.toString());
			System.out.println(flight.date_of_arrival);
			System.out.println();
		}
		
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		//Assert.assertEquals("ali", "ali");
	}
	
	
	
}
