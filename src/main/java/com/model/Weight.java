package com.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.bson.types.ObjectId;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

/**
 * Created by Jigar on 8/12/2017.
 */
@Entity("weight")
public class Weight {
    @Id
    private ObjectId id;

    Date timeStamp;
    int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timestamp) {
        this.timeStamp = timestamp;
    }

    @Override
    public String toString() {
        return "ts "+timeStamp+" "+value;
    }
}
