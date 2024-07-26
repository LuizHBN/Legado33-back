package br.com.legado33.app.api.controller.dto.request.newDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewAccessDTO(@NotBlank
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
                           boolean canEditFaq) {
}
