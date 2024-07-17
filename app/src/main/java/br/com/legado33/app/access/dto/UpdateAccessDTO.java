package br.com.legado33.app.access.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateAccessDTO(

       @NotNull
       String description,
       @NotNull
       boolean canEditWorship,
       @NotNull
       boolean canEditReels,
       @NotNull
       boolean canEditPosts,
       @NotNull
       boolean canAccessFinances,
       @NotNull
       boolean canSendNotifications,
       @NotNull
       boolean canReplyMessage,
       @NotNull
       boolean canEditNews,
       @NotNull
       boolean canEditFaq
) {
}
