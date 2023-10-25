package com.example.demo.apiproperties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ApiConfig {

    @Value("${lawapi.qna.key}")
    private String lawApiQnaKey;

    @Value("${lawapi.prec-clau.key}")
    private String lawApiPrecClauKey;

}
