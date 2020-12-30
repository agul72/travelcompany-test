package com.travelfactory.partners.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongCompanyIdException extends RuntimeException {

    public WrongCompanyIdException(Integer id, String warnMessage) {
        super("Company with id " + id + " " + warnMessage);
    }
}
