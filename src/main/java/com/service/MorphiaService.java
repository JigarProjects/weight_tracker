package com.service;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import com.mongodb.MongoClient;
/**
 * Created by Jigar on 8/12/2017.
 */
public class MorphiaService {

    private Morphia morphia;
    private Datastore metricStore;

    public MorphiaService(){
        MongoClient mongoClient = new MongoClient("127.0.0.1:27017");
        //create a new morphia instance
        this.morphia = new Morphia();
        String databaseName = "metrics";
        this.metricStore = morphia.createDatastore(mongoClient, databaseName);
    }

    public Morphia getMorphia() {
        return morphia;
    }

    public void setMorphia(Morphia morphia) {
        this.morphia = morphia;
    }

    public Datastore getMetricStore() {
        return metricStore;
    }

    public void setMetricStore(Datastore datastore) {
        this.metricStore = datastore;
    }
}
