package com.elite.core.exception;

import com.elite.core.exception.exceptions.NotFoundException;
import com.elite.core.exception.exceptions.ServiceException;
import com.elite.core.exception.exceptions.UnAuthorisedException;
import com.elite.core.exception.model.Error;
import com.elite.core.exception.model.ErrorMessage;
import com.elite.core.factory.MessageResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
@Slf4j
public class ExceptionsHandler {

    /*
    Custom Exception Handler
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage notFoundExceptionHandler(NotFoundException ex, WebRequest request) {
        log.error(MessageResource.getMessage(ESFault.ES_000), HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                new Error(ex.getMessage(), request.getDescription(false)));
    }

    @ExceptionHandler(UnAuthorisedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorMessage unAuthorisedExceptionHandler(UnAuthorisedException ex, WebRequest request) {
        log.error(MessageResource.getMessage(ESFault.ES_000), HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(), ex.getMessage());
        return new ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(),
                new Error(ex.getMessage(), request.getDescription(false)));
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage serviceExceptionHandler(ServiceException ex, WebRequest request) {
        log.error(MessageResource.getMessage(ESFault.ES_000), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), new Error(ex.getMessage(), request.getDescription(false)));
    }

    /*
    Framework Exception Handler
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorMessage authenticationExceptionHandler(AccessDeniedException ex, WebRequest request) {
        log.error(MessageResource.getMessage(ESFault.ES_000), HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(), ex.getMessage());
        return new ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(),
                new Error(ex.getMessage(), request.getDescription(false)));
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorMessage accessDeniedExceptionHandler(AccessDeniedException ex, WebRequest request) {
        log.error(MessageResource.getMessage(ESFault.ES_000), HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(), ex.getMessage());
        return new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                new Date(),
                new Error(ex.getMessage(), request.getDescription(false)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException ex, WebRequest request) {
        log.error(MessageResource.getMessage(ESFault.ES_000), HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());
        BindingResult bindingResult = ex.getBindingResult();
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                new Error(
                        bindingResult.getFieldError().getDefaultMessage(), request.getDescription(false)));
    }

    /*
    Global Exception Handler
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
        log.error(MessageResource.getMessage(ESFault.ES_000), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                new Error(ex.getMessage(), request.getDescription(false)));
    }
}
