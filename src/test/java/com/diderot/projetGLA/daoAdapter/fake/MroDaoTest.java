package com.diderot.projetGLA.daoAdapter.fake;

import java.util.List;

import org.junit.Test;

import com.diderot.projetGLA.Object.Airport;
import com.diderot.projetGLA.Object.Mro;
import com.diderot.projetGLA.daoAdapter.AirportDaoAdapter;
import com.diderot.projetGLA.daoAdapter.DAO;
import com.diderot.projetGLA.daoAdapter.MroDaoAdapter;


public class MroDaoTest {
	private MroDaoAdapter MroDao = DAO.getmroDao();
	private AirportDaoAdapter AirportDao = DAO.getAirportDao();
	@Test
	public void ajouter(){
		List<Airport> airports = AirportDao.getAirports();
		for(Airport airport : airports){
			Mro mro = Mro.randomInstance();
			mro.airport_id = airport.airport_id;
			MroDao.addMro(mro);
		}
		
	}
}
