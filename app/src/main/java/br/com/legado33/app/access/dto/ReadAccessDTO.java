package br.com.legado33.app.access.dto;

import br.com.legado33.app.access.Access;
import br.com.legado33.app.conversation.Conversation;

public record ReadAccessDTO(
        Long id,
        String description,
        boolean canEditWorship,
        boolean canEditReels,
        boolean canEditPosts,
        boolean canAccessFinances,
        boolean canSendNotifications,
        boolean canReplyMessage,
        boolean canEditNews,
        boolean canEditFaq

) {

    public ReadAccessDTO(Access access){
        this(access.getId(),
                access.getDescription(),
                access.isCanEditWorship(),
                access.isCanEditReels(),
                access.isCanEditPosts(),
                access.isCanAccessFinances(),
                access.isCanSendNotifications(),
                access.isCanReplyMessage(),
                access.isCanEditNews(),
                access.isCanEditFaq());
    }


}
