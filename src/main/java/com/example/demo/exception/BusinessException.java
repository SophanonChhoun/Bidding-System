package com.example.demo.exception;

import com.example.demo.constant.MessageKey;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final String fieldName;
    private final MessageKey key;
    private final Object[] objects;
    private final String message;


    public BusinessException(String fieldName, MessageKey key, String message) {
        super("Business Exception");
        this.fieldName = fieldName;
        this.key = key;
        this.message = message;
        this.objects = null;
    }

    public BusinessException(String fieldName, MessageKey key, Object[] objects, String message) {
        super("Business Exception");
        this.fieldName = fieldName;
        this.key = key;
        this.objects = objects;
        this.message = message;
    }

}
