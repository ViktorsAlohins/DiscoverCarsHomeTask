package com.discovercarshometask.DiscoverCarsHomeTask.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.io.StringReader;
import java.io.StringWriter;

@RestController
public class PostController {
    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @PostMapping(value = "/api/post", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> handlePostRequest(@RequestBody MultiValueMap<String, String> postBody) {
        String format = postBody.getFirst("format");

        switch (format) {
            case "json":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unsupported format: JSON");
            case "xml":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unsupported format: XML");
            case "html":
                return generateHtmlOutput(postBody);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unsupported format");
        }
    }

    private ResponseEntity<String> generateHtmlOutput(MultiValueMap<String, String> postBody) {
        StringBuilder html = new StringBuilder("<html><head><style>table, th, td {border: 1px solid black;}</style></head><body><table><tr><th>Parameter</th><th>Value</th></tr>");

        postBody.forEach((key, values) -> {
            values.forEach(value -> {
                html.append("<tr><td>").append(key).append("</td><td>").append(value).append("</td></tr>");
            });
        });

        html.append("</table></body></html>");
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(html.toString());
    }
}
