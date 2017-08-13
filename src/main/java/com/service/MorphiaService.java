package com.service;

import com.Rules.OverWeightRule;
import com.Rules.UnderWeightRule;
import com.model.Weight;
import com.mongodb.MongoClient;
import org.easyrules.api.RulesEngine;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

/**
 * Created by Jigar on 8/12/2017.
 */
public class MorphiaService {

    private Morphia morphia;
    private Datastore metricStore;
    private Datastore notifyStore;
    private Datastore sensorStore;
    private static MorphiaService morphiaService;
    private static UnderWeightRule underWeightRule;
    private static OverWeightRule overWeightRule;
    private static RulesEngine weightRuleEngine;

    private MorphiaService() {
        MongoClient mongoClient = new MongoClient("127.0.0.1:27017");
        //create a new morphia instance
        this.morphia = new Morphia();

        this.metricStore = morphia.createDatastore(mongoClient, "metrics");
        this.notifyStore = morphia.createDatastore(mongoClient, "notify");
        this.sensorStore = morphia.createDatastore(mongoClient, "sensor");
        //as a value would be either higher than baseWeight or lower than baseWeight
        weightRuleEngine = aNewRulesEngine()
                .withSkipOnFirstAppliedRule(true)
                .build();

        underWeightRule = new UnderWeightRule();
        weightRuleEngine.registerRule(underWeightRule);
        overWeightRule = new OverWeightRule();
        weightRuleEngine.registerRule(overWeightRule);
    }


    public static MorphiaService getInstance() {
        if (morphiaService == null) {
            morphiaService = new MorphiaService();
        }
        return morphiaService;
    }

    public static void executerWeightRule(String sensorIP, int baseWeight, Weight currentWeight) {
        System.out.println(baseWeight + " compared with " + currentWeight.getValue());
        underWeightRule.setInput(sensorIP, baseWeight, currentWeight);
        overWeightRule.setInput(sensorIP, baseWeight, currentWeight);
        weightRuleEngine.fireRules();
    }

    public Morphia getMorphia() {
        return morphia;
    }

    public Datastore getMetricStore() {
        return metricStore;
    }

    public Datastore getNotifyStore() {
        return notifyStore;
    }

    public Datastore getSensorStore() {
        return sensorStore;
    }
}
