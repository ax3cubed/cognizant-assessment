package com.adeola.cognizantassessment.repository;

import com.adeola.cognizantassessment.model.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Integer> {

    @Query("SELECT * FROM users  WHERE email = :email")
    Mono<User> findByEmail(String email);
}
