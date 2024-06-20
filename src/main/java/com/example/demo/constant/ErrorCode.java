package com.example.demo.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    HTTP_CODE_200("00", "Success", HttpStatus.OK),
    HTTP_CODE_400("01", "Invalid Business Logic", HttpStatus.BAD_REQUEST),
    HTTP_CODE_304("02", "Can not update data", HttpStatus.NOT_MODIFIED),
    HTTP_CODE_422("03", "Wrong Parameter", HttpStatus.BAD_REQUEST),
    HTTP_CODE_403("04", "You don't have permission", HttpStatus.FORBIDDEN),
    HTTP_CODE_404("05", "Not Found", HttpStatus.BAD_REQUEST),
    HTTP_CODE_500("06", "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    HTTP_CODE_503("07", "Service Unavailable", HttpStatus.SERVICE_UNAVAILABLE),
    HTTP_CODE_401("08", "Unauthorized request", HttpStatus.UNAUTHORIZED);

    public final String errorCode;
    public final String message;
    public final HttpStatus httpStatus;

    ErrorCode(String errorCode, String message, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }


}
