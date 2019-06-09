package org.fasttrackit.dailyfoodconsumptionapi.persistence;

import org.fasttrackit.dailyfoodconsumptionapi.domain.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FoodRepository extends PagingAndSortingRepository<Food, Long> {

    Page<Food> findByNameContainingAndQuantityGreaterThanEqual(
            String partialName, int minimumQuantity, Pageable pageable);

    Page<Food> findByNutritionDeclarationBetweenAndQuantityGreaterThanEqual(
            int minimumNutritionDeclaration, int maximumNutritionDeclaration,
            int minimumQuantity, Pageable pageable);

    Page<Food> findByNameContainingAndNutritionDeclarationBetweenAndQuantityGreaterThanEqual(
            String partialName, int minimumNutritionDeclaration, int maximumNutritionDeclaration,
            int minimumQuantity, Pageable pageable);

    Page<Food> findByNameContaining(String partialName, Pageable pageable);


    //same result as method above (except the returned columns)
    @Query(value = "SELECT id, name FROM Food food WHERE name LIKE '%?1'")
    Page<Food> findByPartialName(String partialName, Pageable pageable);

}
