package com.example.posts.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

public class FriendsResponse implements Serializable {

    public FriendsResponse() {
    }

    private boolean status;
    private String error;
    private Object body;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "FriendsResponse{" +
                "status=" + status +
                ", error='" + error + '\'' +
                ", body=" + body +
                '}';
    }
}
