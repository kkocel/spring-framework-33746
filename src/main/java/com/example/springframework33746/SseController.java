package com.example.springframework33746;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
class SseController {

    private final EventGenerator eventGenerator;

    public SseController(EventGenerator eventGenerator) {
        this.eventGenerator = eventGenerator;
    }

    @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    SseEmitter sendEvents() throws IOException {
        final var emitter = new SseEmitter();
        eventGenerator.generateEvents(emitter);
        return emitter;
    }

}
