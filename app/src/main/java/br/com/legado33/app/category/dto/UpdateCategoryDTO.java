package br.com.legado33.app.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCategoryDTO(
        @NotNull
        @NotBlank
        String title
) {
}
