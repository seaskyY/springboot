package com.tc.pes.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration(exclude={SecurityAutoConfiguration.class})
public class WebApplication {

    @RequestMapping("/")
    public String greeting() {
        return "Hello World!";
    }
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
