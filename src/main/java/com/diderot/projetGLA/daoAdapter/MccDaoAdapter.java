package com.diderot.projetGLA.daoAdapter;

import java.util.List;

import com.diderot.projetGLA.Object.Mcc;



public interface MccDaoAdapter {
	
	/**
	 * Finds Mcc by email 
	 * 
	 * @param String
	 * @return Mcc 
	 */
	public Mcc getMcc(String email);
	
	/**
	 * Finds Mcc by Id 
	 * 
	 * @param String
	 * @return Mcc 
	 */
	public Mcc getMcc(long id);
	
	/**
	 * Returns list of all Mcc instances
	 *
	 * @return list of Mcc
	 */
	public List<Mcc> getAllMcc();
	/**
	 * 
	 * @param mcc
	 */
	void addMcc(Mcc mcc);
	
}
