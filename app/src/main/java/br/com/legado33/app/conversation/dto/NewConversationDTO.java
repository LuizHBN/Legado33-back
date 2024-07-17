package br.com.legado33.app.conversation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewConversationDTO(

        @NotBlank
        @NotNull
        String title,
        @NotBlank
        @NotNull
        String description,
        @NotBlank
        String image
) {
}
