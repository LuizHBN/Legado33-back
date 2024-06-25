package br.com.legado33.app.access.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateAccessDTO(

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
}
