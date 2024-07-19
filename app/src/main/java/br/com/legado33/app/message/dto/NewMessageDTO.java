package br.com.legado33.app.message.dto;

import br.com.legado33.app.conversation.Conversation;
import br.com.legado33.app.message.Message;
import jakarta.validation.constraints.NotNull;

public record NewMessageDTO(
        @NotNull
        Conversation conversation,
        @NotNull
        String dateTime,
        @NotNull
        Message.Status status
) {

}