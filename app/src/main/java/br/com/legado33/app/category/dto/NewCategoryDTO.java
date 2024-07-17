package br.com.legado33.app.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewCategoryDTO(
        @NotBlank
        @NotNull
        String title
) {
}
