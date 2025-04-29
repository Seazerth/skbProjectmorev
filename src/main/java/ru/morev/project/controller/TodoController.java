package ru.morev.project.controller;

import org.springframework.web.bind.annotation.*;
import ru.morev.project.model.TodoItem;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final Map<Long, TodoItem> todoMap = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @GetMapping
    public Collection<TodoItem> getAll() {
        return todoMap.values();
    }

    @PostMapping
    public TodoItem create(@RequestBody TodoItem item) {
        long id = idGenerator.incrementAndGet();
        item.setId(id);
        todoMap.put(id, item);
        return item;
    }

    @GetMapping("/{id}")
    public TodoItem getById(@PathVariable Long id) {
        return todoMap.get(id);
    }

    @PutMapping("/{id}")
    public TodoItem update(@PathVariable Long id, @RequestBody TodoItem updated) {
        updated.setId(id);
        todoMap.put(id, updated);
        return updated;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        todoMap.remove(id);
    }
}
