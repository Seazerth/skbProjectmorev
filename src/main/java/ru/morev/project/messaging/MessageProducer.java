package ru.morev.project.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;
    private final String queueName;

    public MessageProducer(RabbitTemplate rabbitTemplate,
                           @Value("${rabbit.queue.name:defaultQueue}") String queueName) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
    }

    public void sendMessage(String message) {
        log.info("Отправка сообщения в очередь {}: {}", queueName, message);
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
