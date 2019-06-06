package org.fasttrackit.dailyfoodconsumptionapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.dailyfoodconsumptionapi.domain.Food;
import org.fasttrackit.dailyfoodconsumptionapi.exception.ResourceNotFoundException;
import org.fasttrackit.dailyfoodconsumptionapi.persistence.FoodRepository;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.food.CreateFoodRequest;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.food.GetFoodsRequest;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.food.UpdateFoodRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(FoodService.class);

    private final FoodRepository foodRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public FoodService(FoodRepository foodRepository, ObjectMapper objectMapper) {
        this.foodRepository = foodRepository;
        this.objectMapper = objectMapper;
    }

    public Food createFood(CreateFoodRequest request) {
        LOGGER.info("Creating food {}", request);
        Food food = objectMapper.convertValue(request, Food.class);
        return foodRepository.save(food);
    }

    public Food getFood(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving food {}", id);
        return foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Food " + id + "not found"));
    }

    public Page<Food> getFoods(GetFoodsRequest request, Pageable pageable) {
        LOGGER.info("Retrieving foods >>  {}", request);

        if (request.getPartialName() != null &&
                request.getMinimumQuantity() != null &&
                request.getMinimumNutritionDeclaration() != null &&
                request.getMaximumNutritionDeclaration() != null) {
            return foodRepository.findByNameContainingAndNutritionDeclarationBetweenAndQuantityGreaterThanEqual(
                    request.getPartialName(), request.getMinimumNutritionDeclaration(),
                    request.getMaximumNutritionDeclaration(), request.getMinimumQuantity(), pageable);

        } else if (request.getMinimumNutritionDeclaration() != null &&
                request.getMaximumNutritionDeclaration() != null &&
                request.getMinimumQuantity() != null) {
            return foodRepository.findByNutritionDeclarationBetweenAndQuantityGreaterThanEqual(
                    request.getMinimumNutritionDeclaration(), request.getMaximumNutritionDeclaration(),
                    request.getMinimumQuantity(), pageable);

        } else if (request.getPartialName() != null &&
                request.getMinimumQuantity() != null) {
            return foodRepository.findByNameContainingAndQuantityGreaterThanEqual(
                    request.getPartialName(), request.getMinimumQuantity(), pageable);
        }
        return foodRepository.findAll(pageable);

    }

    public Food updateFood(long id, UpdateFoodRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating food {}, {}", id, request);
        Food food = getFood(id);

        BeanUtils.copyProperties(request, food);

        return foodRepository.save(food);
    }

    public void deleteFood(long id) {
        LOGGER.info("Deleting food {}", id);
        foodRepository.deleteById(id);
        LOGGER.info("Deleted food {}", id);

    }

}
