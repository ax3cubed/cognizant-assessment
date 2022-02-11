package com.adeola.cognizantassessment.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserTaskRequest {
    private Integer userId;
    private Integer taskId;
    private String language;
    private String userResponse;
}
