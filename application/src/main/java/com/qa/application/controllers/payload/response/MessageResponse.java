package com.qa.application.controllers.payload.response;

public class MessageResponse {

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {return message;}

    public void setMessage() {this.message = message;}
}
