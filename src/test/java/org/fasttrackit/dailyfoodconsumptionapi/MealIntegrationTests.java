package org.fasttrackit.dailyfoodconsumptionapi;

import org.fasttrackit.dailyfoodconsumptionapi.domain.Food;
import org.fasttrackit.dailyfoodconsumptionapi.domain.Meal;
import org.fasttrackit.dailyfoodconsumptionapi.domain.User;
import org.fasttrackit.dailyfoodconsumptionapi.service.MealService;
import org.fasttrackit.dailyfoodconsumptionapi.steps.FoodSteps;
import org.fasttrackit.dailyfoodconsumptionapi.steps.UserSteps;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.food.FoodIdentifier;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.meal.SaveMealRequest;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.user.UserIdentifier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MealIntegrationTests {

	@Autowired
	private MealService mealService;

	@Autowired
	private FoodSteps foodSteps;

	@Autowired
	private UserSteps userSteps;

	@Test
	public void testAddFoodsToMeal_whenValidRequest_thenReturnMeal() {
		Food food = foodSteps.createFood();
		User user = userSteps.createUser();

		FoodIdentifier foodIdentifier = new FoodIdentifier();
		foodIdentifier.setId(food.getId());

		UserIdentifier userIdentifier = new UserIdentifier();
		user.setId(user.getId());

		SaveMealRequest request = new SaveMealRequest();
		request.setUser(userIdentifier);
		request.setFoods(Collections.singleton(foodIdentifier));

		Meal meal = mealService.addFoodsToMeal(request);
	}

}
