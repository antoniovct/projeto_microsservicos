package com.antonio_victor.projetos.distribuidora.estoque.error;

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
    public ResponseEntity<Void> entityNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DtoError>> validation(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        return ResponseEntity.badRequest().body(fieldErrors.stream().map(DtoError::new).toList());
    }


    private record DtoError(String msg, String field) {
        public DtoError(FieldError fieldError) {
            this(fieldError.getDefaultMessage(), fieldError.getField());
        }
    }
}
