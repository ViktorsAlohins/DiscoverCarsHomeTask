package com.discovercars.hometask.DiscoverCarsHomeTask.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @PostMapping(value = "/api/post", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> handlePostRequest(@RequestBody MultiValueMap<String, String> postBody) {
        String format = postBody.getFirst("format");

        switch (format) {
            case "html":
                return generateHtmlOutput(postBody);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Format not allowed.");
        }
    }

    private ResponseEntity<String> generateHtmlOutput(MultiValueMap<String, String> postBody) {
        StringBuilder output = new StringBuilder("<html>");
        postBody.forEach((key, value) -> output.append("<li>").append(key).append(": ").append(value).append("</li>"));
        output.append("</html>");
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(output.toString());
    }
}
