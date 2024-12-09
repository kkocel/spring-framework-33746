package com.example.springframework33746;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SseController.class)
@Import(EventConfiguration.class)
class EventGeneratorWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetSseEvents() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/sse"))
                .andExpect(status().isOk())
                .andReturn();

        String generateEvents = result.getResponse().getContentAsString();

        assertEquals("""
                event:processing started
                
                event:entry 1
                
                event:processing completed
                
                """, generateEvents);
    }
}
