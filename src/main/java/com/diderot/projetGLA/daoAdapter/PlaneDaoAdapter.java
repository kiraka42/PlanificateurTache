package com.diderot.projetGLA.daoAdapter;

import java.util.List;

import com.diderot.projetGLA.Object.Plane;

public interface PlaneDaoAdapter {
	/**
	 * 
	 * @return
	 */
	List<Plane> getPlanes();
	
	/**
	 * 
	 * @param plane_type
	 * @return
	 */
	List<Plane> getPlanes(String plane_type);
	/**
	 * 
	 * @param plane_id
	 * @return
	 */
	public Plane getPlane(String plane_id);
	/**
	 * 
	 * @param plane
	 */
	void addPlane(Plane plane);
}
