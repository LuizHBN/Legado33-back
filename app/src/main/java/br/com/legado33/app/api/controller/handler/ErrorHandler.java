package br.com.legado33.app.api.controller.handler;

import br.com.legado33.app.api.controller.handler.dto.DefaultErrorDTO;
import br.com.legado33.app.domain.access.exception.AccessNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(AccessNotFoundException.class)
    public ResponseEntity<DefaultErrorDTO> handleNotFoundException(NotFoundException ex) {
        DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(defaultErrorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DefaultErrorDTO> handleException(Exception ex) {
        DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(defaultErrorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
