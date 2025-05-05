package ru.morev.project.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@Profile("prod")
public class ProdProfileConfig {

    @PostConstruct
    public void init() {
        log.info("Активен профиль: PROD");
    }
}
