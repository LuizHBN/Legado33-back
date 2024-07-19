package br.com.legado33.app.message.dto;

import br.com.legado33.app.conversation.Conversation;
import br.com.legado33.app.message.Message;

public record ReadMessageDTO(
        Conversation conversation,
        String dateTime,
        Message.Status status
) {

    public ReadMessageDTO(Message message) {
        this(message.getConversation(),message.getDateTime(),message.getStatus());
    }
}