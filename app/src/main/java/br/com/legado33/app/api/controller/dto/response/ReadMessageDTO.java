package br.com.legado33.app.api.controller.dto.response;

import br.com.legado33.app.domain.conversation.Conversation;
import br.com.legado33.app.domain.message.Message;

public record ReadMessageDTO(
        Conversation conversation,
        String dateTime,
        Message.Status status
) {

    public ReadMessageDTO(Message message) {
        this(message.getConversation(),message.getDateTime(),message.getStatus());
    }
}