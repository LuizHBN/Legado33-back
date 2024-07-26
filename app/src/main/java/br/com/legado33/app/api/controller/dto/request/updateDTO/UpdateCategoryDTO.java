package br.com.legado33.app.api.controller.dto.request.updateDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCategoryDTO(
        @NotNull
        @NotBlank
        String title
) {
}
