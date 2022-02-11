package com.adeola.cognizantassessment.service;

import com.adeola.cognizantassessment.model.UserTask;
import com.adeola.cognizantassessment.repository.UserTaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Service
@Transactional
@Slf4j
public class UserTaskService {
    private final UserTaskRepository userTaskRepository;

    public UserTaskService(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }

    public Mono<UserTask> addUserTask(UserTask userTask) {
       return userTaskRepository.save(userTask);
    }

    public Mono<UserTask> getUserTask(Integer id) {
        return userTaskRepository.findById(id);
    }

    public Flux<UserTask> getAllTaskByUserId(Integer userId) {
        return userTaskRepository.findAllByUserId(userId);
    }
    public Flux<UserTask> getAllUserTasks() {
        return userTaskRepository.findAll();
    }
    public Flux<UserTask> getTopThreeTasks(){
        return userTaskRepository.findAll().filter(userTask -> userTask.getStatus() == true).sort(Comparator.comparing(UserTask::getUserId) ).
                groupBy(userTask -> userTask.getUserId()).flatMap(userTask -> userTask.take(3));
    }
}
