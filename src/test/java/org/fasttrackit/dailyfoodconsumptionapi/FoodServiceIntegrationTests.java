package org.fasttrackit.dailyfoodconsumptionapi;

import org.fasttrackit.dailyfoodconsumptionapi.domain.Food;
import org.fasttrackit.dailyfoodconsumptionapi.service.FoodService;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.CreateFoodRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodServiceIntegrationTests {

    @Autowired
    private FoodService foodService;

    @Test
    public void testCreateFood_whenValidRequest_thenReturnFoodWithId(){
        CreateFoodRequest request = new CreateFoodRequest();
        request.setName("Milk");
        request.setNutritionDeclaration(200);
        request.setQuantity(2);

        Food food = foodService.createFood(request);

        assertThat(food, notNullValue());
        assertThat(food.getId(), greaterThan(0L));
    }
}
