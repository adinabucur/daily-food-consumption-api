package org.fasttrackit.dailyfoodconsumptionapi.persistence;

import org.fasttrackit.dailyfoodconsumptionapi.domain.Food;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FoodRepository extends PagingAndSortingRepository<Food, Long>{
}
