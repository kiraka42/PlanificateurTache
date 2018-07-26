package com.diderot.projetGLA.daoAdapter.elasticsearch;


import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.diderot.projetGLA.Object.Plane;
import com.diderot.projetGLA.daoAdapter.PlaneDaoAdapter;

public class PlaneDao implements PlaneDaoAdapter{
	public static final String TABLE="plane"; 
	//private DBConection dbConection=new DBConection();
	private DBConection dbConection = DBConection.getInstance();
	/**
	 * 
	 */
	public List<Plane> getPlanes()
	{
		List<Plane> listPlane = dbConection.getAll(TABLE,"plane_id", Plane.class);
		return listPlane;
	}
	/**
	 * 
	 */
	public List<Plane> getPlanes(String plane_type)
	{
		QueryBuilder query=QueryBuilders.matchQuery("plane_type", plane_type);
		List<Plane> listPlane=dbConection.searchByQuery(TABLE, query,"plane_id", Plane.class);
		
		return listPlane;
	}
	/**
	 * 
	 */
	public Plane getPlane(String plane_id)
	{
		Plane plane=dbConection.getById(TABLE, plane_id, Plane.class);
		
		return plane;
	}	
	/**
	 * 
	 */
	public void addPlane(Plane plane)
	{
		long id =  dbConection.getnextId(TABLE);
		plane.plane_id = id;
		dbConection.addObject(TABLE, plane, String.valueOf(plane.plane_id));
		dbConection.setnextId(TABLE, id + 1);
	}
	
}
