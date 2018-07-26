package com.diderot.projetGLA.daoAdapter;

import java.util.List;

import com.diderot.projetGLA.Object.Flight;

public interface FlightDaoAdapter {

	/**
	 * 
	 * @return
	 */
	List<Flight> getFlights();
	/**
	 * 
	 * @param ommercial_number
	 * @return
	 */
	Flight getFlight(String commercial_number);
	
	/**
	 * 
	 * @param planeId
	 * @return
	 */
	List<Flight> getFlights(long planeId);
	
	/**
	 * 
	 * @param departure_airport
	 * @return
	 */
	List<Flight> getFlightsFrom(long departure_airport);
	
	/**
	 * 
	 * @param arrival_airport
	 * @return
	 */
	List<Flight> getFlightsTo(long arrival_airport);
	
	/**
	 * 
	 * @param planeId
	 * @param date
	 * @return
	 */
	public List<Flight> getFlights(long planeId, String date);
	
	/**
	 * 
	 * @param flight
	 */
	void addFlight(Flight flight);
	
}
