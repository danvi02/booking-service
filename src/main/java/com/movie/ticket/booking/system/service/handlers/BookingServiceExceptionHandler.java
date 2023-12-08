package com.movie.ticket.booking.system.service.handlers;

import com.movie.ticket.booking.system.service.dtos.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class BookingServiceExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        return new ResponseEntity<ResponseDTO>(ResponseDTO.builder()
                .errorDescription(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errorMessages(
                        methodArgumentNotValidException.getBindingResult().getAllErrors()
                                .stream()
                                .map(ObjectError::getDefaultMessage)
                                .collect(Collectors.toList())
                )
                .build(), HttpStatus.BAD_REQUEST);
    }

}
