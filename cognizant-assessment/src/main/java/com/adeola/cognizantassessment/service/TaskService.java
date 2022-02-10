package com.adeola.cognizantassessment.service;


import com.adeola.cognizantassessment.model.Task;
import com.adeola.cognizantassessment.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@Slf4j
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Mono<Task> createTask(Task task) {
        log.info("Saving task: {}", task);
        return taskRepository.save(task);
    }
    public Flux<Task> getAllTasks() {
        log.info("Getting all tasks");
        return taskRepository.findAll();
    }
    public Mono<Task> getTaskById(Integer id) {
        log.info("Getting task by id: {}", id);
        return taskRepository.findById(id);
    }
    public Mono<Task> updateTask(Task task , Integer id) {
        log.info("Updating task: {}", task);
        return taskRepository.findById(id).flatMap(existingTask -> {
            existingTask.setTaskDescription(task.getTaskDescription());
            existingTask.setTaskName(task.getTaskName());
            existingTask.setTaskInputParameter(task.getTaskInputParameter());
            existingTask.setTaskOutputParameter(task.getTaskOutputParameter());
            return taskRepository.save(existingTask);
        });

    }
    public Mono<Void> deleteTask(Integer id) {
        log.info("Deleting task by id: {}", id);
        return taskRepository.deleteById(id);
    }
}
