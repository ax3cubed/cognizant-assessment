package com.adeola.cognizantassessment.repository;

import com.adeola.cognizantassessment.model.Task;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends ReactiveCrudRepository<Task, Integer> {
}
