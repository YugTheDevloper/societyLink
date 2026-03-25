package org.yug.societylink.exception;

public class BadCredentials extends RuntimeException{
    public BadCredentials(String message){
        super(message);
    }
}
