package org.fasttrackit.dailyfoodconsumptionapi.steps;

import org.fasttrackit.dailyfoodconsumptionapi.domain.Food;
import org.fasttrackit.dailyfoodconsumptionapi.service.FoodService;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.food.CreateFoodRequest;
import org.springframework.stereotype.Component;

@Component
public class FoodSteps {

    private FoodService foodService;

    public Food createFood() {
        CreateFoodRequest request = new CreateFoodRequest();
        request.setName("Milk");
        request.setNutritionDeclaration(200);
        request.setQuantity(2);

        return foodService.createFood(request);
    }
}
