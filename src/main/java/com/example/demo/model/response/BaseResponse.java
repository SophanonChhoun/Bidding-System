package com.example.demo.model.response;

import com.example.demo.constant.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Slf4j
@Getter
@Setter(AccessLevel.MODULE)
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    private String code;

    private String fieldName;

    private String message;

    private String messageContent;
    private Object validation;

    private long timestamps;
    private T data;

    public BaseResponse() {
        this.data = null;
        timestamps = Instant.now().toEpochMilli();
        code = ErrorCode.HTTP_CODE_200.errorCode;
        this.message = "";
        this.messageContent = ErrorCode.HTTP_CODE_200.message;
    }

    public static <T> BaseResponse<T> of(final Result<T> result) {
        BaseResponse<T> response = new BaseResponse<T>();
        response.data = result.getData();
        response.code = ErrorCode.HTTP_CODE_200.errorCode;
        response.messageContent = ErrorCode.HTTP_CODE_200.message;
        response.timestamps = Instant.now().toEpochMilli();
        response.message = result.getMessageKey() != null ? result.getMessageKey() : "";
        return response;
    }

    public static BaseResponse<Void> fail(final String fieldName, final String message, final ErrorCode errorCode, final String messageCode) {
        var response = new BaseResponse<Void>();
        response.setFieldName(fieldName);
        response.setMessageContent(message);
        response.setCode(errorCode.getErrorCode());
        response.setMessage(messageCode);
        response.setTimestamps(Instant.now().toEpochMilli());
        response.setValidation(null);
        return response;
    }

    public static BaseResponse<Void> fail(final String message, final ErrorCode errorCode, final String messageCode) {
        var response = new BaseResponse<Void>();
        response.setFieldName(null);
        response.setMessageContent(message);
        response.setCode(errorCode.getErrorCode());
        response.setMessage(messageCode);
        response.setTimestamps(Instant.now().toEpochMilli());
        response.setValidation(null);
        return response;
    }

    public static BaseResponse<Void> fail(final String message, final ErrorCode errorCode, final String messageCode, final Object validation) {
        var response = new BaseResponse<Void>();
        response.setFieldName(null);
        response.setMessageContent(message);
        response.setCode(errorCode.getErrorCode());
        response.setMessage(messageCode);
        response.setTimestamps(Instant.now().toEpochMilli());
        response.setValidation(validation);
        return response;
    }

}
