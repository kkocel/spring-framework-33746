package org.springframework.web.servlet.mvc.method.annotation;

import com.example.springframework33746.EventGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.Charset;

class EventGeneratorUnitTest {

    @Test
    void shouldHandleEvents() throws IOException {
        var testHandler = new TestHandler();
        var emitter = new SseEmitter();
        emitter.initialize(testHandler);

        new EventGenerator().generateEvents(emitter);

        testHandler.assertObject(
                0,
                "event:processing started\n\n",
                new MediaType(MediaType.TEXT_PLAIN, Charset.defaultCharset())
        );
        testHandler.assertObject(
                1,
                "event:entry 1\n\n",
                new MediaType(MediaType.TEXT_PLAIN, Charset.defaultCharset())
        );
        testHandler.assertObject(
                2,
                "event:processing completed\n\n",
                new MediaType(MediaType.TEXT_PLAIN, Charset.defaultCharset())
        );
    }
}
