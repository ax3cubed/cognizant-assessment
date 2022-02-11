package com.adeola.cognizantassessment.controller;

import com.adeola.cognizantassessment.dto.TaskMini;
import com.adeola.cognizantassessment.model.Task;
import com.adeola.cognizantassessment.service.TaskService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(value = { "http://localhost:3000" },
        maxAge = 900
)
@RestController
@Slf4j
@RequestMapping("/task")
public class TaskController {
    public final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
}

    @GetMapping
    public Flux<TaskMini> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Task> createTask(@RequestBody Task task) {
        return  taskService.createTask(task);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Task>> getTaskById(@PathVariable Integer id) {
        return taskService.getTaskById(id).map(t -> ResponseEntity.ok(t))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Task>> updateTask(@RequestBody Task task,@PathVariable Integer id) {
        return taskService.updateTask(task,id).map(updatedTask -> ResponseEntity.ok(updatedTask))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteTask(@PathVariable Integer id) {
        return taskService.deleteTask(id);
    }
}

