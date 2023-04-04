package com.discovercarshometask.DiscoverCarsHomeTask.Controller;

import com.discovercarshometask.DiscoverCarsHomeTask.Model.PostBodyWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
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

    private ResponseEntity<String> generateJsonOutput(MultiValueMap<String, String> postBody) {
        PostBodyWrapper postBodyWrapper = new PostBodyWrapper(postBody.toSingleValueMap());
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonOutput = objectMapper.writeValueAsString(postBodyWrapper);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jsonOutput);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).contentType(MediaType.TEXT_PLAIN).body("Error generating JSON output.");
        }
    }

    private ResponseEntity<String> generateXmlOutput(MultiValueMap<String, String> postBody) {
        PostBodyWrapper postBodyWrapper = new PostBodyWrapper(postBody.toSingleValueMap());

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonInput = objectMapper.writeValueAsString(postBodyWrapper);

            JAXBContext jaxbContext = JAXBContext.newInstance(PostBodyWrapper.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader stringReader = new StringReader(jsonInput);
            PostBodyWrapper postBodyWrapperResult = (PostBodyWrapper) unmarshaller.unmarshal(stringReader);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(postBodyWrapperResult, stringWriter);
            String xmlOutput = stringWriter.toString();

            return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(xmlOutput);
        } catch (JAXBException | JsonProcessingException e) {
            log.error("Exception while generating XML output: {}", e.getMessage(), e);
            return ResponseEntity.status(500).contentType(MediaType.TEXT_PLAIN).body("Error generating XML output.");
        }
    }
}
