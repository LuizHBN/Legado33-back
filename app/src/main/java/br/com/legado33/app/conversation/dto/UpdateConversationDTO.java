package br.com.legado33.app.conversation.dto;

import br.com.legado33.app.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateConversationDTO(
        @NotNull
        User user_1,

        User user_2
) {
}
