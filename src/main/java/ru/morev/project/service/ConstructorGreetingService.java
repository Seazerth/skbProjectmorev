package ru.morev.project.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class ConstructorGreetingService implements GreetingService {

    public ConstructorGreetingService() {
        System.out.println("🧱 ConstructorGreetingService создан через конструктор");
    }

    @Override
    public String greet() {
        return "Привет из ConstructorGreetingService";
    }

    @PostConstruct
    public void init() {
        System.out.println("📦 ConstructorGreetingService — @PostConstruct");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("🧹 ConstructorGreetingService — @PreDestroy");
    }
}
