package ru.morev.project.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageConsumer {

    @RabbitListener(queues = "${rabbit.queue.name:defaultQueue}")
    public void receiveMessage(String message) {
        log.info("📥 Получено сообщение из очереди: {}", message);
    }
}
