package com.eleks.academy.pharmagator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MedicineAlreadyExistsException.class)
    public ResponseEntity<Object> handleMedicineAlreadyExistsException(MedicineAlreadyExistsException ex, WebRequest webRequest) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PharmacyAlreadyExistsException.class)
    public ResponseEntity<Object> handlePharmacyAlreadyExistsException(PharmacyAlreadyExistsException ex, WebRequest webRequest) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MedicineNotFoundException.class)
    public ResponseEntity<Object> handleMedicineNotFoundException(MedicineNotFoundException ex, WebRequest webRequest) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PharmacyNotFoundException.class)
    public ResponseEntity<Object> handlePharmacyNotFoundException(PharmacyNotFoundException ex, WebRequest webRequest) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),HttpStatus.NOT_FOUND);
    }

}
