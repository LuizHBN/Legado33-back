package br.com.legado33.app.message.exceptions;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(Long id){
        super("Message not found with id " + id);
    }
}
