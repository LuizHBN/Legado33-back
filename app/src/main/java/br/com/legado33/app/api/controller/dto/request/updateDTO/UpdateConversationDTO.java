package br.com.legado33.app.api.controller.dto.request.updateDTO;

import br.com.legado33.app.domain.user.User;
import jakarta.validation.constraints.NotNull;

public record UpdateConversationDTO(
        @NotNull
        User user_1,

        User user_2
) {
}
