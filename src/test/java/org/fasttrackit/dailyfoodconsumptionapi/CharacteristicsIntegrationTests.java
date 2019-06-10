package org.fasttrackit.dailyfoodconsumptionapi;

import org.fasttrackit.dailyfoodconsumptionapi.domain.Characteristics;
import org.fasttrackit.dailyfoodconsumptionapi.domain.Food;
import org.fasttrackit.dailyfoodconsumptionapi.exception.ResourceNotFoundException;
import org.fasttrackit.dailyfoodconsumptionapi.service.CharacteristicsService;
import org.fasttrackit.dailyfoodconsumptionapi.steps.FoodSteps;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.characteristics.CreateCharacteristicsRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharacteristicsIntegrationTests {

	@Autowired
	private CharacteristicsService characteristicsService;

	@Autowired
	private FoodSteps foodSteps;

	@Test
	public void testCreateCharacteristics_whenValidRequest_thenReturnCharacteristics() throws ResourceNotFoundException {
		Food food = foodSteps.createFood();

		CreateCharacteristicsRequest characteristicsRequest = new CreateCharacteristicsRequest();
		characteristicsRequest.setFoodId(food.getId());
		characteristicsRequest.setContent("High level of proteins");

		Characteristics characteristics = characteristicsService.createCharacteristics(characteristicsRequest);

		assertThat(characteristics, notNullValue());
		assertThat(characteristics.getId(), greaterThan(0L));

		assertThat(characteristics.getFood(), notNullValue());
		assertThat(characteristics.getFood().getId(), is(food.getId()));
		assertThat(characteristics.getContent(), is(characteristicsRequest.getContent()));

	}

}
