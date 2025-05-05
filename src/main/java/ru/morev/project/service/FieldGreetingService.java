package ru.morev.project.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class FieldGreetingService implements GreetingService {

    @Override
    public String greet() {
        return "Привет из FieldGreetingService";
    }

    @PostConstruct
    public void init() {
        System.out.println("FieldGreetingService — @PostConstruct");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("FieldGreetingService — @PreDestroy");
    }
}
