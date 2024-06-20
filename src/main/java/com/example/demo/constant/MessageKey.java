package com.example.demo.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum MessageKey {

    INVALID_STATE("ERR01000", "Invalid Logic"),
    EMAIL_ALREADY_EXIST("ERR01001", "Email already exist"),
    BID_AMOUNT_NOT_ENOUGH("ERR01002", "Bid Amount not enough"),
    USER_NOT_ACTIVE("ERR01101", "User not active"),
    PASSWORD_NOT_MATCH("ERR01102", "Password not match"),
    WRONG_PARAMETER("ERR03100", "Wrong parameter"),
    DONT_HAVE_PERMISSION("ERR04000", "You dont have permission"),
    ROUTE_NOT_FOUND("ERR05000", "Route not found"),
    NOT_FOUND("ERR05001", "Error not found"),
    INTERNAL_ERROR("ERR06000", "Internal Error"),
    UNAUTHORIZED("ERR08000", "Unauthorized");

    private final String key;
    private final String content;

    MessageKey(String key, String content) {
        this.key = key;
        this.content = content;
    }
}