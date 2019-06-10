package org.fasttrackit.dailyfoodconsumptionapi.service;

import org.fasttrackit.dailyfoodconsumptionapi.domain.Characteristics;
import org.fasttrackit.dailyfoodconsumptionapi.domain.Food;
import org.fasttrackit.dailyfoodconsumptionapi.exception.ResourceNotFoundException;
import org.fasttrackit.dailyfoodconsumptionapi.persistence.CharacteristicsRepository;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.characteristics.CreateCharacteristicsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CharacteristicsService {
    private final CharacteristicsRepository characteristicsRepository;
    private final FoodService foodService;

    @Autowired
    public CharacteristicsService(CharacteristicsRepository characteristicsRepository, FoodService foodService) {
        this.characteristicsRepository = characteristicsRepository;
        this.foodService = foodService;
    }

    @Transactional
    public Characteristics createCharacteristics(CreateCharacteristicsRequest request) throws ResourceNotFoundException {
        Food food = foodService.getFood(request.getFoodId());

        Characteristics characteristics = new Characteristics();
        characteristics.setContent(request.getContent());
        characteristics.setFood(food);

        return characteristicsRepository.save(characteristics);
    }
}
