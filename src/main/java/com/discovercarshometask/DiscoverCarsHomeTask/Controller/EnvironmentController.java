package com.discovercarshometask.DiscoverCarsHomeTask.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvironmentController {

    @GetMapping("/api/environment")
    public ResponseEntity<String> getHelloWorld(@RequestParam(value = "format", required = false, defaultValue = "html") String format) {
        String message = "Hello World";

        switch (format.toLowerCase()) {
            case "json":
                return generateJsonOutput(message);
            case "xml":
                return generateXmlOutput(message);
            default:
                return generateHtmlOutput(message);
        }
    }

    private ResponseEntity<String> generateHtmlOutput(String message) {
        String html = String.format("<html><body>%s</body></html>", message);
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(html);
    }

    private ResponseEntity<String> generateJsonOutput(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonOutput = objectMapper.writeValueAsString(new MessageWrapper(message));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jsonOutput);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).contentType(MediaType.TEXT_PLAIN).body("Error generating JSON output.");
        }
    }

    private ResponseEntity<String> generateXmlOutput(String message) {
        String xmlOutput = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?><message>%s</message>", message);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(xmlOutput);
    }

    private static class MessageWrapper {
        private String message;

        public MessageWrapper(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
