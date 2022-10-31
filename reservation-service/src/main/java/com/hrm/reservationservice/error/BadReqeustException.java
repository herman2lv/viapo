package com.hrm.reservationservice.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadReqeustException extends RuntimeException {
    public BadReqeustException() {
        super();
    }

    public BadReqeustException(String message) {
        super(message);
    }

    public BadReqeustException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadReqeustException(Throwable cause) {
        super(cause);
    }
}
