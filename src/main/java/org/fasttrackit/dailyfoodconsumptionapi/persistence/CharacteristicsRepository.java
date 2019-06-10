package org.fasttrackit.dailyfoodconsumptionapi.persistence;

import org.fasttrackit.dailyfoodconsumptionapi.domain.Characteristics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacteristicsRepository extends JpaRepository<Characteristics, Long> {
    Page<Characteristics> findByFoodId(long foodId, Pageable pageable);
}
