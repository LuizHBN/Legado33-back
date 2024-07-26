package br.com.legado33.app.api.controller.dto.response;

import br.com.legado33.app.domain.conversation.Conversation;
import br.com.legado33.app.domain.user.User;

public record ReadConversationDTO(
        Long id,

        User user_1,

        User user_2
) {
        public ReadConversationDTO(Conversation conversation){
                this(conversation.getId(),conversation.getUser_1(),conversation.getUser_2());
        }
}
