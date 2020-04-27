package com.flight.ticket.service.base;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Burak Naroglu
 */

@Getter
public class ServiceResult<T> {

    private T result;

    private HttpStatus httpStatus = HttpStatus.OK;

    public ServiceResult(T result) {
        this.result = result;
    }

    public ServiceResult(T result, HttpStatus httpStatus) {
        this.result = result;
        this.httpStatus = httpStatus;
    }

}
