package ru.morev.project.model;

import lombok.Data;

@Data
public class TodoItem {
    private Long id;
    private String title;
    private boolean completed;
}
