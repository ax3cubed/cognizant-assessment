package com.adeola.cognizantassessment.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table("user_task")
@Builder
public class UserTask {
    @Id
    private Integer id;
    private Integer userId;
    private Integer taskId;
    private Boolean status;
}

