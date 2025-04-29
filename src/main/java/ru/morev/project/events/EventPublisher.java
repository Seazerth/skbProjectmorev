package ru.morev.project.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publishCustomEvent(String msg) {
        CustomEvent event = new CustomEvent(this, msg);
        publisher.publishEvent(event);
    }

    public void publishGenericEvent(String msg) {
        publisher.publishEvent("📢 Сообщение: " + msg);
    }
}
