package br.com.legado33.app.worshipMaterial.dto;

import br.com.legado33.app.worship.Worship;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewWorshipMaterialDTO(
        @NotNull
        Worship worship,
        @NotNull
        @NotBlank
        String description,
        @NotNull
        @NotBlank
        String media,
        @NotNull
        @NotBlank
        String comment
) {
}
