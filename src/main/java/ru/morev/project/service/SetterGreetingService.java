package ru.morev.project.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class SetterGreetingService implements GreetingService {

    public SetterGreetingService() {
        System.out.println("🧱 SetterGreetingService создан");
    }

    public String greet() {
        return "Привет из SetterGreetingService";
    }

    @PostConstruct
    public void init() {
        System.out.println("📦 SetterGreetingService — @PostConstruct");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("🧹 SetterGreetingService — @PreDestroy");
    }
}
