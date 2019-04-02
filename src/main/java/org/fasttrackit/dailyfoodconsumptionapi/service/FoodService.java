package org.fasttrackit.dailyfoodconsumptionapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.dailyfoodconsumptionapi.domain.Food;
import org.fasttrackit.dailyfoodconsumptionapi.persistence.FoodRepository;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    @Autowired
    private final FoodRepository foodRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public FoodService(FoodRepository foodRepository, ObjectMapper objectMapper) {
        this.foodRepository = foodRepository;
        this.objectMapper = objectMapper;
    }
    public Food createFood(CreateFoodRequest request){
        Food food = objectMapper.convertValue(request, Food.class);
        return foodRepository.save(food);
    }
}
