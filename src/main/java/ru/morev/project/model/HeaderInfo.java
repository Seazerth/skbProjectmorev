package ru.morev.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HeaderInfo {
    private String key;
    private String value;
}
