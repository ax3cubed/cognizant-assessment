package com.adeola.cognizantassessment.controller;

import com.adeola.cognizantassessment.dto.JdoodleRequest;
import com.adeola.cognizantassessment.dto.JdoodleResponse;
import com.adeola.cognizantassessment.dto.UserTaskRequest;
import com.adeola.cognizantassessment.model.Task;
import com.adeola.cognizantassessment.model.User;
import com.adeola.cognizantassessment.model.UserTask;
import com.adeola.cognizantassessment.service.TaskService;
import com.adeola.cognizantassessment.service.UserService;
import com.adeola.cognizantassessment.service.UserTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/userTasks")
public class UserTaskController {
    private Environment env;

    public  final UserService userService;
    public final UserTaskService userTaskService;
    public  final TaskService taskService;

    public UserTaskController(UserService userService, UserTaskService userTaskService, TaskService taskService) {
        this.userService = userService;

        this.userTaskService = userTaskService;
        this.taskService = taskService;
    }

    @GetMapping
    public Flux<UserTask> getAllTasks() {
        return userTaskService.getAllUserTasks();
    }

    @GetMapping("/topThree")
    public Flux<UserTask> getTopThreeTasks() {
        return userTaskService.getTopThreeTasks();
    }

    @GetMapping("/{userId}")
    public Flux<UserTask> getUserTasksByUserId(@PathVariable Integer userId) {
        return userTaskService.getAllTaskByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserTask> createUserTask(@RequestBody UserTaskRequest userTaskRequest) {
        Mono<User> user = userService.findUserByEmail(userTaskRequest.getEmail());
        if (user.block() == null) {
            user = userService.saveUser(User.builder().email(userTaskRequest.getEmail()).name(userTaskRequest.getName()
            ).build());
        }

        Mono<JdoodleRequest> jdoodleRequest = Mono.just(JdoodleRequest.builder().clientId(env.getProperty("jdoodle.clientId")).clientSecret(env.getProperty("jdoodle.clientSecret")).language(userTaskRequest.getLanguage()).script(userTaskRequest.getUserResponse()).versionIndex("0").build());
        Mono<JdoodleResponse> response = WebClient.create("https://api.jdoodle.com").post().uri("/v1/execute").contentType(MediaType.APPLICATION_JSON).body(jdoodleRequest, JdoodleRequest.class).retrieve().bodyToMono(JdoodleResponse.class).log("request Sent");
        Mono<Task> task = taskService.getTaskById(userTaskRequest.getTaskId());
        UserTask userTask = UserTask.builder().taskId(userTaskRequest.getTaskId()).userId(user.block().getId()).build();

        if(task.block().getTaskOutputParameter().equals(response.block().getOutput())){
            userTask.setStatus(true);
        }
        else{
            userTask.setStatus(false);
        }
        return userTaskService.addUserTask(userTask);
    }
}


