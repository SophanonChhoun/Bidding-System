package com.example.demo.model.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Result<T> {

    private final T data;
    private final String messageKey;
    private final Object[] objects;

    public static <T> Result<T> of(final T data) {
        return new Result<>(data, null, null);
    }

}
