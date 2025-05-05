package ru.morev.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;
import ru.morev.project.config.RabbitConfig;
import ru.morev.project.dto.MessageDto;
@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageSendController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public String sendMessage(@RequestBody MessageDto message) {
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitConfig.ROUTING_KEY, message);
        return "Message sent to RabbitMQ";
    }
}
