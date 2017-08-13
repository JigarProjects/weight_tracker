package com.service;

import com.model.Sensor;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 * Created by Jigar on 8/13/2017.
 */
public class SensorService extends BasicDAO<Sensor, ObjectId> {
    static final String sensorIPStr = "sensorIP";
    static SensorService sensorService;

    private SensorService(Class<Sensor> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    public static SensorService getInstance() {

        if (sensorService == null) {
            System.out.println("intialization");
            sensorService = new SensorService(Sensor.class, MorphiaService.getInstance().getSensorStore());
        }
        return sensorService;
    }

    public Sensor getSensorInformation(String sensorIP) {
        Query<Sensor> sensorQuery = createQuery().disableValidation();
        sensorQuery.field(sensorIPStr).equal(sensorIP);
        return findOne(sensorQuery);
    }

    //checks for addition
    // if sensor information exists then return it
    // else add new information
    public Sensor addSensorInformation(String sensorIP, int weight) {
        Sensor existingSensor = getSensorInformation(sensorIP);
        if (existingSensor == null) {
            System.out.println("adding new sensor");
            Sensor sensor = new Sensor(sensorIP, weight);
            save(sensor);
            return sensor;
        }
        return existingSensor;
    }
}