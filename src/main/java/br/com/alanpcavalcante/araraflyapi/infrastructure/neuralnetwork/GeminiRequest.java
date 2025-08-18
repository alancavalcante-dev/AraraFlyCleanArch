package br.com.alanpcavalcante.araraflyapi.infrastructure.neuralnetwork;

import lombok.Data;

import java.util.List;

@Data
public class GeminiRequest {
    private String model;
    private List<Content> contents;

    @Data
    public static class Content {
        private List<Part> parts;
    }

    @Data
    public static class Part {
        private String text;
    }
}
