package com.test.naceapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class APIErrorResponse {
    private String error;
    private String message;
}
