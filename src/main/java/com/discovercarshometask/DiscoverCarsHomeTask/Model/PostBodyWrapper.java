package com.discovercarshometask.DiscoverCarsHomeTask.Model;

import java.util.Map;

public class PostBodyWrapper {
    private Map<String, String> postFields;

    public PostBodyWrapper() {}

    public PostBodyWrapper(Map<String, String> postFields) {
        this.postFields = postFields;
    }

    public Map<String, String> getPostFields() {
        return postFields;
    }

    public void setPostFields(Map<String, String> postFields) {
        this.postFields = postFields;
    }
}
