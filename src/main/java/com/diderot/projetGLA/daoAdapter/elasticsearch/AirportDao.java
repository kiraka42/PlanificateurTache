package com.diderot.projetGLA.daoAdapter.elasticsearch;

import java.util.List;
import com.diderot.projetGLA.Object.Airport;
import com.diderot.projetGLA.daoAdapter.AirportDaoAdapter;

public class AirportDao implements AirportDaoAdapter{
	public static final String TABLE="airport";
	//private DBConection dbConection=new DBConection();
	private DBConection dbConection = DBConection.getInstance();
	/**
	 *
	 */
	public List<Airport> getAirports()
	{
		//DBConection dbConection = new DBConection();
		List<Airport> listAirport=dbConection.getAll(TABLE,"airport_id", Airport.class);

		return listAirport;
	}

	/**
	 *
	 */
	public Airport getAirport(long airport_id)
	{
		//DBConection dbConection = new DBConection();
		Airport airport=dbConection.getById(TABLE, String.valueOf(airport_id), Airport.class);

		return airport;
	}
	/**
	 *
	 * @param airport
	 */
	public void addAirport(Airport airport)
	{
		//DBConection dbConection = new DBConection();
		long id =  dbConection.getnextId(TABLE);
		airport.airport_id = id;
		dbConection.addObject(TABLE, airport, String.valueOf(airport.airport_id ));
		dbConection.setnextId(TABLE, id + 1);
	}


}
