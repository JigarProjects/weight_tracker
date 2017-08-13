package com.service;

import com.model.Weight;
import org.bson.types.ObjectId;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;

import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;

import java.util.Date;
import java.util.List;

/**
 * Created by Jigar on 8/12/2017.
 */
public class WeightService extends BasicDAO<Weight, ObjectId> {

    String timeStamp = "timeStamp";
    public WeightService(Class<Weight> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    public Weight create(Weight weight){
        Key<Weight> key = save(weight);
        /*System.out.println(key);*/

        return get((ObjectId) key.getId());
    }


    public List<Weight> getAllWeight(){
        QueryResults<Weight> resultWeights = find();
        List<Weight> weights = resultWeights.asList();
        return weights;
    }

    public List<Weight> getWeight(String fromStr, String toStr){
        List<Weight> response = null;
        if(fromStr.equals("null") && toStr.equals(null)){
            response = getAllWeight();
        }else{
            response = getWeightInRange(fromStr, toStr);
        }
        return response;
    }

    /*
        Create query as per supplied arguments.
     */
    public List<Weight> getWeightInRange(String fromStr, String toStr){
        List<Weight> response;
        Query<Weight> query;
        if(fromStr != null && toStr != null) {
            Date from = new Date(Long.parseLong(fromStr));
            Date to = new Date(Long.parseLong(toStr));
            query = createQuery().disableValidation();
            query.and(
                    query.criteria(timeStamp).greaterThanOrEq(from),
                    query.criteria(timeStamp).lessThanOrEq(to)
            );

        }else{
            if(fromStr != null){
                Date from = new Date(Long.parseLong(fromStr));
                query = createQuery().disableValidation()
                        .field(timeStamp).greaterThanOrEq(from);
            }else{
                Date to = new Date(Long.parseLong(toStr));
                query = createQuery().disableValidation()
                        .field(timeStamp).lessThanOrEq(to);
            }
        }

        response = find(query).asList();

        return response;
    }
    //read, readByTimeRange
}
