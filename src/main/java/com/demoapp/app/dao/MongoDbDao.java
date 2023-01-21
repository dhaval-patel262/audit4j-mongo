package com.demoapp.app.dao;

import com.demoapp.app.model.MongoEvent;

/**
 * @author dhaval on 20/1/2022
 */
public interface MongoDbDao {
	/***
	 * writes event to db
	 * 
	 * @param event
	 * @return
	 */
	boolean writeEvent(final MongoEvent event);
}
