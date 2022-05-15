package com.test.naceapi.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public void httpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletResponse response) {
        if (ex.getCause() instanceof InvalidFormatException)
            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        else
            response.setStatus(HttpStatus.BAD_REQUEST.value());

    }

    @ExceptionHandler({OrderNotFoundException.class})
    public ResponseEntity<APIErrorResponse> handleApiException(OrderNotFoundException ex) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler({InvalidFileFormatException.class})
    public ResponseEntity<APIErrorResponse> handleInvalidFileApiException(InvalidFileFormatException ex) {
        APIErrorResponse response = new APIErrorResponse("error-0023", "The file format for " + ex.getMessage() + "is invalid");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TechnicalException.class})
    public ResponseEntity<APIErrorResponse> handleUnknownException(TechnicalException ex) {
        APIErrorResponse response = new APIErrorResponse("error-0024", "An internal error occurred while processing this request");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
