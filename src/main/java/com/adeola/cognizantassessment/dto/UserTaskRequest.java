package com.adeola.cognizantassessment.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserTaskRequest {
    private String name;
    private String email;
    private Integer taskId;
    private String language;
    private String userResponse;
}
