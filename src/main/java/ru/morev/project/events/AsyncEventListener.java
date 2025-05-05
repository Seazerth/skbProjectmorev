package ru.morev.project.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsyncEventListener {

    @Async
    @EventListener
    public void handleAsync(Object event) {
        log.info("AsyncEventListener: обработка события {}", event);
    }
}
