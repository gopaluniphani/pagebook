package com.example.posts.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Response {

    private boolean status;
    private String errorMessage;
    private Object body;
}
