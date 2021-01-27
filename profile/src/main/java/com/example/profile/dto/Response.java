package com.example.profile.dto;


import java.util.Map;

public class Response {

    Object body;

    String errorMessage;

    boolean status;

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Response{" +
                "body=" + body +
                ", error='" + errorMessage + '\'' +
                ", status=" + status +
                '}';
    }
}
