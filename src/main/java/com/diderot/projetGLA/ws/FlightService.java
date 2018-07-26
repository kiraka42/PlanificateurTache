package com.diderot.projetGLA.ws;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import com.diderot.projetGLA.Object.Airport;
import com.diderot.projetGLA.Object.Flight;
import com.diderot.projetGLA.daoAdapter.AirportDaoAdapter;
import com.diderot.projetGLA.daoAdapter.DAO;
import com.diderot.projetGLA.daoAdapter.FlightDaoAdapter;

@PermitAll
@Path("/flight")
public class FlightService {
	FlightDaoAdapter flightDao=DAO.getFlightDao();
	AirportDaoAdapter airportDao=DAO.getAirportDao();
	@GET
	@RolesAllowed("mcc")
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id_plane}/{date}")
	public List<Flight> getFlights(	@PathParam("id_plane") long planeId,
							@PathParam("date") String date) {
		
		
		List<Flight> listFlight= new ArrayList<Flight>();
		
		listFlight=flightDao.getFlights(planeId,date);
		/*for(Flight flight:listFlight){
			airports.add(airportDao.getAirport(flight.departure_airport));
			airports.add(airportDao.getAirport(flight.arrival_airport));
		}*/
		//airports = airportDao.getAirports();
		return listFlight;
	}
	@GET
	@RolesAllowed("mcc")
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id_airport}")
	public Airport getAirport(@PathParam("id_airport") long airportId) {
		Airport airport;//= new Airport();
		airport=airportDao.getAirport(airportId);
		List<Airport> airports = new ArrayList<Airport>();
		/*for(Flight flight:listFlight){
			airports.add(airportDao.getAirport(flight.departure_airport));
			airports.add(airportDao.getAirport(flight.arrival_airport));
		}*/
		airports.add(airport);
		return airport;
	}

	@PUT
	@RolesAllowed("mcc")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	@Path("/add")
	public String add(Flight flight) {
		flightDao.addFlight(flight);
		return "Success";
	}

}