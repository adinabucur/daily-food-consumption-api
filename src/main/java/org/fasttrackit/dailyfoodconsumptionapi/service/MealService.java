package org.fasttrackit.dailyfoodconsumptionapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.dailyfoodconsumptionapi.domain.Food;
import org.fasttrackit.dailyfoodconsumptionapi.domain.Meal;
import org.fasttrackit.dailyfoodconsumptionapi.domain.User;
import org.fasttrackit.dailyfoodconsumptionapi.exception.ResourceNotFoundException;
import org.fasttrackit.dailyfoodconsumptionapi.persistence.MealRepository;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.meal.SaveMealRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MealService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(MealService.class);

    private final MealRepository mealRepository;
    private final UserService userService;
    private final FoodService foodService;
    private final ObjectMapper objectMapper;

    @Autowired
    public MealService(MealRepository mealRepository, UserService userService, FoodService foodService, ObjectMapper objectMapper) {

        this.mealRepository = mealRepository;
        this.userService = userService;
        this.foodService = foodService;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public Meal addFoodsToMeal(SaveMealRequest request) throws ResourceNotFoundException {
        LOGGER.info("Adding foods to meal: {}", request);

        User user =
                userService.getUser(request.getUserId());



        Meal meal = new Meal();
        meal.setUser(user);

        for (Long id : request.getFoodIds()){
            Food food = foodService.getFood(id);
            meal.addFood(food);
        }

        return mealRepository.save(meal);
    }
}

