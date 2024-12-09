package com.example.springframework33746;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

public class EventGenerator {

    public void generateEvents(SseEmitter emitter) throws IOException {

        emitter.send(
                SseEmitter
                        .event()
                        .name("processing started"));

        emitter.send(
                SseEmitter
                        .event()
                        .name("entry 1"));

        emitter.send(
                SseEmitter
                        .event()
                        .name("processing completed"));

        emitter.complete();

    }
}
