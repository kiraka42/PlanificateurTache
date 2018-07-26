package com.diderot.projetGLA.daoAdapter;



import java.util.List;

import com.diderot.projetGLA.Object.Airport;

public interface AirportDaoAdapter {
	/**
	 * 
	 * @return
	 */
	List<Airport> getAirports();
	
	/**
	 * 
	 * @param airport_id
	 * @return
	 */
	Airport getAirport(long airport_id);
	
	/**
	 * 
	 * @param airport
	 */
	void addAirport(Airport airport);
	
}
