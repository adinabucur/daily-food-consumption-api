package org.fasttrackit.dailyfoodconsumptionapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.dailyfoodconsumptionapi.domain.Meal;
import org.fasttrackit.dailyfoodconsumptionapi.persistence.MealRepository;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.meal.SaveMealRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(MealService.class);

    private final MealRepository mealRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public MealService(MealRepository mealRepository, ObjectMapper objectMapper) {
        this.mealRepository = mealRepository;
        this.objectMapper = objectMapper;
    }

    public Meal addFoodsToMeal(SaveMealRequest request) {
        LOGGER.info("Adding foods to meal: {}", request);
        Meal meal = objectMapper.convertValue(request, Meal.class);

        return mealRepository.save(meal);
    }
}

