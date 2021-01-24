package com.example.business.models;

public class Response {
    private boolean status;
    private String errorMessage;
    private Object body;

    public Response() {
        this.errorMessage = "";
        this.body = null;
    }

    public Response(boolean status, String errorMessage, Object body) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.body = body;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
