package com.discovercars.hometask.DiscoverCarsHomeTask.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "message")
public class MessageWrapper {

    private String message;

    public MessageWrapper() {
    }

    public MessageWrapper(String message) {
        this.message = message;
    }

    @XmlElement(name = "text")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
