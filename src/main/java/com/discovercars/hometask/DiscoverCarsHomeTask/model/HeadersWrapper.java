package com.discovercars.hometask.DiscoverCarsHomeTask.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Map;
@XmlRootElement(name = "headers")
public class HeadersWrapper {
    private Map<String, String> headersMap;

    public HeadersWrapper() {}

    public HeadersWrapper(Map<String, String> headersMap) {
        this.headersMap = headersMap;
    }

    public Map<String, String> getHeadersMap() {
        return headersMap;
    }

    public void setHeadersMap(Map<String, String> headersMap) {
        this.headersMap = headersMap;
    }
}