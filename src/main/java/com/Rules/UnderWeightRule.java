package com.Rules;

import com.model.Notify;
import com.model.Weight;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

/**
 * Created by Jigar on 8/12/2017.
 */
@Rule(name = "Under Weight Rule",
        description = "notify when weight is under 10%")
public class UnderWeightRule {
    private int baseWeight;
    private Weight currentWeight;
    private String sensorIP;

    @Condition
    public boolean checkInput() {
        boolean isUnderWeight = false;
        if ((baseWeight * 0.9) > currentWeight.getValue()) {
            isUnderWeight = true;
        }

        return isUnderWeight;
    }

    @Action
    public void sayHelloToDukeFriend() throws Exception {
        String description = "Base value was: " + baseWeight + " Current value is: " + currentWeight.getValue();
        Notify notify = new Notify(sensorIP, currentWeight.getId(), currentWeight.getTimeStamp(), "Under Weight Notification", description);
    }

    public void setInput(String sensorIP, int baseWeight, Weight currentWeight) {
        this.baseWeight = baseWeight;
        this.currentWeight = currentWeight;
    }
}
