package com.example.demo.exception;

import com.example.demo.constant.ErrorCode;
import com.example.demo.constant.MessageKey;
import com.example.demo.model.response.BaseResponse;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BusinessException.class)
    BaseResponse<Void> handleException(final BusinessException exception) {
        log.error(exception.getMessage(), exception);
        return BaseResponse.fail(exception.getFieldName(), exception.getMessage(), ErrorCode.HTTP_CODE_400, exception.getKey().getKey());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    BaseResponse<Void> handleException(final NotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return BaseResponse.fail(exception.getMessage(), ErrorCode.HTTP_CODE_404, MessageKey.NOT_FOUND.getKey());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResponse<Void> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        log.error("No handler found for request: {}", ex.getRequestURL());
        return BaseResponse.fail("No handler found for this request.", ErrorCode.HTTP_CODE_404, MessageKey.ROUTE_NOT_FOUND.getKey());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    BaseResponse<Void> handleException(final ConstraintViolationException exception) {
        log.error(exception.getMessage(), exception);

        return BaseResponse.fail(exception.getMessage(), ErrorCode.HTTP_CODE_422, MessageKey.WRONG_PARAMETER.getKey());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    BaseResponse<Void> handleException(final MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);
        StringBuilder errorMessage = new StringBuilder();
        final Map<String, List<String>> errors = new HashMap<>();
        for (final FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), Collections.singletonList(error.getDefaultMessage()));
        }
        for (final ObjectError error : exception.getBindingResult().getGlobalErrors()) {
            errors.put(error.getObjectName(), Collections.singletonList(error.getDefaultMessage()));
        }

        return BaseResponse.fail("wrong validation", ErrorCode.HTTP_CODE_400, MessageKey.WRONG_PARAMETER.getKey(), errors);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = AccessDeniedException.class)
    BaseResponse<Void> handleException(final AccessDeniedException exception) {
        log.error(exception.getMessage(), exception);
        return BaseResponse.fail("Can't access.", ErrorCode.HTTP_CODE_403, MessageKey.DONT_HAVE_PERMISSION.getKey());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    BaseResponse<Void> handleException(final Exception exception) {
        log.error(exception.getMessage(), exception);
        return BaseResponse.fail("Internal Server Error", ErrorCode.HTTP_CODE_500, MessageKey.INTERNAL_ERROR.getKey());
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = OAuth2AuthorizationException.class)
    BaseResponse<Void> handleException(final OAuth2AuthorizationException exception) {
        log.error(exception.getMessage(), exception);
        return BaseResponse.fail("Unauthorized access.", ErrorCode.HTTP_CODE_401, MessageKey.UNAUTHORIZED.getKey());
    }


}
