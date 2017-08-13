package com.Rules;

import com.model.Notify;
import com.model.Weight;
import com.service.NotifyService;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

/**
 * Created by Jigar on 8/12/2017.
 */
@Rule(name = "Over Weight Rule",
        description = "notify when weight is over 10%")
public class OverWeightRule {
    private int baseWeight;
    private Weight currentWeight;
    private String sensorIP;

    @Condition
    public boolean checkInput() {
        boolean isOverWeight = false;
        if ((baseWeight * 1.1) < currentWeight.getValue())
            isOverWeight = true;

        return isOverWeight;
    }

    @Action
    public void notifyPerson() throws Exception {
        //push notification for weightID at TIME
        String description = "Base value was: " + baseWeight + " Current value is: " + currentWeight.getValue();

        Notify notify = new Notify(sensorIP, currentWeight.getId(), currentWeight.getTimeStamp(), "Over Weight Notification", description);
        NotifyService.getInstance().pushNotification(notify);
    }

    public void setInput(String sensorIP, int baseWeight, Weight currentWeight) {
        this.baseWeight = baseWeight;
        this.currentWeight = currentWeight;
        this.sensorIP = sensorIP;
    }
}
