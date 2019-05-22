package org.fasttrackit.dailyfoodconsumptionapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.dailyfoodconsumptionapi.domain.Food;
import org.fasttrackit.dailyfoodconsumptionapi.exception.ResourceNotFoundException;
import org.fasttrackit.dailyfoodconsumptionapi.persistence.FoodRepository;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.CreateFoodRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Food createFood(CreateFoodRequest request){
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
}
