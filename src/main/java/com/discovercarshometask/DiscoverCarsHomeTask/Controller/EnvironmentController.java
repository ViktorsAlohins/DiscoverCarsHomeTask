package com.discovercarshometask.DiscoverCarsHomeTask.Controller;

<<<<<<< HEAD
import com.discovercarshometask.DiscoverCarsHomeTask.Model.MessageWrapper;
=======
>>>>>>> a5aa195 (Creating simple application that makes HTTP request and returns "Hello World" in 3 different formats - HTTP, Json or XML)
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import java.util.regex.Pattern;

@RestController
public class EnvironmentController {

    private static final Pattern HEX_COLOR_PATTERN = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$",
            Pattern.CASE_INSENSITIVE);

    @GetMapping("/api/environment")
    public ResponseEntity<String> getHelloWorld(@RequestParam(value = "format", required = false, defaultValue = "html")
                                                String format) {
=======
@RestController
public class EnvironmentController {

    @GetMapping("/api/environment")
    public ResponseEntity<String> getHelloWorld(@RequestParam(value = "format", required = false, defaultValue = "html") String format) {
>>>>>>> a5aa195 (Creating simple application that makes HTTP request and returns "Hello World" in 3 different formats - HTTP, Json or XML)
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
<<<<<<< HEAD
        String bgColor = System.getenv().getOrDefault("BGCOLOR", "#FFFFFF");
        String fgColor = System.getenv().getOrDefault("FGCOLOR", "#000");

        if (!HEX_COLOR_PATTERN.matcher(bgColor).matches()) {
            bgColor = "#FFFFFF";
        }

        if (!HEX_COLOR_PATTERN.matcher(fgColor).matches()) {
            fgColor = "#000000";
        }

        String html = String.format("<html><head><style>body { background-color: %s; color: %s; }</style></head><body>%s</body></html>", bgColor, fgColor, message);
=======
        String html = String.format("<html><body>%s</body></html>", message);
>>>>>>> a5aa195 (Creating simple application that makes HTTP request and returns "Hello World" in 3 different formats - HTTP, Json or XML)
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

<<<<<<< HEAD
}
=======
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
>>>>>>> a5aa195 (Creating simple application that makes HTTP request and returns "Hello World" in 3 different formats - HTTP, Json or XML)
