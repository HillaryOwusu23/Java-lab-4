package com.java.week4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Week4Controller {

    @GetMapping("/hello")
        public String sayHello(){
            return "Hello, welcome to the Spring Boot application!";
        }
    }
