package com.Assignment.TODO.exception;

public class OurException extends RuntimeException{
    private String message;
    private int statusCode;
    public OurException(String message,int statusCode){
        super(message);
        this.message=message;
        this.statusCode=statusCode;
    }
    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
