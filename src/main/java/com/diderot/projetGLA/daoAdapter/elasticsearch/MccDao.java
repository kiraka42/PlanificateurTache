package com.diderot.projetGLA.daoAdapter.elasticsearch;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.diderot.projetGLA.Object.Mcc;
import com.diderot.projetGLA.Object.Mro;
import com.diderot.projetGLA.daoAdapter.MccDaoAdapter;

public class MccDao implements MccDaoAdapter{
	public static final String TABLE="mcc";
	//private DBConection dbConection=new DBConection();
	private DBConection dbConection = DBConection.getInstance();
	/**
	 * 
	 */
	
	public Mcc getMcc(long mccId)
	{
		Mcc mcc=dbConection.getById(TABLE, String.valueOf(mccId), Mcc.class);
		
		return mcc;
	}
	
	public Mcc getMcc(String email)
	{
		QueryBuilder query = QueryBuilders.matchQuery("e_mail", email);		
		List<Mcc> mccs = dbConection.searchByQuery(TABLE, query, "MCC_id", Mcc.class);
		if(mccs.size() > 0)
			return mccs.get(0);
		return null;
	}
	/**
	 * 
	 */
	public List<Mcc> getAllMcc(){
		List<Mcc> listMcc=dbConection.getAll(TABLE,"mccId", Mcc.class);
		
		return listMcc;
	}
	/**
	 * 
	 */
	public void addMcc(Mcc mcc){
		long id = dbConection.getnextId(TABLE);
		mcc.MCC_id = id;
		dbConection.addObject(TABLE, mcc, String.valueOf(mcc.MCC_id ));
		dbConection.setnextId(TABLE, id + 1);
	}
	
}
