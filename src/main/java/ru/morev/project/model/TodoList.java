package ru.morev.project.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class TodoList {
    @NotEmpty(message = "Имя списка не может быть пустым")
    private String name;

    @NotEmpty(message = "Список задач не может быть пустым")
    private List<String> events;
}
