package com.winter.ch7_6.domain;

public class WinterResponse {
    private String responseMessage;
    public WinterResponse(String responseMessage){
        this.responseMessage = responseMessage;
    }
    public String getResponseMessage(){
        return responseMessage;
    }
}
