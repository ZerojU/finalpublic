package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HealthController {

    @ResponseBody
    @GetMapping("/health")
    public ResponseEntity<Object> forHealthCheck() {
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
