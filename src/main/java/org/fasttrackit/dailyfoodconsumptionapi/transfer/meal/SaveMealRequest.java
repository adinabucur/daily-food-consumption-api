package org.fasttrackit.dailyfoodconsumptionapi.transfer.meal;

import java.util.Set;

public class SaveMealRequest {

    private long userId;
    private Set<Long> foodIds;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Set<Long> getFoodIds() {
        return foodIds;
    }

    public void setFoodIds(Set<Long> foodIds) {
        this.foodIds = foodIds;
    }

    @Override
    public String toString() {
        return "SaveMealRequest{" +
                "userId=" + userId +
                ", foodIds=" + foodIds +
                '}';
    }
}
