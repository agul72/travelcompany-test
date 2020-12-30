package com.travelfactory.partners.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongFormatException extends RuntimeException {

    public WrongFormatException(String field) {
        super("wrong format for field {" + field + "}");
    }
}
