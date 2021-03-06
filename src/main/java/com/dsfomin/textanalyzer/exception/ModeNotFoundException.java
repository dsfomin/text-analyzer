package com.dsfomin.textanalyzer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ModeNotFoundException extends RuntimeException {
    public ModeNotFoundException(String mode) {
        super("Mode: {" + mode + "} wasn't found");
    }
}
