package br.com.legado33.app.conversation.exceptions;

public class ConversationNotFoundException extends RuntimeException{
    public ConversationNotFoundException(Long id){
        super("Conversation not found with id: " + id);
    }
}
