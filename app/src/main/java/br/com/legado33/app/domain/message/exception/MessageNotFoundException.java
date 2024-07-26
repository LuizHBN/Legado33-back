package br.com.legado33.app.domain.message.exception;

import br.com.legado33.app.api.controller.handler.NotFoundException;

public class MessageNotFoundException extends NotFoundException {
    public MessageNotFoundException(Long id){
        super("Message not found with id " + id);
    }
}
