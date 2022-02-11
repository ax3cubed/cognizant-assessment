package com.adeola.cognizantassessment.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("users")
@Builder
public class User {
    @Id
    private Integer id;
    private String name;
    private String email;
}
