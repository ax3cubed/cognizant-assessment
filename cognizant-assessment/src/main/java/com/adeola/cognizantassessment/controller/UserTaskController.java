package com.adeola.cognizantassessment.controller;

import com.adeola.cognizantassessment.model.Task;
import com.adeola.cognizantassessment.model.UserTask;
import com.adeola.cognizantassessment.service.UserTaskService;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/userTasks")
public class UserTaskController {
    public  final UserTaskService userTaskService;

    public UserTaskController(UserTaskService userTaskService) {
        this.userTaskService = userTaskService;
    }

    @GetMapping
    public Flux<UserTask> getAllTasks(){
        return userTaskService.getAllUserTasks();
    }

    @GetMapping("/topThree")
    public Flux<UserTask> getTopThreeTasks() {
    return userTaskService.getTopThreeTasks();
    }

    @GetMapping("/userTasks/{userId}")
    public Flux<UserTask> getUserTasksByUserId(@PathVariable Integer userId) {
        return userTaskService.getAllTaskByUserId(userId);
    }
    @PostMapping
    public Mono<UserTask> createUserTask(@RequestBody Task task) {
        return userTaskService.addUserTask(userTask);
    }
}


