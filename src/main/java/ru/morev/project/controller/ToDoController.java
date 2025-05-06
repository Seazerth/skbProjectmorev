package ru.morev.project.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class ToDoController {

    private final MeterRegistry meterRegistry;

    private final Map<String, Counter> taskTypeCounters = new ConcurrentHashMap<>();

    @PostConstruct
    public void initCounters() {
        for (String type : new String[]{"home", "work", "other"}) {
            taskTypeCounters.put(type, Counter.builder("todo.completed.tasks")
                    .tag("type", type)
                    .description("Количество выполненных задач по типу")
                    .register(meterRegistry));
        }
    }

    @PostMapping("/complete/{type}")
    public ResponseEntity<String> completeTask(@PathVariable String type) {
        Timer.Sample sample = Timer.start(meterRegistry);

        if (!taskTypeCounters.containsKey(type)) {
            return ResponseEntity.badRequest().body("Неизвестный тип задачи");
        }

        taskTypeCounters.get(type).increment();

        sample.stop(Timer.builder("todo.task.completion.duration")
                .description("Время обработки запроса завершения задачи")
                .tag("type", type)
                .register(meterRegistry));

        return ResponseEntity.ok("Задача типа " + type + " завершена!");
    }

    @GetMapping("/metrics")
    public ResponseEntity<Map<String, Double>> getMetrics() {
        Map<String, Double> result = new HashMap<>();
        taskTypeCounters.forEach((type, counter) -> result.put(type, counter.count()));
        return ResponseEntity.ok(result);
    }
}
