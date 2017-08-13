package com.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

/**
 * Created by Jigar on 8/13/2017.
 */

@Entity("sensor")
public class Sensor {
    @Id
    private ObjectId sensorID;

    @Indexed(unique = true)
    private String sensorIP;

    private int baseWeight;

    public String getSensorIP() {
        return sensorIP;
    }

    public void setSensorIP(String sensorIP) {
        this.sensorIP = sensorIP;
    }

    public int getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(int baseWeight) {
        this.baseWeight = baseWeight;
    }

    public ObjectId getSensorID() {
        return sensorID;
    }

    public void setSensorID(ObjectId sensorID) {
        this.sensorID = sensorID;
    }

    public Sensor() {

    }

    public Sensor(String sensorIP, int baseWeight) {
        this.sensorIP = sensorIP;
        this.baseWeight = baseWeight;
    }

    @Override
    public String toString() {
        return sensorIP + " " + baseWeight;
    }
}
