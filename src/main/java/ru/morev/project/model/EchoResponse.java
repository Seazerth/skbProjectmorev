package ru.morev.project.model;

import lombok.Data;

@Data
public class EchoResponse {
    private double price;
    private Info info;

    @Data
    public static class Info {
        private Long id;
        private String date;
    }
}
