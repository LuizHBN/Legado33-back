package br.com.legado33.app.domain.conversation.exception;

import br.com.legado33.app.api.controller.handler.NotFoundException;

public class ConversationNotFoundException extends NotFoundException {
    public ConversationNotFoundException(Long id){
        super("Conversation not found with id: " + id.toString());
    }
}
