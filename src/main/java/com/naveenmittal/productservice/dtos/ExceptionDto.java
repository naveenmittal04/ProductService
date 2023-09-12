package com.naveenmittal.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionDto {
    private String message;
    private HttpStatus status;

    public ExceptionDto(HttpStatus httpStatus, String message) {
        this.message = message;
        this.status = httpStatus;
    }
}
