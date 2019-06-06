package org.fasttrackit.dailyfoodconsumptionapi;

import org.fasttrackit.dailyfoodconsumptionapi.domain.Food;
import org.fasttrackit.dailyfoodconsumptionapi.exception.ResourceNotFoundException;
import org.fasttrackit.dailyfoodconsumptionapi.service.FoodService;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.food.CreateFoodRequest;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.food.GetFoodsRequest;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.food.UpdateFoodRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodServiceIntegrationTests {

    @Autowired
    private FoodService foodService;

    @Test
    public void testCreateFood_whenValidRequest_thenReturnFoodWithId() {
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

    @Test
    public void testUpdateFood_whenValidRequestWithAllFields_thenReturnUpdatedFood() throws ResourceNotFoundException {
        Food createdFood = createFood();

        UpdateFoodRequest request = new UpdateFoodRequest();
        request.setName(createdFood.getName() + "_Edited");
        request.setNutritionDeclaration((createdFood.getNutritionDeclaration() + 20));
        request.setQuantity(createdFood.getQuantity() + 5);


        Food updatedFood =
                foodService.updateFood(createdFood.getId(), request);

        assertThat(updatedFood.getName(), is(request.getName()));
        assertThat(updatedFood.getName(), not(is(createdFood.getName())));

        assertThat(updatedFood.getNutritionDeclaration(), is(request.getNutritionDeclaration()));
        assertThat(updatedFood.getQuantity(), is(request.getQuantity()));

        assertThat(updatedFood.getId(), is(createdFood.getId()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteFood_whenExistingId_thenFoodIsDeleted() throws ResourceNotFoundException {
        Food createdFood = createFood();

        foodService.deleteFood(createdFood.getId());

        foodService.getFood(createdFood.getId());

    }

    @Test
    public void testGetFoods_whenAllInformationProvidedAndMatching_thenReturnFilteredResults() {
        Food createdFood = createFood();

        GetFoodsRequest request = new GetFoodsRequest();
        request.setPartialName("Mil");
        request.setMinimumNutritionDeclaration(20);
        request.setMaximumNutritionDeclaration(210);
        request.setMinimumQuantity(1);

        Page<Food> foods =
                foodService.getFoods(request, PageRequest.of(0, 10));

        assertThat(foods.getTotalElements(), greaterThanOrEqualTo(1L));
    }
}
