package org.fasttrackit.dailyfoodconsumptionapi.web;

import org.fasttrackit.dailyfoodconsumptionapi.domain.Food;
import org.fasttrackit.dailyfoodconsumptionapi.exception.ResourceNotFoundException;
import org.fasttrackit.dailyfoodconsumptionapi.service.FoodService;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.food.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> getFood(@PathVariable("id") long id) throws ResourceNotFoundException {
        Food response = foodService.getFood(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody @Valid CreateFoodRequest request){
        Food response = foodService.createFood(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
