package ru.morev.project.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@Profile("dev")
public class DevProfileConfig {

    @PostConstruct
    public void init() {
        log.info("✅ Активен профиль: DEV");
    }
}
