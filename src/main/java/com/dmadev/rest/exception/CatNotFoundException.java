package com.dmadev.rest.exception;

public class CatNotFoundException extends Exception{
    public CatNotFoundException(String message) {
        super(message);
    }
}
