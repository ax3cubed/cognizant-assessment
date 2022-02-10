package com.adeola.cognizantassessment.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table("user_task")
public class UserTask {
    @Id
    private Integer id;
    private Long userId;
    private Long taskId;
    private Boolean status;
}

