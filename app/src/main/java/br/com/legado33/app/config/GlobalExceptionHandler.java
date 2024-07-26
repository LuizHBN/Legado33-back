package br.com.legado33.app.config;

import br.com.legado33.app.access.exceptions.AccessNotFoundException;
import br.com.legado33.app.campaign.exceptions.CampaignNotFoundException;
import br.com.legado33.app.config.ErrorResponse;
import br.com.legado33.app.category.exceptions.CategoryNotFoundException;
import br.com.legado33.app.conversation.exceptions.ConversationNotFoundException;
import br.com.legado33.app.exceptions.NotFoundException;
import br.com.legado33.app.message.Message;
import br.com.legado33.app.message.exceptions.MessageNotFoundException;
import br.com.legado33.app.news.exceptions.NewsNotFoundException;
import br.com.legado33.app.readContent.exceptions.ReadContentNotFoundException;
import br.com.legado33.app.reels.exceptions.ReelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
