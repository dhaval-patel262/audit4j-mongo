package com.demoapp.app.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.audit4j.core.dto.Field;
import org.bson.Document;

import com.demoapp.app.model.MongoEvent;
import com.demoapp.app.util.MongoDbOperations;
import com.mongodb.client.MongoCollection;

/**
 * @author dhaval on 20/1/2022
 */
public class MongoDbDaoImpl implements MongoDbDao {

	public boolean writeEvent(MongoEvent event) {
		Long uuid;
		Date timestamp;
		Map<String, String> elements = new HashMap<String, String>();
		if (event.getUuid() == null) {
			uuid = UUID.randomUUID().getMostSignificantBits();
			event.setUuid(uuid);
		}

		if (event.getTimestamp() == null) {
			timestamp = new Date();
			event.setTimestamp(timestamp);
		}

		event.setTimestampInMillis(event.getTimestamp().getTime());

		for (Field element : event.getFields()) {
			elements.put(element.getName().replace(".", "_"), element.getValue());
		}
		event.setElements(elements);

		MongoCollection<Document> coll = MongoDbOperations.getInstance().getDbCollection();
		Document doc = new Document("action", event.getAction()).append("actor", event.getActor())
				.append("timestamp", event.getTimestamp()).append("timestampInMillis", event.getTimestampInMillis())
				.append("meta", event.getMeta()).append("uuid", event.getUuid()).append("tag", event.getTag())
				.append("origin", event.getOrigin()).append("elements", elements);

		coll.insertOne(doc);
		return true;
	}

}
