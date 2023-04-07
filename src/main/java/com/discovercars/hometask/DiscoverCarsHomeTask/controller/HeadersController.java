package com.discovercars.hometask.DiscoverCarsHomeTask.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.discovercars.hometask.DiscoverCarsHomeTask.model.HeadersWrapper;;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
public class HeadersController {
    private static final Logger log = LoggerFactory.getLogger(HeadersController.class);
    private static final Pattern HEX_COLOR_PATTERN = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", Pattern.CASE_INSENSITIVE);

    @GetMapping("/api/headers")
    public ResponseEntity<String> getHeaders(HttpServletRequest request) {
        Map<String, String> headersMap = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headersMap.put(headerName, headerValue);
            log.debug("Header: {} = {}", headerName, headerValue);
        }

        String format = request.getParameter("format");
        if (format == null) {
            format = "html";
        }

        switch (format) {
            case "xml":
                return generateXmlOutput(headersMap);
            case "json":
                return generateJsonOutput(headersMap);
            default:
                return generateHtmlOutput(headersMap);
        }
    }

    private ResponseEntity<String> generateHtmlOutput(Map<String, String> headersMap) {
        StringBuilder html = new StringBuilder("<html><head><style>table, th, td {border: 1px solid black;}</style></head><body><table><tr><th>Header</th><th>Value</th></tr>");

        headersMap.forEach((header, value) -> {
            html.append("<tr><td>").append(header).append("</td><td>").append(value).append("</td></tr>");
        });

        html.append("</table></body></html>");
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(html.toString());
    }

    private ResponseEntity<String> generateJsonOutput(Map<String, String> headersMap) {
        try {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new ObjectMapper().writeValueAsString(headersMap));
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).contentType(MediaType.TEXT_PLAIN).body("Error generating JSON output.");
        }
    }

    private ResponseEntity<String> generateXmlOutput(Map<String, String> headersMap) {
        try {
            log.info("Headers Map: {}", headersMap);

            JAXBContext jaxbContext = JAXBContext.newInstance(HeadersWrapper.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(new HeadersWrapper(headersMap), stringWriter);
            String xmlOutput = stringWriter.toString();

            return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(xmlOutput);
        } catch (JAXBException e) {
                log.error("JAXBException while generating XML output: {}", e.getMessage(), e);
                return ResponseEntity.status(500).contentType(MediaType.TEXT_PLAIN).body("Error generating XML output.");
            }
        }
}