package org.fasttrackit.dailyfoodconsumptionapi.persistence;

import org.fasttrackit.dailyfoodconsumptionapi.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
