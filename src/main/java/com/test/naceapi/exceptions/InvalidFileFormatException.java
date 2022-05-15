package com.test.naceapi.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidFileFormatException extends RuntimeException{
    private String fileName;

    public InvalidFileFormatException(String fileName, String message) {
        super(message);
        this.fileName = fileName;
    }

    public InvalidFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
