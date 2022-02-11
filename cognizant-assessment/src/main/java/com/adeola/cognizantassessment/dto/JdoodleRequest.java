package com.adeola.cognizantassessment.dto;


import lombok.Builder;
import lombok.Data;
import org.springframework.core.env.Environment;

@Data
@Builder
public class JdoodleRequest {
    private  String clientId;
    private String clientSecret;
    private  String language;
    private  String script;
    private String versionIndex;


}
