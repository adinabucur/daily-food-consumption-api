package org.fasttrackit.dailyfoodconsumptionapi.transfer.meal;

import org.fasttrackit.dailyfoodconsumptionapi.transfer.food.FoodIdentifier;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.user.UserIdentifier;

import java.util.Set;

public class SaveMealRequest {

    private UserIdentifier user;
    private Set<FoodIdentifier> foods;

    public UserIdentifier getUser() {
        return user;
    }

    public void setUser(UserIdentifier user) {
        this.user = user;
    }

    public Set<FoodIdentifier> getFoods() {
        return foods;
    }

    public void setFoods(Set<FoodIdentifier> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return "SaveMealRequest{" +
                "user=" + user +
                ", foods=" + foods +
                '}';
    }
}
