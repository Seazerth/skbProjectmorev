package ru.morev.project.model;

import lombok.Data;

@Data
public class EchoRequest {
    private double price;
    private Info info;

    @Data
    public static class Info {
        private String date;
    }
}
