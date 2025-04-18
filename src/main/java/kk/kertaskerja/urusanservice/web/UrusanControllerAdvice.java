package kk.kertaskerja.urusanservice.web;

import jakarta.servlet.http.HttpServletRequest;
import kk.kertaskerja.urusanservice.common.exception.ApiError;
import kk.kertaskerja.urusanservice.domain.UrusanAlreadyExistsException;
import kk.kertaskerja.urusanservice.domain.UrusanNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class UrusanControllerAdvice {
    @ExceptionHandler(UrusanNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ApiError urusanNotFoundHandler(UrusanNotFoundException ex, HttpServletRequest request) {
        return new ApiError(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                Instant.now(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(UrusanAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ApiError urusanAlreadyExistsHandler(UrusanAlreadyExistsException ex, HttpServletRequest request) {
        return new ApiError(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ex.getMessage(),
                Instant.now(),
                request.getRequestURI()
        );
    }
}
