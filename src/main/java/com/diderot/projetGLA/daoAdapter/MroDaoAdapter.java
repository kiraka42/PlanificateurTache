package com.diderot.projetGLA.daoAdapter;



import java.util.List;

import com.diderot.projetGLA.Object.Mro;

public interface MroDaoAdapter {

	/**
	 * 
	 * @return
	 */
	public List<Mro> getAllMro();
	
	/**
	 * 
	 * @param mro_id
	 * @return
	 */
	public Mro getMro(long mro_id);
	
	/**
	 * 
	 * @param airport_id
	 * @return
	 */
	public Mro getMroByairport(long airport_id);
	/**
	 * 
	 * @param mro
	 */
	public void addMro(Mro mro);
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public Mro getMro(String userName);
}
