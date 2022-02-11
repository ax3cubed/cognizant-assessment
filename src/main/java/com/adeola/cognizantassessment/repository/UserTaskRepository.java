package com.adeola.cognizantassessment.repository;

import com.adeola.cognizantassessment.model.UserTask;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserTaskRepository extends ReactiveCrudRepository<UserTask, Integer> {

    @Query("SELECT * FROM user_task WHERE user_id = :userId")
    Flux<UserTask> findAllByUserId(Integer userId);
}
