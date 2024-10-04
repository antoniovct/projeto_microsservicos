package com.antonio_victor.projetos.distribuidora.cadastros.error;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratarExceptions {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> entityNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DtoError>> validation(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors().stream().map(DtoError::new).toList();
        return ResponseEntity.badRequest().body(erros);
    }

    private record DtoError(String field, String message) {
        public DtoError(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
