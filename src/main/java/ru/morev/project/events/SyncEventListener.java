package ru.morev.project.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SyncEventListener {

    @EventListener
    public void handleEvent(Object event) {
        log.info("SyncEventListener: получено событие {}", event);
    }
}
