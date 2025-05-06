package ru.morev.project.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Endpoint(id = "tasklog")
public class LogTimeActuator {

    private static final Logger logger = LoggerFactory.getLogger(LogTimeActuator.class);

    @ReadOperation
    public String logCurrentTime() {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        logger.info("Актуатор tasklog вызван: {}", time);
        return "Время вызова tasklog: " + time;
    }
}
