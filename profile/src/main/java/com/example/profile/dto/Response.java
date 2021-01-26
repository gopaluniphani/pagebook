package com.example.profile.dto;


public class Response {

    Object body;

    String error;

    boolean status;

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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
                ", error='" + error + '\'' +
                ", status=" + status +
                '}';
    }
}
