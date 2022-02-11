package com.adeola.cognizantassessment.service;

import com.adeola.cognizantassessment.model.User;
import com.adeola.cognizantassessment.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Flux<User> findAllUsers() {
        return userRepository.findAll();
    }
    public Mono<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }
    public Mono<User> saveUser(User user) {
        return userRepository.save(user);
    }
    public  Mono<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
