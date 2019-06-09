package org.fasttrackit.dailyfoodconsumptionapi.web;

import org.fasttrackit.dailyfoodconsumptionapi.domain.Food;
import org.fasttrackit.dailyfoodconsumptionapi.exception.ResourceNotFoundException;
import org.fasttrackit.dailyfoodconsumptionapi.service.FoodService;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.food.CreateFoodRequest;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.food.GetFoodsRequest;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.food.UpdateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Food> createFood(@RequestBody @Valid CreateFoodRequest request) {
        Food response = foodService.createFood(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateFood(
            @PathVariable("id") long id,
            @RequestBody @Valid UpdateFoodRequest request) throws ResourceNotFoundException {
        foodService.updateFood(id, request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFood(@PathVariable("id") long id) {
        foodService.deleteFood(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<Food>> getFoods(@Valid GetFoodsRequest request, Pageable pageable){
        Page<Food> response = foodService.getFoods(request, pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
