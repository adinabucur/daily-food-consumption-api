package org.fasttrackit.dailyfoodconsumptionapi.persistence;

import org.fasttrackit.dailyfoodconsumptionapi.domain.Meal;
import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<Meal, Long> {
}
