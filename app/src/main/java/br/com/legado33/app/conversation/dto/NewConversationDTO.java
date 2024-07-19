package br.com.legado33.app.conversation.dto;

import br.com.legado33.app.user.User;
import jakarta.validation.constraints.NotNull;

public record NewConversationDTO(
        @NotNull
        User user_1,

        User user_2

) {
}
