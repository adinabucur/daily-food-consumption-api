package org.fasttrackit.dailyfoodconsumptionapi;

import org.fasttrackit.dailyfoodconsumptionapi.domain.Food;
import org.fasttrackit.dailyfoodconsumptionapi.domain.Meal;
import org.fasttrackit.dailyfoodconsumptionapi.domain.User;
import org.fasttrackit.dailyfoodconsumptionapi.exception.ResourceNotFoundException;
import org.fasttrackit.dailyfoodconsumptionapi.service.MealService;
import org.fasttrackit.dailyfoodconsumptionapi.steps.FoodSteps;
import org.fasttrackit.dailyfoodconsumptionapi.steps.UserSteps;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.meal.SaveMealRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;


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
	public void testAddFoodsToMeal_whenValidRequest_thenReturnMeal() throws ResourceNotFoundException {
		Food food = foodSteps.createFood();
		User user = userSteps.createUser();


		SaveMealRequest request = new SaveMealRequest();
		request.setUserId(user.getId());
		request.setFoodIds(Collections.singleton(food.getId()));

		Meal meal = mealService.addFoodsToMeal(request);

		assertThat(meal, notNullValue());
		assertThat(meal.getId(), is(user.getId()));

		assertThat(meal.getFoods(), notNullValue());
		assertThat(meal.getFoods(), hasSize(1));

	}

}
