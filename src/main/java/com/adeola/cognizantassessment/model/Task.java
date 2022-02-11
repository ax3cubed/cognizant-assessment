package com.adeola.cognizantassessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table("tasks")
public class Task {
    @Id
    private Integer id;
    private String taskName;
    private String taskDescription;
    private String taskInputParameter;
    private String taskOutputParameter;

    public Task(String taskName, String taskDescription, String taskInputParameter, String taskOutputParameter) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskInputParameter = taskInputParameter;
        this.taskOutputParameter = taskOutputParameter;
    }
}

