package com.diderot.projetGLA.daoAdapter.elasticsearch;


import java.util.List;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;

import com.diderot.projetGLA.Object.Flight;
import com.diderot.projetGLA.daoAdapter.FlightDaoAdapter;


public class FlightDao implements FlightDaoAdapter{
	public static final String TABLE="flight";
	//private DBConection dbConection=new DBConection();
	private DBConection dbConection = DBConection.getInstance();
	/**
	 * 
	 */
	public List<Flight> getFlights()
	{
		List<Flight> listFlight=dbConection.getAll(TABLE,"date_of_departure", Flight.class);
		return listFlight;
	}
	/**
	 * 
	 */
	public Flight getFlight(String commercial_number)
	{
		Flight flight=dbConection.getById(TABLE, commercial_number, Flight.class);
		return flight;
	}

	public List<Flight> getFlights(long planeId)
	{
		TermQueryBuilder query=QueryBuilders.termQuery("planeId", planeId);
		List<Flight> listFlight=dbConection.searchByQuery(TABLE, query,"date_of_departure", Flight.class);
		return listFlight;
	}
	
	public List<Flight> getFlightsFrom(long departure_airport)
	{
		TermQueryBuilder query=QueryBuilders.termQuery("departure_airport", departure_airport);
		List<Flight> listFlight=dbConection.searchByQuery(TABLE, query,"date_of_departure", Flight.class);	
		return listFlight;
	}

	public List<Flight> getFlightsTo(long arrival_airport)
	{
		TermQueryBuilder query=QueryBuilders.termQuery("planeId", arrival_airport);
		List<Flight> listFlight=dbConection.searchByQuery(TABLE, query,"date_of_departure", Flight.class);
		return listFlight;
	}
	
	public void addFlight(Flight flight )
	{
		long id = dbConection.getnextId(TABLE);
		flight.flight_id=id;
		dbConection.addObject(TABLE, flight, String.valueOf(flight.flight_id));
		dbConection.setnextId(TABLE, id + 1);
	}
	
	public List<Flight> getFlights(long planeId, String date)
	{
		QueryBuilder query=QueryBuilders
				.boolQuery().must(QueryBuilders.matchQuery("planeId", planeId))
				.should(QueryBuilders.matchQuery("date_of_departure",date))
				.should(QueryBuilders.matchQuery("date_of_arrival", date))
				.minimumShouldMatch(1);

		List<Flight> listFlight=dbConection.searchByQuery(TABLE, query,"date_of_departure", Flight.class);
		return listFlight;
	}
	
	
}
