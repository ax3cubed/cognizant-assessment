package com.adeola.cognizantassessment.dto;

import lombok.Data;

@Data
public class JdoodleResponse {
    private String output;
    private String statusCode;
    private String memory;
    private  String cpuTime;
}
