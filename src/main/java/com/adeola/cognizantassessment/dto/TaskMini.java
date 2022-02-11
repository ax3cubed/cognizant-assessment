package com.adeola.cognizantassessment.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class TaskMini {
    @Id
    private Integer id;
    private String taskName;
    private String taskDescription;
    private String taskInputParameter;

}