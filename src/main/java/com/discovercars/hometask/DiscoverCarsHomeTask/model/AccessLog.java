package com.discovercars.hometask.DiscoverCarsHomeTask.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "access_log")
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime requestTimestamp;
    private String ipAddress;
    private String method;
    private String path;
    private String userAgent;

    public AccessLog(Long id, LocalDateTime requestTimestamp, String ipAddress, String method, String path, String userAgent) {
        this.id = id;
        this.requestTimestamp = requestTimestamp;
        this.ipAddress = ipAddress;
        this.method = method;
        this.path = path;
        this.userAgent = userAgent;
    }

    public AccessLog() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(LocalDateTime requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
