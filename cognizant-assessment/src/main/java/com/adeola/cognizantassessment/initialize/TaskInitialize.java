package com.adeola.cognizantassessment.initialize;


import com.adeola.cognizantassessment.model.Task;
import com.adeola.cognizantassessment.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
@Slf4j
public class TaskInitialize implements CommandLineRunner {
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public void run(String... args) throws Exception {
        log.info("Initializing Task");
        initiateTaskSetup();
    }

    private List<Task> getTasks(){
      return   List.of(
                new Task(1, "Longest Common Prefix","Write a function to find the longest common prefix string amongst an array of strings.\n" +
                        "\n" +
                        "If there is no common prefix, return an empty string","[\"flower\",\"flow\",\"flight\"]","fl"),
                new Task(2, "Reverse Integer","Given a 32-bit signed integer, reverse digits of an integer.","123","321"),
                new Task(3, "Integer to Roman","Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.","58","LVIII"),
                new Task(4, "Merge k Sorted Lists","You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.","[[1,4,5],[1,3,4],[2,6]]","[1,1,2,3,4,4,5,6]")
        );
    }
    private void initiateTaskSetup(){
        taskRepository.deleteAll().thenMany(Flux.fromIterable(getTasks())).flatMap(taskRepository::save).
                thenMany(taskRepository.findAll()).subscribe(task ->{
            log.info("Car inserted from CommandLineRunner: " + task);
        });
    }
}
