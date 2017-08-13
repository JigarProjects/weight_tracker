package com.service;


import com.model.Notify;
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
public class NotifyService extends BasicDAO<Notify, ObjectId> {
    private final String timeStamp = "timeStamp";
    private static NotifyService notifyService;

    private NotifyService(Class<Notify> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    public static NotifyService getInstance() {
        if (notifyService == null) {
            notifyService = new NotifyService(Notify.class, MorphiaService.getInstance().getNotifyStore());
        }
        return notifyService;
    }

    public Notify pushNotification(Notify notify) {
        System.out.println("Pushing " + notify);
        Key<Notify> key = save(notify);
        return get((ObjectId) key.getId());
    }

    public List<Notify> getAllNotifications() {
        QueryResults<Notify> resultWeights = find();
        List<Notify> weights = resultWeights.asList();
        return weights;
    }

    public List<Notify> getNotifications(String fromStr, String toStr) {
        List<Notify> response = null;
        if (fromStr == null && toStr == null) {
            response = getAllNotifications();
        } else {
            response = getNotificationInRange(fromStr, toStr);
        }
        return response;
    }


    public List<Notify> getNotificationInRange(String fromStr, String toStr) {
        List<Notify> response;
        Query<Notify> query;
        if (fromStr != null && toStr != null) {
            Date from = new Date(Long.parseLong(fromStr));
            Date to = new Date(Long.parseLong(toStr));
            query = createQuery().disableValidation();
            query.and(
                    query.criteria(timeStamp).greaterThanOrEq(from),
                    query.criteria(timeStamp).lessThanOrEq(to)
            );

        } else {
            if (fromStr != null) {
                Date from = new Date(Long.parseLong(fromStr));
                query = createQuery().disableValidation()
                        .field(timeStamp).greaterThanOrEq(from);
            } else {
                Date to = new Date(Long.parseLong(toStr));
                query = createQuery().disableValidation()
                        .field(timeStamp).lessThanOrEq(to);
            }
        }

        response = find(query).asList();

        return response;
    }
}
