package com.diderot.projetGLA.daoAdapter.elasticsearch;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.diderot.projetGLA.Object.Mro;
import com.diderot.projetGLA.daoAdapter.MroDaoAdapter;

public class MroDao implements MroDaoAdapter {
	public static final String TABLE="mro";
	//private DBConection dbConection=new DBConection();
	private DBConection dbConection = DBConection.getInstance();
	/**
	 * 
	 * @return
	 */
	public List<Mro> getAllMro(){
		
		List<Mro> listMro=dbConection.getAll(TABLE,"mro_id", Mro.class);
		
		return listMro;
	}
	
	/**
	 * 
	 * @param mro_id
	 * @return
	 */
	public Mro getMro(long mro_id){
		Mro mro=dbConection.getById(TABLE, String.valueOf(mro_id), Mro.class);
		return mro;
	}
	
	public Mro getMro(String userName){
		QueryBuilder query = QueryBuilders.matchQuery("user_name", userName);		
		List<Mro> mros = dbConection.searchByQuery(TABLE, query, "user_name", Mro.class);
		if(mros.size() > 0)
			return mros.get(0);
		
		return null;
	}
	
	
	/**
	 * 
	 */
	public Mro getMroByairport(long airport_id){
		QueryBuilder query = QueryBuilders.matchQuery("airport_id", airport_id);		
		List<Mro> mros = dbConection.searchByQuery(TABLE, query, "airport_id", Mro.class);
		if(mros.size() > 0)
			return mros.get(0);
		
		return null;
		
	}

	@Override
	public void addMro(Mro mro) {
		// TODO Auto-generated method stub
		long id = dbConection.getnextId(TABLE);
		mro.MRO_id = id;
		dbConection.addObject(TABLE, mro, String.valueOf(id));
		dbConection.setnextId(TABLE, id + 1);
	}
}
