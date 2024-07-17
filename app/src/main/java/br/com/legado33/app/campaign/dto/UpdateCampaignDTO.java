package br.com.legado33.app.campaign.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCampaignDTO(
        @NotNull
        @NotBlank
        String title,
        @NotNull
        @NotBlank
        String description
) {
}
