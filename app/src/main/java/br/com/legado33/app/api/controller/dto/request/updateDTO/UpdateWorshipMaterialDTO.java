package br.com.legado33.app.api.controller.dto.request.updateDTO;

import br.com.legado33.app.domain.worship.Worship;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateWorshipMaterialDTO(
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
