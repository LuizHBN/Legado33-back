package br.com.legado33.app.message.exceptions;

import br.com.legado33.app.exceptions.NotFoundException;

public class MessageNotFoundException extends NotFoundException {
    public MessageNotFoundException(Long id){
        super("Message not found with id " + id);
    }
}
