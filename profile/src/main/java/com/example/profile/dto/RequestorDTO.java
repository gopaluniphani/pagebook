package com.example.profile.dto;

public class RequestorDTO {

    private String firstName;

    private String imgUrl;

    private  String requestorId;

    public String getRequestorId() {
        return requestorId;
    }

    public void setRequestorId(String requestorId) {
        this.requestorId = requestorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "RequestorDTO{" +
                "firstName='" + firstName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", requestorId='" + requestorId + '\'' +
                '}';
    }
}
