package com.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

/**
 * Created by Jigar on 8/12/2017.
 */
@Entity("weight")
public class Notify {
    @Id
    private ObjectId notificationId;
    //Stores information related to weight
    private ObjectId weightID;
    private Date timeStamp;
    private String notificationType;
    private String sensorIP;
    private String description;

    public String getSensorIP() {
        return sensorIP;
    }

    public void setSensorIP(String sensorIP) {
        this.sensorIP = sensorIP;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ObjectId getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(ObjectId notificationId) {
        this.notificationId = notificationId;
    }

    public ObjectId getWeightID() {
        return weightID;
    }

    public void setWeightID(ObjectId weightID) {
        this.weightID = weightID;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public Notify() {

    }

    public Notify(String sensorIP, ObjectId weightID, Date timeStamp, String notificationType, String description) {
        this.weightID = weightID;
        this.timeStamp = timeStamp;
        this.notificationType = notificationType;
        this.sensorIP = sensorIP;
        this.description = description;
    }

    @Override
    public String toString() {
        return notificationType + " " + weightID;
    }
}
