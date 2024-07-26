package br.com.legado33.app.api.controller.dto.request.newDTO;

import br.com.legado33.app.domain.conversation.Conversation;
import br.com.legado33.app.domain.message.Message;
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
