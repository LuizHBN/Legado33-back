package br.com.legado33.app.conversation.dto;

import br.com.legado33.app.conversation.Conversation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReadConversationDTO(
        @NotBlank
        @NotNull
        String title,
        @NotBlank
        @NotNull
        String description,
        @NotBlank
        String image
) {
        public ReadConversationDTO(Conversation conversation){
                this(conversation.getTitle(), conversation.getDescription(), conversation.getImage());
        }
}
