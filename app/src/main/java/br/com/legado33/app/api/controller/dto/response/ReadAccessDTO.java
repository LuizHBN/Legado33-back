package br.com.legado33.app.api.controller.dto.response;

import br.com.legado33.app.domain.access.Access;

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
