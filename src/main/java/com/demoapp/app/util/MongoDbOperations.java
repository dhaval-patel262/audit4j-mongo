package com.demoapp.app.util;

import org.audit4j.core.exception.HandlerException;
import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author dhaval on 20/1/2022
 */
public class MongoDbOperations {
	/**
	 * host
	 */
	private String mongoURI;
	
	/**
	 * collection name
	 */
	private String collection = "audit_logs";

	/**
	 * dbName
	 */
	private String dbName;

	/**
	 * dbCollection
	 */
	private MongoCollection<Document> dbCollection;

	private static MongoDbOperations instance = null;
	private MongoDatabase db = null;
	private MongoClient mongoClient;

	private MongoDbOperations() {

	}

	/**
	 * gets collection
	 * 
	 * @return
	 */
	public String getCollection() {
		return collection;
	}

	/**
	 * gets dbName
	 * 
	 * @return
	 */
	public String getDbName() {
		return dbName;
	}

	/***
	 * sets collection
	 * 
	 * @param collection
	 */
	public void setCollection(String collection) {
		this.collection = collection;
	}

	/***
	 * sets dbName
	 * 
	 * @param dbName
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	

	public MongoCollection<Document> getDbCollection() {
		return dbCollection;
	}

	public void setDbCollection(MongoCollection<Document> dbCollection) {
		this.dbCollection = dbCollection;
	}

	public MongoDatabase getDb() {
		return db;
	}

	public void setDb(MongoDatabase db) {
		this.db = db;
	}

	/**
	 * gets singleton instance of MongoDbOperations
	 * 
	 * @return
	 */
	public static MongoDbOperations getInstance() {
		synchronized (MongoDbOperations.class) {
			if (instance == null) {
				instance = new MongoDbOperations();
			}
		}
		return instance;
	}

	/**
	 * init
	 * 
	 * @throws HandlerException
	 */
	public void init() throws HandlerException {
		MongoClient mongoClient = MongoClients.create(getMongoURI());
		db = mongoClient.getDatabase(getDbName());
		dbCollection = db.getCollection(getCollection());
	}

	/**
	 * stops mongo connection
	 */
	public void stop() {
		this.mongoClient = null;
		this.dbCollection = null;

	}

	public String getMongoURI() {
		return mongoURI;
	}

	public void setMongoURI(String mongoURI) {
		this.mongoURI = mongoURI;
	}
}
