package com.discovercarshometask.DiscoverCarsHomeTask.Controller;

import com.discovercarshometask.DiscoverCarsHomeTask.Model.MessageWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
public class EnvironmentController {

    private static final Pattern HEX_COLOR_PATTERN = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$",
            Pattern.CASE_INSENSITIVE);

    @GetMapping("/api/environment")
    public ResponseEntity<String> getHelloWorld(@RequestParam(value = "format", required = false, defaultValue = "html")
                                                String format) {
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
        String bgColor = System.getenv().getOrDefault("BGCOLOR", "#FFFFFF");
        String fgColor = System.getenv().getOrDefault("FGCOLOR", "#000");

        if (!HEX_COLOR_PATTERN.matcher(bgColor).matches()) {
            bgColor = "#FFFFFF";
        }

        if (!HEX_COLOR_PATTERN.matcher(fgColor).matches()) {
            fgColor = "#000000";
        }

        String html = String.format("<html><head><style>body { background-color: %s; color: %s; }</style></head><body>%s</body></html>", bgColor, fgColor, message);
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

}