package com.diderot.projetGLA.daoAdapter.elasticsearch;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.index.IndexRequest.OpType;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.sun.javafx.collections.MappingChange.Map;



public class DBConection{
	public final String URL="localhost";
	public final int PORT1=9300;
	public final int PORT2=9200;
	public final int SCROLL_SIZE=100;
	public static final String DB_NAME="task_planning";
	public static final TimeValue MAX_TIME = new TimeValue(60000);
	private static Client client=null;
	
	private static DBConection instance;
	
	public static DBConection getInstance() {
		if (instance == null)
			instance = new DBConection();
		return instance;
	}
	
	public DBConection() 
	{
		// TODO Auto-generated constructor stub
		try {
			client = new PreBuiltTransportClient(Settings.EMPTY)
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(URL), PORT1))
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(URL), PORT1));
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			LogManager.getLogger("logger").log(Level.ERROR,"erreur de connexion", e.getMessage());
		}
	}
	
	public <T> List<T> searchByQuery(String tableName, QueryBuilder query,String sort, Class<T> c) 
	{
		List<T> list=new ArrayList<T>();
		try{
			SearchResponse searchResponse =client.prepareSearch(DB_NAME)
					.addSort(sort, SortOrder.ASC)
					.setTypes(tableName)
					.setQuery(query)
					.setScroll(MAX_TIME)
					.setSize(SCROLL_SIZE)
					.get();
			
			//System.out.println(searchResponse.toString());	
			do{
				for(SearchHit hit:searchResponse.getHits().getHits()){
					T object=getObjectFromJson(hit.getSourceAsString(), c);
					if(object!=null)
						list.add(object);
				}
				searchResponse = client.prepareSearchScroll(searchResponse.getScrollId())
						.setScroll(DBConection.MAX_TIME).execute()
						.actionGet();
			
			}while(searchResponse.getHits().getHits().length!=0);
			return list;
		}catch ( ElasticsearchException e) {
			// TODO: handle exception
			LogManager.getLogger(this.getClass().getName()).log(Level.ERROR,""+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public <T> List<T> searchByQuery(String tableName, QueryBuilder query,String sort, Class<T> c, int nombre) 
	{
		List<T> list=new ArrayList<T>();
		//System.out.println(query.toString());
		try{
			SearchResponse searchResponse =client.prepareSearch(DB_NAME)
					.addSort(sort, SortOrder.DESC)
					.setTypes(tableName)
					.setQuery(query)
					.setScroll(MAX_TIME)
					.setSize(nombre)
					.get();
			for(SearchHit hit:searchResponse.getHits().getHits()){
				T object=getObjectFromJson(hit.getSourceAsString(), c);
				if(object!=null)
					list.add(object);
			}
			return list;
		}catch ( ElasticsearchException e) {
			// TODO: handle exception
			LogManager.getLogger(this.getClass().getName()).log(Level.ERROR,""+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	public boolean addObject(String tableName, Object object, String id)
	{
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.configure(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		try{
			String json= objectMapper.writeValueAsString(object);
			IndexResponse response=client.prepareIndex()
									.setIndex(DB_NAME).setType(tableName)
									.setId(id).setOpType(OpType.CREATE)
									.setSource(json).get();
			LogManager.getLogger(this.getClass().getName()).log(Level.INFO,object.getClass().getName()+" ajouté");
			return true;
		}catch(IOException  | ElasticsearchException e){
			LogManager.getLogger(this.getClass().getName()).log(Level.WARN,""+e.getMessage());
			return false;
		}
	}
	public boolean updateObject(String tableName, Object object, long id)
	{
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.configure(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		try{
			String json= objectMapper.writeValueAsString(object);
			IndexResponse response=client.prepareIndex()
									.setIndex(DB_NAME).setType(tableName)
									.setId(String.valueOf(id))
									.setSource(json).get();
			LogManager.getLogger(this.getClass().getName()).log(Level.INFO,object.getClass().getName()+" ajouté");
			return true;
		}catch(IOException  | ElasticsearchException e){
			LogManager.getLogger(this.getClass().getName()).log(Level.WARN,""+e.getMessage());
			return false;
		}
	}
	
	public <T> List<T> getAll(String tableName,String sort, Class<T> c)
	{
		List<T> list=new ArrayList<T>();
		try{
			SearchResponse searchResponse =client
					.prepareSearch(DB_NAME)
					.addSort(sort, SortOrder.DESC)
					.setTypes(tableName)
					.setScroll(MAX_TIME)
					.setSize(SCROLL_SIZE)
					.get();
			do{
				for(SearchHit hit:searchResponse.getHits().getHits()){
					T object=getObjectFromJson(hit.getSourceAsString(), c);
					if(object!=null)
						list.add(object);
				}
				searchResponse = client.prepareSearchScroll(searchResponse.getScrollId())
						.setScroll(DBConection.MAX_TIME).execute()
						.actionGet();
			}while(searchResponse.getHits().getHits().length!=0);
			return list;
		}catch ( ElasticsearchException e) {
			// TODO: handle exception
			LogManager.getLogger(DBConection.class.getName()).log(Level.WARN,""+e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	public <T> T getById(String tableName, String id, Class<T> c)
	{
		T object = null;
		try{
			GetResponse getResponse =client
		
				.prepareGet()
				.setIndex(DB_NAME)
				.setType(tableName)
				.setId(id)
				.get();
			object=(T)getObjectFromJson(getResponse.getSourceAsString(), c);
			return object;
		}catch ( ElasticsearchException e) {
			// TODO: handle exception
			LogManager.getLogger(this.getClass().getName()).log(Level.ERROR,""+e.getMessage());
		}
		return null;
	}
	
	private <T> T getObjectFromJson(String json, Class<T> c)
	{
		if(json==null)
			return null;
		T object=null;
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.configure(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		try {
			object = (T) objectMapper.readValue(json, c);
			return object;
		} catch (Exception e) {
			LogManager.getLogger(this.getClass().getName()).log(Level.WARN,"erreur parse "+e.getMessage());
		}
		return null;
	}
	public long getnextId(String tableName){
		GetResponse getResponse =client	
				.prepareGet()
				.setIndex(DB_NAME)
				.setType(tableName+"_id")
				.setId("id")
				.get();
		if(getResponse.getSource() != null)
			return Long.parseLong(getResponse.getSource().get("id").toString());
		setnextId(tableName, 1);
		return 1;
	}
	public void setnextId(String tableName, long id){
		String json="{"
				+"\"id\": "+ id
				+ "}";
		IndexResponse response=client.prepareIndex()
				.setIndex(DB_NAME).setType(tableName+"_id")
				.setId("id")
				.setSource(json).get();
	}
	public void initDataBase(){
		try{
			CreateIndexRequestBuilder createIndexRequestBuilder = client.admin().indices()
										.prepareCreate(DB_NAME);
		    CreateIndexResponse createIndexResponse= createIndexRequestBuilder.execute().actionGet();
		   
		} catch ( ElasticsearchException e) {
			// TODO Auto-generated catch block
			if(!ElasticsearchException.getExceptionName(e).equals("resource_already_exists_exception"))
				LogManager.getLogger("logger").log(Level.ERROR,"erreur de creation d'index "+e.getMessage());
		}
	}
}
