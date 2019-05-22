package org.fasttrackit.dailyfoodconsumptionapi;

import org.fasttrackit.dailyfoodconsumptionapi.domain.Food;
import org.fasttrackit.dailyfoodconsumptionapi.exception.ResourceNotFoundException;
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
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodServiceIntegrationTests {

    @Autowired
    private FoodService foodService;

    @Test
    public void testCreateFood_whenValidRequest_thenReturnFoodWithId(){
        Food food = createFood();

        assertThat(food, notNullValue());
        assertThat(food.getId(), greaterThan(0L));
    }

    private Food createFood() {
        CreateFoodRequest request = new CreateFoodRequest();
        request.setName("Milk");
        request.setNutritionDeclaration(200);
        request.setQuantity(2);

        return foodService.createFood(request);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetFood_whenFoodNotFound_thenThrowResourceNotFoundException() throws ResourceNotFoundException {
    foodService.getFood(0);
    }

    @Test
    public void testGetFood_whenExistingId_thenReturnMatchingFood() throws ResourceNotFoundException {
    Food food = createFood();

        Food retrievedFood = foodService.getFood(food.getId());

        assertThat(retrievedFood.getId(), is(food.getId()));
        assertThat(retrievedFood.getName(), is(food.getName()));


    }
}
