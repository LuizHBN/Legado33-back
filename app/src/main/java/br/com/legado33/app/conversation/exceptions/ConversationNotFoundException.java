package br.com.legado33.app.conversation.exceptions;

import br.com.legado33.app.exceptions.NotFoundException;

public class ConversationNotFoundException extends NotFoundException {
    public ConversationNotFoundException(Long id){
        super("Conversation not found with id: " + id.toString());
    }
}
